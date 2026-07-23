/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.tools.service.builder.test.model.DistinctUserIdEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DistinctUserIdEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DistinctUserIdEntryCacheModel
	implements CacheModel<DistinctUserIdEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DistinctUserIdEntryCacheModel)) {
			return false;
		}

		DistinctUserIdEntryCacheModel distinctUserIdEntryCacheModel =
			(DistinctUserIdEntryCacheModel)object;

		if (distinctUserIdEntryId ==
				distinctUserIdEntryCacheModel.distinctUserIdEntryId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, distinctUserIdEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{distinctUserIdEntryId=");
		sb.append(distinctUserIdEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DistinctUserIdEntry toEntityModel() {
		DistinctUserIdEntryImpl distinctUserIdEntryImpl =
			new DistinctUserIdEntryImpl();

		distinctUserIdEntryImpl.setDistinctUserIdEntryId(distinctUserIdEntryId);
		distinctUserIdEntryImpl.setUserId(userId);

		distinctUserIdEntryImpl.resetOriginalValues();

		return distinctUserIdEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		distinctUserIdEntryId = objectInput.readLong();

		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(distinctUserIdEntryId);

		objectOutput.writeLong(userId);
	}

	public long distinctUserIdEntryId;
	public long userId;

}
// LIFERAY-SERVICE-BUILDER-HASH:952275833