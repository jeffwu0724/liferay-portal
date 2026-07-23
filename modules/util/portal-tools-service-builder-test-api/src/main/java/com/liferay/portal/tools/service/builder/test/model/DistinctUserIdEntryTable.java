/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;DistinctUserIdEntry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see DistinctUserIdEntry
 * @generated
 */
public class DistinctUserIdEntryTable
	extends BaseTable<DistinctUserIdEntryTable> {

	public static final DistinctUserIdEntryTable INSTANCE =
		new DistinctUserIdEntryTable();

	public final Column<DistinctUserIdEntryTable, Long> distinctUserIdEntryId =
		createColumn(
			"distinctUserIdEntryId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<DistinctUserIdEntryTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private DistinctUserIdEntryTable() {
		super("DistinctUserIdEntry", DistinctUserIdEntryTable::new);
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-660371517