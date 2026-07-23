/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchDistinctUserIdEntryException;
import com.liferay.portal.tools.service.builder.test.model.DistinctUserIdEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.DistinctUserIdEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.DistinctUserIdEntryUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class DistinctUserIdEntryPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.portal.tools.service.builder.test.service"));

	@Before
	public void setUp() {
		_persistence = DistinctUserIdEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DistinctUserIdEntry> iterator =
			_distinctUserIdEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DistinctUserIdEntry distinctUserIdEntry = _persistence.create(pk);

		Assert.assertNotNull(distinctUserIdEntry);

		Assert.assertEquals(distinctUserIdEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DistinctUserIdEntry newDistinctUserIdEntry = addDistinctUserIdEntry();

		_persistence.remove(newDistinctUserIdEntry);

		DistinctUserIdEntry existingDistinctUserIdEntry =
			_persistence.fetchByPrimaryKey(
				newDistinctUserIdEntry.getPrimaryKey());

		Assert.assertNull(existingDistinctUserIdEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDistinctUserIdEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		DistinctUserIdEntry newDistinctUserIdEntry = addDistinctUserIdEntry();

		newDistinctUserIdEntry.setUserId(RandomTestUtil.nextLong());

		newDistinctUserIdEntry = _persistence.update(newDistinctUserIdEntry);

		_distinctUserIdEntries.add(newDistinctUserIdEntry);

		DistinctUserIdEntry existingDistinctUserIdEntry =
			_persistence.findByPrimaryKey(
				newDistinctUserIdEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDistinctUserIdEntry.getDistinctUserIdEntryId(),
			newDistinctUserIdEntry.getDistinctUserIdEntryId());
		Assert.assertEquals(
			existingDistinctUserIdEntry.getUserId(),
			newDistinctUserIdEntry.getUserId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DistinctUserIdEntry newDistinctUserIdEntry = addDistinctUserIdEntry();

		DistinctUserIdEntry existingDistinctUserIdEntry =
			_persistence.findByPrimaryKey(
				newDistinctUserIdEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDistinctUserIdEntry, newDistinctUserIdEntry);
	}

	@Test(expected = NoSuchDistinctUserIdEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DistinctUserIdEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"DistinctUserIdEntry", "distinctUserIdEntryId", true, "userId",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DistinctUserIdEntry newDistinctUserIdEntry = addDistinctUserIdEntry();

		DistinctUserIdEntry existingDistinctUserIdEntry =
			_persistence.fetchByPrimaryKey(
				newDistinctUserIdEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDistinctUserIdEntry, newDistinctUserIdEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DistinctUserIdEntry missingDistinctUserIdEntry =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDistinctUserIdEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DistinctUserIdEntry newDistinctUserIdEntry1 = addDistinctUserIdEntry();
		DistinctUserIdEntry newDistinctUserIdEntry2 = addDistinctUserIdEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDistinctUserIdEntry1.getPrimaryKey());
		primaryKeys.add(newDistinctUserIdEntry2.getPrimaryKey());

		Map<Serializable, DistinctUserIdEntry> distinctUserIdEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, distinctUserIdEntries.size());
		Assert.assertEquals(
			newDistinctUserIdEntry1,
			distinctUserIdEntries.get(newDistinctUserIdEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newDistinctUserIdEntry2,
			distinctUserIdEntries.get(newDistinctUserIdEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DistinctUserIdEntry> distinctUserIdEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(distinctUserIdEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DistinctUserIdEntry newDistinctUserIdEntry = addDistinctUserIdEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDistinctUserIdEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DistinctUserIdEntry> distinctUserIdEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, distinctUserIdEntries.size());
		Assert.assertEquals(
			newDistinctUserIdEntry,
			distinctUserIdEntries.get(newDistinctUserIdEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DistinctUserIdEntry> distinctUserIdEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(distinctUserIdEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DistinctUserIdEntry newDistinctUserIdEntry = addDistinctUserIdEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDistinctUserIdEntry.getPrimaryKey());

		Map<Serializable, DistinctUserIdEntry> distinctUserIdEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, distinctUserIdEntries.size());
		Assert.assertEquals(
			newDistinctUserIdEntry,
			distinctUserIdEntries.get(newDistinctUserIdEntry.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DistinctUserIdEntry newDistinctUserIdEntry = addDistinctUserIdEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DistinctUserIdEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"distinctUserIdEntryId",
				newDistinctUserIdEntry.getDistinctUserIdEntryId()));

		List<DistinctUserIdEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		DistinctUserIdEntry existingDistinctUserIdEntry = result.get(0);

		Assert.assertEquals(
			existingDistinctUserIdEntry, newDistinctUserIdEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DistinctUserIdEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"distinctUserIdEntryId", RandomTestUtil.nextLong()));

		List<DistinctUserIdEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DistinctUserIdEntry newDistinctUserIdEntry = addDistinctUserIdEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DistinctUserIdEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("distinctUserIdEntryId"));

		Object newDistinctUserIdEntryId =
			newDistinctUserIdEntry.getDistinctUserIdEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"distinctUserIdEntryId",
				new Object[] {newDistinctUserIdEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDistinctUserIdEntryId = result.get(0);

		Assert.assertEquals(
			existingDistinctUserIdEntryId, newDistinctUserIdEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DistinctUserIdEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("distinctUserIdEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"distinctUserIdEntryId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DistinctUserIdEntry addDistinctUserIdEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DistinctUserIdEntry distinctUserIdEntry = _persistence.create(pk);

		distinctUserIdEntry.setUserId(RandomTestUtil.nextLong());

		_distinctUserIdEntries.add(_persistence.update(distinctUserIdEntry));

		return distinctUserIdEntry;
	}

	private List<DistinctUserIdEntry> _distinctUserIdEntries =
		new ArrayList<DistinctUserIdEntry>();
	private DistinctUserIdEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1539945507