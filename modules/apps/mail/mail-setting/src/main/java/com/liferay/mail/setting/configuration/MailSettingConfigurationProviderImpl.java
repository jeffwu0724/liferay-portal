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

		return _getMailSettingConfiguration(
			companyId
		).additionalJavaMailProperties();
	}

	@Override
	public boolean getEnablePOPServerNotifications(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).enablePOPServerNotifications();
	}

	@Override
	public boolean getEnableStartTLS(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).enableStartTLS();
	}

	@Override
	public String getIncomingPOPPort(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).incomingPOPPort();
	}

	@Override
	public String getIncomingPOPServer(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).incomingPOPServer();
	}

	@Override
	public String getOutgoingSMTPPort(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).outgoingSMTPPort();
	}

	@Override
	public String getOutgoingSMTPServer(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).outgoingSMTPServer();
	}

	@Override
	public String getPOPPassword(long companyId) throws ConfigurationException {
		return _getMailSettingConfiguration(
			companyId
		).popPassword();
	}

	@Override
	public String getPOPUserName(long companyId) throws ConfigurationException {
		return _getMailSettingConfiguration(
			companyId
		).popUserName();
	}

	@Override
	public String getSMTPPassword(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).smtpPassword();
	}

	@Override
	public String getSMTPUserName(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).smtpUserName();
	}

	@Override
	public boolean getUseASecureNetworkConnectionForPOP(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).useASecureNetworkConnectionForPOP();
	}

	@Override
	public boolean getUseASecureNetworkConnectionForSMTP(long companyId)
		throws ConfigurationException {

		return _getMailSettingConfiguration(
			companyId
		).useASecureNetworkConnectionForSMTP();
	}

	private MailSettingConfiguration _getMailSettingConfiguration(
			long companyId)
		throws ConfigurationException {

		return _configurationProvider.getCompanyConfiguration(
			MailSettingConfiguration.class, companyId);
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

}