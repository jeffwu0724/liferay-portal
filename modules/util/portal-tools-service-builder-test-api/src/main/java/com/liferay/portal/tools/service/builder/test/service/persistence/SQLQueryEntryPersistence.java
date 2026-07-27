/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.tools.service.builder.test.exception.NoSuchSQLQueryEntryException;
import com.liferay.portal.tools.service.builder.test.model.SQLQueryEntry;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the sql query entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SQLQueryEntryUtil
 * @generated
 */
@ProviderType
public interface SQLQueryEntryPersistence
	extends BasePersistence<SQLQueryEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SQLQueryEntryUtil} to access the sql query entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Creates a new sql query entry with the primary key. Does not add the sql query entry to the database.
	 *
	 * @param sqlQueryEntryId the primary key for the new sql query entry
	 * @return the new sql query entry
	 */
	public SQLQueryEntry create(long sqlQueryEntryId);

	/**
	 * Removes the sql query entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sqlQueryEntryId the primary key of the sql query entry
	 * @return the sql query entry that was removed
	 * @throws NoSuchSQLQueryEntryException if a sql query entry with the primary key could not be found
	 */
	public SQLQueryEntry remove(long sqlQueryEntryId)
		throws NoSuchSQLQueryEntryException;

	public SQLQueryEntry updateImpl(SQLQueryEntry sqlQueryEntry);

	/**
	 * Returns the sql query entry with the primary key or throws a <code>NoSuchSQLQueryEntryException</code> if it could not be found.
	 *
	 * @param sqlQueryEntryId the primary key of the sql query entry
	 * @return the sql query entry
	 * @throws NoSuchSQLQueryEntryException if a sql query entry with the primary key could not be found
	 */
	public SQLQueryEntry findByPrimaryKey(long sqlQueryEntryId)
		throws NoSuchSQLQueryEntryException;

	/**
	 * Returns the sql query entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param sqlQueryEntryId the primary key of the sql query entry
	 * @return the sql query entry, or <code>null</code> if a sql query entry with the primary key could not be found
	 */
	public SQLQueryEntry fetchByPrimaryKey(long sqlQueryEntryId);

}
// LIFERAY-SERVICE-BUILDER-HASH:1559912760