/*
 * Copyright 2005-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS
 * IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.kuali.rice.kns.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.bo.ParameterDetailType;
import org.kuali.rice.kns.datadictionary.AttributeDefinition;
import org.kuali.rice.kns.datadictionary.BusinessObjectEntry;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.TransactionalDocumentEntry;
import org.kuali.rice.kns.document.TransactionalDocument;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.service.RiceApplicationConfigurationService;
import org.kuali.rice.kns.service.ParameterConstants.COMPONENT;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.KNSUtils;

//@Transactional
public class RiceApplicationConfigurationServiceImpl implements RiceApplicationConfigurationService {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RiceApplicationConfigurationServiceImpl.class);
    
    protected List<ParameterDetailType> components = new ArrayList<ParameterDetailType>();
    protected List<String> packagePrefixes = new ArrayList<String>();
    private KualiConfigurationService kualiConfigurationService;
    private ParameterService parameterService;    
    private DataDictionaryService dataDictionaryService;
    
    public String getConfigurationParameter( String parameterName ){
    	return getKualiConfigurationService().getPropertyString(parameterName);
    }
    
    /**
     * This method derived ParameterDetailedTypes from the DataDictionary for all BusinessObjects and Documents and from Spring for
     * all batch Steps.
     * 
     * @return List<ParameterDetailedType> containing the detailed types derived from the data dictionary and Spring
     */
    public List<ParameterDetailType> getNonDatabaseComponents() {
        if (components.isEmpty()) {
            Map<String, ParameterDetailType> uniqueParameterDetailTypeMap = new HashMap<String, ParameterDetailType>();
                        
            DataDictionaryService dataDictionaryService = KNSServiceLocator.getDataDictionaryService();
            
            //dataDictionaryService.getDataDictionary().forceCompleteDataDictionaryLoad();
            for (BusinessObjectEntry businessObjectEntry : dataDictionaryService.getDataDictionary().getBusinessObjectEntries().values()) {
                try {
                    ParameterDetailType parameterDetailType = getParameterDetailType((businessObjectEntry.getBaseBusinessObjectClass() != null) ? businessObjectEntry.getBaseBusinessObjectClass() : businessObjectEntry.getBusinessObjectClass());
                    uniqueParameterDetailTypeMap.put(parameterDetailType.getParameterDetailTypeCode(), parameterDetailType);
                }
                catch (Exception e) {
                    LOG.error("The getDataDictionaryAndSpringComponents method of ParameterUtils encountered an exception while trying to create the detail type for business object class: " + businessObjectEntry.getBusinessObjectClass(), e);
                }
            }
            for (DocumentEntry documentEntry : dataDictionaryService.getDataDictionary().getDocumentEntries().values()) {
                if (documentEntry instanceof TransactionalDocumentEntry) {
                    try {
                        ParameterDetailType parameterDetailType = getParameterDetailType((documentEntry.getBaseDocumentClass() != null) ? documentEntry.getBaseDocumentClass() : documentEntry.getDocumentClass());
                        uniqueParameterDetailTypeMap.put(parameterDetailType.getParameterDetailTypeCode(), parameterDetailType);
                    }
                    catch (Exception e) {
                        LOG.error("The getNonDatabaseDetailTypes method of ParameterServiceImpl encountered an exception while trying to create the detail type for transactional document class: " +
                        		((documentEntry.getBaseDocumentClass() != null) ? documentEntry.getBaseDocumentClass() : documentEntry.getDocumentClass()), e);
                    }
                }
            }
            components.addAll(uniqueParameterDetailTypeMap.values());
        }
        return Collections.unmodifiableList(components);
    }
    
    @SuppressWarnings("unchecked")
	protected ParameterDetailType getParameterDetailType(Class documentOrStepClass) {
        String detailTypeString = getParameterService().getDetailType(documentOrStepClass);
        String detailTypeName = getDetailTypeName(documentOrStepClass);
        ParameterDetailType detailType = new ParameterDetailType(getParameterService().getNamespace(documentOrStepClass), detailTypeString, (detailTypeName == null) ? detailTypeString : detailTypeName);
        detailType.refreshNonUpdateableReferences();
        return detailType;
    }

    @SuppressWarnings("unchecked")
    /**
     * This method derived ParameterDetailedTypes from the DataDictionary for all BusinessObjects and Transactional Documents Entries and from Spring for
     * all batch Steps.
     * 
     * @return String containing the detailed type name derived from the data dictionary/Business Object
     */
	protected String getDetailTypeName(Class documentOrStepClass) {
        if (documentOrStepClass == null) {
            throw new IllegalArgumentException("The getDetailTypeName method of ParameterServiceImpl requires non-null documentOrStepClass");
        }
        
        /* 
         * Some business objects have a Component annotation that sets the value
         * of the classes annotaion.  This if block will test to see if it is there, try to get the 
         * component value from the Data Dictionary if the BusinessObjectEntry exists, if it doesn't
         * exist, it will fall back to the annotation's value.
         */
        if (documentOrStepClass.isAnnotationPresent(COMPONENT.class)) {
            BusinessObjectEntry boe = getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(documentOrStepClass.getName());
            if (boe != null) {
                return boe.getObjectLabel();
            }
            else {
                return ((COMPONENT) documentOrStepClass.getAnnotation(COMPONENT.class)).component();
            }
        }

        /*
         * If block that determines if the class is either a BusinessObject or a TransactionalDocument
         * return calls try to either get the BusinessObjectEntry's ObjectLable, or grabbing the 
         * data dictionary's BusinessTitleForClass if it is a BusinessObject, or the DocumentLabel if it is a
         * TransactionalDocument
         */
        if (TransactionalDocument.class.isAssignableFrom(documentOrStepClass)) {
            return getDataDictionaryService().getDocumentLabelByClass(documentOrStepClass);
        }
        else if (BusinessObject.class.isAssignableFrom(documentOrStepClass) ) {
            BusinessObjectEntry boe = getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(documentOrStepClass.getName());
            if (boe != null) {
                return boe.getObjectLabel();
            }
            else {
                return KNSUtils.getBusinessTitleForClass(documentOrStepClass);
            }
        }
        throw new IllegalArgumentException("The getDetailTypeName method of ParameterServiceImpl requires TransactionalDocument, BusinessObject, or Step class. Was: " + documentOrStepClass.getName() );
    }
    
    protected KualiConfigurationService getKualiConfigurationService() {
		if (kualiConfigurationService == null) {
			kualiConfigurationService = KNSServiceLocator.getKualiConfigurationService();
		}
		return kualiConfigurationService;
	}
    
    protected ParameterService getParameterService() {
    	if (parameterService == null) {
    		parameterService = KNSServiceLocator.getParameterService();
    	}
    	return parameterService;
    }

    protected DataDictionaryService getDataDictionaryService() {
    	if (dataDictionaryService == null) {
    		dataDictionaryService = KNSServiceLocator.getDataDictionaryService();
    	}
    	return dataDictionaryService;
    }

	/**
	 * @see org.kuali.rice.kns.service.RiceApplicationConfigurationService#getBaseInquiryUrl(java.lang.String)
	 */
	public String getBaseInquiryUrl(String businessObjectClassName) {
		return LookupUtils.getBaseInquiryUrl();
	}

	/**
	 * @see org.kuali.rice.kns.service.RiceApplicationConfigurationService#getBaseLookupUrl(java.lang.String)
	 */
	public String getBaseLookupUrl(String businessObjectClassName) {
		// all Rice applications share the same type of lookup URL
		return LookupUtils.getBaseLookupUrl(false);
	}
	
	public String getBaseHelpUrl(String businessObjectClassName) {
		return KNSServiceLocator.getKualiConfigurationService().getPropertyString(KNSConstants.APPLICATION_URL_KEY) + "/kr/help.do";
	}

	/**
	 * @see org.kuali.rice.kns.service.RiceApplicationConfigurationService#isResponsibleForPackage(java.lang.String)
	 */
	public boolean isResponsibleForPackage(String packageName) {
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( "Checking if application ("+packagePrefixes+") is responsible for package: " + packageName );
		}
		for ( String prefix : packagePrefixes ) {
			if ( packageName.startsWith(prefix) ) {
				if ( LOG.isDebugEnabled() ) {
					LOG.debug("Found match ("+prefix+") - returning true");
				}
				return true;
			}
		}
		if ( LOG.isDebugEnabled() ) {
			LOG.debug("No Match Found: packageName="+packageName+" / prefix list=" + packagePrefixes);
		}
		return false;
	}
	
	/**
	 * @see org.kuali.rice.kns.service.RiceApplicationConfigurationService#supportsBusinessObjectClass(java.lang.String)
	 */
	public boolean supportsBusinessObjectClass(String businessObjectClassName) {
		return getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(businessObjectClassName) != null;
	}
	
	/**
	 * @see org.kuali.rice.kns.service.RiceApplicationConfigurationService#getBusinessObjectAttributeDefinition(java.lang.String, java.lang.String)
	 */
	public AttributeDefinition getBusinessObjectAttributeDefinition( String businessObjectClassName, String attributeName) {
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( "Asking ("+packagePrefixes+") for BO AttributeDefinition: " + businessObjectClassName + " / " + attributeName );
		}
		BusinessObjectEntry boe = getDataDictionaryService().getDataDictionary().getBusinessObjectEntry(businessObjectClassName);
		if ( boe == null ) {
			if ( LOG.isInfoEnabled() ) {
				LOG.info( "No BusinessObjectEntry found for class name: " + businessObjectClassName );
			}
			return null;
		}
		return boe.getAttributeDefinition(attributeName);
	}

	/**
	 * @return the packagePrefixes
	 */
	public List<String> getPackagePrefixes() {
		return this.packagePrefixes;
	}

	/**
	 * @param packagePrefixes the packagePrefixes to set
	 */
	public void setPackagePrefixes(List<String> packagePrefixes) {
		this.packagePrefixes = packagePrefixes;
	}
}