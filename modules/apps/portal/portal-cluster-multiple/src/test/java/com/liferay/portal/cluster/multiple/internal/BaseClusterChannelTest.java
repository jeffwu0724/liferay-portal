/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cluster.multiple.internal;

import com.liferay.portal.kernel.cluster.Address;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.io.Serializable;

import java.net.InetAddress;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Jeff Wu
 */
public class BaseClusterChannelTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testSendMessageFailureDoesNotKillWorkerThread()
		throws Exception {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		try {
			CountDownLatch countDownLatch = new CountDownLatch(2);

			List<Thread> threads = new CopyOnWriteArrayList<>();

			AtomicInteger counter = new AtomicInteger();

			BaseClusterChannel clusterChannel = new TestBaseClusterChannel(
				executorService) {

				@Override
				protected void doSendMessage(
					Serializable message, Address address) {

					threads.add(Thread.currentThread());

					countDownLatch.countDown();

					// The first queued send fails the way a JGroups channel
					// does while it is tearing down. Before the fix, the
					// uncaught exception killed the worker thread.

					if (counter.getAndIncrement() == 0) {
						throw new RuntimeException(
							"Simulated channel teardown failure");
					}
				}

			};

			clusterChannel.sendMulticastMessage("message1");
			clusterChannel.sendMulticastMessage("message2");

			Assert.assertTrue(
				"Both queued send tasks should run",
				countDownLatch.await(1, TimeUnit.MINUTES));

			Assert.assertEquals(threads.toString(), 2, threads.size());

			Assert.assertSame(
				"A failing send task must not kill its worker thread",
				threads.get(0), threads.get(1));
		}
		finally {
			executorService.shutdownNow();
		}
	}

	private abstract static class TestBaseClusterChannel
		extends BaseClusterChannel {

		@Override
		public void close() {
		}

		@Override
		public InetAddress getBindInetAddress() {
			return null;
		}

		@Override
		public String getClusterName() {
			return null;
		}

		@Override
		public ClusterReceiver getClusterReceiver() {
			return null;
		}

		@Override
		public Address getLocalAddress() {
			return null;
		}

		private TestBaseClusterChannel(ExecutorService executorService) {
			super(executorService);
		}

	}

}