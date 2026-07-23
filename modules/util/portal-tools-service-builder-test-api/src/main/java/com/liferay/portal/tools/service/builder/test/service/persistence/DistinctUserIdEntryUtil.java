/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.tools.service.builder.test.model.DistinctUserIdEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the distinct user ID entry service. This utility wraps <code>com.liferay.portal.tools.service.builder.test.service.persistence.impl.DistinctUserIdEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DistinctUserIdEntryPersistence
 * @generated
 */
public class DistinctUserIdEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#cacheResult(List)
	 */
	public static void cacheResult(
		List<DistinctUserIdEntry> distinctUserIdEntries) {

		getPersistence().cacheResult(distinctUserIdEntries);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#cacheResult(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void cacheResult(DistinctUserIdEntry distinctUserIdEntry) {
		getPersistence().cacheResult(distinctUserIdEntry);
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
	public static void clearCache(DistinctUserIdEntry distinctUserIdEntry) {
		getPersistence().clearCache(distinctUserIdEntry);
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
	public static Map<Serializable, DistinctUserIdEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DistinctUserIdEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DistinctUserIdEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DistinctUserIdEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DistinctUserIdEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DistinctUserIdEntry update(
		DistinctUserIdEntry distinctUserIdEntry) {

		return getPersistence().update(distinctUserIdEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DistinctUserIdEntry update(
		DistinctUserIdEntry distinctUserIdEntry,
		ServiceContext serviceContext) {

		return getPersistence().update(distinctUserIdEntry, serviceContext);
	}

	/**
	 * Creates a new distinct user ID entry with the primary key. Does not add the distinct user ID entry to the database.
	 *
	 * @param distinctUserIdEntryId the primary key for the new distinct user ID entry
	 * @return the new distinct user ID entry
	 */
	public static DistinctUserIdEntry create(long distinctUserIdEntryId) {
		return getPersistence().create(distinctUserIdEntryId);
	}

	/**
	 * Removes the distinct user ID entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param distinctUserIdEntryId the primary key of the distinct user ID entry
	 * @return the distinct user ID entry that was removed
	 * @throws NoSuchDistinctUserIdEntryException if a distinct user ID entry with the primary key could not be found
	 */
	public static DistinctUserIdEntry remove(long distinctUserIdEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchDistinctUserIdEntryException {

		return getPersistence().remove(distinctUserIdEntryId);
	}

	public static DistinctUserIdEntry updateImpl(
		DistinctUserIdEntry distinctUserIdEntry) {

		return getPersistence().updateImpl(distinctUserIdEntry);
	}

	/**
	 * Returns the distinct user ID entry with the primary key or throws a <code>NoSuchDistinctUserIdEntryException</code> if it could not be found.
	 *
	 * @param distinctUserIdEntryId the primary key of the distinct user ID entry
	 * @return the distinct user ID entry
	 * @throws NoSuchDistinctUserIdEntryException if a distinct user ID entry with the primary key could not be found
	 */
	public static DistinctUserIdEntry findByPrimaryKey(
			long distinctUserIdEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchDistinctUserIdEntryException {

		return getPersistence().findByPrimaryKey(distinctUserIdEntryId);
	}

	/**
	 * Returns the distinct user ID entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param distinctUserIdEntryId the primary key of the distinct user ID entry
	 * @return the distinct user ID entry, or <code>null</code> if a distinct user ID entry with the primary key could not be found
	 */
	public static DistinctUserIdEntry fetchByPrimaryKey(
		long distinctUserIdEntryId) {

		return getPersistence().fetchByPrimaryKey(distinctUserIdEntryId);
	}

	public static DistinctUserIdEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		DistinctUserIdEntryPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile DistinctUserIdEntryPersistence _persistence;

}
// LIFERAY-SERVICE-BUILDER-HASH:1469408037