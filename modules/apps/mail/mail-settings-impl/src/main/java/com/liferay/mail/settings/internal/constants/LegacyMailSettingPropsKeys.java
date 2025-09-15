/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.settings.internal.constants;

/**
 * @author Jiefeng Wu
 */
public class LegacyMailSettingPropsKeys {

	public static final String MAIL_AUDIT_TRAIL = "mail.audit.trail";

	public static final String MAIL_BATCH_SIZE = "mail.batch.size";

	public static final String MAIL_SEND_BLACKLIST = "mail.send.blacklist";

	public static final String MAIL_SESSION_JNDI_NAME =
		"mail.session.jndi.name";

	public static final String MAIL_SESSION_MAIL = "mail.session.mail";

	public static final String MAIL_SESSION_MAIL_ADVANCED_PROPERTIES =
		"mail.session.mail.advanced.properties";

	public static final String MAIL_SESSION_MAIL_POP3_HOST =
		"mail.session.mail.pop3.host";

	public static final String MAIL_SESSION_MAIL_POP3_PASSWORD =
		"mail.session.mail.pop3.password";

	public static final String MAIL_SESSION_MAIL_POP3_PORT =
		"mail.session.mail.pop3.port";

	public static final String MAIL_SESSION_MAIL_POP3_USER =
		"mail.session.mail.pop3.user";

	public static final String MAIL_SESSION_MAIL_SMTP_AUTH =
		"mail.session.mail.smtp.auth";

	public static final String MAIL_SESSION_MAIL_SMTP_HOST =
		"mail.session.mail.smtp.host";

	public static final String MAIL_SESSION_MAIL_SMTP_PASSWORD =
		"mail.session.mail.smtp.password";

	public static final String MAIL_SESSION_MAIL_SMTP_PORT =
		"mail.session.mail.smtp.port";

	public static final String MAIL_SESSION_MAIL_SMTP_STARTTLS_ENABLE =
		"mail.session.mail.smtp.starttls.enable";

	public static final String MAIL_SESSION_MAIL_SMTP_USER =
		"mail.session.mail.smtp.user";

	public static final String MAIL_SESSION_MAIL_STORE_PROTOCOL =
		"mail.session.mail.store.protocol";

	public static final String MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL =
		"mail.session.mail.transport.protocol";

	public static final String[] MAIL_SETTING_KEYS = {
		MAIL_BATCH_SIZE, MAIL_AUDIT_TRAIL, MAIL_SEND_BLACKLIST,
		MAIL_SESSION_MAIL, MAIL_SESSION_MAIL_ADVANCED_PROPERTIES,
		MAIL_SESSION_MAIL_POP3_HOST, MAIL_SESSION_MAIL_POP3_PASSWORD,
		MAIL_SESSION_MAIL_POP3_PORT, MAIL_SESSION_MAIL_POP3_USER,
		MAIL_SESSION_MAIL_SMTP_HOST, MAIL_SESSION_MAIL_SMTP_PASSWORD,
		MAIL_SESSION_MAIL_SMTP_PORT, MAIL_SESSION_MAIL_SMTP_STARTTLS_ENABLE,
		MAIL_SESSION_MAIL_SMTP_USER, MAIL_SESSION_MAIL_STORE_PROTOCOL,
		MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL,
		LegacyMailSettingPropsKeys.MAIL_THROWS_EXCEPTION_ON_FAILURE,
		LegacyMailSettingPropsKeys.POP_SERVER_NOTIFICATIONS_ENABLED,
		LegacyMailSettingPropsKeys.POP_SERVER_SUBDOMAIN
	};

	public static final String MAIL_THROWS_EXCEPTION_ON_FAILURE =
		"mail.throws.exception.on.failure";

	public static final String POP_SERVER_NOTIFICATIONS_ENABLED =
		"pop.server.notifications.enabled";

	public static final String POP_SERVER_SUBDOMAIN = "pop.server.subdomain";

}