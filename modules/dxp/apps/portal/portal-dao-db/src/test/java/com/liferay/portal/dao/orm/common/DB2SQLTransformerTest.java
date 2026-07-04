/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.common;

import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Jeff Wu
 */
public class DB2SQLTransformerTest extends BaseSQLTransformerTestCase {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testTransformToHQLKeepsBooleanLiteral() {

		// HQL path: [$FALSE$] becomes the HQL literal FALSE, not the dialect
		// integer 0 that Hibernate 7 rejects for a Boolean attribute.

		Assert.assertEquals(
			"SELECT layout FROM Layout layout WHERE layout.system = FALSE",
			SQLTransformer.transformToHQL(
				"SELECT layout FROM Layout layout WHERE layout.system = " +
					"[$FALSE$]"));
	}

	@Test
	public void testTransformToHQLDoesNotCastLike() {

		// HQL path: LIKE ? stays a plain numbered parameter, with none of the
		// CAST(? AS VARCHAR(n)) the DB2 dialect adds for native SQL and that
		// Hibernate 7 rejects as an HQL cast target.

		Assert.assertEquals(
			"SELECT indexEntry FROM IndexEntry indexEntry WHERE " +
				"indexEntry.name LIKE ?1",
			SQLTransformer.transformToHQL(
				"SELECT indexEntry FROM IndexEntry indexEntry WHERE " +
					"indexEntry.name LIKE ?"));
	}

	@Test
	public void testTransformFromJPQLToHQLKeepsNativeVarcharCast() {

		// Native SQL path is unchanged: the DB2 dialect still wraps LIKE ? as
		// CAST(? AS VARCHAR(2000)), which is valid DB2 SQL. The HQL rewrite
		// must not reach this path (that is what broke native queries on DB2).

		String transformedSQL = SQLTransformer.transformFromJPQLToHQL(
			"SELECT * FROM IndexEntry WHERE name LIKE ?");

		Assert.assertTrue(transformedSQL, transformedSQL.contains("VARCHAR"));
	}

	@Override
	protected DBType getDBType() {
		return DBType.DB2;
	}

}
