/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.kernel.service;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;

/**
 * @author Jiefeng Wu
 */
public interface MailSettingConfigurationProvider {

	public String getAdditionalJavaMailProperties(long companyId)
		throws ConfigurationException;

	public boolean getEnablePOPServerNotifications(long companyId)
		throws ConfigurationException;

	public boolean getEnableStartTLS(long companyId)
		throws ConfigurationException;

	public String getIncomingPOPPort(long companyId)
		throws ConfigurationException;

	public String getIncomingPOPServer(long companyId)
		throws ConfigurationException;

	public String getOutgoingSMTPPort(long companyId)
		throws ConfigurationException;

	public String getOutgoingSMTPServer(long companyId)
		throws ConfigurationException;

	public String getPOPPassword(long companyId) throws ConfigurationException;

	public String getPOPUserName(long companyId) throws ConfigurationException;

	public String getSMTPPassword(long companyId) throws ConfigurationException;

	public String getSMTPUserName(long companyId) throws ConfigurationException;

	public boolean getUseASecureNetworkConnectionForPOP(long companyId)
		throws ConfigurationException;

	public boolean getUseASecureNetworkConnectionForSMTP(long companyId)
		throws ConfigurationException;

}