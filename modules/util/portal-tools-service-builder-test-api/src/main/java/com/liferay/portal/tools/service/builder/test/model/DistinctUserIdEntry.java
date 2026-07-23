/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the DistinctUserIdEntry service. Represents a row in the &quot;DistinctUserIdEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DistinctUserIdEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.portal.tools.service.builder.test.model.impl.DistinctUserIdEntryImpl"
)
@ProviderType
public interface DistinctUserIdEntry extends DistinctUserIdEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.tools.service.builder.test.model.impl.DistinctUserIdEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DistinctUserIdEntry, Long>
		DISTINCT_USER_ID_ENTRY_ID_ACCESSOR =
			new Accessor<DistinctUserIdEntry, Long>() {

				@Override
				public Long get(DistinctUserIdEntry distinctUserIdEntry) {
					return distinctUserIdEntry.getDistinctUserIdEntryId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<DistinctUserIdEntry> getTypeClass() {
					return DistinctUserIdEntry.class;
				}

			};

}
// LIFERAY-SERVICE-BUILDER-HASH:-1260360476