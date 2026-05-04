/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.internal.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.cache.PortalCacheReplicator;
import com.liferay.portal.cache.PortalCacheWrapper;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheException;
import com.liferay.portal.kernel.cache.PortalCacheListener;
import com.liferay.portal.kernel.cache.PortalCacheListenerScope;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.TomcatClusterTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.model.impl.UserGroupImpl;
import com.liferay.portal.test.cluster.tomcat.TomcatCluster;
import com.liferay.portal.test.cluster.tomcat.TomcatNode;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jiefeng Wu
 */
@RunWith(Arquillian.class)
public class CacheReplicationMessageCountTest implements Serializable {

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

	@Test
	public void testEntityAndFinderCacheReplicationMessageCount()
		throws Exception {

		_tomcatNode1.syncExecute(
			() -> {
				EntityCacheUtil.clearCache(UserGroupImpl.class);
				FinderCacheUtil.clearCache(UserGroupImpl.class);

				PortalCache<Serializable, Serializable> entityPortalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				entityPortalCache.registerPortalCacheListener(
					new TestPortalCacheReplicator());

				TestPortalCacheReplicator finderListener =
					new TestPortalCacheReplicator();

				for (PortalCache<Serializable, Serializable> finderPortalCache :
						_getFinderEhcachePortalCaches(UserGroupImpl.class)) {

					finderPortalCache.registerPortalCacheListener(
						finderListener);
				}

				return null;
			});

		int[][] counts = _tomcatNode1.syncExecute(
			() -> {
				EntityCacheUtil.putResult(
					UserGroupImpl.class, new UserGroupImpl(), false, true);

				return _getEntityAndFinderCounts();
			});

		_assertCounts(counts[0], 1, 0, 0);

		counts = _tomcatNode1.syncExecute(
			() -> {
				EntityCacheUtil.putResult(
					UserGroupImpl.class, new UserGroupImpl(), false, true);

				return _getEntityAndFinderCounts();
			});

		_assertCounts(counts[0], 1, 1, 0);

		counts = _tomcatNode1.syncExecute(
			() -> {
				EntityCacheUtil.removeResult(
					UserGroupImpl.class, new UserGroupImpl());

				return _getEntityAndFinderCounts();
			});

		_assertCounts(counts[0], 1, 1, 1);

		Assert.assertEquals(
			3, counts[0][0] + counts[0][1] + counts[0][2] + counts[0][3]);

		System.out.println(
			"finder events total = " +
				(counts[1][0] + counts[1][1] + counts[1][2] + counts[1][3]));

		_tomcatNode1.syncExecute(
			() -> {
				PortalCache<Serializable, Serializable> entityPortalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				entityPortalCache.unregisterPortalCacheListener(
					(TestPortalCacheReplicator)_getListenerOnEhcachePortalCache(
						TestPortalCacheReplicator.class.getName(),
						entityPortalCache));

				for (PortalCache<Serializable, Serializable> finderPortalCache :
						_getFinderEhcachePortalCaches(UserGroupImpl.class)) {

					finderPortalCache.unregisterPortalCacheListener(
						(TestPortalCacheReplicator)
							_getListenerOnEhcachePortalCache(
								TestPortalCacheReplicator.class.getName(),
								finderPortalCache));
				}

				EntityCacheUtil.clearCache(UserGroupImpl.class);
				FinderCacheUtil.clearCache(UserGroupImpl.class);

				return null;
			});
	}

