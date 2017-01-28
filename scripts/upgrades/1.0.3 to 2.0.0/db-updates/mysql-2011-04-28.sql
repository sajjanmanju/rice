--
-- Copyright 2005-2017 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

--
-- KULRICE-4794
-- The following statements will change the DOC_HDR_ID from a decimal to a VARCHAR(40) on various tables. 
--

ALTER TABLE KREW_ACTN_ITM_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_ACTN_RQST_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_ACTN_TKN_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_APP_DOC_STAT_TRAN_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40)
/
ALTER TABLE KREW_DOC_HDR_CNTNT_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_DOC_HDR_EXT_DT_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_DOC_HDR_EXT_FLT_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_DOC_HDR_EXT_LONG_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_DOC_HDR_EXT_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_DOC_HDR_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_DOC_NTE_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_DOC_TYP_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40)
/
ALTER TABLE KREW_EDL_DMP_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_EDL_FLD_DMP_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_INIT_RTE_NODE_INSTN_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_OUT_BOX_ITM_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_RMV_RPLC_DOC_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_RMV_RPLC_GRP_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_RMV_RPLC_RULE_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_RTE_NODE_INSTN_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_RULE_T CHANGE DOC_HDR_ID DOC_HDR_ID VARCHAR(40)
/

--
-- The following statements will change the ORGN_DOC_ID and DEST_DOC_ID 
-- from a decimal to a VARCHAR(40) on the KREW_DOC_LNK_T table 
--

ALTER TABLE KREW_DOC_LNK_T CHANGE ORGN_DOC_ID ORGN_DOC_ID VARCHAR(40) NOT NULL
/
ALTER TABLE KREW_DOC_LNK_T CHANGE DEST_DOC_ID DEST_DOC_ID VARCHAR(40) NOT NULL
