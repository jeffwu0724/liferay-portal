/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.internal.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.cache.PortalCacheReplicator;
import com.liferay.portal.cache.PortalCacheWrapper;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheException;
import com.liferay.portal.kernel.cache.PortalCacheHelperUtil;
import com.liferay.portal.kernel.cache.PortalCacheListener;
import com.liferay.portal.kernel.cache.PortalCacheListenerScope;
import com.liferay.portal.kernel.cache.PortalCacheManagerNames;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.TomcatClusterTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserGroupTestUtil;
import com.liferay.portal.model.impl.UserGroupImpl;
import com.liferay.portal.test.cluster.tomcat.TomcatCluster;
import com.liferay.portal.test.cluster.tomcat.TomcatNode;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@RunWith(Arquillian.class)
public class ClusterCacheReplicationTest implements Serializable {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@ClassRule
	public static final TomcatClusterTestRule tomcatClusterTestRule =
		new TomcatClusterTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		TomcatCluster.Builder builder1 =
			tomcatClusterTestRule.buildTomcatNode();

		_tomcatNode1 = builder1.build();

		_tomcatNode1.start(true);

		TomcatCluster.Builder builder2 =
			tomcatClusterTestRule.buildTomcatNode();

		_tomcatNode2 = builder2.build();

		_tomcatNode2.start(true);
	}

	@After
	public void tearDown() throws Exception {
		String testCacheName = ClusterCacheReplicationTest.class.getName();

		_tomcatNode1.syncExecute(
			() -> {
				PortalCacheHelperUtil.removePortalCache(
					PortalCacheManagerNames.MULTI_VM, testCacheName);

				return null;
			});

		_tomcatNode2.syncExecute(
			() -> {
				PortalCacheHelperUtil.removePortalCache(
					PortalCacheManagerNames.MULTI_VM, testCacheName);

				return null;
			});
	}

	@Test
	public void testDoNotReplicatePut() throws Exception {

		// Assert empty on node 1, set up property

		String testCacheName = ClusterCacheReplicationTest.class.getName();

		String testKey = "testKey";
		String testValue = "testValue";
		String updateValue = "test.value.update";

		Assert.assertNull(
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					_setFieldValue(portalCache, "_replicatePuts", false);

					return portalCache.get(testKey);
				}));

		// Assert empty on node 2, set up property and listener

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					_setFieldValue(portalCache, "_replicatePuts", false);

					portalCache.registerPortalCacheListener(
						new TestPortalCacheListener());

					return portalCache.get(testKey);
				}));

		// Assert node 1 can see the value it just put

		Assert.assertEquals(
			testValue,
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					portalCache.put(testKey, testValue);

					return portalCache.get(testKey);
				}));

		// Assert empty on node 2, because put is not replicate

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					return portalCache.get(testKey);
				}));

		// Assert node 2 can see the value it just put

		Assert.assertEquals(
			updateValue,
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					portalCache.put(testKey, updateValue);

					return portalCache.get(testKey);
				}));

		// Assert node 1 has the old value, because put is not replicate

		Assert.assertEquals(
			testValue,
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					return portalCache.get(testKey);
				}));

		// Assert node 1 is empty after removal

		Assert.assertNull(
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					portalCache.remove(testKey);

					return portalCache.get(testKey);
				}));

		// Assert node 2 is also empty, because remove is still replicate

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					TestPortalCacheListener.await(
						portalCache,
						TestPortalCacheListener::getRemoveCountDownLatch);

					return portalCache.get(testKey);
				}));
	}

	@Test
	public void testEntityCacheFinderCacheSynchronization() throws Exception {

		// Assert node 1 does not see any UserGroup with testing prefix

		String userGroupNamePrefix =
			ClusterCacheReplicationTest.class.getSimpleName();

		TomcatNode.ClusterExecutable<ArrayList<UserGroup>>
			getUserGroupsClusterExecutable = () -> new ArrayList<>(
				UserGroupLocalServiceUtil.getUserGroups(
					TestPropsValues.getCompanyId(), userGroupNamePrefix + "%",
					QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		List<UserGroup> userGroups = _tomcatNode1.syncExecute(
			getUserGroupsClusterExecutable);

		Assert.assertTrue(userGroups.toString(), userGroups.isEmpty());

		// Add user group1 on node 1

		String userGroupName1 = userGroupNamePrefix + "_userGroup1";

		UserGroup userGroup1 = _tomcatNode1.syncExecute(
			() -> {
				UserGroup userGroup = UserGroupTestUtil.addUserGroup();

				userGroup.setName(userGroupName1);

				return UserGroupLocalServiceUtil.updateUserGroup(userGroup);
			});

		Assert.assertEquals(userGroupName1, userGroup1.getName());

		// Assert node 2 can see user group 1

		userGroups = _tomcatNode2.syncExecute(getUserGroupsClusterExecutable);

		Assert.assertEquals(userGroups.toString(), 1, userGroups.size());
		Assert.assertEquals(userGroup1, userGroups.get(0));

		// Add user group 2 on node 1

		String userGroupName2 = userGroupNamePrefix + "_userGroup2";

		UserGroup userGroup2 = _tomcatNode1.syncExecute(
			() -> {
				UserGroup userGroup = UserGroupTestUtil.addUserGroup();

				userGroup.setName(userGroupName2);

				return UserGroupLocalServiceUtil.updateUserGroup(userGroup);
			});

		Assert.assertEquals(userGroupName2, userGroup2.getName());

		// Assert node 2 can see user group 1 and user group 2

		userGroups = _tomcatNode2.syncExecute(getUserGroupsClusterExecutable);

		Assert.assertEquals(userGroups.toString(), 2, userGroups.size());
		Assert.assertEquals(userGroup1, userGroups.get(0));
		Assert.assertEquals(userGroup2, userGroups.get(1));

		// Remove user group 1 and user group 2 on node 2

		userGroups = _tomcatNode2.syncExecute(
			() -> {
				UserGroupLocalServiceUtil.deleteUserGroup(userGroup1);
				UserGroupLocalServiceUtil.deleteUserGroup(userGroup2);

				return getUserGroupsClusterExecutable.execute();
			});

		Assert.assertTrue(userGroups.toString(), userGroups.isEmpty());

		// Assert node 1 sees no user group

		userGroups = _tomcatNode1.syncExecute(getUserGroupsClusterExecutable);

		Assert.assertTrue(userGroups.toString(), userGroups.isEmpty());
	}

	@Test
	public void testEntityCacheReplicationMessageCount() throws Exception {
		_tomcatNode1.syncExecute(
			() -> {
				EntityCacheUtil.clearCache(UserGroupImpl.class);

				PortalCache<Serializable, Serializable> portalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				portalCache.registerPortalCacheListener(
					new TestPortalCacheReplicator());

				return null;
			});

		_tomcatNode1.syncExecute(
			() -> {
				PortalCache<Serializable, Serializable> portalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				long testKey = RandomTestUtil.randomLong();

				portalCache.put(testKey, "test1");
				portalCache.put(testKey, "test2");
				portalCache.remove(testKey);

				return null;
			});

		int[] counts = _tomcatNode1.syncExecute(
			() -> {
				PortalCache<Serializable, Serializable> portalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				TestPortalCacheReplicator testPortalCacheReplicator =
					(TestPortalCacheReplicator)_getListenerOnEhcachePortalCache(
						TestPortalCacheReplicator.class.getName(), portalCache);

				int[] result = {
					testPortalCacheReplicator.getPutCount(),
					testPortalCacheReplicator.getUpdateCount(),
					testPortalCacheReplicator.getRemoveCount(),
					testPortalCacheReplicator.getRemoveAllCount()
				};

				portalCache.unregisterPortalCacheListener(
					testPortalCacheReplicator);

				EntityCacheUtil.clearCache(UserGroupImpl.class);

				return result;
			});

		System.out.println(
			StringBundler.concat(
				"!!!!!!!!!!! events on node 1: put=", counts[0], ", update=",
				counts[1], ", remove=", counts[2], ", removeAll=", counts[3]));

		Assert.assertEquals("put", 1, counts[0]);
		Assert.assertEquals("update", 1, counts[1]);
		Assert.assertEquals("remove", 1, counts[2]);
		Assert.assertEquals("removeAll", 0, counts[3]);
	}

	@Test
	public void testPingPongFlushing() throws Exception {

		// Assert empty and put value on node 1

		String testCacheName = ClusterCacheReplicationTest.class.getName();

		String testKey = "testKey";
		String testValue1 = "testValue1";
		String testValue2 = "testValue2";

		Assert.assertNull(
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					String value = portalCache.get(testKey);

					portalCache.put(testKey, testValue1);

					return value;
				}));

		// Assert node 1 can see the value it put

		Assert.assertEquals(
			testValue1,
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					return portalCache.get(testKey);
				}));

		// Assert empty and put value on node 2

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					String value = portalCache.get(testKey);

					portalCache.put(testKey, testValue2);

					return value;
				}));

		// Assert node 2 can see the value it put

		Assert.assertEquals(
			testValue2,
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					return portalCache.get(testKey);
				}));

		// Assert node 1 sees no value

		Assert.assertNull(
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					return portalCache.get(testKey);
				}));
	}

	@Test
	public void testReplicateByCopy() throws Exception {

		// Assert node 1 is empty

		String testCacheName = ClusterCacheReplicationTest.class.getName();

		String testKey = "testKey";
		String testValue = "testValue";

		Assert.assertNull(
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					_setFieldValue(portalCache, "_replicatePutsViaCopy", true);

					return portalCache.get(testKey);
				}));

		// Assert node 2 is empty

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					_setFieldValue(portalCache, "_replicatePutsViaCopy", true);

					portalCache.registerPortalCacheListener(
						new TestPortalCacheListener());

					return portalCache.get(testKey);
				}));

		// Assert node 1 can see the value it just put

		Assert.assertEquals(
			testValue,
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					portalCache.put(testKey, testValue);

					return portalCache.get(testKey);
				}));

		// Assert node 2 has the same value because "_replicatePutsViaCopy" is
		// set to true

		Assert.assertEquals(
			testValue,
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					TestPortalCacheListener.await(
						portalCache,
						TestPortalCacheListener::getPutCountDownLatch);

					return portalCache.get(testKey);
				}));

		// Assert node 1 is empty after removal

		Assert.assertNull(
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					portalCache.remove(testKey);

					return portalCache.get(testKey);
				}));

		// Assert node 2 is also empty

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					TestPortalCacheListener.await(
						portalCache,
						TestPortalCacheListener::getRemoveCountDownLatch);

					return portalCache.get(testKey);
				}));
	}

	@Test
	public void testReplicateByRemove() throws Exception {

		// Assert node 1 is empty

		String testCacheName = ClusterCacheReplicationTest.class.getName();

		String testKey = "testKey";
		String testValue = "testValue";

		Assert.assertNull(
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					_setFieldValue(portalCache, "_replicatePutsViaCopy", false);

					return portalCache.get(testKey);
				}));

		// Assert node 2 is empty

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					_setFieldValue(portalCache, "_replicatePutsViaCopy", false);

					portalCache.registerPortalCacheListener(
						new TestPortalCacheListener());

					return portalCache.get(testKey);
				}));

		// Assert node 1 can see the value it just put

		Assert.assertEquals(
			testValue,
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					portalCache.put(testKey, testValue);

					return portalCache.get(testKey);
				}));

		// Assert node 2 is still empty, and triggered notifyEntryRemoved

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					TestPortalCacheListener.await(
						portalCache,
						TestPortalCacheListener::getRemoveCountDownLatch);

					return portalCache.get(testKey);
				}));

		// Assert node 1 is empty after removeAll

		Assert.assertNull(
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					portalCache.removeAll();

					return portalCache.get(testKey);
				}));

		// Assert node 2 is still empty, and triggered notifyEntryRemoved

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					TestPortalCacheListener.await(
						portalCache,
						TestPortalCacheListener::getRemoveAllCountDownLatch);

					return portalCache.get(testKey);
				}));
	}

	@Test
	public void testReplicateByUpdateViaCopy() throws Exception {

		// Assert node 1 is empty

		String testCacheName = ClusterCacheReplicationTest.class.getName();

		String testKey = "testKey";
		String testValue = "testValue";
		String updateValue = "test.value.update";

		Assert.assertNull(
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					_setFieldValue(portalCache, "_replicatePutsViaCopy", true);
					_setFieldValue(
						portalCache, "_replicateUpdatesViaCopy", true);

					return portalCache.get(testKey);
				}));

		// Assert node 2 is empty

		Assert.assertNull(
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					_setFieldValue(portalCache, "_replicatePutsViaCopy", true);
					_setFieldValue(
						portalCache, "_replicateUpdatesViaCopy", true);

					portalCache.registerPortalCacheListener(
						new TestPortalCacheListener());

					return portalCache.get(testKey);
				}));

		// Assert node 1 can see the value it just put

		Assert.assertEquals(
			testValue,
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					portalCache.put(testKey, testValue);

					return portalCache.get(testKey);
				}));

		// Assert node 2 has the same value because "_replicatePutsViaCopy" is
		// set to true

		Assert.assertEquals(
			testValue,
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					TestPortalCacheListener.await(
						portalCache,
						TestPortalCacheListener::getPutCountDownLatch);

					return portalCache.get(testKey);
				}));

		// Assert node 1 can see the value it just update

		Assert.assertEquals(
			updateValue,
			_tomcatNode1.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					portalCache.put(testKey, updateValue);

					return portalCache.get(testKey);
				}));

		// Assert node 2 has the same value after node 1 update

		Assert.assertEquals(
			updateValue,
			_tomcatNode2.syncExecute(
				() -> {
					PortalCache<String, String> portalCache =
						PortalCacheHelperUtil.getPortalCache(
							PortalCacheManagerNames.MULTI_VM, testCacheName);

					TestPortalCacheListener.await(
						portalCache,
						TestPortalCacheListener::getUpdatedCountDownLatch);

					return portalCache.get(testKey);
				}));
	}

	public static class TestPortalCacheListener
		implements PortalCacheListener<String, String>, Serializable {

		public static void await(
				PortalCache<?, ?> portalCache,
				Function<TestPortalCacheListener, CountDownLatch> function)
			throws InterruptedException {

			TestPortalCacheListener testPortalCacheListener =
				(TestPortalCacheListener)_getPortalCacheListener(
					TestPortalCacheListener.class.getName(), portalCache);

			CountDownLatch countDownLatch = function.apply(
				testPortalCacheListener);

			countDownLatch.await();
		}

		public TestPortalCacheListener() {
			_putCountDownLatch = new CountDownLatch(1);
			_removeCountDownLatch = new CountDownLatch(1);
			_removeAllCountDownLatch = new CountDownLatch(1);
			_updatedCountDownLatch = new CountDownLatch(1);
		}

		@Override
		public void dispose() {
		}

		public CountDownLatch getPutCountDownLatch() {
			return _putCountDownLatch;
		}

		public CountDownLatch getRemoveAllCountDownLatch() {
			return _removeAllCountDownLatch;
		}

		public CountDownLatch getRemoveCountDownLatch() {
			return _removeCountDownLatch;
		}

		public CountDownLatch getUpdatedCountDownLatch() {
			return _updatedCountDownLatch;
		}

		@Override
		public void notifyEntryEvicted(
				PortalCache<String, String> portalCache, String key,
				String value, int timeToLive)
			throws PortalCacheException {
		}

		@Override
		public void notifyEntryExpired(
				PortalCache<String, String> portalCache, String key,
				String value, int timeToLive)
			throws PortalCacheException {
		}

		@Override
		public void notifyEntryPut(
				PortalCache<String, String> portalCache, String key,
				String value, int timeToLive)
			throws PortalCacheException {

			_putCountDownLatch.countDown();
		}

		@Override
		public void notifyEntryRemoved(
				PortalCache<String, String> portalCache, String key,
				String value, int timeToLive)
			throws PortalCacheException {

			_removeCountDownLatch.countDown();
		}

		@Override
		public void notifyEntryUpdated(
				PortalCache<String, String> portalCache, String key,
				String value, int timeToLive)
			throws PortalCacheException {

			_updatedCountDownLatch.countDown();
		}

		@Override
		public void notifyRemoveAll(PortalCache<String, String> portalCache)
			throws PortalCacheException {

			_removeAllCountDownLatch.countDown();
		}

		private final CountDownLatch _putCountDownLatch;
		private final CountDownLatch _removeAllCountDownLatch;
		private final CountDownLatch _removeCountDownLatch;
		private final CountDownLatch _updatedCountDownLatch;

	}

	public static class TestPortalCacheReplicator
		implements PortalCacheReplicator<Serializable, Serializable>,
				   Serializable {

		@Override
		public void dispose() {
		}

		public int getPutCount() {
			return _putCount.get();
		}

		public int getRemoveAllCount() {
			return _removeAllCount.get();
		}

		public int getRemoveCount() {
			return _removeCount.get();
		}

		public int getUpdateCount() {
			return _updateCount.get();
		}

		@Override
		public void notifyEntryEvicted(
				PortalCache<Serializable, Serializable> portalCache,
				Serializable key, Serializable value, int timeToLive)
			throws PortalCacheException {
		}

		@Override
		public void notifyEntryExpired(
				PortalCache<Serializable, Serializable> portalCache,
				Serializable key, Serializable value, int timeToLive)
			throws PortalCacheException {
		}

		@Override
		public void notifyEntryPut(
				PortalCache<Serializable, Serializable> portalCache,
				Serializable key, Serializable value, int timeToLive)
			throws PortalCacheException {

			_putCount.incrementAndGet();
		}

		@Override
		public void notifyEntryRemoved(
				PortalCache<Serializable, Serializable> portalCache,
				Serializable key, Serializable value, int timeToLive)
			throws PortalCacheException {

			_removeCount.incrementAndGet();
		}

		@Override
		public void notifyEntryUpdated(
				PortalCache<Serializable, Serializable> portalCache,
				Serializable key, Serializable value, int timeToLive)
			throws PortalCacheException {

			_updateCount.incrementAndGet();
		}

		@Override
		public void notifyRemoveAll(
				PortalCache<Serializable, Serializable> portalCache)
			throws PortalCacheException {

			_removeAllCount.incrementAndGet();
		}

		private final AtomicInteger _putCount = new AtomicInteger();
		private final AtomicInteger _removeAllCount = new AtomicInteger();
		private final AtomicInteger _removeCount = new AtomicInteger();
		private final AtomicInteger _updateCount = new AtomicInteger();

	}

	private static PortalCacheListener<?, ?> _getPortalCacheListener(
		String className, PortalCache<?, ?> portalCache) {

		portalCache = ReflectionTestUtil.getFieldValue(
			portalCache, "_portalCache");

		Object aggregatedPortalCacheListener = ReflectionTestUtil.getFieldValue(
			portalCache, "aggregatedPortalCacheListener");

		ConcurrentMap<PortalCacheListener<?, ?>, PortalCacheListenerScope>
			portalCacheListeners = ReflectionTestUtil.getFieldValue(
				aggregatedPortalCacheListener, "_portalCacheListeners");

		for (PortalCacheListener<?, ?> portalCacheListener :
				portalCacheListeners.keySet()) {

			Class<?> clazz = portalCacheListener.getClass();

			if (Objects.equals(clazz.getName(), className)) {
				return portalCacheListener;
			}
		}

		throw new IllegalStateException(className + " does not exist");
	}

	@SuppressWarnings("unchecked")
	private PortalCache<Serializable, Serializable>
		_getEntityEhcachePortalCache(Class<?> modelImplClass) {

		PortalCache<?, ?> portalCache = EntityCacheUtil.getPortalCache(
			modelImplClass);

		if (Objects.equals(
				portalCache.getClass(
				).getName(),
				_CLASS_NAME_CT_AWARE_PORTAL_CACHE)) {

			portalCache = ReflectionTestUtil.getFieldValue(
				portalCache, "_productionPortalCache");
		}

		while (portalCache instanceof PortalCacheWrapper) {
			PortalCacheWrapper<?, ?> portalCacheWrapper =
				(PortalCacheWrapper<?, ?>)portalCache;

			portalCache = portalCacheWrapper.getWrappedPortalCache();
		}

		return (PortalCache<Serializable, Serializable>)portalCache;
	}

	private PortalCacheListener<?, ?> _getListenerOnEhcachePortalCache(
		String className, PortalCache<?, ?> portalCache) {

		Object aggregatedPortalCacheListener = ReflectionTestUtil.getFieldValue(
			portalCache, "aggregatedPortalCacheListener");

		ConcurrentMap<PortalCacheListener<?, ?>, PortalCacheListenerScope>
			portalCacheListeners = ReflectionTestUtil.getFieldValue(
				aggregatedPortalCacheListener, "_portalCacheListeners");

		for (PortalCacheListener<?, ?> portalCacheListener :
				portalCacheListeners.keySet()) {

			Class<?> clazz = portalCacheListener.getClass();

			if (Objects.equals(clazz.getName(), className)) {
				return portalCacheListener;
			}
		}

		throw new IllegalStateException(className + " does not exist");
	}

	private void _setFieldValue(
		PortalCache<?, ?> portalCache, String fieldName, boolean fieldValue) {

		ReflectionTestUtil.setFieldValue(
			(Object)ReflectionTestUtil.getFieldValue(
				_getPortalCacheListener(
					_CLASS_NAME_EHCACHE_PORTAL_CACHE_REPLICATOR, portalCache),
				"_portalCacheReplicator"),
			fieldName, fieldValue);
	}

	private static final String _CLASS_NAME_CT_AWARE_PORTAL_CACHE =
		"com.liferay.portal.cache.internal.dao.orm.CTAwarePortalCache";

	private static final String _CLASS_NAME_EHCACHE_PORTAL_CACHE_REPLICATOR =
		"com.liferay.portal.cache.ehcache.internal.events." +
			"EhcachePortalCacheReplicatorUtil$EhcachePortalCacheReplicator";

	private static transient TomcatNode _tomcatNode1;
	private static transient TomcatNode _tomcatNode2;

}