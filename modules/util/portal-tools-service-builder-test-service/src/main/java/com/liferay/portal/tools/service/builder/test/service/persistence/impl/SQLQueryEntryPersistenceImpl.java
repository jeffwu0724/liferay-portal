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
import com.liferay.portal.tools.service.builder.test.exception.NoSuchSQLQueryEntryException;
import com.liferay.portal.tools.service.builder.test.model.SQLQueryEntry;
import com.liferay.portal.tools.service.builder.test.model.SQLQueryEntryTable;
import com.liferay.portal.tools.service.builder.test.model.impl.SQLQueryEntryImpl;
import com.liferay.portal.tools.service.builder.test.model.impl.SQLQueryEntryModelImpl;
import com.liferay.portal.tools.service.builder.test.service.persistence.SQLQueryEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.SQLQueryEntryUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * The persistence implementation for the sql query entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SQLQueryEntryPersistenceImpl
	extends BasePersistenceImpl<SQLQueryEntry, NoSuchSQLQueryEntryException>
	implements SQLQueryEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>SQLQueryEntryUtil</code> to access the sql query entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		SQLQueryEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	public SQLQueryEntryPersistenceImpl() {
		setModelClass(SQLQueryEntry.class);

		setModelImplClass(SQLQueryEntryImpl.class);
		setModelPKClass(long.class);

		setTable(SQLQueryEntryTable.INSTANCE);
	}

	/**
	 * Creates a new sql query entry with the primary key. Does not add the sql query entry to the database.
	 *
	 * @param sqlQueryEntryId the primary key for the new sql query entry
	 * @return the new sql query entry
	 */
	@Override
	public SQLQueryEntry create(long sqlQueryEntryId) {
		SQLQueryEntry sqlQueryEntry = new SQLQueryEntryImpl();

		sqlQueryEntry.setNew(true);
		sqlQueryEntry.setPrimaryKey(sqlQueryEntryId);

		return sqlQueryEntry;
	}

	/**
	 * Removes the sql query entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sqlQueryEntryId the primary key of the sql query entry
	 * @return the sql query entry that was removed
	 * @throws NoSuchSQLQueryEntryException if a sql query entry with the primary key could not be found
	 */
	@Override
	public SQLQueryEntry remove(long sqlQueryEntryId)
		throws NoSuchSQLQueryEntryException {

		return remove((Serializable)sqlQueryEntryId);
	}

	@Override
	protected SQLQueryEntry removeImpl(SQLQueryEntry sqlQueryEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(sqlQueryEntry)) {
				sqlQueryEntry = (SQLQueryEntry)session.get(
					SQLQueryEntryImpl.class, sqlQueryEntry.getPrimaryKeyObj());
			}

			if (sqlQueryEntry != null) {
				session.delete(sqlQueryEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (sqlQueryEntry != null) {
			clearCache(sqlQueryEntry);
		}

		return sqlQueryEntry;
	}

	@Override
	public SQLQueryEntry updateImpl(SQLQueryEntry sqlQueryEntry) {
		boolean isNew = sqlQueryEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(sqlQueryEntry);
			}
			else {
				sqlQueryEntry = (SQLQueryEntry)session.merge(sqlQueryEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		cacheUniqueFindersResult(sqlQueryEntry, false);

		if (isNew) {
			sqlQueryEntry.setNew(false);
		}

		sqlQueryEntry.resetOriginalValues();

		return sqlQueryEntry;
	}

	/**
	 * Returns the sql query entry with the primary key or throws a <code>NoSuchSQLQueryEntryException</code> if it could not be found.
	 *
	 * @param sqlQueryEntryId the primary key of the sql query entry
	 * @return the sql query entry
	 * @throws NoSuchSQLQueryEntryException if a sql query entry with the primary key could not be found
	 */
	@Override
	public SQLQueryEntry findByPrimaryKey(long sqlQueryEntryId)
		throws NoSuchSQLQueryEntryException {

		return findByPrimaryKey((Serializable)sqlQueryEntryId);
	}

	/**
	 * Returns the sql query entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sqlQueryEntryId the primary key of the sql query entry
	 * @return the sql query entry, or <code>null</code> if a sql query entry with the primary key could not be found
	 */
	@Override
	public SQLQueryEntry fetchByPrimaryKey(long sqlQueryEntryId) {
		return fetchByPrimaryKey((Serializable)sqlQueryEntryId);
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "sqlQueryEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_SQLQUERYENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SQLQueryEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sql query entry persistence.
	 */
	public void afterPropertiesSet() {
		SQLQueryEntryUtil.setPersistence(this);
	}

	public void destroy() {
		SQLQueryEntryUtil.setPersistence(null);

		entityCache.removeCache(SQLQueryEntryImpl.class.getName());
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_SQLQUERYENTRY =
		"SELECT sqlQueryEntry FROM SQLQueryEntry sqlQueryEntry";

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-30758055