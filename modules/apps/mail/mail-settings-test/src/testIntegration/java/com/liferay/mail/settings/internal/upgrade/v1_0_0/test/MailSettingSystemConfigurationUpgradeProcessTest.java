/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.settings.internal.upgrade.v1_0_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.mail.settings.configuration.MailSettingSystemConfiguration;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.test.util.UpgradeTestUtil;

import org.junit.After;
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
public class MailSettingSystemConfigurationUpgradeProcessTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_upgradeProcess = UpgradeTestUtil.getUpgradeStep(
			_upgradeStepRegistrator,
			"com.liferay.mail.settings.internal.upgrade.v1_0_0." +
				"MailSettingSystemConfigurationUpgradeProcess");

		_portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(
			TestPropsValues.getUserId(), true);

		_originalPortalPreferencesXML = PortletPreferencesFactoryUtil.toXML(
			_portalPreferences);
	}

	@After
	public void tearDown() throws Exception {
		PortalPreferencesLocalServiceUtil.updatePreferences(
			0L, PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			_originalPortalPreferencesXML);
		_configurationProvider.deleteSystemConfiguration(
			MailSettingSystemConfiguration.class);
	}

	@Test
	public void testUpgradeMailSettingSystemConfiguration() throws Exception {
		_portalPreferences.setValue("", "mail.session.mail", "true");
		_portalPreferences.setValue("", "mail.audit.trail", "test");
		_portalPreferences.setValue("", "mail.batch.size", "2");

		PortalPreferencesLocalServiceUtil.updatePreferences(
			0L, PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			PortletPreferencesFactoryUtil.toXML(_portalPreferences));

		_upgradeProcess.upgrade();

		MailSettingSystemConfiguration mailSettingSystemConfiguration =
			_configurationProvider.getSystemConfiguration(
				MailSettingSystemConfiguration.class);

		Assert.assertTrue(mailSettingSystemConfiguration.mailSessionMail());
		Assert.assertEquals(
			"test", mailSettingSystemConfiguration.mailAuditTrail());
		Assert.assertEquals(
			"2", mailSettingSystemConfiguration.mailBatchSize());
	}

	private static String _originalPortalPreferencesXML;

	@Inject
	private ConfigurationProvider _configurationProvider;

	private PortalPreferences _portalPreferences;
	private UpgradeProcess _upgradeProcess;

	@Inject(
		filter = "(&(component.name=com.liferay.mail.settings.internal.upgrade.registry.MailSettingUpgradeStepRegistrator))"
	)
	private UpgradeStepRegistrator _upgradeStepRegistrator;

}