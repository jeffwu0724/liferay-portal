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

package com.liferay.portal.search.internal.sort;

import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistry;
import com.liferay.portal.kernel.test.util.PropsTestUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Michael C. Han
 */
public class SortFieldBuilderImplTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		_sortFieldBuilderImpl = new SortFieldBuilderImpl();

		Mockito.when(
			_indexerRegistry.getIndexer(Mockito.anyString())
		).thenAnswer(
			invocation -> _indexer
		);

		_sortFieldBuilderImpl.indexerRegistry = _indexerRegistry;
		_sortFieldBuilderImpl.props = PropsTestUtil.setProps(
			"index.sortable.text.fields",
			new String[] {
				"firstName", "jobTitle", "lastName", "name", "screenName",
				"title"
			});

		_sortFieldBuilderImpl.activate();
	}

	@Mock
	private Indexer<?> _indexer;

	@Mock
	private IndexerRegistry _indexerRegistry;

	private SortFieldBuilderImpl _sortFieldBuilderImpl;

}