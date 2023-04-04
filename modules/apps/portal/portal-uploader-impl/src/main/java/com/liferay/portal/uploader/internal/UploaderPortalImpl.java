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

package com.liferay.portal.uploader.internal;

import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadServletRequest;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.uploader.UploaderPortal;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuyang Zhou
 */
@Component(service = UploaderPortal.class)
public class UploaderPortalImpl implements UploaderPortal {

	@Activate
	public void activate() {
		System.out.println("Started UploadPortalImpl");
	}

	@Override
	public UploadPortletRequest getUploadPortletRequest(
		PortletRequest portletRequest) {
		return _portal.getUploadPortletRequest(portletRequest);
	}

	@Override
	public UploadServletRequest getUploadServletRequest(
		HttpServletRequest httpServletRequest) {
		return _portal.getUploadServletRequest(httpServletRequest);
	}

	@Override
	public UploadServletRequest getUploadServletRequest(
		HttpServletRequest httpServletRequest, int fileSizeThreshold,
		String location, long maxRequestSize, long maxFileSize) {
		return _portal.getUploadServletRequest(httpServletRequest, fileSizeThreshold, location, maxRequestSize, maxFileSize);
	}

	@Reference
	private Portal _portal;

}