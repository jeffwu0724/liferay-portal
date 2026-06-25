/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchRenamedPKColumnEntryException;
import com.liferay.portal.tools.service.builder.test.model.RenamedPKColumnEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the renamed pk column entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RenamedPKColumnEntryUtil
 * @generated
 */
@ProviderType
public interface RenamedPKColumnEntryPersistence
	extends BasePersistence<RenamedPKColumnEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RenamedPKColumnEntryUtil} to access the renamed pk column entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Creates a new renamed pk column entry with the primary key. Does not add the renamed pk column entry to the database.
	 *
	 * @param renamedPKColumnEntryId the primary key for the new renamed pk column entry
	 * @return the new renamed pk column entry
	 */
	public RenamedPKColumnEntry create(long renamedPKColumnEntryId);

	/**
	 * Removes the renamed pk column entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param renamedPKColumnEntryId the primary key of the renamed pk column entry
	 * @return the renamed pk column entry that was removed
	 * @throws NoSuchRenamedPKColumnEntryException if a renamed pk column entry with the primary key could not be found
	 */
	public RenamedPKColumnEntry remove(long renamedPKColumnEntryId)
		throws NoSuchRenamedPKColumnEntryException;

	public RenamedPKColumnEntry updateImpl(
		RenamedPKColumnEntry renamedPKColumnEntry);

	/**
	 * Returns the renamed pk column entry with the primary key or throws a <code>NoSuchRenamedPKColumnEntryException</code> if it could not be found.
	 *
	 * @param renamedPKColumnEntryId the primary key of the renamed pk column entry
	 * @return the renamed pk column entry
	 * @throws NoSuchRenamedPKColumnEntryException if a renamed pk column entry with the primary key could not be found
	 */
	public RenamedPKColumnEntry findByPrimaryKey(long renamedPKColumnEntryId)
		throws NoSuchRenamedPKColumnEntryException;

	/**
	 * Returns the renamed pk column entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param renamedPKColumnEntryId the primary key of the renamed pk column entry
	 * @return the renamed pk column entry, or <code>null</code> if a renamed pk column entry with the primary key could not be found
	 */
	public RenamedPKColumnEntry fetchByPrimaryKey(long renamedPKColumnEntryId);

}
// LIFERAY-SERVICE-BUILDER-HASH:252429274