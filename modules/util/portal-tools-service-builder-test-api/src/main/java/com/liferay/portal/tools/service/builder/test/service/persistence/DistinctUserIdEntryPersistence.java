/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchDistinctUserIdEntryException;
import com.liferay.portal.tools.service.builder.test.model.DistinctUserIdEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the distinct user ID entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DistinctUserIdEntryUtil
 * @generated
 */
@ProviderType
public interface DistinctUserIdEntryPersistence
	extends BasePersistence<DistinctUserIdEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DistinctUserIdEntryUtil} to access the distinct user ID entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Creates a new distinct user ID entry with the primary key. Does not add the distinct user ID entry to the database.
	 *
	 * @param distinctUserIdEntryId the primary key for the new distinct user ID entry
	 * @return the new distinct user ID entry
	 */
	public DistinctUserIdEntry create(long distinctUserIdEntryId);

	/**
	 * Removes the distinct user ID entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param distinctUserIdEntryId the primary key of the distinct user ID entry
	 * @return the distinct user ID entry that was removed
	 * @throws NoSuchDistinctUserIdEntryException if a distinct user ID entry with the primary key could not be found
	 */
	public DistinctUserIdEntry remove(long distinctUserIdEntryId)
		throws NoSuchDistinctUserIdEntryException;

	public DistinctUserIdEntry updateImpl(
		DistinctUserIdEntry distinctUserIdEntry);

	/**
	 * Returns the distinct user ID entry with the primary key or throws a <code>NoSuchDistinctUserIdEntryException</code> if it could not be found.
	 *
	 * @param distinctUserIdEntryId the primary key of the distinct user ID entry
	 * @return the distinct user ID entry
	 * @throws NoSuchDistinctUserIdEntryException if a distinct user ID entry with the primary key could not be found
	 */
	public DistinctUserIdEntry findByPrimaryKey(long distinctUserIdEntryId)
		throws NoSuchDistinctUserIdEntryException;

	/**
	 * Returns the distinct user ID entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param distinctUserIdEntryId the primary key of the distinct user ID entry
	 * @return the distinct user ID entry, or <code>null</code> if a distinct user ID entry with the primary key could not be found
	 */
	public DistinctUserIdEntry fetchByPrimaryKey(long distinctUserIdEntryId);

}
// LIFERAY-SERVICE-BUILDER-HASH:1439173134