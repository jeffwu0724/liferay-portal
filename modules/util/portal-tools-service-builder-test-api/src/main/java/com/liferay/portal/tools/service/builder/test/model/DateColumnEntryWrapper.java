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
 * This class is a wrapper for {@link DateColumnEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DateColumnEntry
 * @generated
 */
public class DateColumnEntryWrapper
	extends BaseModelWrapper<DateColumnEntry>
	implements DateColumnEntry, ModelWrapper<DateColumnEntry> {

	public DateColumnEntryWrapper(DateColumnEntry dateColumnEntry) {
		super(dateColumnEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("dateColumnEntryId", getDateColumnEntryId());
		attributes.put("dateValue", getDateValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long dateColumnEntryId = (Long)attributes.get("dateColumnEntryId");

		if (dateColumnEntryId != null) {
			setDateColumnEntryId(dateColumnEntryId);
		}

		Date dateValue = (Date)attributes.get("dateValue");

		if (dateValue != null) {
			setDateValue(dateValue);
		}
	}

	@Override
	public DateColumnEntry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the date column entry ID of this date column entry.
	 *
	 * @return the date column entry ID of this date column entry
	 */
	@Override
	public long getDateColumnEntryId() {
		return model.getDateColumnEntryId();
	}

	/**
	 * Returns the date value of this date column entry.
	 *
	 * @return the date value of this date column entry
	 */
	@Override
	public Date getDateValue() {
		return model.getDateValue();
	}

	/**
	 * Returns the primary key of this date column entry.
	 *
	 * @return the primary key of this date column entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Sets the date column entry ID of this date column entry.
	 *
	 * @param dateColumnEntryId the date column entry ID of this date column entry
	 */
	@Override
	public void setDateColumnEntryId(long dateColumnEntryId) {
		model.setDateColumnEntryId(dateColumnEntryId);
	}

	/**
	 * Sets the date value of this date column entry.
	 *
	 * @param dateValue the date value of this date column entry
	 */
	@Override
	public void setDateValue(Date dateValue) {
		model.setDateValue(dateValue);
	}

	/**
	 * Sets the primary key of this date column entry.
	 *
	 * @param primaryKey the primary key of this date column entry
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
	protected DateColumnEntryWrapper wrap(DateColumnEntry dateColumnEntry) {
		return new DateColumnEntryWrapper(dateColumnEntry);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:1039310523