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
import com.liferay.portal.tools.service.builder.test.exception.NoSuchDistinctUserIdEntryException;
import com.liferay.portal.tools.service.builder.test.model.DistinctUserIdEntry;
import com.liferay.portal.tools.service.builder.test.model.DistinctUserIdEntryTable;
import com.liferay.portal.tools.service.builder.test.model.impl.DistinctUserIdEntryImpl;
import com.liferay.portal.tools.service.builder.test.model.impl.DistinctUserIdEntryModelImpl;
import com.liferay.portal.tools.service.builder.test.service.persistence.DistinctUserIdEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.DistinctUserIdEntryUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * The persistence implementation for the distinct user ID entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DistinctUserIdEntryPersistenceImpl
	extends BasePersistenceImpl
		<DistinctUserIdEntry, NoSuchDistinctUserIdEntryException>
	implements DistinctUserIdEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DistinctUserIdEntryUtil</code> to access the distinct user ID entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DistinctUserIdEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	public DistinctUserIdEntryPersistenceImpl() {
		setModelClass(DistinctUserIdEntry.class);

		setModelImplClass(DistinctUserIdEntryImpl.class);
		setModelPKClass(long.class);

		setTable(DistinctUserIdEntryTable.INSTANCE);
	}

	/**
	 * Creates a new distinct user ID entry with the primary key. Does not add the distinct user ID entry to the database.
	 *
	 * @param distinctUserIdEntryId the primary key for the new distinct user ID entry
	 * @return the new distinct user ID entry
	 */
	@Override
	public DistinctUserIdEntry create(long distinctUserIdEntryId) {
		DistinctUserIdEntry distinctUserIdEntry = new DistinctUserIdEntryImpl();

		distinctUserIdEntry.setNew(true);
		distinctUserIdEntry.setPrimaryKey(distinctUserIdEntryId);

		return distinctUserIdEntry;
	}

	/**
	 * Removes the distinct user ID entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param distinctUserIdEntryId the primary key of the distinct user ID entry
	 * @return the distinct user ID entry that was removed
	 * @throws NoSuchDistinctUserIdEntryException if a distinct user ID entry with the primary key could not be found
	 */
	@Override
	public DistinctUserIdEntry remove(long distinctUserIdEntryId)
		throws NoSuchDistinctUserIdEntryException {

		return remove((Serializable)distinctUserIdEntryId);
	}

	@Override
	protected DistinctUserIdEntry removeImpl(
		DistinctUserIdEntry distinctUserIdEntry) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(distinctUserIdEntry)) {
				distinctUserIdEntry = (DistinctUserIdEntry)session.get(
					DistinctUserIdEntryImpl.class,
					distinctUserIdEntry.getPrimaryKeyObj());
			}

			if (distinctUserIdEntry != null) {
				session.delete(distinctUserIdEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (distinctUserIdEntry != null) {
			clearCache(distinctUserIdEntry);
		}

		return distinctUserIdEntry;
	}

	@Override
	public DistinctUserIdEntry updateImpl(
		DistinctUserIdEntry distinctUserIdEntry) {

		boolean isNew = distinctUserIdEntry.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(distinctUserIdEntry);
			}
			else {
				distinctUserIdEntry = (DistinctUserIdEntry)session.merge(
					distinctUserIdEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		cacheUniqueFindersResult(distinctUserIdEntry, false);

		if (isNew) {
			distinctUserIdEntry.setNew(false);
		}

		distinctUserIdEntry.resetOriginalValues();

		return distinctUserIdEntry;
	}

	/**
	 * Returns the distinct user ID entry with the primary key or throws a <code>NoSuchDistinctUserIdEntryException</code> if it could not be found.
	 *
	 * @param distinctUserIdEntryId the primary key of the distinct user ID entry
	 * @return the distinct user ID entry
	 * @throws NoSuchDistinctUserIdEntryException if a distinct user ID entry with the primary key could not be found
	 */
	@Override
	public DistinctUserIdEntry findByPrimaryKey(long distinctUserIdEntryId)
		throws NoSuchDistinctUserIdEntryException {

		return findByPrimaryKey((Serializable)distinctUserIdEntryId);
	}

	/**
	 * Returns the distinct user ID entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param distinctUserIdEntryId the primary key of the distinct user ID entry
	 * @return the distinct user ID entry, or <code>null</code> if a distinct user ID entry with the primary key could not be found
	 */
	@Override
	public DistinctUserIdEntry fetchByPrimaryKey(long distinctUserIdEntryId) {
		return fetchByPrimaryKey((Serializable)distinctUserIdEntryId);
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "distinctUserIdEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DISTINCTUSERIDENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DistinctUserIdEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the distinct user ID entry persistence.
	 */
	public void afterPropertiesSet() {
		DistinctUserIdEntryUtil.setPersistence(this);
	}

	public void destroy() {
		DistinctUserIdEntryUtil.setPersistence(null);

		entityCache.removeCache(DistinctUserIdEntryImpl.class.getName());
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DISTINCTUSERIDENTRY =
		"SELECT distinctUserIdEntry FROM DistinctUserIdEntry distinctUserIdEntry";

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}
// LIFERAY-SERVICE-BUILDER-HASH:-1707778269