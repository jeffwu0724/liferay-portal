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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchDateColumnEntryException;
import com.liferay.portal.tools.service.builder.test.model.DateColumnEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.DateColumnEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.DateColumnEntryUtil;

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
public class DateColumnEntryPersistenceTest {

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
		_persistence = DateColumnEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DateColumnEntry> iterator = _dateColumnEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DateColumnEntry dateColumnEntry = _persistence.create(pk);

		Assert.assertNotNull(dateColumnEntry);

		Assert.assertEquals(dateColumnEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DateColumnEntry newDateColumnEntry = addDateColumnEntry();

		_persistence.remove(newDateColumnEntry);

		DateColumnEntry existingDateColumnEntry =
			_persistence.fetchByPrimaryKey(newDateColumnEntry.getPrimaryKey());

		Assert.assertNull(existingDateColumnEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDateColumnEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		DateColumnEntry newDateColumnEntry = addDateColumnEntry();

		newDateColumnEntry.setDateValue(RandomTestUtil.nextDate());

		newDateColumnEntry = _persistence.update(newDateColumnEntry);

		_dateColumnEntries.add(newDateColumnEntry);

		DateColumnEntry existingDateColumnEntry = _persistence.findByPrimaryKey(
			newDateColumnEntry.getPrimaryKey());

		Assert.assertEquals(
			existingDateColumnEntry.getDateColumnEntryId(),
			newDateColumnEntry.getDateColumnEntryId());
		Assert.assertEquals(
			Time.getShortTimestamp(existingDateColumnEntry.getDateValue()),
			Time.getShortTimestamp(newDateColumnEntry.getDateValue()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DateColumnEntry newDateColumnEntry = addDateColumnEntry();

		DateColumnEntry existingDateColumnEntry = _persistence.findByPrimaryKey(
			newDateColumnEntry.getPrimaryKey());

		Assert.assertEquals(existingDateColumnEntry, newDateColumnEntry);
	}

	@Test(expected = NoSuchDateColumnEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<DateColumnEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"DateColumnEntry", "dateColumnEntryId", true, "dateValue", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DateColumnEntry newDateColumnEntry = addDateColumnEntry();

		DateColumnEntry existingDateColumnEntry =
			_persistence.fetchByPrimaryKey(newDateColumnEntry.getPrimaryKey());

		Assert.assertEquals(existingDateColumnEntry, newDateColumnEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DateColumnEntry missingDateColumnEntry = _persistence.fetchByPrimaryKey(
			pk);

		Assert.assertNull(missingDateColumnEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		DateColumnEntry newDateColumnEntry1 = addDateColumnEntry();
		DateColumnEntry newDateColumnEntry2 = addDateColumnEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDateColumnEntry1.getPrimaryKey());
		primaryKeys.add(newDateColumnEntry2.getPrimaryKey());

		Map<Serializable, DateColumnEntry> dateColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, dateColumnEntries.size());
		Assert.assertEquals(
			newDateColumnEntry1,
			dateColumnEntries.get(newDateColumnEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newDateColumnEntry2,
			dateColumnEntries.get(newDateColumnEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DateColumnEntry> dateColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dateColumnEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		DateColumnEntry newDateColumnEntry = addDateColumnEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDateColumnEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DateColumnEntry> dateColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dateColumnEntries.size());
		Assert.assertEquals(
			newDateColumnEntry,
			dateColumnEntries.get(newDateColumnEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DateColumnEntry> dateColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(dateColumnEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		DateColumnEntry newDateColumnEntry = addDateColumnEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDateColumnEntry.getPrimaryKey());

		Map<Serializable, DateColumnEntry> dateColumnEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, dateColumnEntries.size());
		Assert.assertEquals(
			newDateColumnEntry,
			dateColumnEntries.get(newDateColumnEntry.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		DateColumnEntry newDateColumnEntry = addDateColumnEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DateColumnEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"dateColumnEntryId",
				newDateColumnEntry.getDateColumnEntryId()));

		List<DateColumnEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		DateColumnEntry existingDateColumnEntry = result.get(0);

		Assert.assertEquals(existingDateColumnEntry, newDateColumnEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DateColumnEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"dateColumnEntryId", RandomTestUtil.nextLong()));

		List<DateColumnEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		DateColumnEntry newDateColumnEntry = addDateColumnEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DateColumnEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("dateColumnEntryId"));

		Object newDateColumnEntryId = newDateColumnEntry.getDateColumnEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"dateColumnEntryId", new Object[] {newDateColumnEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDateColumnEntryId = result.get(0);

		Assert.assertEquals(existingDateColumnEntryId, newDateColumnEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DateColumnEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("dateColumnEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"dateColumnEntryId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected DateColumnEntry addDateColumnEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DateColumnEntry dateColumnEntry = _persistence.create(pk);

		dateColumnEntry.setDateValue(RandomTestUtil.nextDate());

		_dateColumnEntries.add(_persistence.update(dateColumnEntry));

		return dateColumnEntry;
	}

	private List<DateColumnEntry> _dateColumnEntries =
		new ArrayList<DateColumnEntry>();
	private DateColumnEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}
// LIFERAY-SERVICE-BUILDER-HASH:-8911622