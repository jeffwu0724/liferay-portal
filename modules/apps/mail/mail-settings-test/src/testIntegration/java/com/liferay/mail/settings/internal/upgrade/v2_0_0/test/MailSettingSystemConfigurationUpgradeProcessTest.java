/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.settings.internal.upgrade.v2_0_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.mail.settings.configuration.MailSettingSystemConfiguration;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PrefsProps;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.test.util.UpgradeTestUtil;

import jakarta.portlet.PortletPreferences;

import java.util.Dictionary;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Jiefeng Wu
 */
@RunWith(Arquillian.class)
public class MailSettingSystemConfigurationUpgradeProcessTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_storedMailSettingSystemConfiguration =
			_configurationAdmin.getConfiguration(
				"com.liferay.mail.settings.configuration." +
					"MailSettingSystemConfiguration",
				StringPool.QUESTION);

		_storedProperties =
			_storedMailSettingSystemConfiguration.getProperties();

		_configurationProvider.deleteSystemConfiguration(
			MailSettingSystemConfiguration.class);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		_configurationProvider.saveSystemConfiguration(
			MailSettingSystemConfiguration.class, _storedProperties);
	}

	@Before
	public void setUp() throws Exception {
		_upgradeProcess = UpgradeTestUtil.getUpgradeStep(
			_upgradeStepRegistrator,
			"com.liferay.mail.settings.internal.upgrade.v2_0_0." +
				"MailSettingSystemConfigurationUpgradeProcess");

		_testSystemIdPortletPreferences = _prefsProps.getPreferences();
	}

	@After
	public void tearDown() throws Exception {
		_configurationProvider.deleteSystemConfiguration(
			MailSettingSystemConfiguration.class);
	}

	@Test
	public void testUpgradeMailSettingSystemConfiguration() throws Exception {
		String stringValue = RandomTestUtil.randomString();
		String intValue = "111";
		String[] stringArrayValue = {"test1", "test2"};

		_populatePreferences(
			_testSystemIdPortletPreferences, stringValue, intValue,
			stringArrayValue);

		_upgradeProcess.upgrade();

		Thread.sleep(2000);

		MailSettingSystemConfiguration mailSettingSystemConfiguration =
			_configurationProvider.getSystemConfiguration(
				MailSettingSystemConfiguration.class);

		_assertConfiguration(
			stringValue, intValue, stringArrayValue,
			mailSettingSystemConfiguration);
	}

	private void _assertConfiguration(
		String stringValue, String intValue, String[] stringArrayValue,
		MailSettingSystemConfiguration mailSettingSystemConfiguration) {

		Assert.assertEquals(
			intValue, mailSettingSystemConfiguration.mailBatchSize());
		Assert.assertArrayEquals(
			stringArrayValue,
			mailSettingSystemConfiguration.mailSendBlacklist());

		Assert.assertEquals(
			stringValue, mailSettingSystemConfiguration.mailAuditTrail());
		Assert.assertTrue(
			stringValue,
			mailSettingSystemConfiguration.mailThrowsExceptionOnFailure());
		Assert.assertEquals(
			stringValue, mailSettingSystemConfiguration.mailSessionJndiName());

		Assert.assertEquals(
			stringValue, mailSettingSystemConfiguration.popServerSubdomain());
	}

	private void _populatePreferences(
			PortletPreferences portletPreferences, String stringValue,
			String intValue, String[] stringArrayValue)
		throws Exception {

		portletPreferences.setValue("mail.audit.trail", stringValue);
		portletPreferences.setValue("mail.session.jndi.name", stringValue);
		portletPreferences.setValue("mail.batch.size", intValue);
		portletPreferences.setValue(
			"mail.send.blacklist",
			StringUtil.merge(stringArrayValue, StringPool.COMMA));

		portletPreferences.setValue("mail.throws.exception.on.failure", "true");
		portletPreferences.setValue("pop.server.subdomain", stringValue);

		portletPreferences.store();
	}

	@Inject
	private static ConfigurationAdmin _configurationAdmin;

	@Inject
	private static ConfigurationProvider _configurationProvider;

	private static Configuration _storedMailSettingSystemConfiguration;
	private static Dictionary<String, Object> _storedProperties;

	@Inject
	private PrefsProps _prefsProps;

	private PortletPreferences _testSystemIdPortletPreferences;
	private UpgradeProcess _upgradeProcess;

	@Inject(
		filter = "(&(component.name=com.liferay.mail.settings.internal.upgrade.registry.MailSettingUpgradeStepRegistrator))"
	)
	private UpgradeStepRegistrator _upgradeStepRegistrator;

}