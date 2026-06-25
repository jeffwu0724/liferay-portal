/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.tools.service.builder.test.model.RenamedPKColumnEntry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the renamed pk column entry service. This utility wraps <code>com.liferay.portal.tools.service.builder.test.service.persistence.impl.RenamedPKColumnEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RenamedPKColumnEntryPersistence
 * @generated
 */
public class RenamedPKColumnEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#cacheResult(List)
	 */
	public static void cacheResult(
		List<RenamedPKColumnEntry> renamedPKColumnEntries) {

		getPersistence().cacheResult(renamedPKColumnEntries);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#cacheResult(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void cacheResult(RenamedPKColumnEntry renamedPKColumnEntry) {
		getPersistence().cacheResult(renamedPKColumnEntry);
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
	public static void clearCache(RenamedPKColumnEntry renamedPKColumnEntry) {
		getPersistence().clearCache(renamedPKColumnEntry);
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
	public static Map<Serializable, RenamedPKColumnEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<RenamedPKColumnEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RenamedPKColumnEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RenamedPKColumnEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RenamedPKColumnEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RenamedPKColumnEntry update(
		RenamedPKColumnEntry renamedPKColumnEntry) {

		return getPersistence().update(renamedPKColumnEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RenamedPKColumnEntry update(
		RenamedPKColumnEntry renamedPKColumnEntry,
		ServiceContext serviceContext) {

		return getPersistence().update(renamedPKColumnEntry, serviceContext);
	}

	/**
	 * Creates a new renamed pk column entry with the primary key. Does not add the renamed pk column entry to the database.
	 *
	 * @param renamedPKColumnEntryId the primary key for the new renamed pk column entry
	 * @return the new renamed pk column entry
	 */
	public static RenamedPKColumnEntry create(long renamedPKColumnEntryId) {
		return getPersistence().create(renamedPKColumnEntryId);
	}

	/**
	 * Removes the renamed pk column entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param renamedPKColumnEntryId the primary key of the renamed pk column entry
	 * @return the renamed pk column entry that was removed
	 * @throws NoSuchRenamedPKColumnEntryException if a renamed pk column entry with the primary key could not be found
	 */
	public static RenamedPKColumnEntry remove(long renamedPKColumnEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchRenamedPKColumnEntryException {

		return getPersistence().remove(renamedPKColumnEntryId);
	}

	public static RenamedPKColumnEntry updateImpl(
		RenamedPKColumnEntry renamedPKColumnEntry) {

		return getPersistence().updateImpl(renamedPKColumnEntry);
	}

	/**
	 * Returns the renamed pk column entry with the primary key or throws a <code>NoSuchRenamedPKColumnEntryException</code> if it could not be found.
	 *
	 * @param renamedPKColumnEntryId the primary key of the renamed pk column entry
	 * @return the renamed pk column entry
	 * @throws NoSuchRenamedPKColumnEntryException if a renamed pk column entry with the primary key could not be found
	 */
	public static RenamedPKColumnEntry findByPrimaryKey(
			long renamedPKColumnEntryId)
		throws com.liferay.portal.tools.service.builder.test.exception.
			NoSuchRenamedPKColumnEntryException {

		return getPersistence().findByPrimaryKey(renamedPKColumnEntryId);
	}

	/**
	 * Returns the renamed pk column entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param renamedPKColumnEntryId the primary key of the renamed pk column entry
	 * @return the renamed pk column entry, or <code>null</code> if a renamed pk column entry with the primary key could not be found
	 */
	public static RenamedPKColumnEntry fetchByPrimaryKey(
		long renamedPKColumnEntryId) {

		return getPersistence().fetchByPrimaryKey(renamedPKColumnEntryId);
	}

	public static RenamedPKColumnEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		RenamedPKColumnEntryPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile RenamedPKColumnEntryPersistence _persistence;

}
// LIFERAY-SERVICE-BUILDER-HASH:-2036737942