/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.tools.service.builder.test.model.SQLQueryEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SQLQueryEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SQLQueryEntryCacheModel
	implements CacheModel<SQLQueryEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SQLQueryEntryCacheModel)) {
			return false;
		}

		SQLQueryEntryCacheModel sqlQueryEntryCacheModel =
			(SQLQueryEntryCacheModel)object;

		if (sqlQueryEntryId == sqlQueryEntryCacheModel.sqlQueryEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, sqlQueryEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{sqlQueryEntryId=");
		sb.append(sqlQueryEntryId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SQLQueryEntry toEntityModel() {
		SQLQueryEntryImpl sqlQueryEntryImpl = new SQLQueryEntryImpl();

		sqlQueryEntryImpl.setSqlQueryEntryId(sqlQueryEntryId);
		sqlQueryEntryImpl.setUserId(userId);

		sqlQueryEntryImpl.resetOriginalValues();

		return sqlQueryEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		sqlQueryEntryId = objectInput.readLong();

		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(sqlQueryEntryId);

		objectOutput.writeLong(userId);
	}

	public long sqlQueryEntryId;
	public long userId;

}
// LIFERAY-SERVICE-BUILDER-HASH:1806901201