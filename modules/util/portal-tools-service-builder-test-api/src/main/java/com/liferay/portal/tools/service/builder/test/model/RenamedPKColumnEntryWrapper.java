/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RenamedPKColumnEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RenamedPKColumnEntry
 * @generated
 */
public class RenamedPKColumnEntryWrapper
	extends BaseModelWrapper<RenamedPKColumnEntry>
	implements ModelWrapper<RenamedPKColumnEntry>, RenamedPKColumnEntry {

	public RenamedPKColumnEntryWrapper(
		RenamedPKColumnEntry renamedPKColumnEntry) {

		super(renamedPKColumnEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("renamedPKColumnEntryId", getRenamedPKColumnEntryId());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long renamedPKColumnEntryId = (Long)attributes.get(
			"renamedPKColumnEntryId");

		if (renamedPKColumnEntryId != null) {
			setRenamedPKColumnEntryId(renamedPKColumnEntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public RenamedPKColumnEntry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the name of this renamed pk column entry.
	 *
	 * @return the name of this renamed pk column entry
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this renamed pk column entry.
	 *
	 * @return the primary key of this renamed pk column entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the renamed pk column entry ID of this renamed pk column entry.
	 *
	 * @return the renamed pk column entry ID of this renamed pk column entry
	 */
	@Override
	public long getRenamedPKColumnEntryId() {
		return model.getRenamedPKColumnEntryId();
	}

	/**
	 * Sets the name of this renamed pk column entry.
	 *
	 * @param name the name of this renamed pk column entry
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this renamed pk column entry.
	 *
	 * @param primaryKey the primary key of this renamed pk column entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the renamed pk column entry ID of this renamed pk column entry.
	 *
	 * @param renamedPKColumnEntryId the renamed pk column entry ID of this renamed pk column entry
	 */
	@Override
	public void setRenamedPKColumnEntryId(long renamedPKColumnEntryId) {
		model.setRenamedPKColumnEntryId(renamedPKColumnEntryId);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected RenamedPKColumnEntryWrapper wrap(
		RenamedPKColumnEntry renamedPKColumnEntry) {

		return new RenamedPKColumnEntryWrapper(renamedPKColumnEntry);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-1196766363