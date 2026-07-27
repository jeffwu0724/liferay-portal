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
import com.liferay.portal.tools.service.builder.test.exception.NoSuchSQLQueryEntryException;
import com.liferay.portal.tools.service.builder.test.model.SQLQueryEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.SQLQueryEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.SQLQueryEntryUtil;

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
public class SQLQueryEntryPersistenceTest {

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
		_persistence = SQLQueryEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<SQLQueryEntry> iterator = _sqlQueryEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SQLQueryEntry sqlQueryEntry = _persistence.create(pk);

		Assert.assertNotNull(sqlQueryEntry);

		Assert.assertEquals(sqlQueryEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		SQLQueryEntry newSQLQueryEntry = addSQLQueryEntry();

		_persistence.remove(newSQLQueryEntry);

		SQLQueryEntry existingSQLQueryEntry = _persistence.fetchByPrimaryKey(
			newSQLQueryEntry.getPrimaryKey());

		Assert.assertNull(existingSQLQueryEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addSQLQueryEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		SQLQueryEntry newSQLQueryEntry = addSQLQueryEntry();

		newSQLQueryEntry.setUserId(RandomTestUtil.nextLong());

		newSQLQueryEntry = _persistence.update(newSQLQueryEntry);

		_sqlQueryEntries.add(newSQLQueryEntry);

		SQLQueryEntry existingSQLQueryEntry = _persistence.findByPrimaryKey(
			newSQLQueryEntry.getPrimaryKey());

		Assert.assertEquals(
			existingSQLQueryEntry.getSqlQueryEntryId(),
			newSQLQueryEntry.getSqlQueryEntryId());
		Assert.assertEquals(
			existingSQLQueryEntry.getUserId(), newSQLQueryEntry.getUserId());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		SQLQueryEntry newSQLQueryEntry = addSQLQueryEntry();

		SQLQueryEntry existingSQLQueryEntry = _persistence.findByPrimaryKey(
			newSQLQueryEntry.getPrimaryKey());

		Assert.assertEquals(existingSQLQueryEntry, newSQLQueryEntry);
	}

	@Test(expected = NoSuchSQLQueryEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<SQLQueryEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"SQLQueryEntry", "sqlQueryEntryId", true, "userId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		SQLQueryEntry newSQLQueryEntry = addSQLQueryEntry();

		SQLQueryEntry existingSQLQueryEntry = _persistence.fetchByPrimaryKey(
			newSQLQueryEntry.getPrimaryKey());

		Assert.assertEquals(existingSQLQueryEntry, newSQLQueryEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SQLQueryEntry missingSQLQueryEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingSQLQueryEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		SQLQueryEntry newSQLQueryEntry1 = addSQLQueryEntry();
		SQLQueryEntry newSQLQueryEntry2 = addSQLQueryEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSQLQueryEntry1.getPrimaryKey());
		primaryKeys.add(newSQLQueryEntry2.getPrimaryKey());

		Map<Serializable, SQLQueryEntry> sqlQueryEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, sqlQueryEntries.size());
		Assert.assertEquals(
			newSQLQueryEntry1,
			sqlQueryEntries.get(newSQLQueryEntry1.getPrimaryKey()));
		Assert.assertEquals(
			newSQLQueryEntry2,
			sqlQueryEntries.get(newSQLQueryEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, SQLQueryEntry> sqlQueryEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(sqlQueryEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		SQLQueryEntry newSQLQueryEntry = addSQLQueryEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSQLQueryEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, SQLQueryEntry> sqlQueryEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, sqlQueryEntries.size());
		Assert.assertEquals(
			newSQLQueryEntry,
			sqlQueryEntries.get(newSQLQueryEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, SQLQueryEntry> sqlQueryEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(sqlQueryEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		SQLQueryEntry newSQLQueryEntry = addSQLQueryEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newSQLQueryEntry.getPrimaryKey());

		Map<Serializable, SQLQueryEntry> sqlQueryEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, sqlQueryEntries.size());
		Assert.assertEquals(
			newSQLQueryEntry,
			sqlQueryEntries.get(newSQLQueryEntry.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		SQLQueryEntry newSQLQueryEntry = addSQLQueryEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SQLQueryEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"sqlQueryEntryId", newSQLQueryEntry.getSqlQueryEntryId()));

		List<SQLQueryEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		SQLQueryEntry existingSQLQueryEntry = result.get(0);

		Assert.assertEquals(existingSQLQueryEntry, newSQLQueryEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SQLQueryEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"sqlQueryEntryId", RandomTestUtil.nextLong()));

		List<SQLQueryEntry> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		SQLQueryEntry newSQLQueryEntry = addSQLQueryEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SQLQueryEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("sqlQueryEntryId"));

		Object newSqlQueryEntryId = newSQLQueryEntry.getSqlQueryEntryId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"sqlQueryEntryId", new Object[] {newSqlQueryEntryId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingSqlQueryEntryId = result.get(0);

		Assert.assertEquals(existingSqlQueryEntryId, newSqlQueryEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			SQLQueryEntry.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("sqlQueryEntryId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"sqlQueryEntryId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected SQLQueryEntry addSQLQueryEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		SQLQueryEntry sqlQueryEntry = _persistence.create(pk);

		sqlQueryEntry.setUserId(RandomTestUtil.nextLong());

		_sqlQueryEntries.add(_persistence.update(sqlQueryEntry));

		return sqlQueryEntry;
	}

	private List<SQLQueryEntry> _sqlQueryEntries =
		new ArrayList<SQLQueryEntry>();
	private SQLQueryEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}
// LIFERAY-SERVICE-BUILDER-HASH:458221757