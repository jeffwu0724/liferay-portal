/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.model.DateEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.DateEntryPersistence;

import java.util.Date;
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
public class DateEntryTest {

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
			_dateEntryPersistence, "_sessionFactory");
	}

	@Test
	public void testDateColumnReadAsDate() throws Exception {
		DateEntry dateEntry = _dateEntryPersistence.create(
			RandomTestUtil.nextLong());

		dateEntry.setDateValue(new Date());

		_dateEntryPersistence.update(dateEntry);

		Session session = null;

		try {
			session = _sessionFactory.openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(_SQL);

			List<Object> results = sqlQuery.list();

			Object maxDate = results.get(0);

			Assert.assertTrue(maxDate instanceof Date);
		}
		finally {
			_sessionFactory.closeSession(session);
		}
	}

	private static final String _SQL =
		"SELECT max(dateValue) AS maxDate FROM DateEntry";

	@Inject
	private DateEntryPersistence _dateEntryPersistence;

	private SessionFactory _sessionFactory;

}