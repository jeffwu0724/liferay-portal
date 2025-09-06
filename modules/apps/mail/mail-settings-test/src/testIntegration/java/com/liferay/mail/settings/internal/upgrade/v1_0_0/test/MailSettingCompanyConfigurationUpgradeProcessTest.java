/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.settings.internal.upgrade.v1_0_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.mail.settings.configuration.MailSettingCompanyConfiguration;
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
public class MailSettingCompanyConfigurationUpgradeProcessTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_upgradeProcess = UpgradeTestUtil.getUpgradeStep(
			_upgradeStepRegistrator,
			"com.liferay.mail.settings.internal.upgrade.v1_0_0." +
				"MailSettingCompanyConfigurationUpgradeProcess");

		_portalPreferences1 =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				TestPropsValues.getUserId(), true);

		_originalPortalPreferencesXML = PortletPreferencesFactoryUtil.toXML(
			_portalPreferences1);

		_portalPreferences2 =
			PortletPreferencesFactoryUtil.getPortalPreferences(0L, true);

		_originalPortalPreferencesXML = PortletPreferencesFactoryUtil.toXML(
			_portalPreferences2);
	}

	@After
	public void tearDown() throws Exception {
		PortalPreferencesLocalServiceUtil.updatePreferences(
			0L, PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			_originalPortalPreferencesXML);

		PortalPreferencesLocalServiceUtil.updatePreferences(
			TestPropsValues.getCompanyId(),
			PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			_originalPortalPreferencesXML);

		_configurationProvider.deleteCompanyConfiguration(
			MailSettingCompanyConfiguration.class,
			TestPropsValues.getCompanyId());
		_configurationProvider.deleteSystemConfiguration(
			MailSettingCompanyConfiguration.class);
	}

	@Test
	public void testUpgradeMailSettingCompanyConfigurationWithCompanyId()
		throws Exception {

		_portalPreferences1.setValue(
			"", "mail.session.mail.pop3.user", "test1");
		_portalPreferences1.setValue(
			"", "mail.session.mail.pop3.host", "test1");
		_portalPreferences1.setValue(
			"", "mail.session.mail.smtp.starttls.enable", "false");

		PortalPreferencesLocalServiceUtil.updatePreferences(
			TestPropsValues.getCompanyId(),
			PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			PortletPreferencesFactoryUtil.toXML(_portalPreferences1));

		_upgradeProcess.upgrade();

		MailSettingCompanyConfiguration mailSettingCompanyConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingCompanyConfiguration.class,
				TestPropsValues.getCompanyId());

		Assert.assertFalse(
			mailSettingCompanyConfiguration.enablePOPServerNotifications());
		Assert.assertEquals(
			"test1", mailSettingCompanyConfiguration.popUserName());
		Assert.assertEquals(
			"test1", mailSettingCompanyConfiguration.incomingPOPServer());
	}

	@Test
	public void testUpgradeMailSettingCompanyConfigurationWithSystemId()
		throws Exception {

		_portalPreferences2.setValue(
			"", "mail.session.mail.pop3.user", "test2");
		_portalPreferences2.setValue(
			"", "mail.session.mail.pop3.host", "test2");
		_portalPreferences2.setValue(
			"", "mail.session.mail.smtp.starttls.enable", "false");

		PortalPreferencesLocalServiceUtil.updatePreferences(
			0L, PortletKeys.PREFS_OWNER_TYPE_COMPANY,
			PortletPreferencesFactoryUtil.toXML(_portalPreferences2));

		_upgradeProcess.upgrade();

		MailSettingCompanyConfiguration mailSettingCompanyConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingCompanyConfiguration.class,
				TestPropsValues.getCompanyId());

		Assert.assertFalse(
			mailSettingCompanyConfiguration.enablePOPServerNotifications());
		Assert.assertEquals(
			"test2", mailSettingCompanyConfiguration.popUserName());
		Assert.assertEquals(
			"test2", mailSettingCompanyConfiguration.incomingPOPServer());
	}

	private static String _originalPortalPreferencesXML;

	@Inject
	private ConfigurationProvider _configurationProvider;

	private PortalPreferences _portalPreferences1;
	private PortalPreferences _portalPreferences2;
	private UpgradeProcess _upgradeProcess;

	@Inject(
		filter = "(&(component.name=com.liferay.mail.settings.internal.upgrade.registry.MailSettingUpgradeStepRegistrator))"
	)
	private UpgradeStepRegistrator _upgradeStepRegistrator;

}