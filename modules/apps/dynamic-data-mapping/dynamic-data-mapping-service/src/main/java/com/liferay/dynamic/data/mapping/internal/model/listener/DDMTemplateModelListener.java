/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.model.listener;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.function.BiFunction;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 */
@Component(service = ModelListener.class)
public class DDMTemplateModelListener extends BaseModelListener<DDMTemplate> {

	@Override
	public void onBeforeCreate(DDMTemplate ddmTemplate)
		throws ModelListenerException {

		_jakartaTransformScript(ddmTemplate);
	}

	@Override
	public void onBeforeUpdate(
			DDMTemplate originalDDMTemplate, DDMTemplate ddmTemplate)
		throws ModelListenerException {

		_jakartaTransformScript(ddmTemplate);
	}

	private void _jakartaTransformScript(DDMTemplate ddmTemplate) {
		if (_textReplacerBiFunction != null) {
			ddmTemplate.setScript(
				_textReplacerBiFunction.apply(
					"DDMTemplate#Script#" + ddmTemplate.getTemplateId(),
					ddmTemplate.getScript()));
		}
	}

	private static final BiFunction<String, String, String>
		_textReplacerBiFunction;

	static {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();

		Object instance = null;

		try {
			Class<?> clazz = classLoader.loadClass(
				"com.liferay.portal.tools.jakarta.ee.transformer.function." +
					"TextReplacerBiFunction");

			instance = clazz.newInstance();
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			if (!(reflectiveOperationException instanceof
					ClassNotFoundException)) {

				throw new ExceptionInInitializerError(
					reflectiveOperationException);
			}
		}

		_textReplacerBiFunction = (BiFunction<String, String, String>)instance;
	}

}