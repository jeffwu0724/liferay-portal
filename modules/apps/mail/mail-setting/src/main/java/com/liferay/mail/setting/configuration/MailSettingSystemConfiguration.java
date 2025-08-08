/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.setting.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Jiefeng Wu
 */
@ExtendedObjectClassDefinition(
	category = "email", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.mail.setting.configuration.MailSettingSystemConfiguration",
	localization = "content/Language",
	name = "mail-settings-system-configuration-name"
)
public interface MailSettingSystemConfiguration {
	//	Set the batch size for outbound emails. Set this property to 0 to submit to the mail server a request to send an email with multiple recipients all at once. Set a value greater than 0 to split up the list of recipients by the batch size so that multiple requests are submitted to the mail server to ensure that the mail server's recipient size limit is not reached.
	@Meta.AD(deflt = "0", name = "mail-batch-size", required = false)
	public String mailBatchSize();

	//	Input a list of comma delimited email addresses that will be blacklisted when sending emails. The specified email addresses will be ignored and a warning will be logged.
	@Meta.AD(
		deflt = "noreply@liferay.com|test@liferay.com|noreply@domain.invalid|test@domain.invalid",
		name = "mail-send-blacklist", required = false
	)
	public String[] mailSendBlacklist();

	@Meta.AD(deflt = "false", name = "mail-session-mail", required = false)
	public boolean mailSessionMail();

	@Meta.AD(
		deflt = "false", name = "mail-session-mail-smtp-auth", required = false
	)
	public boolean mailSessionmailsmtpauth();

	@Meta.AD(
		deflt = "pop3", name = "mail-session-mail-store-protocol",
		required = false
	)
	public String mailSessionMailStoreProtocol();

	@Meta.AD(
		deflt = "smtp", name = "mail-session-mail-transport-protocol",
		required = false
	)
	public String mailSessionMailTransportProtocol();

	// Set this to false if an administrator should not be allowed to change the mail domain via the Admin portlet.

	@Meta.AD(deflt = "true", name = "mail-mx-update", required = false)
	public boolean mailMxUpdate();

	// Input a list of comma delimited email addresses that will receive a BCC of every email sent through the mail server.

	@Meta.AD(deflt = "", name = "mail-audit-trail", required = false)
	public String mailAuditTrail();

	//	Set this to true to throw an exception when com.liferay.mail.messaging.internal.MailEngine fails to send an email.
	@Meta.AD(
		deflt = "false", name = "mail-throws-exception-on-failure",
		required = false
	)
	public boolean mailThrowsExceptionOnFailure();

	@Meta.AD(deflt = "events", name = "pop-server-subdomain", required = false)
	public String popServerSubdomain();

	/*
	Set the JNDI name to lookup the Java Mail session. If none is set, then
	the portal will attempt to create the Java Mail session based on the
	properties prefixed with "mail.session.".
	Env: LIFERAY_MAIL_PERIOD_SESSION_PERIOD_JNDI_PERIOD_NAME
	#mail.session.jndi.name=mail/MailSession
		*/

}