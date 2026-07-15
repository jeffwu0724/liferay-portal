/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.sql.transformer;

import com.liferay.portal.dao.orm.common.SQLTransformer;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Manuel de la Peña
 */
public class JPQLToHQLTransformerLogicTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Test
	public void testReplaceCount() {
		Assert.assertEquals(
			"SELECT COUNT(*) FROM Foo foo",
			SQLTransformer.transformFromJPQLToHQL(
				"SELECT COUNT(foo) FROM Foo foo"));
	}

	@Test
	public void testReplaceCountWithIncorrectAlias() {
		String sql = "SELECT COUNT(bar) FROM Foo foo";

		Assert.assertEquals(sql, SQLTransformer.transformFromJPQLToHQL(sql));
	}

	@Test
	public void testReplaceCountWithNoCount() {
		String sql = "SELECT * FROM Foo where foo != 1";

		Assert.assertEquals(sql, SQLTransformer.transformFromJPQLToHQL(sql));
	}

	@Test
	public void testReplacePositionalParameters() {
		Function<String, String> function =
			JPQLToHQLTransformerLogic.getPositionalParameterFunction();

		Assert.assertEquals(
			"SELECT * FROM Foo WHERE a = ?1 AND b = ?2 AND c = ?3",
			function.apply("SELECT * FROM Foo WHERE a = ? AND b = ? AND c = ?"));
	}

	@Test
	public void testReplacePositionalParametersWithNoParameters() {
		String sql = "SELECT * FROM Foo WHERE a = 1";

		Function<String, String> function =
			JPQLToHQLTransformerLogic.getPositionalParameterFunction();

		Assert.assertEquals(sql, function.apply(sql));
	}

	@Test
	public void testReplacePositionalParametersWithQuotedQuestionMark() {
		String sql = "SELECT * FROM Foo WHERE a = '?' AND b = ?";

		Function<String, String> function =
			JPQLToHQLTransformerLogic.getPositionalParameterFunction();

		Assert.assertEquals(
			"SELECT * FROM Foo WHERE a = '?' AND b = ?1", function.apply(sql));
	}

}