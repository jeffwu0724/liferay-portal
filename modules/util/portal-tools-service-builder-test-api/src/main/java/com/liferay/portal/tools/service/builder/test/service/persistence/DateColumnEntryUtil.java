/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.tools.service.builder.test.model.DateColumnEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the date column entry service. This utility wraps <code>com.liferay.portal.tools.service.builder.test.service.persistence.impl.DateColumnEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DateColumnEntryPersistence
 * @generated
 */
public class DateColumnEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#cacheResult(List)
	 */
	public static void cacheResult(List<DateColumnEntry> dateColumnEntries) {
		getPersistence().cacheResult(dateColumnEntries);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#cacheResult(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void cacheResult(DateColumnEntry dateColumnEntry) {
		getPersistence().cacheResult(dateColumnEntry);
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
	public static void clearCache(DateColumnEntry dateColumnEntry) {
		getPersistence().clearCache(dateColumnEntry);
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
	public static Map<Serializable, DateColumnEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DateColumnEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DateColumnEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DateColumnEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DateColumnEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DateColumnEntry update(DateColumnEntry dateColumnEntry) {
		return getPersistence().update(dateColumnEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DateColumnEntry update(
		DateColumnEntry dateColumnEntry, ServiceContext serviceContext) {

		return getPersistence().update(dateColumnEntry, serviceContext);
	}

	/**
	 * Creates a new date column entry with the primary key. Does not add the date column entry to the database.
	 *
	 * @param dateColumnEntryId the primary key for the new date column entry
	 * @return the new date column entry
	 */
	public static DateColumnEntry create(long dateColumnEntryId) {
		return getPersistence().create(dateColumnEntryId);
	}

	/**
	 * Removes the date column entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dateColumnEntryId the primary key of the date column entry
	 * @return the date column entry that was removed
	 * @throws NoSuchDateColumnEntryException if a date column entry with the primary key could not be found
	 */
	public static DateColumnEntry remove(long dateColumnEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchDateColumnEntryException {

		return getPersistence().remove(dateColumnEntryId);
	}

	public static DateColumnEntry updateImpl(DateColumnEntry dateColumnEntry) {
		return getPersistence().updateImpl(dateColumnEntry);
	}

	/**
	 * Returns the date column entry with the primary key or throws a <code>NoSuchDateColumnEntryException</code> if it could not be found.
	 *
	 * @param dateColumnEntryId the primary key of the date column entry
	 * @return the date column entry
	 * @throws NoSuchDateColumnEntryException if a date column entry with the primary key could not be found
	 */
	public static DateColumnEntry findByPrimaryKey(long dateColumnEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchDateColumnEntryException {

		return getPersistence().findByPrimaryKey(dateColumnEntryId);
	}

	/**
	 * Returns the date column entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dateColumnEntryId the primary key of the date column entry
	 * @return the date column entry, or <code>null</code> if a date column entry with the primary key could not be found
	 */
	public static DateColumnEntry fetchByPrimaryKey(long dateColumnEntryId) {
		return getPersistence().fetchByPrimaryKey(dateColumnEntryId);
	}

	public static DateColumnEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(DateColumnEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile DateColumnEntryPersistence _persistence;

}
// LIFERAY-SERVICE-BUILDER-HASH:88239425