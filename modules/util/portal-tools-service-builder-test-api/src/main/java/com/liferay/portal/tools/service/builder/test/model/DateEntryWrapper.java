/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DateEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DateEntry
 * @generated
 */
public class DateEntryWrapper
	extends BaseModelWrapper<DateEntry>
	implements DateEntry, ModelWrapper<DateEntry> {

	public DateEntryWrapper(DateEntry dateEntry) {
		super(dateEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("dateEntryId", getDateEntryId());
		attributes.put("dateValue", getDateValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long dateEntryId = (Long)attributes.get("dateEntryId");

		if (dateEntryId != null) {
			setDateEntryId(dateEntryId);
		}

		Date dateValue = (Date)attributes.get("dateValue");

		if (dateValue != null) {
			setDateValue(dateValue);
		}
	}

	@Override
	public DateEntry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the date entry ID of this date entry.
	 *
	 * @return the date entry ID of this date entry
	 */
	@Override
	public long getDateEntryId() {
		return model.getDateEntryId();
	}

	/**
	 * Returns the date value of this date entry.
	 *
	 * @return the date value of this date entry
	 */
	@Override
	public Date getDateValue() {
		return model.getDateValue();
	}

	/**
	 * Returns the primary key of this date entry.
	 *
	 * @return the primary key of this date entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Sets the date entry ID of this date entry.
	 *
	 * @param dateEntryId the date entry ID of this date entry
	 */
	@Override
	public void setDateEntryId(long dateEntryId) {
		model.setDateEntryId(dateEntryId);
	}

	/**
	 * Sets the date value of this date entry.
	 *
	 * @param dateValue the date value of this date entry
	 */
	@Override
	public void setDateValue(Date dateValue) {
		model.setDateValue(dateValue);
	}

	/**
	 * Sets the primary key of this date entry.
	 *
	 * @param primaryKey the primary key of this date entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected DateEntryWrapper wrap(DateEntry dateEntry) {
		return new DateEntryWrapper(dateEntry);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-1811119977