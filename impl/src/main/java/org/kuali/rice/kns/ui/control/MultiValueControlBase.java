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
package org.kuali.rice.kns.ui.control;

import java.util.List;

import org.kuali.rice.core.util.KeyLabelPair;

/**
 * Base class for controls that accept/display multiple values
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public abstract class MultiValueControlBase extends ControlBase {
	private List<KeyLabelPair> options;

	public MultiValueControlBase() {

	}

	/**
	 * <code>List</code> of values the control can accept. Each value consists
	 * of a key and a label. The key is the what will be submitted back if the
	 * user selects the choice, the label is what will be displayed to the user
	 * for the choice.
	 * <p>
	 * <code>KeyLabelPair</code> instances are usually generated by the
	 * <code>KeyValueFinder</code> associated with the <code>Field</code> for
	 * which the control belongs
	 * </p>
	 * 
	 * @return List of KeyLabelPair instances
	 */
	public List<KeyLabelPair> getOptions() {
		return this.options;
	}

	public void setOptions(List<KeyLabelPair> options) {
		this.options = options;
	}

}
