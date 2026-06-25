/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the RenamedPKColumnEntry service. Represents a row in the &quot;RenamedPKColumnEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see RenamedPKColumnEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.portal.tools.service.builder.test.model.impl.RenamedPKColumnEntryImpl"
)
@ProviderType
public interface RenamedPKColumnEntry extends RenamedPKColumnEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.tools.service.builder.test.model.impl.RenamedPKColumnEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<RenamedPKColumnEntry, Long>
		RENAMED_PK_COLUMN_ENTRY_ID_ACCESSOR =
			new Accessor<RenamedPKColumnEntry, Long>() {

				@Override
				public Long get(RenamedPKColumnEntry renamedPKColumnEntry) {
					return renamedPKColumnEntry.getRenamedPKColumnEntryId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<RenamedPKColumnEntry> getTypeClass() {
					return RenamedPKColumnEntry.class;
				}

			};

}
// LIFERAY-SERVICE-BUILDER-HASH:696914122