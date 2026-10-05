/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Map;

/**
 * Provides the remote service utility for ResourcePermission. This utility wraps
 * <code>com.liferay.portal.service.impl.ResourcePermissionServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ResourcePermissionService
 * @generated
 */
public class ResourcePermissionServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.ResourcePermissionServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Grants the role permission at the scope to perform the action on
	 * resources of the type. Existing actions are retained.
	 *
	 * <p>
	 * This method cannot be used to grant individual scope permissions, but is
	 * only intended for adding permissions at the company, group, and
	 * group-template scopes. For example, this method could be used to grant a
	 * company scope permission to edit message board posts.
	 * </p>
	 *
	 * <p>
	 * If a company scope permission is granted to resources that the role
	 * already had group scope permissions to, the group scope permissions are
	 * deleted. Likewise, if a group scope permission is granted to resources
	 * that the role already had company scope permissions to, the company scope
	 * permissions are deleted. Be aware that this latter behavior can result in
	 * an overall reduction in permissions for the role.
	 * </p>
	 *
	 * <p>
	 * Depending on the scope, the value of <code>primKey</code> will have
	 * different meanings. For more information, see {@link
	 * com.liferay.portal.model.impl.ResourcePermissionImpl}.
	 * </p>
	 *
	 * @param groupId the primary key of the group
	 * @param companyId the primary key of the company
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param scope the scope. This method only supports company, group, and
	 group-template scope.
	 * @param primKey the primary key
	 * @param roleId the primary key of the role
	 * @param actionId the action ID
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #addRoleResourcePermission(long, String, int, long, String)}
	 */
	@Deprecated
	public static void addResourcePermission(
			long groupId, long companyId, String name, int scope,
			String primKey, long roleId, String actionId)
		throws PortalException {

		getService().addResourcePermission(
			groupId, companyId, name, scope, primKey, roleId, actionId);
	}

	/**
	 * Grants the role permission to perform the action on every resource of
	 * the type within the scope. Existing actions are retained.
	 *
	 * <p>
	 * Only the company, group, and group template scopes are supported. Use
	 * {@link #setIndividualResourcePermissions(String, String, Map)} for a
	 * single resource.
	 * </p>
	 *
	 * <p>
	 * If a company scope permission is granted to resources that the role
	 * already had group scope permissions to, the group scope permissions are
	 * deleted. Likewise, if a group scope permission is granted to resources
	 * that the role already had company scope permissions to, the company scope
	 * permissions are deleted. Be aware that this latter behavior can result in
	 * an overall reduction in permissions for the role.
	 * </p>
	 *
	 * @param roleId the primary key of the role
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param scope the company, group, or group template scope
	 * @param targetGroupId the primary key of the group the permission covers
	 at the group scope, or <code>0</code> at any other scope
	 * @param actionId the action ID
	 */
	public static void addRoleResourcePermission(
			long roleId, String name, int scope, long targetGroupId,
			String actionId)
		throws PortalException {

		getService().addRoleResourcePermission(
			roleId, name, scope, targetGroupId, actionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Revokes permission at the scope from the role to perform the action on
	 * resources of the type. For example, this method could be used to revoke a
	 * group scope permission to edit blog posts.
	 *
	 * <p>
	 * Depending on the scope, the value of <code>primKey</code> will have
	 * different meanings. For more information, see {@link
	 * com.liferay.portal.model.impl.ResourcePermissionImpl}.
	 * </p>
	 *
	 * @param groupId the primary key of the group
	 * @param companyId the primary key of the company
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param scope the scope
	 * @param primKey the primary key
	 * @param roleId the primary key of the role
	 * @param actionId the action ID
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #removeRoleResourcePermission(long, String, int, long,
	 String)}
	 */
	@Deprecated
	public static void removeResourcePermission(
			long groupId, long companyId, String name, int scope,
			String primKey, long roleId, String actionId)
		throws PortalException {

		getService().removeResourcePermission(
			groupId, companyId, name, scope, primKey, roleId, actionId);
	}

	/**
	 * Revokes all permissions at the scope from the role to perform the action
	 * on resources of the type. For example, this method could be used to
	 * revoke all individual scope permissions to edit blog posts from site
	 * members.
	 *
	 * @param groupId the primary key of the group
	 * @param companyId the primary key of the company
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param scope the scope
	 * @param roleId the primary key of the role
	 * @param actionId the action ID
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #removeRoleResourcePermissions(long, String, int, String)}
	 */
	@Deprecated
	public static void removeResourcePermissions(
			long groupId, long companyId, String name, int scope, long roleId,
			String actionId)
		throws PortalException {

		getService().removeResourcePermissions(
			groupId, companyId, name, scope, roleId, actionId);
	}

	/**
	 * Revokes the role's permission to perform the action on every resource of
	 * the type within the scope.
	 *
	 * <p>
	 * Only the company, group, and group template scopes are supported. Use
	 * {@link #setIndividualResourcePermissions(String, String, Map)} for a
	 * single resource.
	 * </p>
	 *
	 * @param roleId the primary key of the role
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param scope the company, group, or group template scope
	 * @param targetGroupId the primary key of the group the permission covers
	 at the group scope, or <code>0</code> at any other scope
	 * @param actionId the action ID
	 */
	public static void removeRoleResourcePermission(
			long roleId, String name, int scope, long targetGroupId,
			String actionId)
		throws PortalException {

		getService().removeRoleResourcePermission(
			roleId, name, scope, targetGroupId, actionId);
	}

	/**
	 * Revokes the role's permission to perform the action on resources of the
	 * type in every group of the scope. For example, this method could be used
	 * to revoke the role's group scope permissions to edit blog posts in all
	 * groups.
	 *
	 * @param roleId the primary key of the role
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param scope the company, group, or group template scope
	 * @param actionId the action ID
	 */
	public static void removeRoleResourcePermissions(
			long roleId, String name, int scope, String actionId)
		throws PortalException {

		getService().removeRoleResourcePermissions(
			roleId, name, scope, actionId);
	}

	/**
	 * Updates the role's permissions at the scope, setting the actions that can
	 * be performed on resources of the type. Existing actions are replaced.
	 *
	 * <p>
	 * This method can be used to set permissions at any scope, but it is
	 * generally only used at the individual scope. For example, it could be
	 * used to set the guest permissions on a blog post.
	 * </p>
	 *
	 * <p>
	 * Depending on the scope, the value of <code>primKey</code> will have
	 * different meanings. For more information, see {@link
	 * com.liferay.portal.model.impl.ResourcePermissionImpl}.
	 * </p>
	 *
	 * @param groupId the primary key of the group
	 * @param companyId the primary key of the company
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param primKey the primary key
	 * @param roleId the primary key of the role
	 * @param actionIds the action IDs of the actions
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #setIndividualResourcePermissions(String, String, long,
	 String[])}
	 */
	@Deprecated
	public static void setIndividualResourcePermissions(
			long groupId, long companyId, String name, String primKey,
			long roleId, String[] actionIds)
		throws PortalException {

		getService().setIndividualResourcePermissions(
			groupId, companyId, name, primKey, roleId, actionIds);
	}

	/**
	 * Updates the role's permissions at the scope, setting the actions that can
	 * be performed on resources of the type. Existing actions are replaced.
	 *
	 * <p>
	 * This method can be used to set permissions at any scope, but it is
	 * generally only used at the individual scope. For example, it could be
	 * used to set the guest permissions on a blog post.
	 * </p>
	 *
	 * <p>
	 * Depending on the scope, the value of <code>primKey</code> will have
	 * different meanings. For more information, see {@link
	 * com.liferay.portal.model.impl.ResourcePermissionImpl}.
	 * </p>
	 *
	 * @param groupId the primary key of the group
	 * @param companyId the primary key of the company
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param primKey the primary key
	 * @param roleIdsToActionIds a map of role IDs to action IDs of the actions
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link
	 #setIndividualResourcePermissions(String, String, Map)}
	 */
	@Deprecated
	public static void setIndividualResourcePermissions(
			long groupId, long companyId, String name, String primKey,
			Map<Long, String[]> roleIdsToActionIds)
		throws PortalException {

		getService().setIndividualResourcePermissions(
			groupId, companyId, name, primKey, roleIdsToActionIds);
	}

	/**
	 * Replaces the actions the role can perform on the single resource.
	 *
	 * <p>
	 * The permission check runs against the group the resource belongs to,
	 * which is derived from the resource and never supplied by the caller.
	 * </p>
	 *
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param primKey the primary key of the resource
	 * @param roleId the primary key of the role
	 * @param actionIds the action IDs of the actions
	 */
	public static void setIndividualResourcePermissions(
			String name, String primKey, long roleId, String[] actionIds)
		throws PortalException {

		getService().setIndividualResourcePermissions(
			name, primKey, roleId, actionIds);
	}

	/**
	 * Replaces the actions each role can perform on the single resource.
	 *
	 * <p>
	 * The permission check runs against the group the resource belongs to,
	 * which is derived from the resource and never supplied by the caller.
	 * </p>
	 *
	 * @param name the resource's name, which can be either a class name or a
	 portlet ID
	 * @param primKey the primary key of the resource
	 * @param roleIdsToActionIds a map of role IDs to action IDs of the actions
	 */
	public static void setIndividualResourcePermissions(
			String name, String primKey, Map<Long, String[]> roleIdsToActionIds)
		throws PortalException {

		getService().setIndividualResourcePermissions(
			name, primKey, roleIdsToActionIds);
	}

	public static ResourcePermissionService getService() {
		return _service;
	}

	public static void setService(ResourcePermissionService service) {
		_service = service;
	}

	private static volatile ResourcePermissionService _service;

}
// LIFERAY-SERVICE-BUILDER-HASH:1729705030