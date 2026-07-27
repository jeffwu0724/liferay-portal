/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringBundler;
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
import com.liferay.portal.tools.service.builder.test.model.SQLQueryEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.SQLQueryEntryPersistence;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jiefeng Wu
 */
@RunWith(Arquillian.class)
public class SQLQueryEntryTest {

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
			_sqlQueryEntryPersistence, "_sessionFactory");
	}

	@Test
	public void testDuplicateUserIdAlias() throws Exception {
		long userId = RandomTestUtil.nextLong();

		SQLQueryEntry sqlQueryEntry = _sqlQueryEntryPersistence.create(
			RandomTestUtil.nextLong());

		sqlQueryEntry.setUserId(userId);

		_sqlQueryEntryPersistence.update(sqlQueryEntry);

		Session session = null;

		try {
			session = _sessionFactory.openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(
				_SQL_DUPLICATE_USER_ID_ALIAS);

			sqlQuery.addScalar("userId", Type.LONG);

			List<Long> userIds = sqlQuery.list();

			Assert.assertTrue(userIds.toString(), userIds.contains(userId));
		}
		finally {
			_sessionFactory.closeSession(session);
		}
	}

	private static final String _SQL_DUPLICATE_USER_ID_ALIAS =
		StringBundler.concat(
			"SELECT SQLQueryEntry.userId AS userId, SQLQueryEntry.userId AS ",
			"userId FROM SQLQueryEntry ORDER BY SQLQueryEntry.userId ASC");

	private SessionFactory _sessionFactory;

	@Inject
	private SQLQueryEntryPersistence _sqlQueryEntryPersistence;

}