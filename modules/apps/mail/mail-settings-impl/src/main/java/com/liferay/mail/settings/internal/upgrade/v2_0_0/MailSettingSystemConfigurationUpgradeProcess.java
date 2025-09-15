/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.settings.internal.upgrade.v2_0_0;

import com.liferay.mail.settings.configuration.MailSettingSystemConfiguration;
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
			new KeyValuePair("mail.batch.size", "mailBatchSize"),
			new KeyValuePair("mail.send.blacklist", "mailSendBlacklist"),
			new KeyValuePair("mail.audit.trail", "mailAuditTrail"),
			new KeyValuePair(
				"mail.throws.exception.on.failure",
				"mailThrowsExceptionOnFailure"),
			new KeyValuePair("mail.session.jndi.name", "mailSessionJndiName"),
			new KeyValuePair("pop.server.subdomain", "popServerSubdomain"));
	}

	private final PrefsPropsToConfigurationUpgradeHelper
		_prefsPropsToConfigurationUpgradeHelper;

}