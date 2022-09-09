/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.lpkg.deployer.internal;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Jiefeng Wu
 */
public class LPKGNeededProps {

	public static final int MODULE_FRAMEWORK_DYNAMIC_INSTALL_START_LEVEL =
		GetterUtil.getInteger(
			PropsUtil.get(
				PropsKeys.MODULE_FRAMEWORK_DYNAMIC_INSTALL_START_LEVEL));

	public static final String MODULE_FRAMEWORK_MARKETPLACE_DIR = PropsUtil.get(
		PropsKeys.MODULE_FRAMEWORK_MARKETPLACE_DIR);

	public static final String MODULE_FRAMEWORK_WAR_DIR = PropsUtil.get(
		PropsKeys.MODULE_FRAMEWORK_WAR_DIR);

	public static final int MODULE_FRAMEWORK_WEB_START_LEVEL =
		GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.MODULE_FRAMEWORK_WEB_START_LEVEL));

}