	@Test
	public void testEntityCacheReplicationMessageCount() throws Exception {
		long testKey = RandomTestUtil.randomLong();

		_tomcatNode1.syncExecute(
			() -> {
				EntityCacheUtil.clearCache(UserGroupImpl.class);

				PortalCache<Serializable, Serializable> portalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				portalCache.registerPortalCacheListener(
					new TestPortalCacheReplicator());

				return null;
			});

		int[] counts = _tomcatNode1.syncExecute(
			() -> {
				PortalCache<Serializable, Serializable> portalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				portalCache.put(testKey, "test1");

				return _getCounts(portalCache);
			});

		_assertCounts(counts, 1, 0, 0);

		counts = _tomcatNode1.syncExecute(
			() -> {
				PortalCache<Serializable, Serializable> portalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				portalCache.put(testKey, "test2");

				return _getCounts(portalCache);
			});

		_assertCounts(counts, 1, 1, 0);

		counts = _tomcatNode1.syncExecute(
			() -> {
				PortalCache<Serializable, Serializable> portalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				portalCache.remove(testKey);

				return _getCounts(portalCache);
			});

		_assertCounts(counts, 1, 1, 1);

		Assert.assertEquals(3, counts[0] + counts[1] + counts[2] + counts[3]);

		_tomcatNode1.syncExecute(
			() -> {
				PortalCache<Serializable, Serializable> portalCache =
					_getEntityEhcachePortalCache(UserGroupImpl.class);

				portalCache.unregisterPortalCacheListener(
					(TestPortalCacheReplicator)_getListenerOnEhcachePortalCache(
						TestPortalCacheReplicator.class.getName(),
						portalCache));

				EntityCacheUtil.clearCache(UserGroupImpl.class);

				return null;
			});
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

	private void _assertCounts(int[] counts, int put, int update, int remove) {
		Assert.assertEquals("put", put, counts[0]);
		Assert.assertEquals("update", update, counts[1]);
		Assert.assertEquals("remove", remove, counts[2]);
		Assert.assertEquals("removeAll", 0, counts[3]);
	}

	private int[] _getCounts(
		PortalCache<Serializable, Serializable> portalCache) {

		TestPortalCacheReplicator testPortalCacheReplicator =
			(TestPortalCacheReplicator)_getListenerOnEhcachePortalCache(
				TestPortalCacheReplicator.class.getName(), portalCache);

		return new int[] {
			testPortalCacheReplicator.getPutCount(),
			testPortalCacheReplicator.getUpdateCount(),
			testPortalCacheReplicator.getRemoveCount(),
			testPortalCacheReplicator.getRemoveAllCount()
		};
	}

	private int[][] _getEntityAndFinderCounts() {
		List<PortalCache<Serializable, Serializable>> finderPortalCaches =
			_getFinderEhcachePortalCaches(UserGroupImpl.class);

		return new int[][] {
			_getCounts(_getEntityEhcachePortalCache(UserGroupImpl.class)),
			_getCounts(finderPortalCaches.get(0))
		};
	}

	private PortalCache<Serializable, Serializable>
		_getEntityEhcachePortalCache(Class<?> clazz) {

		return _unwrapToEhcachePortalCache(
			EntityCacheUtil.getPortalCache(clazz));
	}

	private List<PortalCache<Serializable, Serializable>>
		_getFinderEhcachePortalCaches(Class<?> clazz) {

		List<PortalCache<Serializable, Serializable>>
			finderEhcachePortalCaches = new ArrayList<>();

		ConcurrentMap<String, PortalCache<Serializable, Serializable>>
			portalCaches = ReflectionTestUtil.getFieldValue(
				FinderCacheUtil.getFinderCache(), "_portalCaches");

		for (Map.Entry<String, PortalCache<Serializable, Serializable>> entry :
				portalCaches.entrySet()) {

			if (entry.getKey(
				).startsWith(
					clazz.getName()
				)) {

				finderEhcachePortalCaches.add(
					_unwrapToEhcachePortalCache(entry.getValue()));
			}
		}

		return finderEhcachePortalCaches;
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

	private PortalCache<Serializable, Serializable> _unwrapToEhcachePortalCache(
		PortalCache<Serializable, Serializable> portalCache) {

		if (Objects.equals(
				portalCache.getClass(
				).getName(),
				_CLASS_NAME_CT_AWARE_PORTAL_CACHE)) {

			portalCache = ReflectionTestUtil.getFieldValue(
				portalCache, "_productionPortalCache");
		}

		while (portalCache instanceof PortalCacheWrapper) {
			portalCache = ReflectionTestUtil.getFieldValue(
				portalCache, "portalCache");
		}

		return portalCache;
	}

	private static final String _CLASS_NAME_CT_AWARE_PORTAL_CACHE =
		"com.liferay.portal.cache.internal.dao.orm.CTAwarePortalCache";

	private static transient TomcatNode _tomcatNode1;
	private static transient TomcatNode _tomcatNode2;

}