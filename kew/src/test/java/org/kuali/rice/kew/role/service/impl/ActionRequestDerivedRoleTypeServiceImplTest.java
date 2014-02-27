/*
 * Copyright 2007-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.kew.role.service.impl;

import org.junit.Test;
import org.kuali.rice.kew.service.WorkflowDocument;
import org.kuali.rice.kew.test.KEWTestCase;
import org.kuali.rice.kew.test.TestUtilities;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kim.bo.impl.KimAttributes;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;
import org.kuali.rice.kim.service.support.impl.KimDerivedRoleTypeServiceBase;


/**
 * Tests the ActionRequestDerivedRoleTypeServiceImpl class. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class ActionRequestDerivedRoleTypeServiceImplTest extends KEWTestCase {
	
	private static final String APPROVE_REQUEST_RECIPIENT_ROLE_NAME = "Approve Request Recipient";
	private static final String NON_AD_HOC_APPROVE_REQUEST_RECIPIENT_ROLE_NAME = "Non-Ad Hoc Approve Request Recipient";
	private static final String ACKNOWLEDGE_REQUEST_RECIPIENT_ROLE_NAME = "Acknowledge Request Recipient";
	private static final String FYI_REQUEST_RECIPIENT_ROLE_NAME = "FYI Request Recipient";
	
	
	
	@Override
	protected void loadTestData() throws Exception {
		loadXmlFile("ActionRequestDerivedRoleTypeServiceImplTest.xml");
	}



	@Test public void testHasApplicationRole() throws Exception {
		WorkflowDocument document = new WorkflowDocument(getPrincipalIdForName("ewestfal"), "ActionRequestDerivedRoleTypeServiceImplTest");
		
		// let's send an adhoc request to rkirkend
		document.adHocRouteDocumentToPrincipal(KEWConstants.ACTION_REQUEST_APPROVE_REQ, "", getPrincipalIdForName("rkirkend"), "", true);
		document.routeDocument("");
		
		KimDerivedRoleTypeServiceBase roleTypeService = new ActionRequestDerivedRoleTypeServiceImpl();
		
		AttributeSet qualifications = new AttributeSet();
		qualifications.put(KimAttributes.DOCUMENT_NUMBER, ""+document.getRouteHeaderId());
		
		// rkirkend should have role as an approver
		assertTrue("rkirkend should have role.", roleTypeService.hasApplicationRole(getPrincipalIdForName("rkirkend"), null, null, APPROVE_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
		
		// now verify it returns false for the non-adhoc case since we created request to rkirkend as an adhoc request
		assertFalse("rkirkend should not have role for non-adhoc (since this was an adhoc request)", roleTypeService.hasApplicationRole(getPrincipalIdForName("rkirkend"), null, null, NON_AD_HOC_APPROVE_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
		
		// switch to bmcgough and send an acknowledge
		document = TestUtilities.switchByPrincipalName("bmcgough", document);
		document.adHocRouteDocumentToPrincipal(KEWConstants.ACTION_REQUEST_ACKNOWLEDGE_REQ, "", getPrincipalIdForName("bmcgough"), "", true);
		assertTrue("bmcgough should have ack", document.isAcknowledgeRequested());
		
		// bmcgough should not have role as an approver
		assertFalse("bmcgough should not have role", roleTypeService.hasApplicationRole(getPrincipalIdForName("bmcgough"), null, null, APPROVE_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
		// bmcgough should have role as an ack
		assertTrue("bmcgough should have role", roleTypeService.hasApplicationRole(getPrincipalIdForName("bmcgough"), null, null, ACKNOWLEDGE_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
		// bmcgough should not have role as an fyi
		assertFalse("bmcgough should not have role", roleTypeService.hasApplicationRole(getPrincipalIdForName("bmcgough"), null, null, FYI_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
		
		// now approve as "rkirkend", should generate a requeest to jhopf
		document = TestUtilities.switchByPrincipalName("rkirkend", document);
		assertTrue(document.isApprovalRequested());
		document.approve("");
		
		// jhopf should now have approve request, not as the result of an adhoc request
		document = TestUtilities.switchByPrincipalName("jhopf", document);
		assertTrue(document.isApprovalRequested());
		assertTrue("jhopf should have role", roleTypeService.hasApplicationRole(getPrincipalIdForName("jhopf"), null, null, APPROVE_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
		assertTrue("jhopf should have role", roleTypeService.hasApplicationRole(getPrincipalIdForName("jhopf"), null, null, NON_AD_HOC_APPROVE_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
		
		// send an fyi to "pmckown"
		document.adHocRouteDocumentToPrincipal(KEWConstants.ACTION_REQUEST_FYI_REQ, "", getPrincipalIdForName("pmckown"), "", true);
		document = TestUtilities.switchByPrincipalName("pmckown", document);
		assertTrue(document.isFYIRequested());
		assertTrue("pmckown should have role", roleTypeService.hasApplicationRole(getPrincipalIdForName("pmckown"), null, null, FYI_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
				
		// create a new doc and "save" as ewestfal, this should generate a "complete" request to ewestfal
		document = new WorkflowDocument(getPrincipalIdForName("ewestfal"), "ActionRequestDerivedRoleTypeServiceImplTest");
		document.saveDocument("");
		assertTrue(document.stateIsSaved());
		assertTrue(document.isCompletionRequested());
		assertTrue(document.isApprovalRequested());
		
		// the ActionRequestDerivedRoleTypeService does not consider COMPLETE and APPROVE to be the same, let's verify that's the case
		assertFalse("ewestfal should not have role", roleTypeService.hasApplicationRole(getPrincipalIdForName("ewestfal"), null, null, APPROVE_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
		assertFalse("ewestfal should not have role", roleTypeService.hasApplicationRole(getPrincipalIdForName("ewestfal"), null, null, NON_AD_HOC_APPROVE_REQUEST_RECIPIENT_ROLE_NAME, qualifications));
		
	}
	
}