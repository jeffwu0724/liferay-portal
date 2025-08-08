/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.kernel.service;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.service.Snapshot;

/**
 * @author Jiefeng Wu
 */
public class MailSettingConfigurationProviderUtil {

	public static String getAdditionalJavaMailProperties(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.
				getAdditionalJavaMailProperties(companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return StringPool.BLANK;
	}

	public static boolean getEnablePOPServerNotifications(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.
				getEnablePOPServerNotifications(companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return false;
	}

	public static boolean getEnableStartTLS(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.getEnableStartTLS(
				companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return false;
	}

	public static String getIncomingPOPPort(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.getIncomingPOPPort(
				companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return StringPool.BLANK;
	}

	public static String getIncomingPOPServer(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.getIncomingPOPServer(
				companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return StringPool.BLANK;
	}

	public static String getOutgoingSMTPPort(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.getOutgoingSMTPPort(
				companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return StringPool.BLANK;
	}

	public static String getOutgoingSMTPServer(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.getOutgoingSMTPServer(
				companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return StringPool.BLANK;
	}

	public static String getPOPPassword(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.getPOPUserName(companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return StringPool.BLANK;
	}

	public static String getPOPUserName(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.getPOPUserName(companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return StringPool.BLANK;
	}

	public static String getSMTPPassword(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.getSMTPUserName(companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return StringPool.BLANK;
	}

	public static String getSMTPUserName(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.getSMTPUserName(companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return StringPool.BLANK;
	}

	public static boolean getUseASecureNetworkConnectionForPOP(long companyId) {
		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.
				getUseASecureNetworkConnectionForPOP(companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return false;
	}

	public static boolean getUseASecureNetworkConnectionForSMTP(
		long companyId) {

		try {
			MailSettingConfigurationProvider mailSettingConfigurationProvider =
				_mailSettingConfigurationProviderSnapshot.get();

			return mailSettingConfigurationProvider.
				getUseASecureNetworkConnectionForSMTP(companyId);
		}
		catch (ConfigurationException configurationException) {
			_log.error(
				"Unable to get mail setting configuration for company " +
					companyId,
				configurationException);
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MailSettingConfigurationProviderUtil.class);

	private static final Snapshot<MailSettingConfigurationProvider>
		_mailSettingConfigurationProviderSnapshot = new Snapshot<>(
			MailSettingConfigurationProviderUtil.class,
			MailSettingConfigurationProvider.class);

}