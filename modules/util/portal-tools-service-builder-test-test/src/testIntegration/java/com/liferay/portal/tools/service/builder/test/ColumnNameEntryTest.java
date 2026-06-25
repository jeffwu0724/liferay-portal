/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.ColumnNameEntryPersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Tina Tian
 */
@RunWith(Arquillian.class)
public class ColumnNameEntryTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.portal.tools.service.builder.test.service"));

	@Test
	public void testFetchByPrimaryKeys() {
		ColumnNameEntry columnNameEntry1 = _addColumnNameEntry();
		ColumnNameEntry columnNameEntry2 = _addColumnNameEntry();

		_columnNameEntryPersistence.clearCache();

		Set<Serializable> primaryKeys = new HashSet<>();

		primaryKeys.add(columnNameEntry1.getPrimaryKey());
		primaryKeys.add(columnNameEntry2.getPrimaryKey());

		Map<Serializable, ColumnNameEntry> columnNameEntries =
			_columnNameEntryPersistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(
			columnNameEntries.toString(), 2, columnNameEntries.size());
		Assert.assertEquals(
			columnNameEntry1,
			columnNameEntries.get(columnNameEntry1.getPrimaryKey()));
		Assert.assertEquals(
			columnNameEntry2,
			columnNameEntries.get(columnNameEntry2.getPrimaryKey()));
	}

	private ColumnNameEntry _addColumnNameEntry() {
		ColumnNameEntry columnNameEntry = _columnNameEntryPersistence.create(
			RandomTestUtil.nextLong());

		columnNameEntry.setName(RandomTestUtil.randomString());

		columnNameEntry = _columnNameEntryPersistence.update(columnNameEntry);

		_columnNameEntries.add(columnNameEntry);

		return columnNameEntry;
	}

	@DeleteAfterTestRun
	private List<ColumnNameEntry> _columnNameEntries = new ArrayList<>();

	@Inject
	private ColumnNameEntryPersistence _columnNameEntryPersistence;

}