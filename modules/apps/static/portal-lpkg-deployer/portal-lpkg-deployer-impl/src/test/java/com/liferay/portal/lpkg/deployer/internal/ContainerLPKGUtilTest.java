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

package com.liferay.portal.lpkg.deployer.internal;

import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.NewEnv;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LogEntry;
import com.liferay.portal.test.log.LoggerTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Jiefeng Wu
 */
public class ContainerLPKGUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() throws IOException {
		Path tempPath = Files.createTempDirectory("testDeploy");

		ReflectionTestUtil.setFieldValue(
			PropsValues.class, "MODULE_FRAMEWORK_MARKETPLACE_DIR",
			tempPath.toString());

		_tempFolder = tempPath.toFile();
	}

	@After
	public void tearDown() {
		for (File file : _tempFolder.listFiles()) {
			file.delete();
		}

		_tempFolder.delete();
	}

	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testDeploy() throws Exception {
		File innerLPKG = new File(_tempFolder, "inner.lpkg");

		Assert.assertEquals(
			Arrays.asList(innerLPKG),
			ContainerLPKGUtil.deploy(
				_createLPKGContainerFile("inner.lpkg"), null));

		Assert.assertTrue(innerLPKG.exists());
	}

	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testDeployWithZipSlip() throws Exception {
		try (LogCapture logCapture = LoggerTestUtil.configureJDKLogger(
				ContainerLPKGUtil.class.getName(), Level.WARNING)) {

			File goodLPKG = new File(_tempFolder, "good.lpkg");
			File badLPKG = new File(_tempFolder, "bad.lpkg");

			Assert.assertEquals(
				Arrays.asList(goodLPKG),
				ContainerLPKGUtil.deploy(
					_createLPKGContainerFile("good.lpkg", "../bad.lpkg"),
					null));

			Assert.assertTrue(goodLPKG.exists());
			Assert.assertFalse(badLPKG.exists());

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals(
				"Invalid LPKG File name: ../bad.lpkg", logEntry.getMessage());
		}
	}

	private File _createLPKGContainerFile(String... entries) throws Exception {
		File lpkgFile = new File(_tempFolder, "outer.lpkg");

		try (ZipOutputStream zipOutputStream = new ZipOutputStream(
				new FileOutputStream(lpkgFile))) {

			for (String entry : entries) {
				zipOutputStream.putNextEntry(new ZipEntry(entry));

				zipOutputStream.write("FAKE LPKG".getBytes());
			}
		}

		return lpkgFile;
	}

	private File _tempFolder;

}