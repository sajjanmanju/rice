/*
 * Copyright 2007 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.kns.ui.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.ui.container.View;
import org.kuali.rice.kns.ui.service.ViewService;

/**
 * Implementation of <code>ViewService</code>
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class ViewServiceImpl implements ViewService {
	protected DataDictionaryService dataDictionaryService;

	/**
	 * @see org.kuali.rice.kns.ui.service.ViewService#getViewById(java.lang.String)
	 */
	public View getViewById(String viewId) {
		return getView(viewId, new HashMap<String, String>());
	}

	/**
	 * @see org.kuali.rice.kns.ui.service.ViewService#getView(java.lang.String,
	 *      java.util.Map)
	 */
	public View getView(String viewId, Map<String, String> options) {
		View view = dataDictionaryService.getViewById(viewId);

		// invoke initialize phase on the views lifecycle service
		view.getViewLifecycleService().performInitialization(view, options);

		return view;
	}

	public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
		this.dataDictionaryService = dataDictionaryService;
	}

}
