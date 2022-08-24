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
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.lpkg.deployer.test.util.LPKGTestUtil;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LogEntry;
import com.liferay.portal.test.log.LoggerTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

		ReflectionTestUtil.setFieldValue(
			FileUtil.class, "_file", FileImpl.getInstance());

		_tempFolder = tempPath.toFile();
	}

	@After
	public void tearDown() {
		FileUtil.deltree(_tempFolder);
	}

	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testDeploy() throws Exception {
		Assert.assertEquals(
			_createExpectedOutputFileList("inner.lpkg"),
			ContainerLPKGUtil.deploy(
				_createLPKGContainerFile("inner.lpkg"), null));

		Assert.assertTrue(FileUtil.exists(new File(_tempFolder, "inner.lpkg")));
	}

	@NewEnv(type = NewEnv.Type.CLASSLOADER)
	@Test
	public void testDeployWithZipSlip() throws Exception {
		try (LogCapture logCapture = LoggerTestUtil.configureJDKLogger(
				ContainerLPKGUtil.class.getName(), Level.WARNING)) {

			Assert.assertEquals(
				_createExpectedOutputFileList("good.lpkg"),
				ContainerLPKGUtil.deploy(
					_createLPKGContainerFile("good.lpkg", "../bad.lpkg"),
					null));

			Assert.assertTrue(
				FileUtil.exists(new File(_tempFolder, "good.lpkg")));

			Assert.assertFalse(
				FileUtil.exists(new File(_tempFolder.getParent(), "bad.lpkg")));

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertEquals(logEntries.toString(), 1, logEntries.size());

			LogEntry logEntry = logEntries.get(0);

			Assert.assertEquals(
				"Invalid LPKG File name: ../bad.lpkg", logEntry.getMessage());
		}
	}

	private List<File> _createExpectedOutputFileList(String fileName) {
		return Arrays.asList(new File(_tempFolder, fileName));
	}

	private File _createLPKGContainerFile(String... entries) throws Exception {
		File lpkgFile = new File(_tempFolder, "outer.lpkg");

		try (ZipOutputStream zipOutputStream = new ZipOutputStream(
				new FileOutputStream(lpkgFile))) {

			for (String entry : entries) {
				Path path = Paths.get(_tempFolder.toString(), entry);

				LPKGTestUtil.createLPKG(path, _SYMBOLIC_NAME, true);

				zipOutputStream.putNextEntry(new ZipEntry(entry));

				try (InputStream inputStream = new FileInputStream(
						path.toFile());
					OutputStream outputStream = StreamUtil.uncloseable(
						zipOutputStream)) {

					StreamUtil.transfer(inputStream, outputStream);
				}

				Files.delete(path);
			}
		}

		return lpkgFile;
	}

	private static final String _SYMBOLIC_NAME = "container.lpkg.test";

	private static File _tempFolder;

}