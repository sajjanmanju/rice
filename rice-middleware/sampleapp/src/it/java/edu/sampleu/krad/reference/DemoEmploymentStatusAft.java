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
package edu.sampleu.krad.reference;

import com.thoughtworks.selenium.SeleneseTestBase;

import org.junit.Test;
import org.kuali.rice.testtools.selenium.AutomatedFunctionalTestUtils;
import org.kuali.rice.testtools.selenium.WebDriverLegacyITBase;
import org.kuali.rice.testtools.selenium.WebDriverUtils;

/**
 * Tests the Component section in Rice.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class DemoEmploymentStatusAft extends WebDriverLegacyITBase {

    /**
     *  AutomatedFunctionalTestUtils.PORTAL + "?channelTitle=Employment%20Status&channelUrl="
     *   + WebDriverUtils.getBaseUrlString() + AutomatedFunctionalTestUtils.KRAD_LOOKUP_METHOD +
     *   "org.kuali.rice.kim.impl.identity.employment.EntityEmploymentStatusBo&docFormKey=88888888&returnLocation="
     *   + AutomatedFunctionalTestUtils.PORTAL_URL + AutomatedFunctionalTestUtils.HIDE_RETURN_LINK;
     */
    public static final String BOOKMARK_URL = AutomatedFunctionalTestUtils.PORTAL + "?channelTitle=Employment%20Status&channelUrl="
            + WebDriverUtils.getBaseUrlString() + AutomatedFunctionalTestUtils.KRAD_LOOKUP_METHOD +
            "org.kuali.rice.kim.impl.identity.employment.EntityEmploymentStatusBo&docFormKey=88888888&returnLocation="
            + AutomatedFunctionalTestUtils.PORTAL_URL + AutomatedFunctionalTestUtils.HIDE_RETURN_LINK;
         
    @Override
    protected String getBookmarkUrl() {
        return BOOKMARK_URL;
    }

    protected void navigate() throws InterruptedException {
       waitAndClickKRAD();
       waitAndClickByLinkText("Employment Status");        
    }

    //Code for KRAD Test Package.
    protected void testDemoEmploymentStatus() throws Exception { 
      selectFrameIframePortlet();
      waitAndClickButtonByText("Search");
      Thread.sleep(3000);
      assertTextPresent("Active");
      assertTextPresent("Deceased");
      assertTextPresent("On Non-Pay Leave");
      waitAndTypeByName("lookupCriteria[name]","Active");
      waitAndClickButtonByText("Search");
      Thread.sleep(3000);
      if(isTextPresent("Deceased"))
      {
          fail("Conditions not working !");
      }
    }

    @Test
    public void testDemoEmploymentStatusBookmark() throws Exception {
        testDemoEmploymentStatus();
        passed();
    }

    @Test
    public void testDemoEmploymentStatusNav() throws Exception {
        testDemoEmploymentStatus();
        passed();
    }
}
