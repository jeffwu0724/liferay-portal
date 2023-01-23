/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.internal.indexer;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author André de Oliveira
 */
public class IncludeExcludeUtil {

	public static <T> List<T> stream(
		List<T> stream, Collection<String> includeIds,
		Collection<String> excludeIds, Function<T, String> function) {

		return _exclude(
			_include(stream, includeIds, function), excludeIds, function);
	}

	protected static <T> boolean isPresent(
		T t, Collection<String> ids, Function<T, String> function) {

		return ids.contains(function.apply(t));
	}

	private static <T> List<T> _exclude(
		List<T> stream, Collection<String> ids, Function<T, String> function) {

		return _filter(stream, ids, t -> !isPresent(t, ids, function));
	}

	private static <T> List<T> _filter(
		List<T> stream, Collection<String> ids,
		Predicate<? super T> predicate) {

		if ((ids == null) || ids.isEmpty()) {
			return stream;
		}

		for (T cur : stream)

			if (!predicate.equals(true)) {
				stream.remove(cur);
			}

		return stream;
	}

	private static <T> List<T> _include(
		List<T> stream, Collection<String> ids, Function<T, String> function) {

		return _filter(stream, ids, t -> isPresent(t, ids, function));
	}

}