/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.settings.internal.upgrade.v1_0_0;

import com.liferay.mail.settings.configuration.MailSettingCompanyConfiguration;
import com.liferay.mail.settings.internal.constants.LegacyMailSettingPropsKeys;
import com.liferay.portal.configuration.upgrade.PrefsPropsToConfigurationUpgradeHelper;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.KeyValuePair;

/**
 * @author Jiefeng Wu
 */
public class MailSettingCompanyConfigurationUpgradeProcess
	extends UpgradeProcess {

	public MailSettingCompanyConfigurationUpgradeProcess(
		PrefsPropsToConfigurationUpgradeHelper
			prefsPropsToConfigurationUpgradeHelper) {

		_prefsPropsToConfigurationUpgradeHelper =
			prefsPropsToConfigurationUpgradeHelper;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_prefsPropsToConfigurationUpgradeHelper.mapConfigurations(
			MailSettingCompanyConfiguration.class,
			new KeyValuePair(
				LegacyMailSettingPropsKeys.POP_SERVER_NOTIFICATIONS_ENABLED,
				"enablePOPServerNotifications"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_POP3_HOST,
				"incomingPOPServer"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_POP3_PORT,
				"incomingPOPPort"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_STORE_PROTOCOL,
				"storeProtocol"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL,
				"transportProtocol"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_POP3_USER,
				"popUserName"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_POP3_PASSWORD,
				"popPassword"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_SMTP_HOST,
				"outgoingSMTPServer"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_SMTP_PORT,
				"outgoingSMTPPort"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.
					MAIL_SESSION_MAIL_SMTP_STARTTLS_ENABLE,
				"enableStartTLS"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_SMTP_USER,
				"smtpUserName"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_SMTP_PASSWORD,
				"smtpPassword"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.
					MAIL_SESSION_MAIL_ADVANCED_PROPERTIES,
				"additionalJavaMailProperties"));

		CompanyLocalServiceUtil.forEachCompanyId(
			companyId ->
				_prefsPropsToConfigurationUpgradeHelper.
					mapConfigurationsWithCompanyId(
						companyId, MailSettingCompanyConfiguration.class,
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								POP_SERVER_NOTIFICATIONS_ENABLED,
							"enablePOPServerNotifications"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_POP3_HOST,
							"incomingPOPServer"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_POP3_PORT,
							"incomingPOPPort"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_STORE_PROTOCOL,
							"storeProtocol"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL,
							"transportProtocol"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_POP3_USER,
							"popUserName"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_POP3_PASSWORD,
							"popPassword"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_SMTP_HOST,
							"outgoingSMTPServer"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_SMTP_PORT,
							"outgoingSMTPPort"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_SMTP_STARTTLS_ENABLE,
							"enableStartTLS"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_SMTP_USER,
							"smtpUserName"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_SMTP_PASSWORD,
							"smtpPassword"),
						new KeyValuePair(
							LegacyMailSettingPropsKeys.
								MAIL_SESSION_MAIL_ADVANCED_PROPERTIES,
							"additionalJavaMailProperties")));
	}

	private final PrefsPropsToConfigurationUpgradeHelper
		_prefsPropsToConfigurationUpgradeHelper;

}