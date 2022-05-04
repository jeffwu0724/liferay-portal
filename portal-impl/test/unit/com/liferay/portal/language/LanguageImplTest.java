/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.language;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Jiefeng Wu
 */
public class LanguageImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		Map<String, String> languageMapsForSuffix = HashMapBuilder.put(
			"storage.size.suffix.b", "B"
		).put(
			"storage.size.suffix.gb", "GB"
		).put(
			"storage.size.suffix.kb", "KB"
		).put(
			"storage.size.suffix.mb", "MB"
		).build();

		ReflectionTestUtil.setFieldValue(
			LanguageResources.class, "_languageMaps",
			HashMapBuilder.<Locale, Map<String, String>>put(
				LocaleUtil.SPAIN, languageMapsForSuffix
			).put(
				LocaleUtil.US, languageMapsForSuffix
			).build());
	}

	@Test
	public void testformatStorageSizeOneB() throws Exception {
		long bytes = 1;
		LanguageImpl languageImpl = new LanguageImpl();

		Assert.assertEquals(
			"1 B", languageImpl.formatStorageSize(bytes, LocaleUtil.US));
		Assert.assertEquals(
			"1 B", languageImpl.formatStorageSize(bytes, LocaleUtil.SPAIN));
	}

	@Test
	public void testformatStorageSizeOneGB() throws Exception {
		long bytes = 1024 * 1024 * 1024;
		LanguageImpl languageImpl = new LanguageImpl();

		Assert.assertEquals(
			"1 GB", languageImpl.formatStorageSize(bytes, LocaleUtil.US));
		Assert.assertEquals(
			"1 GB", languageImpl.formatStorageSize(bytes, LocaleUtil.SPAIN));
	}

	@Test
	public void testformatStorageSizeOneKB() throws Exception {
		long bytes = 1024;
		LanguageImpl languageImpl = new LanguageImpl();

		Assert.assertEquals(
			"1 KB", languageImpl.formatStorageSize(bytes, LocaleUtil.SPAIN));
		Assert.assertEquals(
			"1 KB", languageImpl.formatStorageSize(bytes, LocaleUtil.US));
	}

	@Test
	public void testformatStorageSizeOneMB() throws Exception {
		long bytes = 1024 * 1024;
		LanguageImpl languageImpl = new LanguageImpl();

		Assert.assertEquals(
			"1 MB", languageImpl.formatStorageSize(bytes, LocaleUtil.SPAIN));
		Assert.assertEquals(
			"1 MB", languageImpl.formatStorageSize(bytes, LocaleUtil.US));
	}

}