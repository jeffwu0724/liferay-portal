/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;
import com.liferay.portal.tools.service.builder.test.model.CompoundPKEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.CompoundPKEntryPK;
import com.liferay.portal.tools.service.builder.test.service.persistence.CompoundPKEntryPersistence;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jiefeng Wu
 */
@RunWith(Arquillian.class)
public class CompoundPKEntryTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.portal.tools.service.builder.test.service"));

	@After
	public void tearDown() throws Exception {
		if (_compoundPKEntry != null) {
			_compoundPKEntryPersistence.remove(_compoundPKEntry);
		}
	}

	@Test
	public void testDynamicQueryByCompositeKeyPath() throws Exception {
		_compoundPKEntry = _compoundPKEntryPersistence.create(
			new CompoundPKEntryPK(
				RandomTestUtil.nextLong(), RandomTestUtil.nextLong()));

		_compoundPKEntry.setName(RandomTestUtil.randomString());

		_compoundPKEntry = _compoundPKEntryPersistence.update(_compoundPKEntry);

		Class<?> clazz = _compoundPKEntryPersistence.getClass();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CompoundPKEntry.class, clazz.getClassLoader());

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"primaryKey.companyId", _compoundPKEntry.getCompanyId()));
		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"primaryKey.classNameId", _compoundPKEntry.getClassNameId()));

		List<CompoundPKEntry> compoundPKEntries =
			_compoundPKEntryPersistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(
			compoundPKEntries.toString(), 1, compoundPKEntries.size());
		Assert.assertEquals(_compoundPKEntry, compoundPKEntries.get(0));
	}

	private CompoundPKEntry _compoundPKEntry;

	@Inject
	private CompoundPKEntryPersistence _compoundPKEntryPersistence;

}