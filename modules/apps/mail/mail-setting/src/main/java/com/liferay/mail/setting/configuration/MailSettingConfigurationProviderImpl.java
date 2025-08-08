/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.setting.configuration;

import com.liferay.mail.kernel.service.MailSettingConfigurationProvider;
import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jiefeng Wu
 */
@Component(
	configurationPid = "com.liferay.mail.setting.configuration.MailSettingConfiguration",
	service = MailSettingConfigurationProvider.class
)
public class MailSettingConfigurationProviderImpl
	implements MailSettingConfigurationProvider {

	@Override
	public String getAdditionalJavaMailProperties(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.additionalJavaMailProperties();
	}

	@Override
	public boolean getEnablePOPServerNotifications(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.enablePOPServerNotifications();
	}

	@Override
	public boolean getEnableStartTLS(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.enableStartTLS();
	}

	@Override
	public String getIncomingPOPPort(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.incomingPOPPort();
	}

	@Override
	public String getIncomingPOPServer(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.incomingPOPServer();
	}

	@Override
	public String getOutgoingSMTPPort(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.outgoingSMTPPort();
	}

	@Override
	public String getOutgoingSMTPServer(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.outgoingSMTPServer();
	}

	@Override
	public String getPOPPassword(long companyId) throws ConfigurationException {
		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.popPassword();
	}

	@Override
	public String getPOPUserName(long companyId) throws ConfigurationException {
		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.popUserName();
	}

	@Override
	public String getSMTPPassword(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.smtpPassword();
	}

	@Override
	public String getSMTPUserName(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.smtpUserName();
	}

	@Override
	public boolean getUseASecureNetworkConnectionForPOP(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.useASecureNetworkConnectionForPOP();
	}

	@Override
	public boolean getUseASecureNetworkConnectionForSMTP(long companyId)
		throws ConfigurationException {

		MailSettingConfiguration mailSettingConfiguration =
			_configurationProvider.getCompanyConfiguration(
				MailSettingConfiguration.class, companyId);

		return mailSettingConfiguration.useASecureNetworkConnectionForSMTP();
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

}