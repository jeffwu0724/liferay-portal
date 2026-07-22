/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.tools.service.builder.test.model.DateColumnEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DateColumnEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DateColumnEntryCacheModel
	implements CacheModel<DateColumnEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof DateColumnEntryCacheModel)) {
			return false;
		}

		DateColumnEntryCacheModel dateColumnEntryCacheModel =
			(DateColumnEntryCacheModel)object;

		if (dateColumnEntryId == dateColumnEntryCacheModel.dateColumnEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, dateColumnEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{dateColumnEntryId=");
		sb.append(dateColumnEntryId);
		sb.append(", dateValue=");
		sb.append(dateValue);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DateColumnEntry toEntityModel() {
		DateColumnEntryImpl dateColumnEntryImpl = new DateColumnEntryImpl();

		dateColumnEntryImpl.setDateColumnEntryId(dateColumnEntryId);

		if (dateValue == Long.MIN_VALUE) {
			dateColumnEntryImpl.setDateValue(null);
		}
		else {
			dateColumnEntryImpl.setDateValue(new Date(dateValue));
		}

		dateColumnEntryImpl.resetOriginalValues();

		return dateColumnEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		dateColumnEntryId = objectInput.readLong();
		dateValue = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(dateColumnEntryId);
		objectOutput.writeLong(dateValue);
	}

	public long dateColumnEntryId;
	public long dateValue;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1945227475