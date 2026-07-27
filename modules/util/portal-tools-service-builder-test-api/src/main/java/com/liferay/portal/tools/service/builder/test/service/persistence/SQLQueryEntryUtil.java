/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.tools.service.builder.test.model.SQLQueryEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the sql query entry service. This utility wraps <code>com.liferay.portal.tools.service.builder.test.service.persistence.impl.SQLQueryEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SQLQueryEntryPersistence
 * @generated
 */
public class SQLQueryEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#cacheResult(List)
	 */
	public static void cacheResult(List<SQLQueryEntry> sqlQueryEntries) {
		getPersistence().cacheResult(sqlQueryEntries);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#cacheResult(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void cacheResult(SQLQueryEntry sqlQueryEntry) {
		getPersistence().cacheResult(sqlQueryEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(SQLQueryEntry sqlQueryEntry) {
		getPersistence().clearCache(sqlQueryEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, SQLQueryEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SQLQueryEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SQLQueryEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SQLQueryEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SQLQueryEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SQLQueryEntry update(SQLQueryEntry sqlQueryEntry) {
		return getPersistence().update(sqlQueryEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SQLQueryEntry update(
		SQLQueryEntry sqlQueryEntry, ServiceContext serviceContext) {

		return getPersistence().update(sqlQueryEntry, serviceContext);
	}

	/**
	 * Creates a new sql query entry with the primary key. Does not add the sql query entry to the database.
	 *
	 * @param sqlQueryEntryId the primary key for the new sql query entry
	 * @return the new sql query entry
	 */
	public static SQLQueryEntry create(long sqlQueryEntryId) {
		return getPersistence().create(sqlQueryEntryId);
	}

	/**
	 * Removes the sql query entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sqlQueryEntryId the primary key of the sql query entry
	 * @return the sql query entry that was removed
	 * @throws NoSuchSQLQueryEntryException if a sql query entry with the primary key could not be found
	 */
	public static SQLQueryEntry remove(long sqlQueryEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchSQLQueryEntryException {

		return getPersistence().remove(sqlQueryEntryId);
	}

	public static SQLQueryEntry updateImpl(SQLQueryEntry sqlQueryEntry) {
		return getPersistence().updateImpl(sqlQueryEntry);
	}

	/**
	 * Returns the sql query entry with the primary key or throws a <code>NoSuchSQLQueryEntryException</code> if it could not be found.
	 *
	 * @param sqlQueryEntryId the primary key of the sql query entry
	 * @return the sql query entry
	 * @throws NoSuchSQLQueryEntryException if a sql query entry with the primary key could not be found
	 */
	public static SQLQueryEntry findByPrimaryKey(long sqlQueryEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchSQLQueryEntryException {

		return getPersistence().findByPrimaryKey(sqlQueryEntryId);
	}

	/**
	 * Returns the sql query entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sqlQueryEntryId the primary key of the sql query entry
	 * @return the sql query entry, or <code>null</code> if a sql query entry with the primary key could not be found
	 */
	public static SQLQueryEntry fetchByPrimaryKey(long sqlQueryEntryId) {
		return getPersistence().fetchByPrimaryKey(sqlQueryEntryId);
	}

	public static SQLQueryEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(SQLQueryEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile SQLQueryEntryPersistence _persistence;

}
// LIFERAY-SERVICE-BUILDER-HASH:-1859697845