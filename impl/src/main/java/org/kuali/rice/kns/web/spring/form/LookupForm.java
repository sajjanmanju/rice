/*
 * Copyright 2011 The Kuali Foundation
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
package org.kuali.rice.kns.web.spring.form;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.uif.UifConstants.ViewType;
import org.kuali.rice.kns.uif.container.View;
import org.kuali.rice.kns.uif.service.LookupViewHelperService;
import org.kuali.rice.kns.uif.service.ViewHelperService;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * This class is the model for Lookups
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class LookupForm extends UifFormBase {
	private static final long serialVersionUID = -7323484966538685327L;

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(InquiryForm.class);

	private String dataObjectClassName;
	private String docNum;
	private Map<String, String> criteriaFields;
	private Map<String, String> criteriaFieldsForLookup;
	private String conversionFields;
	private Map<String, String> fieldConversions;
	private boolean suppressActions = false;
    private boolean hideReturnLink = false;
	
	private Collection<?> searchResults;

    public LookupForm() {
    	super();
    	setViewTypeName(ViewType.LOOKUP);
    }

	public String getDataObjectClassName() {
    	return this.dataObjectClassName;
    }

	public void setDataObjectClassName(String dataObjectClassName) {
    	this.dataObjectClassName = dataObjectClassName;
    }

	public String getDocNum() {
    	return this.docNum;
    }

	public void setDocNum(String docNum) {
    	this.docNum = docNum;
    }

	public Map<String, String> getCriteriaFields() {
    	return this.criteriaFields;
    }

	public void setCriteriaFields(Map<String, String> criteriaFields) {
    	this.criteriaFields = criteriaFields;
    }

	public Map<String, String> getCriteriaFieldsForLookup() {
    	return this.criteriaFieldsForLookup;
    }

	public void setCriteriaFieldsForLookup(Map<String, String> criteriaFieldsForLookup) {
    	this.criteriaFieldsForLookup = criteriaFieldsForLookup;
    }

	public String getConversionFields() {
    	return this.conversionFields;
    }

	public void setConversionFields(String conversionFields) {
    	this.conversionFields = conversionFields;
    }

	public Map<String, String> getFieldConversions() {
    	return this.fieldConversions;
    }

	public void setFieldConversions(Map<String, String> fieldConversions) {
    	this.fieldConversions = fieldConversions;
    }

	public Collection<?> getSearchResults() {
    	return this.searchResults;
    }

	public void setSearchResults(Collection<?> searchResults) {
    	this.searchResults = searchResults;
    }

    /**
     * @param suppressActions The suppressActions to set.
     */
    public void setSuppressActions(boolean suppressActions) {
        this.suppressActions = suppressActions;
    }

    /**
     * @return Returns the suppressActions.
     */
    public boolean isSuppressActions() {
        return suppressActions;
    }

    /**
     * @return Returns the hideReturnLink.
     */
    public boolean isHideReturnLink() {
        return hideReturnLink;
    }

    /**
     * @param hideReturnLink The hideReturnLink to set.
     */
    public void setHideReturnLink(boolean hideReturnLink) {
        this.hideReturnLink = hideReturnLink;
    }

	protected LookupViewHelperService getLookupViewHelperServiceFromModel(View view) {
        ViewHelperService viewHelperService = view.getViewHelperService();
        if (viewHelperService == null) {
            LOG.error("ViewHelperService is null.");
            throw new RuntimeException("ViewHelperService is null.");
        }
        if (!LookupViewHelperService.class.isAssignableFrom(viewHelperService.getClass())) {
            LOG.error("ViewHelperService class '" + viewHelperService.getClass().getName() + "' is not assignable from '" + LookupViewHelperService.class + "'");
            throw new RuntimeException("ViewHelperService class '" + viewHelperService.getClass().getName() + "' is not assignable from '" + LookupViewHelperService.class + "'");
        }
        return (LookupViewHelperService) viewHelperService;
	}

	/**
	 * Picks out business object name from the request to get retrieve a
	 * lookupable and set properties.
	 */
	@Override
	public void postBind(HttpServletRequest request) {
		super.postBind(request);

		try {
			/*
			 * TODO delyea - Investigate to make sure below retrieval takes into
			 * account the following: 1) handle externalizable business objects
			 * 2) allow for lookupableImpl bean id to be passed in via request
			 * parameter KNSConstants.LOOKUPABLE_IMPL_ATTRIBUTE_NAME
			 */
//			ViewHelperService localLookupable = getView().getViewHelperService();
			LookupViewHelperService localLookupViewHelperService = getLookupViewHelperServiceFromModel(getView());

			if (localLookupViewHelperService == null) {
				LOG.error("LookupViewHelperService not found for view id " + getView().getId());
				throw new RuntimeException("LookupViewHelperService not found for view id " + getView().getId());
			}
			// set parameters on lookupable
			/*
			 * TODO: delyea - this setter used to get multipart form data
			 * related to KNSConstants.UPLOADED_FILE_REQUEST_ATTRIBUTE_KEY
			 * request attribute (see PojoFormBase.populate() method for more
			 * info)
			 */
//			localLookupable.setParameters(request.getParameterMap());

			// check the doc form key is empty before setting so we don't
			// override a restored lookup form
			if (request.getAttribute(KNSConstants.DOC_FORM_KEY) != null && StringUtils.isBlank(this.getFormKey())) {
				setFormKey((String) request.getAttribute(KNSConstants.DOC_FORM_KEY));
			} else if (request.getParameter(KNSConstants.DOC_FORM_KEY) != null && StringUtils.isBlank(this.getFormKey())) {
				setFormKey(request.getParameter(KNSConstants.DOC_FORM_KEY));
			}

			if (request.getParameter(KNSConstants.DOC_NUM) != null) {
				setDocNum(request.getParameter(KNSConstants.DOC_NUM));
			}

			// this used to be in the Form as a property but has been moved for KRAD
			Boolean showMaintenanceLinks = Boolean.valueOf(request.getParameter("showMaintenanceLinks"));
            // if showMaintenanceLinks is not already true, only show maintenance links if the lookup was called from the portal (or index.html for the generated applications)
            if (!showMaintenanceLinks.booleanValue()) {
            	// TODO delyea - is this the best way to decide whether to display the maintenance actions?
            	if (StringUtils.contains(getBackLocation(), "/"+KNSConstants.PORTAL_ACTION) 
            			|| StringUtils.contains(getBackLocation(), "/index.html")) {
            		showMaintenanceLinks = Boolean.TRUE;
            	}
            }
            localLookupViewHelperService.setShowMaintenanceLinks(showMaintenanceLinks.booleanValue());
            
			// this used to be in the Form as a property but has been moved for KRAD
//			String hideReturnLink = request.getParameter("hideReturnLink");
//			Boolean hideReturnLinkValue = processBooleanParameter(hideReturnLink);
//			if (hideReturnLinkValue != null) {
//				localLookupViewHelperService.setHideReturnLink(hideReturnLinkValue.booleanValue());
//			}

			if (request.getParameter("returnLocation") != null) {
				setBackLocation(request.getParameter("returnLocation"));
			}
			if (request.getParameter("conversionFields") != null) {
				setConversionFields(request.getParameter("conversionFields"));
			}
//			String value = request.getParameter("multipleValues");
//			if (value != null) {
//				if ("YES".equals(value.toUpperCase())) {
//					setMultipleValues(true);
//				} else {
//					setMultipleValues(new Boolean(request.getParameter("multipleValues")).booleanValue());
//				}
//			}
//			if (request.getParameter(KNSConstants.REFERENCES_TO_REFRESH) != null) {
//				setReferencesToRefresh(request.getParameter(KNSConstants.REFERENCES_TO_REFRESH));
//			}

			if (request.getParameter(KNSConstants.LOOKUP_READ_ONLY_FIELDS) != null) {
				setReadOnlyFields(request.getParameter("readOnlyFields"));
				setReadOnlyFieldsList(LookupUtils.translateReadOnlyFieldsToList(getReadOnlyFields()));
				localLookupViewHelperService.setReadOnlyFieldsList(getReadOnlyFieldsList());
			}

			// init lookupable with bo class
			Class<?> boClass = Class.forName(getDataObjectClassName());
			localLookupViewHelperService.setDataObjectClass(boClass);
			Map<String, String> fieldValues = new HashMap<String, String>();
			Map<String, String> formFields = getCriteriaFields();

			if (formFields != null) {
				for (Map.Entry<String, String> entry : formFields.entrySet()) {
					// check here to see if this field is a criteria element on the form
		            fieldValues.put(entry.getKey(), LookupUtils.forceUppercase(boClass, entry.getKey(), entry.getValue()));
	            }
			}
			
			// populate values into the localLookupable Field list
//			for (Iterator iter = localLookupable.getRows().iterator(); iter.hasNext();) {
//				Row row = (Row) iter.next();
//
//				for (Iterator iterator = row.getFields().iterator(); iterator.hasNext();) {
//					Field field = (Field) iterator.next();
//
//					// check whether form already has value for field
//					if (formFields != null && formFields.containsKey(field.getPropertyName())) {
//						field.setPropertyValue(formFields.get(field.getPropertyName()));
//					}
//
//					field.setPropertyValue(LookupUtils.forceUppercase(boClass, field.getPropertyName(), field.getPropertyValue()));
//					fieldValues.put(field.getPropertyName(), field.getPropertyValue());
//					localLookupable.applyFieldAuthorizationsFromNestedLookups(field);
//				}
//			}

			// check the lookupableImpl to see if there are additional fields
//			if (localLookupable.checkForAdditionalFields(fieldValues)) {
//				// populate values into the localLookupable Field list again because additional rows may have been added
//				for (Iterator iter = localLookupable.getRows().iterator(); iter.hasNext();) {
//					Row row = (Row) iter.next();
//
//					for (Iterator iterator = row.getFields().iterator(); iterator.hasNext();) {
//						Field field = (Field) iterator.next();
//
//						// check whether form already has value for field
//						if (formFields != null && formFields.containsKey(field.getPropertyName())) {
//							field.setPropertyValue(formFields.get(field.getPropertyName()));
//						}
//
//						// override values with request
//						if (request.getParameter(field.getPropertyName()) != null) {
//							if (!Field.MULTI_VALUE_FIELD_TYPES.contains(field.getFieldType())) {
//								field.setPropertyValue(request.getParameter(field.getPropertyName()).trim());
//							} else {
//								// multi value, set to values
//								field.setPropertyValues(request.getParameterValues(field.getPropertyName()));
//							}
//						}
//						fieldValues.put(field.getPropertyName(), field.getPropertyValue());
//					}
//				}
//
//			}
			fieldValues.put(KNSConstants.DOC_FORM_KEY, this.getReturnFormKey());
			fieldValues.put(KNSConstants.BACK_LOCATION, this.getBackLocation());
			if (this.getDocNum() != null) {
				fieldValues.put(KNSConstants.DOC_NUM, this.getDocNum());
			}
//			if (StringUtils.isNotBlank(getReferencesToRefresh())) {
//				fieldValues.put(KNSConstants.REFERENCES_TO_REFRESH, this.getReferencesToRefresh());
//			}

			this.setCriteriaFields(fieldValues);

			setFieldConversions(LookupUtils.translateFieldConversions(this.conversionFields));
			localLookupViewHelperService.setFieldConversions(getFieldConversions());
			localLookupViewHelperService.setDocNum(this.getDocNum());
			localLookupViewHelperService.setSuppressActions(isSuppressActions());
			localLookupViewHelperService.setHideReturnLink(isHideReturnLink());
//			setLookupViewHelperService(localLookupViewHelperService);
			setCriteriaFieldsForLookup(fieldValues);

			// if showMaintenanceLinks is not already true, only show
			// maintenance links if the lookup was called from the portal (or
			// index.html for the generated applications)
			// TODO delyea - how to handle whether to show maintenance links or return links
//			if (!isShowMaintenanceLinks()) {
//				if (StringUtils.contains(getBackLocation(), "/" + KNSConstants.PORTAL_ACTION) || StringUtils.contains(getBackLocation(), "/index.html")) {
//					showMaintenanceLinks = true;
//				}
//			}
		} catch (ClassNotFoundException e) {
			LOG.error("Object class " + getDataObjectClassName() + " not found");
			throw new RuntimeException("Object class " + getDataObjectClassName() + " not found", e);
		}
	}

	protected Boolean processBooleanParameter(String parameterValue) {
		if (StringUtils.isNotBlank(parameterValue)) {
			if ("YES".equals(parameterValue.toUpperCase())) {
				return Boolean.TRUE;
			}
			return new Boolean(parameterValue);
		}
		return null;
	}

	/**
	 * BELOW COPIED FROM LookupForm
	 */

    private String readOnlyFields;
    private List<String> readOnlyFieldsList;

	public String getReadOnlyFields() {
    	return this.readOnlyFields;
    }

	public void setReadOnlyFields(String readOnlyFields) {
    	this.readOnlyFields = readOnlyFields;
    }

	public List<String> getReadOnlyFieldsList() {
    	return this.readOnlyFieldsList;
    }

	public void setReadOnlyFieldsList(List<String> readOnlyFieldsList) {
    	this.readOnlyFieldsList = readOnlyFieldsList;
    }

	/**
	 * BELOW COPIED FROM KualiForm
	 */

	private String backLocation;

	/**
	 * @return the backLocation
	 */
	public String getBackLocation() {
		return this.backLocation;
	}

	/**
	 * @param backLocation
	 *            the backLocation to set
	 */
	public void setBackLocation(String backLocation) {
		this.backLocation = backLocation;
	}

}
