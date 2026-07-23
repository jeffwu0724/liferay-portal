/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.model.DistinctUserIdEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.DistinctUserIdEntryPersistence;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Reproduces the duplicate <code>userId</code> SQL alias that Hibernate 7
 * rejects (LPD-98613).
 *
 * <p>
 * When the User keyword search is ordered by user ID,
 * <code>UserFinderImpl.getColumnNames</code> emits
 * <code>DISTINCT User_.userId AS userId</code> and then appends
 * <code>User_.userId AS userId</code> a second time, so the alias
 * <code>userId</code> appears twice. {@link #_SQL} is that same shape against
 * the <code>DistinctUserIdEntry</code> table, run through the entity
 * persistence's {@link SessionFactory} (obtained reflectively, as
 * <code>SQLDateTest</code> and <code>DB2DialectTest</code> do). Hibernate 5
 * tolerates the duplicate and returns the row, so the assertion passes.
 * Hibernate 7 validates native-query aliases in
 * <code>ResultSetMappingImpl.checkDuplicateAliases</code> and throws
 * <code>NonUniqueDiscoveredSqlAliasException</code>, so the query fails before
 * the assertion, exposing the defect.
 * </p>
 *
 * @author Jiefeng Wu
 */
@RunWith(Arquillian.class)
public class DuplicateSQLAliasTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.portal.tools.service.builder.test.service"));

	@Before
	public void setUp() {
		_sessionFactory = ReflectionTestUtil.getFieldValue(
			_distinctUserIdEntryPersistence, "_sessionFactory");
	}

	@Test
	public void testFindByUserIdOrderedByUserId() throws Exception {
		long userId = RandomTestUtil.nextLong();

		DistinctUserIdEntry distinctUserIdEntry =
			_distinctUserIdEntryPersistence.create(RandomTestUtil.nextLong());

		distinctUserIdEntry.setUserId(userId);

		_distinctUserIdEntryPersistence.update(distinctUserIdEntry);

		Session session = null;

		try {
			session = _sessionFactory.openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(_SQL);

			sqlQuery.addScalar("userId", Type.LONG);

			List<Long> userIds = sqlQuery.list();

			Assert.assertTrue(userIds.toString(), userIds.contains(userId));
		}
		finally {
			_sessionFactory.closeSession(session);
		}
	}

	private static final String _SQL =
		"SELECT DISTINCT DistinctUserIdEntry.userId AS userId, " +
			"DistinctUserIdEntry.userId AS userId FROM DistinctUserIdEntry " +
				"ORDER BY DistinctUserIdEntry.userId ASC";

	@Inject
	private DistinctUserIdEntryPersistence _distinctUserIdEntryPersistence;

	private SessionFactory _sessionFactory;

}