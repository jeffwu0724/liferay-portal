/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchDateColumnEntryException;
import com.liferay.portal.tools.service.builder.test.model.DateColumnEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the date column entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DateColumnEntryUtil
 * @generated
 */
@ProviderType
public interface DateColumnEntryPersistence
	extends BasePersistence<DateColumnEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DateColumnEntryUtil} to access the date column entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Creates a new date column entry with the primary key. Does not add the date column entry to the database.
	 *
	 * @param dateColumnEntryId the primary key for the new date column entry
	 * @return the new date column entry
	 */
	public DateColumnEntry create(long dateColumnEntryId);

	/**
	 * Removes the date column entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dateColumnEntryId the primary key of the date column entry
	 * @return the date column entry that was removed
	 * @throws NoSuchDateColumnEntryException if a date column entry with the primary key could not be found
	 */
	public DateColumnEntry remove(long dateColumnEntryId)
		throws NoSuchDateColumnEntryException;

	public DateColumnEntry updateImpl(DateColumnEntry dateColumnEntry);

	/**
	 * Returns the date column entry with the primary key or throws a <code>NoSuchDateColumnEntryException</code> if it could not be found.
	 *
	 * @param dateColumnEntryId the primary key of the date column entry
	 * @return the date column entry
	 * @throws NoSuchDateColumnEntryException if a date column entry with the primary key could not be found
	 */
	public DateColumnEntry findByPrimaryKey(long dateColumnEntryId)
		throws NoSuchDateColumnEntryException;

	/**
	 * Returns the date column entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param dateColumnEntryId the primary key of the date column entry
	 * @return the date column entry, or <code>null</code> if a date column entry with the primary key could not be found
	 */
	public DateColumnEntry fetchByPrimaryKey(long dateColumnEntryId);

}
// LIFERAY-SERVICE-BUILDER-HASH:1034251864