/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.io.Deserializer;
import com.liferay.petra.io.Serializer;
import com.liferay.petra.io.StreamUtil;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.tools.service.builder.test.model.LazyBlobEntry;
import com.liferay.portal.tools.service.builder.test.service.LazyBlobEntryLocalService;
import com.liferay.portal.tools.service.builder.test.service.persistence.LazyBlobEntryPersistence;

import java.io.ByteArrayInputStream;

import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.engine.spi.SessionImplementor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Dante Wang
 * @author Tina Tian
 */
@RunWith(Arquillian.class)
public class LazyBlobEntryTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_blobContent = RandomTestUtil.randomString();

		_lazyBlobEntry = _lazyBlobEntryLocalService.addLazyBlobEntry(
			TestPropsValues.getGroupId(), _blobContent.getBytes(),
			ServiceContextTestUtil.getServiceContext(
				TestPropsValues.getGroupId()));

		_assertLazyBlobEntry(_blobContent, _lazyBlobEntry);
	}

	@Test
	public void testLazyBlobEntryWithFetchingFromDatabase() throws Exception {
		_lazyBlobEntryPersistence.clearCache();

		LazyBlobEntry lazyBlobEntry =
			_lazyBlobEntryLocalService.fetchLazyBlobEntry(
				_lazyBlobEntry.getLazyBlobEntryId());

		Assert.assertNotSame(_lazyBlobEntry, lazyBlobEntry);

		_assertLazyBlobEntry(_blobContent, lazyBlobEntry);
	}

	@Test
	public void testLazyBlobEntryWithLoadingFromSession() throws Throwable {
		TransactionInvokerUtil.invoke(
			TransactionConfig.Factory.create(
				Propagation.REQUIRED, new Class<?>[] {Exception.class}),
			() -> {
				_lazyBlobEntryPersistence.clearCache();

				Session session = _lazyBlobEntryPersistence.openSession();

				try {
					LazyBlobEntry lazyBlobEntry = (LazyBlobEntry)session.load(
						_lazyBlobEntry.getClass(),
						_lazyBlobEntry.getLazyBlobEntryId());

					Assert.assertNull(
						ReflectionTestUtil.getFieldValue(
							lazyBlobEntry, "_blob1BlobModel"));
					Assert.assertNull(
						ReflectionTestUtil.getFieldValue(
							lazyBlobEntry, "_blob2BlobModel"));

					SessionImplementor sessionImplementor =
						(SessionImplementor)session.getWrappedSession();

					PersistenceContext persistenceContext =
						sessionImplementor.getPersistenceContext();

					EntityEntry entityEntry = persistenceContext.getEntry(
						lazyBlobEntry);

					Assert.assertNull(
						entityEntry.getLoadedValue("blob1BlobModel"));
					Assert.assertNull(
						entityEntry.getLoadedValue("blob2BlobModel"));
				}
				finally {
					_lazyBlobEntryPersistence.closeSession(session);
				}

				return null;
			});
	}

	@Test
	public void testLazyBlobEntryWithSerialization() throws Exception {
		Serializer serializer = new Serializer();

		serializer.writeObject(_lazyBlobEntry);

		Deserializer deserializer = new Deserializer(serializer.toByteBuffer());

		LazyBlobEntry lazyBlobEntry = deserializer.readObject();

		Assert.assertEquals(_lazyBlobEntry, lazyBlobEntry);

		_assertLazyBlobEntry(_blobContent, lazyBlobEntry);
	}

	@Test
	public void testLazyBlobEntryWithUpdate() throws Exception {
		String blobString = RandomTestUtil.randomString();

		byte[] blobBytes = blobString.getBytes();

		_lazyBlobEntry.setBlob1(
			new OutputBlob(
				new ByteArrayInputStream(blobBytes), blobBytes.length));
		_lazyBlobEntry.setBlob2(
			new OutputBlob(
				new ByteArrayInputStream(blobBytes), blobBytes.length));

		_lazyBlobEntry = _lazyBlobEntryLocalService.updateLazyBlobEntry(
			_lazyBlobEntry);

		_assertLazyBlobEntry(blobString, _lazyBlobEntry);
	}

	private void _assertLazyBlobEntry(
			String expectedContent, LazyBlobEntry lazyBlobEntry)
		throws Exception {

		Assert.assertNull(
			ReflectionTestUtil.getFieldValue(lazyBlobEntry, "_blob1BlobModel"));

		Assert.assertNotNull(lazyBlobEntry.getBlob1());

		Assert.assertNotNull(
			ReflectionTestUtil.getFieldValue(lazyBlobEntry, "_blob1BlobModel"));

		Assert.assertEquals(
			expectedContent,
			new String(
				StreamUtil.toByteArray(
					_lazyBlobEntryLocalService.openBlob1InputStream(
						lazyBlobEntry.getLazyBlobEntryId()))));

		Assert.assertNull(
			ReflectionTestUtil.getFieldValue(lazyBlobEntry, "_blob2BlobModel"));

		Assert.assertNotNull(lazyBlobEntry.getBlob2());

		Assert.assertNotNull(
			ReflectionTestUtil.getFieldValue(lazyBlobEntry, "_blob2BlobModel"));

		Assert.assertEquals(
			expectedContent,
			new String(
				StreamUtil.toByteArray(
					_lazyBlobEntryLocalService.openBlob2InputStream(
						lazyBlobEntry.getLazyBlobEntryId()))));
	}

	private String _blobContent;

	@DeleteAfterTestRun
	private LazyBlobEntry _lazyBlobEntry;

	@Inject
	private LazyBlobEntryLocalService _lazyBlobEntryLocalService;

	@Inject
	private LazyBlobEntryPersistence _lazyBlobEntryPersistence;

}