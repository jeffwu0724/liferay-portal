/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.settings.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Jiefeng Wu
 */
@ExtendedObjectClassDefinition(
	category = "email", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.mail.settings.configuration.MailSettingSystemConfiguration",
	localization = "content/Language",
	name = "mail-settings-system-configuration-name"
)
public interface MailSettingSystemConfiguration {

	@Meta.AD(deflt = "0", name = "mail-batch-size", required = false)
	public String mailBatchSize();

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
	public boolean mailSessionMailSMTPAuth();

	@Meta.AD(deflt = "", name = "mail-audit-trail", required = false)
	public String mailAuditTrail();

	@Meta.AD(
		deflt = "false", name = "mail-throws-exception-on-failure",
		required = false
	)
	public boolean mailThrowsExceptionOnFailure();

	@Meta.AD(deflt = "events", name = "pop-server-subdomain", required = false)
	public String popServerSubdomain();

}