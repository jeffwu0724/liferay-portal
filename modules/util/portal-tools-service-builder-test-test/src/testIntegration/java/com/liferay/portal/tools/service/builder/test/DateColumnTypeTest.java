/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.tools.service.builder.test.model.DateColumnEntry;
import com.liferay.portal.tools.service.builder.test.service.persistence.DateColumnEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.DateColumnEntryUtil;

import java.util.Date;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Reproduces the type of a single <code>type="Date"</code> Service Builder
 * column across a real database round trip.
 *
 * <p>
 * The write and the read run in separate committed transactions through
 * {@link TransactionInvokerUtil}. Because
 * <code>spring.hibernate.session.delegated</code> is <code>true</code>, each
 * transaction gets its own Hibernate session with an empty first level cache,
 * so the read after {@link DateColumnEntryPersistence#clearCache()} is a
 * genuine database read rather than a hit on the Liferay entity cache or the
 * Hibernate first level cache.
 * </p>
 *
 * @author Jiefeng Wu
 */
@RunWith(Arquillian.class)
public class DateColumnTypeTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_persistence = DateColumnEntryUtil.getPersistence();
	}

	@After
	public void tearDown() throws Exception {
		if (_primaryKey == 0) {
			return;
		}

		_invoke(
			() -> {
				DateColumnEntry dateColumnEntry = _persistence.fetchByPrimaryKey(
					_primaryKey);

				if (dateColumnEntry != null) {
					_persistence.remove(dateColumnEntry);
				}

				return null;
			});

		_primaryKey = 0;
	}

	@Test
	public void testDateColumnType() throws Exception {
		long[] primaryKeys = new long[1];

		// Write in its own committed transaction: create + INSERT (session.save)
		// + UPDATE (session.merge), then capture the merge-returned object type

		String objectType = _invoke(
			() -> {
				DateColumnEntry dateColumnEntry = _persistence.create(
					RandomTestUtil.nextLong());

				dateColumnEntry.setDateValue(new Date());

				dateColumnEntry = _persistence.update(dateColumnEntry);

				dateColumnEntry = _persistence.update(dateColumnEntry);

				primaryKeys[0] = dateColumnEntry.getPrimaryKey();

				return _typeName(dateColumnEntry.getDateValue());
			});

		_primaryKey = primaryKeys[0];

		// Drop the Liferay caches so the next read cannot be served from them

		_persistence.clearCache();

		// Read in a separate transaction: fresh Hibernate session, empty first
		// level cache, so this is a genuine database read

		String databaseType = _invoke(
			() -> {
				DateColumnEntry dateColumnEntry = _persistence.findByPrimaryKey(
					primaryKeys[0]);

				return _typeName(dateColumnEntry.getDateValue());
			});

		String observed =
			"objectInHand(merge-returned)=" + objectType +
				" | fromDatabase(separate transaction)=" + databaseType;

		Assert.assertEquals(
			"objectInHand(merge-returned)=java.util.Date | " +
				"fromDatabase(separate transaction)=java.util.Date",
			observed);
	}

	private <T> T _invoke(Callable<T> callable) throws Exception {
		try {
			return TransactionInvokerUtil.invoke(_transactionConfig, callable);
		}
		catch (Exception exception) {
			throw exception;
		}
		catch (Throwable throwable) {
			throw new Exception(throwable);
		}
	}

	private String _typeName(Date date) {
		if (date == null) {
			return "null";
		}

		return date.getClass().getName();
	}

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	private DateColumnEntryPersistence _persistence;
	private long _primaryKey;

}
