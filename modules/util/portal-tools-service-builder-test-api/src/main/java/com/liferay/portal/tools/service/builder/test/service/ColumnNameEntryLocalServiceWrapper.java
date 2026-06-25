/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link ColumnNameEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ColumnNameEntryLocalService
 * @generated
 */
public class ColumnNameEntryLocalServiceWrapper
	implements ColumnNameEntryLocalService,
			   ServiceWrapper<ColumnNameEntryLocalService> {

	public ColumnNameEntryLocalServiceWrapper() {
		this(null);
	}

	public ColumnNameEntryLocalServiceWrapper(
		ColumnNameEntryLocalService columnNameEntryLocalService) {

		_columnNameEntryLocalService = columnNameEntryLocalService;
	}

	/**
	 * Adds the column name entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ColumnNameEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param columnNameEntry the column name entry
	 * @return the column name entry that was added
	 */
	@Override
	public com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
		addColumnNameEntry(
			com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
				columnNameEntry) {

		return _columnNameEntryLocalService.addColumnNameEntry(columnNameEntry);
	}

	/**
	 * Creates a new column name entry with the primary key. Does not add the column name entry to the database.
	 *
	 * @param columnNameEntryId the primary key for the new column name entry
	 * @return the new column name entry
	 */
	@Override
	public com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
		createColumnNameEntry(long columnNameEntryId) {

		return _columnNameEntryLocalService.createColumnNameEntry(
			columnNameEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _columnNameEntryLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the column name entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ColumnNameEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param columnNameEntry the column name entry
	 * @return the column name entry that was removed
	 */
	@Override
	public com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
		deleteColumnNameEntry(
			com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
				columnNameEntry) {

		return _columnNameEntryLocalService.deleteColumnNameEntry(
			columnNameEntry);
	}

	/**
	 * Deletes the column name entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ColumnNameEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param columnNameEntryId the primary key of the column name entry
	 * @return the column name entry that was removed
	 * @throws PortalException if a column name entry with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
			deleteColumnNameEntry(long columnNameEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _columnNameEntryLocalService.deleteColumnNameEntry(
			columnNameEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _columnNameEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _columnNameEntryLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _columnNameEntryLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _columnNameEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _columnNameEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.ColumnNameEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _columnNameEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.ColumnNameEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _columnNameEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _columnNameEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _columnNameEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
		fetchColumnNameEntry(long columnNameEntryId) {

		return _columnNameEntryLocalService.fetchColumnNameEntry(
			columnNameEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _columnNameEntryLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the column name entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.ColumnNameEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of column name entries
	 * @param end the upper bound of the range of column name entries (not inclusive)
	 * @return the range of column name entries
	 */
	@Override
	public java.util.List
		<com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry>
			getColumnNameEntries(int start, int end) {

		return _columnNameEntryLocalService.getColumnNameEntries(start, end);
	}

	/**
	 * Returns the number of column name entries.
	 *
	 * @return the number of column name entries
	 */
	@Override
	public int getColumnNameEntriesCount() {
		return _columnNameEntryLocalService.getColumnNameEntriesCount();
	}

	/**
	 * Returns the column name entry with the primary key.
	 *
	 * @param columnNameEntryId the primary key of the column name entry
	 * @return the column name entry
	 * @throws PortalException if a column name entry with the primary key could not be found
	 */
	@Override
	public com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
			getColumnNameEntry(long columnNameEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _columnNameEntryLocalService.getColumnNameEntry(
			columnNameEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _columnNameEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _columnNameEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _columnNameEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the column name entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ColumnNameEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param columnNameEntry the column name entry
	 * @return the column name entry that was updated
	 */
	@Override
	public com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
		updateColumnNameEntry(
			com.liferay.portal.tools.service.builder.test.model.ColumnNameEntry
				columnNameEntry) {

		return _columnNameEntryLocalService.updateColumnNameEntry(
			columnNameEntry);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _columnNameEntryLocalService.getBasePersistence();
	}

	@Override
	public ColumnNameEntryLocalService getWrappedService() {
		return _columnNameEntryLocalService;
	}

	@Override
	public void setWrappedService(
		ColumnNameEntryLocalService columnNameEntryLocalService) {

		_columnNameEntryLocalService = columnNameEntryLocalService;
	}

	private ColumnNameEntryLocalService _columnNameEntryLocalService;

}
// LIFERAY-SERVICE-BUILDER-HASH:-393177583