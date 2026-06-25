/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.tools.service.builder.test.model.RenamedPKColumnEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing RenamedPKColumnEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RenamedPKColumnEntryCacheModel
	implements CacheModel<RenamedPKColumnEntry>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof RenamedPKColumnEntryCacheModel)) {
			return false;
		}

		RenamedPKColumnEntryCacheModel renamedPKColumnEntryCacheModel =
			(RenamedPKColumnEntryCacheModel)object;

		if (renamedPKColumnEntryId ==
				renamedPKColumnEntryCacheModel.renamedPKColumnEntryId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, renamedPKColumnEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{renamedPKColumnEntryId=");
		sb.append(renamedPKColumnEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RenamedPKColumnEntry toEntityModel() {
		RenamedPKColumnEntryImpl renamedPKColumnEntryImpl =
			new RenamedPKColumnEntryImpl();

		renamedPKColumnEntryImpl.setRenamedPKColumnEntryId(
			renamedPKColumnEntryId);

		if (name == null) {
			renamedPKColumnEntryImpl.setName("");
		}
		else {
			renamedPKColumnEntryImpl.setName(name);
		}

		renamedPKColumnEntryImpl.resetOriginalValues();

		return renamedPKColumnEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		renamedPKColumnEntryId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(renamedPKColumnEntryId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long renamedPKColumnEntryId;
	public String name;

}
// LIFERAY-SERVICE-BUILDER-HASH:-443324813