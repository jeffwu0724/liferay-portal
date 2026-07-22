/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchDateColumnEntryException;
import com.liferay.portal.tools.service.builder.test.model.DateColumnEntry;
import com.liferay.portal.tools.service.builder.test.model.DateColumnEntryTable;
import com.liferay.portal.tools.service.builder.test.model.impl.DateColumnEntryImpl;
import com.liferay.portal.tools.service.builder.test.model.impl.DateColumnEntryModelImpl;
import com.liferay.portal.tools.service.builder.test.service.persistence.DateColumnEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.DateColumnEntryUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * The persistence implementation for the date column entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DateColumnEntryPersistenceImpl
	extends BasePersistenceImpl<DateColumnEntry, NoSuchDateColumnEntryException>
	implements DateColumnEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DateColumnEntryUtil</code> to access the date column entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DateColumnEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	public DateColumnEntryPersistenceImpl() {
		setModelClass(DateColumnEntry.class);

		setModelImplClass(DateColumnEntryImpl.class);
		setModelPKClass(long.class);

		setTable(DateColumnEntryTable.INSTANCE);
	}

	/**
	 * Creates a new date column entry with the primary key. Does not add the date column entry to the database.
	 *
	 * @param dateColumnEntryId the primary key for the new date column entry
	 * @return the new date column entry
	 */
	@Override
	public DateColumnEntry create(long dateColumnEntryId) {
		DateColumnEntry dateColumnEntry = new DateColumnEntryImpl();

		dateColumnEntry.setNew(true);
		dateColumnEntry.setPrimaryKey(dateColumnEntryId);

		return dateColumnEntry;
	}

	/**
	 * Removes the date column entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dateColumnEntryId the primary key of the date column entry
	 * @return the date column entry that was removed
	 * @throws NoSuchDateColumnEntryException if a date column entry with the primary key could not be found
	 */
	@Override
	public DateColumnEntry remove(long dateColumnEntryId)
		throws NoSuchDateColumnEntryException {

		return remove((Serializable)dateColumnEntryId);
	}

	@Override
	protected DateColumnEntry removeImpl(DateColumnEntry dateColumnEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dateColumnEntry)) {
				dateColumnEntry = (DateColumnEntry)session.get(
					DateColumnEntryImpl.class,
					dateColumnEntry.getPrimaryKeyObj());
			}

			if (dateColumnEntry != null) {
				session.delete(dateColumnEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (dateColumnEntry != null) {
			clearCache(dateColumnEntry);
		}

		return dateColumnEntry;
	}

	@Override
	public DateColumnEntry updateImpl(DateColumnEntry dateColumnEntry) {
		boolean isNew = dateColumnEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(dateColumnEntry);
			}
			else {
				dateColumnEntry = (DateColumnEntry)session.merge(
					dateColumnEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		cacheUniqueFindersResult(dateColumnEntry, false);

		if (isNew) {
			dateColumnEntry.setNew(false);
		}

		dateColumnEntry.resetOriginalValues();

		return dateColumnEntry;
	}

	/**
	 * Returns the date column entry with the primary key or throws a <code>NoSuchDateColumnEntryException</code> if it could not be found.
	 *
	 * @param dateColumnEntryId the primary key of the date column entry
	 * @return the date column entry
	 * @throws NoSuchDateColumnEntryException if a date column entry with the primary key could not be found
	 */
	@Override
	public DateColumnEntry findByPrimaryKey(long dateColumnEntryId)
		throws NoSuchDateColumnEntryException {

		return findByPrimaryKey((Serializable)dateColumnEntryId);
	}

	/**
	 * Returns the date column entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dateColumnEntryId the primary key of the date column entry
	 * @return the date column entry, or <code>null</code> if a date column entry with the primary key could not be found
	 */
	@Override
	public DateColumnEntry fetchByPrimaryKey(long dateColumnEntryId) {
		return fetchByPrimaryKey((Serializable)dateColumnEntryId);
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "dateColumnEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DATECOLUMNENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DateColumnEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the date column entry persistence.
	 */
	public void afterPropertiesSet() {
		DateColumnEntryUtil.setPersistence(this);
	}

	public void destroy() {
		DateColumnEntryUtil.setPersistence(null);

		entityCache.removeCache(DateColumnEntryImpl.class.getName());
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DATECOLUMNENTRY =
		"SELECT dateColumnEntry FROM DateColumnEntry dateColumnEntry";

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:1174143615