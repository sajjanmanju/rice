/**
 * Copyright 2005-2013 The Kuali Foundation
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
package edu.sampleu.admin;

import org.apache.commons.lang.RandomStringUtils;
import org.kuali.rice.testtools.common.JiraAwareFailable;
import org.kuali.rice.testtools.selenium.AutomatedFunctionalTestUtils;
import org.kuali.rice.testtools.selenium.WebDriverUtils;

/**
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public abstract class ConfigComponentLookUpAndCopyAftBase extends AdminTmplMthdAftNavBase {

    /**
     * ITUtil.PORTAL+"?channelTitle=Component&channelUrl="+WebDriverUtils.getBaseUrlString()+
     * "/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.coreservice.impl.component.ComponentBo&docFormKey=88888888&returnLocation="+
     * +ITUtil.PORTAL_URL+ ITUtil.HIDE_RETURN_LINK;
     */    
    public static final String BOOKMARK_URL = AutomatedFunctionalTestUtils.PORTAL+"?channelTitle=Component&channelUrl="+ WebDriverUtils
            .getBaseUrlString()+"/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.rice.coreservice.impl.component.ComponentBo&docFormKey=88888888&returnLocation="+
            AutomatedFunctionalTestUtils.PORTAL_URL+ AutomatedFunctionalTestUtils.HIDE_RETURN_LINK;

    @Override
    protected String getBookmarkUrl() {
        return BOOKMARK_URL;
    }

    /**
     * {@inheritDoc}
     * Component
     * @return
     */
    @Override
    protected String getLinkLocator() {
        return "Component";
    }

    public void testConfigComponentLookUpAndCopyBookmark(JiraAwareFailable failable) throws Exception {
        testConfigComponentLookUpAndCopy();
        passed();
    }

    public void testConfigComponentLookUpAndCopyNav(JiraAwareFailable failable) throws Exception {
        testConfigComponentLookUpAndCopy();
        passed();
    }    
    
    public void testConfigComponentLookUpAndCopy() throws Exception {
        selectFrameIframePortlet();
        waitAndClickSearchSecond();
        waitAndClickByLinkText("copy");
        String fourLetters = RandomStringUtils.randomAlphabetic(4);
        waitAndTypeByName("document.documentHeader.documentDescription","Test description of Component copy " + AutomatedFunctionalTestUtils
                .createUniqueDtsPlusTwoRandomCharsNot9Digits());
        selectByName("document.newMaintainableObject.namespaceCode","KR-WKFLW - Workflow");
        waitAndTypeByName("document.newMaintainableObject.code","ActionList2" + fourLetters);
        waitAndTypeByName("document.newMaintainableObject.name",fourLetters);
        waitAndTypeByName("document.newMaintainableObject.name","Action List 2 " + fourLetters);
        waitAndClickByName("methodToCall.route");
        checkForDocError();
        waitAndClickByName("methodToCall.close");
        waitAndClickByName("methodToCall.processAnswer.button1");
    }
}