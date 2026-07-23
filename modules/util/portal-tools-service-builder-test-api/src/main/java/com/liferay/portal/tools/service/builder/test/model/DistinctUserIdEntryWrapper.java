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
 * This class is a wrapper for {@link DistinctUserIdEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DistinctUserIdEntry
 * @generated
 */
public class DistinctUserIdEntryWrapper
	extends BaseModelWrapper<DistinctUserIdEntry>
	implements DistinctUserIdEntry, ModelWrapper<DistinctUserIdEntry> {

	public DistinctUserIdEntryWrapper(DistinctUserIdEntry distinctUserIdEntry) {
		super(distinctUserIdEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("distinctUserIdEntryId", getDistinctUserIdEntryId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long distinctUserIdEntryId = (Long)attributes.get(
			"distinctUserIdEntryId");

		if (distinctUserIdEntryId != null) {
			setDistinctUserIdEntryId(distinctUserIdEntryId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@Override
	public DistinctUserIdEntry cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the distinct user ID entry ID of this distinct user ID entry.
	 *
	 * @return the distinct user ID entry ID of this distinct user ID entry
	 */
	@Override
	public long getDistinctUserIdEntryId() {
		return model.getDistinctUserIdEntryId();
	}

	/**
	 * Returns the primary key of this distinct user ID entry.
	 *
	 * @return the primary key of this distinct user ID entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this distinct user ID entry.
	 *
	 * @return the user ID of this distinct user ID entry
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this distinct user ID entry.
	 *
	 * @return the user uuid of this distinct user ID entry
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Sets the distinct user ID entry ID of this distinct user ID entry.
	 *
	 * @param distinctUserIdEntryId the distinct user ID entry ID of this distinct user ID entry
	 */
	@Override
	public void setDistinctUserIdEntryId(long distinctUserIdEntryId) {
		model.setDistinctUserIdEntryId(distinctUserIdEntryId);
	}

	/**
	 * Sets the primary key of this distinct user ID entry.
	 *
	 * @param primaryKey the primary key of this distinct user ID entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this distinct user ID entry.
	 *
	 * @param userId the user ID of this distinct user ID entry
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this distinct user ID entry.
	 *
	 * @param userUuid the user uuid of this distinct user ID entry
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
	protected DistinctUserIdEntryWrapper wrap(
		DistinctUserIdEntry distinctUserIdEntry) {

		return new DistinctUserIdEntryWrapper(distinctUserIdEntry);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:1723060332