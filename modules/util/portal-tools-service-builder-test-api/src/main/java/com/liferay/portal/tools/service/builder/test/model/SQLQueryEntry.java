/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the SQLQueryEntry service. Represents a row in the &quot;SQLQueryEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see SQLQueryEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.portal.tools.service.builder.test.model.impl.SQLQueryEntryImpl"
)
@ProviderType
public interface SQLQueryEntry extends SQLQueryEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.tools.service.builder.test.model.impl.SQLQueryEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<SQLQueryEntry, Long>
		SQL_QUERY_ENTRY_ID_ACCESSOR = new Accessor<SQLQueryEntry, Long>() {

			@Override
			public Long get(SQLQueryEntry sqlQueryEntry) {
				return sqlQueryEntry.getSqlQueryEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<SQLQueryEntry> getTypeClass() {
				return SQLQueryEntry.class;
			}

		};

}
// LIFERAY-SERVICE-BUILDER-HASH:328829489