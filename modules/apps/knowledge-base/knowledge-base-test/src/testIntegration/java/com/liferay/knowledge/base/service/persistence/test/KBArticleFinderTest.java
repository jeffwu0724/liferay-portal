/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.knowledge.base.service.persistence.KBArticleFinder;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.util.Collections;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jiefeng Wu
 */
@RunWith(Arquillian.class)
public class KBArticleFinderTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.knowledge.base.service"));

	@Test
	public void testCountByUrlTitleWithEmptyStatus() throws Exception {
		Assert.assertEquals(
			0,
			_kbArticleFinder.countByUrlTitle(
				TestPropsValues.getGroupId(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), new int[0]));
	}

	@Test
	public void testFindByUrlTitleWithEmptyStatus() throws Exception {
		Assert.assertEquals(
			Collections.emptyList(),
			_kbArticleFinder.findByUrlTitle(
				TestPropsValues.getGroupId(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), new int[0], QueryUtil.ALL_POS,
				QueryUtil.ALL_POS));
	}

	@Inject
	private KBArticleFinder _kbArticleFinder;

}