/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.settings.internal.upgrade.v2_0_0;

import com.liferay.mail.settings.configuration.MailSettingSystemConfiguration;
import com.liferay.mail.settings.internal.constants.LegacyMailSettingPropsKeys;
import com.liferay.portal.configuration.upgrade.PrefsPropsToConfigurationUpgradeHelper;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.KeyValuePair;

/**
 * @author Jiefeng Wu
 */
public class MailSettingSystemConfigurationUpgradeProcess
	extends UpgradeProcess {

	public MailSettingSystemConfigurationUpgradeProcess(
		PrefsPropsToConfigurationUpgradeHelper
			prefsPropsToConfigurationUpgradeHelper) {

		_prefsPropsToConfigurationUpgradeHelper =
			prefsPropsToConfigurationUpgradeHelper;
	}

	@Override
	protected void doUpgrade() throws Exception {
		_prefsPropsToConfigurationUpgradeHelper.mapConfigurations(
			MailSettingSystemConfiguration.class,
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_BATCH_SIZE, "mailBatchSize"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SEND_BLACKLIST,
				"mailSendBlacklist"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL,
				"mailSessionMail"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_SESSION_MAIL_SMTP_AUTH,
				"mailSessionMailSMTPAuth"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_AUDIT_TRAIL, "mailAuditTrail"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.MAIL_THROWS_EXCEPTION_ON_FAILURE,
				"mailThrowsExceptionOnFailure"),
			new KeyValuePair(
				LegacyMailSettingPropsKeys.POP_SERVER_SUBDOMAIN,
				"popServerSubdomain"));
	}

	private final PrefsPropsToConfigurationUpgradeHelper
		_prefsPropsToConfigurationUpgradeHelper;

}