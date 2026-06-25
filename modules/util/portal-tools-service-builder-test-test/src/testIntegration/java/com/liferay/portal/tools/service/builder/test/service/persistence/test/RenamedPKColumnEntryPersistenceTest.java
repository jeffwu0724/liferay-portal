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
import com.liferay.portal.tools.service.builder.test.exception.NoSuchRenamedPKColumnEntryException;
import com.liferay.portal.tools.service.builder.test.model.RenamedPKColumnEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.RenamedPKColumnEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.RenamedPKColumnEntryUtil;

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
public class RenamedPKColumnEntryPersistenceTest {

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
		_persistence = RenamedPKColumnEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RenamedPKColumnEntry> iterator =
			_renamedPKColumnEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RenamedPKColumnEntry renamedPKColumnEntry = _persistence.create(pk);

		Assert.assertNotNull(renamedPKColumnEntry);

		Assert.assertEquals(renamedPKColumnEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RenamedPKColumnEntry newRenamedPKColumnEntry =
			addRenamedPKColumnEntry();

		_persistence.remove(newRenamedPKColumnEntry);

		RenamedPKColumnEntry existingRenamedPKColumnEntry =
			_persistence.fetchByPrimaryKey(
				newRenamedPKColumnEntry.getPrimaryKey());

		Assert.assertNull(existingRenamedPKColumnEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRenamedPKColumnEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		RenamedPKColumnEntry newRenamedPKColumnEntry =
			addRenamedPKColumnEntry();

		newRenamedPKColumnEntry.setName(RandomTestUtil.randomString());

		newRenamedPKColumnEntry = _persistence.update(newRenamedPKColumnEntry);

		_renamedPKColumnEntries.add(newRenamedPKColumnEntry);

		RenamedPKColumnEntry existingRenamedPKColumnEntry =
			_persistence.findByPrimaryKey(
				newRenamedPKColumnEntry.getPrimaryKey());

		Assert.assertEquals(
			existingRenamedPKColumnEntry.getRenamedPKColumnEntryId(),
			newRenamedPKColumnEntry.getRenamedPKColumnEntryId());
		Assert.assertEquals(
			existingRenamedPKColumnEntry.getName(),
			newRenamedPKColumnEntry.getName());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RenamedPKColumnEntry newRenamedPKColumnEntry =
			addRenamedPKColumnEntry();

		RenamedPKColumnEntry existingRenamedPKColumnEntry =
			_persistence.findByPrimaryKey(
				newRenamedPKColumnEntry.getPrimaryKey());

		Assert.assertEquals(
			existingRenamedPKColumnEntry, newRenamedPKColumnEntry);
	}

	@Test(expected = NoSuchRenamedPKColumnEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<RenamedPKColumnEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"RenamedPKColumnEntry", "renamedPKColumnEntryId", true, "name",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RenamedPKColumnEntry newRenamedPKColumnEntry =
			addRenamedPKColumnEntry();

		RenamedPKColumnEntry existingRenamedPKColumnEntry =
			_persistence.fetchByPrimaryKey(
				newRenamedPKColumnEntry.getPrimaryKey());

		Assert.assertEquals(
			existingRenamedPKColumnEntry, newRenamedPKColumnEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RenamedPKColumnEntry missingRenamedPKColumnEntry =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRenamedPKColumnEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		RenamedPKColumnEntry newRenamedPKColumnEntry1 =
			addRenamedPKColumnEntry();
		RenamedPKColumnEntry newRenamedPKColumnEntry2 =
			addRenamedPKColumnEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRenamedPKColumnEntry1.getPrimaryKey());
		primaryKeys.add(newRenamedPKColumnEntry2.getPrimaryKey());

		Map<Serializable, RenamedPKColumnEntry> renamedPKColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, renamedPKColumnEntries.size());
		Assert.assertEquals(
			newRenamedPKColumnEntry1,
			renamedPKColumnEntries.get(
				newRenamedPKColumnEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newRenamedPKColumnEntry2,
			renamedPKColumnEntries.get(
				newRenamedPKColumnEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RenamedPKColumnEntry> renamedPKColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(renamedPKColumnEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		RenamedPKColumnEntry newRenamedPKColumnEntry =
			addRenamedPKColumnEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRenamedPKColumnEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RenamedPKColumnEntry> renamedPKColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, renamedPKColumnEntries.size());
		Assert.assertEquals(
			newRenamedPKColumnEntry,
			renamedPKColumnEntries.get(
				newRenamedPKColumnEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RenamedPKColumnEntry> renamedPKColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(renamedPKColumnEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		RenamedPKColumnEntry newRenamedPKColumnEntry =
			addRenamedPKColumnEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRenamedPKColumnEntry.getPrimaryKey());

		Map<Serializable, RenamedPKColumnEntry> renamedPKColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, renamedPKColumnEntries.size());
		Assert.assertEquals(
			newRenamedPKColumnEntry,
			renamedPKColumnEntries.get(
				newRenamedPKColumnEntry.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		RenamedPKColumnEntry newRenamedPKColumnEntry =
			addRenamedPKColumnEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			RenamedPKColumnEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"renamedPKColumnEntryId",
				newRenamedPKColumnEntry.getRenamedPKColumnEntryId()));

		List<RenamedPKColumnEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		RenamedPKColumnEntry existingRenamedPKColumnEntry = result.get(0);

		Assert.assertEquals(
			existingRenamedPKColumnEntry, newRenamedPKColumnEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			RenamedPKColumnEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"renamedPKColumnEntryId", RandomTestUtil.nextLong()));

		List<RenamedPKColumnEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		RenamedPKColumnEntry newRenamedPKColumnEntry =
			addRenamedPKColumnEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			RenamedPKColumnEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("renamedPKColumnEntryId"));

		Object newRenamedPKColumnEntryId =
			newRenamedPKColumnEntry.getRenamedPKColumnEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"renamedPKColumnEntryId",
				new Object[] {newRenamedPKColumnEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRenamedPKColumnEntryId = result.get(0);

		Assert.assertEquals(
			existingRenamedPKColumnEntryId, newRenamedPKColumnEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			RenamedPKColumnEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("renamedPKColumnEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"renamedPKColumnEntryId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected RenamedPKColumnEntry addRenamedPKColumnEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RenamedPKColumnEntry renamedPKColumnEntry = _persistence.create(pk);

		renamedPKColumnEntry.setName(RandomTestUtil.randomString());

		_renamedPKColumnEntries.add(_persistence.update(renamedPKColumnEntry));

		return renamedPKColumnEntry;
	}

	private List<RenamedPKColumnEntry> _renamedPKColumnEntries =
		new ArrayList<RenamedPKColumnEntry>();
	private RenamedPKColumnEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1248250754