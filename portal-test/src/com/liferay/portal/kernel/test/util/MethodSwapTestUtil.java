/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.test.util;

import java.lang.reflect.Method;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.MemberSubstitution;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;

/**
 * @author Jiefeng Wu
 */
public class MethodSwapTestUtil {

	public static Class<?> swapAndRunStaticBlock(
			String className, Method method, Method swapMethod)
		throws ClassNotFoundException {

		Class<?> swapClass = swapMethod.getDeclaringClass();

		ClassLoader classLoader = swapClass.getClassLoader();

		ClassFileLocator classFileLocator = ClassFileLocator.ForClassLoader.of(
			classLoader);

		TypePool typePool = TypePool.Default.of(classFileLocator);

		ByteBuddy byteBuddy = new ByteBuddy();

		DynamicType.Builder<?> builder = byteBuddy.redefine(
			typePool.describe(
				className
			).resolve(),
			classFileLocator);

		builder = builder.visit(
			MemberSubstitution.relaxed(
			).method(
				ElementMatchers.is(method)
			).replaceWith(
				swapMethod
			).on(
				ElementMatchers.isTypeInitializer()
			));

		DynamicType.Unloaded<?> unloaded = builder.make();

		DynamicType.Loaded<?> loaded = unloaded.load(
			classLoader, ClassLoadingStrategy.Default.CHILD_FIRST);

		Class<?> clazz = loaded.getLoaded();

		return Class.forName(className, true, clazz.getClassLoader());
	}

}