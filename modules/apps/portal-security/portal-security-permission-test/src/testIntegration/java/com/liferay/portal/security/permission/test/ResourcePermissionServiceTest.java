/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.permission.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.test.util.DDMStructureTestUtil;
import com.liferay.journal.constants.JournalConstants;
import com.liferay.journal.model.JournalArticle;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.runtime.ServiceComponentRuntime;
import org.osgi.service.component.runtime.dto.ComponentDescriptionDTO;
import org.osgi.util.promise.Promise;

/**
 * @author Jiefeng Wu
 */
@RunWith(Arquillian.class)
public class ResourcePermissionServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_role = RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		_user = UserTestUtil.addGroupAdminUser(_group);

		_originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		_setPermissionChecker(_user);
	}

	@After
	public void tearDown() {
		PermissionThreadLocal.setPermissionChecker(_originalPermissionChecker);
	}

	@Test
	public void testAddRoleResourcePermission() throws Exception {
		_setPermissionChecker(TestPropsValues.getUser());

		_resourcePermissionService.addRoleResourcePermission(
			_role.getRoleId(), JournalConstants.RESOURCE_NAME,
			ResourceConstants.SCOPE_COMPANY, 0, ActionKeys.VIEW);

		_assertHasResourcePermission(
			ResourceConstants.SCOPE_COMPANY,
			String.valueOf(_role.getCompanyId()));

		_resourcePermissionService.addRoleResourcePermission(
			_role.getRoleId(), JournalConstants.RESOURCE_NAME,
			ResourceConstants.SCOPE_GROUP, _group.getGroupId(),
			ActionKeys.VIEW);

		_assertHasResourcePermission(
			ResourceConstants.SCOPE_GROUP, String.valueOf(_group.getGroupId()));

		_resourcePermissionService.addRoleResourcePermission(
			_role.getRoleId(), JournalConstants.RESOURCE_NAME,
			ResourceConstants.SCOPE_GROUP_TEMPLATE, 0, ActionKeys.VIEW);

		_assertHasResourcePermission(
			ResourceConstants.SCOPE_GROUP_TEMPLATE, "0");

		_resourcePermissionService.removeRoleResourcePermission(
			_role.getRoleId(), JournalConstants.RESOURCE_NAME,
			ResourceConstants.SCOPE_GROUP, _group.getGroupId(),
			ActionKeys.VIEW);

		Assert.assertFalse(
			_resourcePermissionLocalService.hasResourcePermission(
				_role.getCompanyId(), JournalConstants.RESOURCE_NAME,
				ResourceConstants.SCOPE_GROUP,
				String.valueOf(_group.getGroupId()), _role.getRoleId(),
				ActionKeys.VIEW));
	}

	@Test
	public void testAddRoleResourcePermissionAsGroupAdmin() throws Exception {
		_assertAddRoleResourcePermissionFails();
	}

	@Test
	public void testAddRoleResourcePermissionAsGroupAdminWithoutRoleModelResourcePermission()
		throws Exception {

		ComponentDescriptionDTO componentDescriptionDTO =
			_serviceComponentRuntime.getComponentDescriptionDTO(
				FrameworkUtil.getBundle(
					_roleModelResourcePermission.getClass()),
				_roleModelResourcePermission.getClass(
				).getName());

		Promise<Void> voidPromise = _serviceComponentRuntime.disableComponent(
			componentDescriptionDTO);

		voidPromise.getValue();

		try {
			_assertAddRoleResourcePermissionFails();
		}
		finally {
			voidPromise = _serviceComponentRuntime.enableComponent(
				componentDescriptionDTO);

			voidPromise.getValue();
		}
	}

	@Test
	public void testAddRoleResourcePermissionWithInvalidTargetGroup()
		throws Exception {

		_setPermissionChecker(TestPropsValues.getUser());

		_assertAddRoleResourcePermissionFails(
			ResourceConstants.SCOPE_COMPANY, _group.getGroupId());
		_assertAddRoleResourcePermissionFails(ResourceConstants.SCOPE_GROUP, 0);
		_assertAddRoleResourcePermissionFails(
			ResourceConstants.SCOPE_GROUP_TEMPLATE, _group.getGroupId());
	}

	@Test
	public void testRemoveResourcePermissionOnIndividualScope()
		throws Exception {

		_otherGroup = GroupTestUtil.addGroup();

		String primKey = String.valueOf(_otherGroup.getGroupId());

		_resourcePermissionLocalService.setResourcePermissions(
			_role.getCompanyId(), Group.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, primKey, _role.getRoleId(),
			new String[] {ActionKeys.VIEW});

		_setPermissionChecker(_addRoleManagerUser());

		try {
			_resourcePermissionService.removeResourcePermission(
				0, _role.getCompanyId(), Group.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, primKey, _role.getRoleId(),
				ActionKeys.VIEW);

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
		}

		try {
			_resourcePermissionService.removeResourcePermissions(
				0, _role.getCompanyId(), Group.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, _role.getRoleId(),
				ActionKeys.VIEW);

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
		}

		try {
			_resourcePermissionService.removeRoleResourcePermissions(
				_role.getRoleId(), Group.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, ActionKeys.VIEW);

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
		}

		Assert.assertTrue(
			_resourcePermissionLocalService.hasResourcePermission(
				_role.getCompanyId(), Group.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, primKey, _role.getRoleId(),
				ActionKeys.VIEW));
	}

	@Test
	public void testSetIndividualResourcePermissionsOnCompanyResource()
		throws Exception {

		_assertSetIndividualResourcePermissionsFails(
			PortletKeys.SERVER_ADMIN, PortletKeys.SERVER_ADMIN);
	}

	@Test
	public void testSetIndividualResourcePermissionsOnCompositeModelResource()
		throws Exception {

		DDMStructure ddmStructure = DDMStructureTestUtil.addStructure(
			_group.getGroupId(), JournalArticle.class.getName());

		_assertSetIndividualResourcePermissions(
			_group.getGroupId(),
			ResourceActionsUtil.getCompositeModelName(
				DDMStructure.class.getName(), JournalArticle.class.getName()),
			String.valueOf(ddmStructure.getStructureId()));
	}

	@Test
	public void testSetIndividualResourcePermissionsOnGroupResource()
		throws Exception {

		_assertSetIndividualResourcePermissions(
			_group.getGroupId(), JournalConstants.RESOURCE_NAME,
			String.valueOf(_group.getGroupId()));
	}

	@Test
	public void testSetIndividualResourcePermissionsOnLayoutScopeGroupResource()
		throws Exception {

		Layout layout = LayoutTestUtil.addTypePortletLayout(_group);

		Group scopeGroup = GroupTestUtil.addGroup(_user.getUserId(), layout);

		_assertSetIndividualResourcePermissions(
			scopeGroup.getGroupId(), JournalConstants.RESOURCE_NAME,
			String.valueOf(_group.getGroupId()));
	}

	@Test
	public void testSetIndividualResourcePermissionsOnOtherGroupPortletResource()
		throws Exception {

		_otherGroup = GroupTestUtil.addGroup();

		Layout layout = LayoutTestUtil.addTypePortletLayout(_otherGroup);

		_assertSetIndividualResourcePermissionsFails(
			PortletKeys.LOGIN,
			PortletPermissionUtil.getPrimaryKey(
				layout.getPlid(), PortletKeys.LOGIN));
	}

	@Test
	public void testSetIndividualResourcePermissionsOnPortletResource()
		throws Exception {

		Layout layout = LayoutTestUtil.addTypePortletLayout(_group);

		_assertSetIndividualResourcePermissions(
			_group.getGroupId(), PortletKeys.LOGIN,
			PortletPermissionUtil.getPrimaryKey(
				layout.getPlid(), PortletKeys.LOGIN));
	}

	private User _addRoleManagerUser() throws Exception {
		Role role = RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		_resourcePermissionLocalService.setResourcePermissions(
			role.getCompanyId(), Role.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(_role.getRoleId()), role.getRoleId(),
			new String[] {ActionKeys.PERMISSIONS});

		User user = UserTestUtil.addUser();

		_roleLocalService.addUserRole(user.getUserId(), role);

		return user;
	}

	private void _assertAddRoleResourcePermissionFails() throws Exception {
		try {
			_resourcePermissionService.addResourcePermission(
				_group.getGroupId(), _role.getCompanyId(),
				JournalConstants.RESOURCE_NAME, ResourceConstants.SCOPE_COMPANY,
				String.valueOf(_role.getCompanyId()), _role.getRoleId(),
				ActionKeys.VIEW);

			Assert.fail();
		}
		catch (PrincipalException principalException) {
		}

		try {
			_resourcePermissionService.addRoleResourcePermission(
				_role.getRoleId(), JournalConstants.RESOURCE_NAME,
				ResourceConstants.SCOPE_COMPANY, 0, ActionKeys.VIEW);

			Assert.fail();
		}
		catch (PrincipalException principalException) {
		}

		Assert.assertFalse(
			_resourcePermissionLocalService.hasResourcePermission(
				_role.getCompanyId(), JournalConstants.RESOURCE_NAME,
				ResourceConstants.SCOPE_COMPANY,
				String.valueOf(_role.getCompanyId()), _role.getRoleId(),
				ActionKeys.VIEW));
	}

	private void _assertAddRoleResourcePermissionFails(
			int scope, long targetGroupId)
		throws Exception {

		try {
			_resourcePermissionService.addRoleResourcePermission(
				_role.getRoleId(), JournalConstants.RESOURCE_NAME, scope,
				targetGroupId, ActionKeys.VIEW);

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
		}
	}

	private void _assertHasResourcePermission(int scope, String primKey)
		throws Exception {

		Assert.assertTrue(
			_resourcePermissionLocalService.hasResourcePermission(
				_role.getCompanyId(), JournalConstants.RESOURCE_NAME, scope,
				primKey, _role.getRoleId(), ActionKeys.VIEW));
	}

	private void _assertSetIndividualResourcePermissions(
			long groupId, String name, String primKey)
		throws Exception {

		_resourcePermissionService.setIndividualResourcePermissions(
			groupId, _group.getCompanyId(), name, primKey,
			HashMapBuilder.put(
				_role.getRoleId(), new String[] {ActionKeys.VIEW}
			).build());

		Assert.assertTrue(
			_resourcePermissionLocalService.hasResourcePermission(
				_group.getCompanyId(), name, ResourceConstants.SCOPE_INDIVIDUAL,
				primKey, _role.getRoleId(), ActionKeys.VIEW));

		_resourcePermissionService.setIndividualResourcePermissions(
			name, primKey, _role.getRoleId(), new String[0]);

		Assert.assertFalse(
			_resourcePermissionLocalService.hasResourcePermission(
				_group.getCompanyId(), name, ResourceConstants.SCOPE_INDIVIDUAL,
				primKey, _role.getRoleId(), ActionKeys.VIEW));

		_resourcePermissionService.setIndividualResourcePermissions(
			name, primKey,
			HashMapBuilder.put(
				_role.getRoleId(), new String[] {ActionKeys.VIEW}
			).build());

		Assert.assertTrue(
			_resourcePermissionLocalService.hasResourcePermission(
				_group.getCompanyId(), name, ResourceConstants.SCOPE_INDIVIDUAL,
				primKey, _role.getRoleId(), ActionKeys.VIEW));
	}

	private void _assertSetIndividualResourcePermissionsFails(
			String name, String primKey)
		throws Exception {

		try {
			_resourcePermissionService.setIndividualResourcePermissions(
				_group.getGroupId(), _group.getCompanyId(), name, primKey,
				_role.getRoleId(), new String[] {ActionKeys.VIEW});

			Assert.fail();
		}
		catch (Exception exception) {
		}

		try {
			_resourcePermissionService.setIndividualResourcePermissions(
				_group.getGroupId(), _group.getCompanyId(), name, primKey,
				HashMapBuilder.put(
					_role.getRoleId(), new String[] {ActionKeys.VIEW}
				).build());

			Assert.fail();
		}
		catch (Exception exception) {
		}

		try {
			_resourcePermissionService.setIndividualResourcePermissions(
				name, primKey, _role.getRoleId(),
				new String[] {ActionKeys.VIEW});

			Assert.fail();
		}
		catch (Exception exception) {
		}

		try {
			_resourcePermissionService.setIndividualResourcePermissions(
				name, primKey,
				HashMapBuilder.put(
					_role.getRoleId(), new String[] {ActionKeys.VIEW}
				).build());

			Assert.fail();
		}
		catch (Exception exception) {
		}

		Assert.assertFalse(
			_resourcePermissionLocalService.hasResourcePermission(
				_group.getCompanyId(), name, ResourceConstants.SCOPE_INDIVIDUAL,
				primKey, _role.getRoleId(), ActionKeys.VIEW));
	}

	private void _setPermissionChecker(User user) {
		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(user));
	}

	@DeleteAfterTestRun
	private Group _group;

	private PermissionChecker _originalPermissionChecker;

	@DeleteAfterTestRun
	private Group _otherGroup;

	@Inject
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Inject
	private ResourcePermissionService _resourcePermissionService;

	@DeleteAfterTestRun
	private Role _role;

	@Inject
	private RoleLocalService _roleLocalService;

	@Inject(filter = "model.class.name=com.liferay.portal.kernel.model.Role")
	private ModelResourcePermission<Role> _roleModelResourcePermission;

	@Inject
	private ServiceComponentRuntime _serviceComponentRuntime;

	@DeleteAfterTestRun
	private User _user;

}