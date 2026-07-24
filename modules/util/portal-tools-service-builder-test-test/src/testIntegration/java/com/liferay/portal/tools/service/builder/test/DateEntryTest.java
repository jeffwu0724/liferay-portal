/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.sql.dsl.DSLFunctionFactoryUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.model.DateEntry;
import com.liferay.portal.tools.service.builder.test.model.DateEntryTable;
import com.liferay.portal.tools.service.builder.test.model.UADPartialEntryTable;
import com.liferay.portal.tools.service.builder.test.service.persistence.DateEntryPersistence;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
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

	@Test
	public void testDSLQueryWithMaxDateInQueryTable() throws Exception {
		DateEntry dateEntry = _dateEntryPersistence.create(
			RandomTestUtil.nextLong());

		dateEntry.setDateValue(new Date());

		_dateEntryPersistence.update(dateEntry);

		Table<?> table = DSLQueryFactoryUtil.select(
			DSLFunctionFactoryUtil.max(
				DateEntryTable.INSTANCE.dateValue
			).as(
				"maxDate"
			)
		).from(
			DateEntryTable.INSTANCE
		).leftJoinOn(
			UADPartialEntryTable.INSTANCE,
			UADPartialEntryTable.INSTANCE.userId.eq(
				DateEntryTable.INSTANCE.dateEntryId)
		).as(
			"tempTable"
		);

		List<Object> results = _dateEntryPersistence.dslQuery(
			DSLQueryFactoryUtil.select(
			).from(
				table
			));

		Object maxDate = results.get(0);

		Assert.assertTrue(
			String.valueOf(maxDate.getClass()), maxDate instanceof Date);
	}

	@Inject
	private DateEntryPersistence _dateEntryPersistence;

}