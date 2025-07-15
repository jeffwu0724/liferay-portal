/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mail.setting.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedAttributeDefinition;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Jiefeng Wu
 */
@ExtendedObjectClassDefinition(
	category = "email", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.mail.setting.internal.configuration.MailSettingConfiguration",
	localization = "content/Language", name = "mail setting"
)
public interface MailSettingConfiguration {

	@ExtendedAttributeDefinition(requiredInput = true)
	@Meta.AD(
		deflt = "false",
		description = "mail-setting-enable-pop-server-notifications-descriptions",
		name = "mail-setting-enable-pop-server-notifications", required = false
	)
	public boolean enablePOPServerNotifications();

	@Meta.AD(
		deflt = "localhost", description = "incoming-pop-server-descriptions",
		name = "incoming-pop-server", required = false
	)
	public String incomingPOPServer();

	@Meta.AD(
		deflt = "25", description = "incoming-pop-port-descriptions",
		name = "incoming-pop-port", required = false
	)
	public String incomingPOPPort();

	@ExtendedAttributeDefinition(requiredInput = true)
	@Meta.AD(
		deflt = "false",
		description = "use-a-secure-network-connection-pop-descriptions",
		name = "use-a-secure-network-connection-pop", required = false
	)
	public boolean useASecureNetworkConnectionForPOP();

	@Meta.AD(
		description = "pop-userName-descriptions", name = "pop-userName",
		required = false
	)
	public String popUserName();

	@Meta.AD(
		deflt = "true", description = "pop-password-descriptions",
		name = "pop-password", required = false
	)
	public String popPassword();

	@Meta.AD(
		deflt = "localhost", description = "outgoing-smtp-server-descriptions",
		name = "outgoing-smtp-server", required = false
	)
	public String outgoingSMTPServer();

	@Meta.AD(
		deflt = "25", description = "outgoing-smtp-port-descriptions",
		name = "outgoing-smtp-port", required = false
	)
	public String outgoingSMTPPort();

	@ExtendedAttributeDefinition(requiredInput = true)
	@Meta.AD(
		deflt = "false",
		description = "use-a-secure-network-connection-smtp-descriptions",
		name = "use-a-secure-network-connection-smtp", required = false
	)
	public boolean useASecureNetworkConnectionForSMTP();

	@ExtendedAttributeDefinition(requiredInput = true)
	@Meta.AD(
		deflt = "false", description = "enable-startTLS-descriptions",
		name = "enable-startTLS", required = false
	)
	public boolean enableStartTLS();

	@Meta.AD(
		description = "smtp-userName-descriptions", name = "smtp-userName",
		required = false
	)
	public String smtpUserName();

	@Meta.AD(
		deflt = "true", description = "smtp-password-descriptions",
		name = "smtp-password", required = false
	)
	public String smtpPassword();

	@Meta.AD(
		description = "additional-javaMail-properties-descriptions",
		name = "additional-javaMail-properties", required = false
	)
	public String additionalJavaMailProperties();

}