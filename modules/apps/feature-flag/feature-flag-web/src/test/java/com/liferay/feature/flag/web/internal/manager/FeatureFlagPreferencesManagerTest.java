/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.feature.flag.web.internal.manager;

import com.liferay.petra.lang.SafeCloseable;
import com.liferay.portal.kernel.change.tracking.CTCollectionThreadLocal;
import com.liferay.portal.kernel.service.PortalPreferencesLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.concurrent.atomic.AtomicLong;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mockito;

/**
 * @author Jiefeng Wu
 */
public class FeatureFlagPreferencesManagerTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testIsEnabledReadsInProductionMode() {
		FeatureFlagPreferencesManager featureFlagPreferencesManager =
			new FeatureFlagPreferencesManager();

		AtomicLong ctCollectionId = new AtomicLong(-1);
		long companyId = RandomTestUtil.randomLong();

		PortalPreferencesLocalService portalPreferencesLocalService =
			Mockito.mock(PortalPreferencesLocalService.class);

		Mockito.when(
			portalPreferencesLocalService.fetchPortalPreferences(
				companyId, PortletKeys.PREFS_OWNER_TYPE_COMPANY)
		).thenAnswer(
			invocation -> {
				ctCollectionId.set(CTCollectionThreadLocal.getCTCollectionId());

				return null;
			}
		);

		ReflectionTestUtil.setFieldValue(
			featureFlagPreferencesManager, "_portalPreferencesLocalService",
			portalPreferencesLocalService);

		long publicationCTCollectionId = RandomTestUtil.randomLong();

		try (SafeCloseable safeCloseable =
				CTCollectionThreadLocal.setCTCollectionIdWithSafeCloseable(
					publicationCTCollectionId)) {

			Assert.assertNull(
				featureFlagPreferencesManager.isEnabled(
					companyId, RandomTestUtil.randomString()));

			Assert.assertEquals(
				CTCollectionThreadLocal.CT_COLLECTION_ID_PRODUCTION,
				ctCollectionId.get());
			Assert.assertEquals(
				publicationCTCollectionId,
				CTCollectionThreadLocal.getCTCollectionId());
		}
	}

}