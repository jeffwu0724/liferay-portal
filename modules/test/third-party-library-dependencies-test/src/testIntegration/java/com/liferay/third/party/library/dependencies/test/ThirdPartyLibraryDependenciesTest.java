/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.third.party.library.dependencies.test;

import static org.osgi.framework.wiring.BundleRevision.PACKAGE_NAMESPACE;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleRevision;
import org.osgi.framework.wiring.BundleWire;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.resource.Capability;

/**
 * @author Jiefeng Wu
 */
@RunWith(Arquillian.class)
public class ThirdPartyLibraryDependenciesTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() {
		Bundle bundle = FrameworkUtil.getBundle(
			ThirdPartyLibraryDependenciesTest.class);

		_bundleContext = bundle.getBundleContext();
	}

	@Test
	public void test() {
		if (_bundleContext == null) {
			throw new IllegalArgumentException();
		}

		Bundle[] bundles = _bundleContext.getBundles();

		for (Bundle curBundle : bundles) {
			String curBundleSymbolicName = curBundle.getSymbolicName();
			if (!curBundleSymbolicName.contains(
					"com.liferay.shared.dependencies."
				)) {

				continue;
			}

			BundleWiring bundleWiring = curBundle.adapt(BundleWiring.class);

			HashSet<String> set = _getInUsedExportPackage(bundleWiring);

			for (String s : _getExportPackageFromManifest(curBundle)) {
				if (!set.contains(s)) {
					Assert.fail(
						curBundle.getSymbolicName() +
							" contains unused export-package: " + s);
				}
			}
		}
	}

	private List<String> _getExportPackageFromManifest(Bundle bundle) {
		Dictionary<String, String> bundleHeaders = bundle.getHeaders();

		String rawExportPackages = bundleHeaders.get("Export-Package");

		List<String> exportPackageFromManifest = new ArrayList<>();

		for (String rawExportPackage : rawExportPackages.split("\",")) {
			String[] filteredExportPackage = rawExportPackage.split(";");

			exportPackageFromManifest.add(filteredExportPackage[0]);
		}

		return exportPackageFromManifest;
	}

	private HashSet<String> _getInUsedExportPackage(BundleWiring bundleWiring) {
		HashSet<String> inUsedExportPackages = new HashSet<>();

		if (bundleWiring != null) {
			for (BundleWire exportWire :
					bundleWiring.getProvidedWires(
						BundleRevision.PACKAGE_NAMESPACE)) {

				Capability capability = exportWire.getCapability();

				Map<String, Object> attributes = capability.getAttributes();

				String exportedPackage = (String)attributes.get(
					BundleRevision.PACKAGE_NAMESPACE);

				inUsedExportPackages.add(exportedPackage);
			}
		}

		return inUsedExportPackages;
	}

	private static BundleContext _bundleContext;

}