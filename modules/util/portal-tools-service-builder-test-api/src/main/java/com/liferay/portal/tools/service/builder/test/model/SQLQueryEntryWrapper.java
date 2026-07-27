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
 * This class is a wrapper for {@link SQLQueryEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SQLQueryEntry
 * @generated
 */
public class SQLQueryEntryWrapper
	extends BaseModelWrapper<SQLQueryEntry>
	implements ModelWrapper<SQLQueryEntry>, SQLQueryEntry {

	public SQLQueryEntryWrapper(SQLQueryEntry sqlQueryEntry) {
		super(sqlQueryEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("sqlQueryEntryId", getSqlQueryEntryId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long sqlQueryEntryId = (Long)attributes.get("sqlQueryEntryId");

		if (sqlQueryEntryId != null) {
			setSqlQueryEntryId(sqlQueryEntryId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@Override
	public SQLQueryEntry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the primary key of this sql query entry.
	 *
	 * @return the primary key of this sql query entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the sql query entry ID of this sql query entry.
	 *
	 * @return the sql query entry ID of this sql query entry
	 */
	@Override
	public long getSqlQueryEntryId() {
		return model.getSqlQueryEntryId();
	}

	/**
	 * Returns the user ID of this sql query entry.
	 *
	 * @return the user ID of this sql query entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this sql query entry.
	 *
	 * @return the user uuid of this sql query entry
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Sets the primary key of this sql query entry.
	 *
	 * @param primaryKey the primary key of this sql query entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the sql query entry ID of this sql query entry.
	 *
	 * @param sqlQueryEntryId the sql query entry ID of this sql query entry
	 */
	@Override
	public void setSqlQueryEntryId(long sqlQueryEntryId) {
		model.setSqlQueryEntryId(sqlQueryEntryId);
	}

	/**
	 * Sets the user ID of this sql query entry.
	 *
	 * @param userId the user ID of this sql query entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this sql query entry.
	 *
	 * @param userUuid the user uuid of this sql query entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected SQLQueryEntryWrapper wrap(SQLQueryEntry sqlQueryEntry) {
		return new SQLQueryEntryWrapper(sqlQueryEntry);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:1299942582