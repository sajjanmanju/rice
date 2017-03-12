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

ALTER TABLE KRCR_CMPNT_T
    ADD CONSTRAINT KRNS_PARM_DTL_TYP_TR1 FOREIGN KEY (NMSPC_CD)
    REFERENCES KRCR_NMSPC_T (NMSPC_CD)
/

ALTER TABLE KRCR_PARM_T
    ADD CONSTRAINT KRNS_PARM_TR2 FOREIGN KEY (PARM_TYP_CD)
    REFERENCES KRCR_PARM_TYP_T (PARM_TYP_CD)
/

ALTER TABLE KRCR_PARM_T
    ADD CONSTRAINT KRNS_PARM_TR1 FOREIGN KEY (NMSPC_CD)
    REFERENCES KRCR_NMSPC_T (NMSPC_CD)
/

ALTER TABLE KREN_CHNL_PRODCR_T
    ADD CONSTRAINT KREN_CHNL_PRODCR_FK2 FOREIGN KEY (PRODCR_ID)
    REFERENCES KREN_PRODCR_T (PRODCR_ID)
/

ALTER TABLE KREN_CHNL_PRODCR_T
    ADD CONSTRAINT KREN_CHNL_PRODCR_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/

ALTER TABLE KREN_CHNL_SUBSCRP_T
    ADD CONSTRAINT KREN_CHAN_SUBSCRP_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/

ALTER TABLE KREN_MSG_DELIV_T
    ADD CONSTRAINT KREN_MSG_DELIV_FK1 FOREIGN KEY (MSG_ID)
    REFERENCES KREN_MSG_T (MSG_ID)
/

ALTER TABLE KREN_NTFCTN_MSG_DELIV_T
    ADD CONSTRAINT KREN_NTFCTN_MSG_DELIV_FK1 FOREIGN KEY (NTFCTN_ID)
    REFERENCES KREN_NTFCTN_T (NTFCTN_ID)
/

ALTER TABLE KREN_NTFCTN_T
    ADD CONSTRAINT KREN_NTFCTN_FK4 FOREIGN KEY (PRODCR_ID)
    REFERENCES KREN_PRODCR_T (PRODCR_ID)
/

ALTER TABLE KREN_NTFCTN_T
    ADD CONSTRAINT KREN_NTFCTN_FK3 FOREIGN KEY (PRIO_ID)
    REFERENCES KREN_PRIO_T (PRIO_ID)
/

ALTER TABLE KREN_NTFCTN_T
    ADD CONSTRAINT KREN_NTFCTN_FK2 FOREIGN KEY (CNTNT_TYP_ID)
    REFERENCES KREN_CNTNT_TYP_T (CNTNT_TYP_ID)
/

ALTER TABLE KREN_NTFCTN_T
    ADD CONSTRAINT KREN_NTFCTN_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/

ALTER TABLE KREN_RECIP_LIST_T
    ADD CONSTRAINT KREN_RECIP_LIST_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/

ALTER TABLE KREN_RECIP_T
    ADD CONSTRAINT KREN_RECIP_FK1 FOREIGN KEY (NTFCTN_ID)
    REFERENCES KREN_NTFCTN_T (NTFCTN_ID)
/

ALTER TABLE KREN_RVWER_T
    ADD CONSTRAINT NOTIFICATION_REVIEWERS_N_FK1 FOREIGN KEY (CHNL_ID)
    REFERENCES KREN_CHNL_T (CHNL_ID)
/

ALTER TABLE KREN_SNDR_T
    ADD CONSTRAINT KREN_SNDR_FK1 FOREIGN KEY (NTFCTN_ID)
    REFERENCES KREN_NTFCTN_T (NTFCTN_ID)
/

ALTER TABLE KREW_DOC_TYP_APP_DOC_STAT_T
    ADD CONSTRAINT KREW_DOC_TYP_APP_DOC_STAT_FK1 FOREIGN KEY (DOC_TYP_ID, CAT_NM)
    REFERENCES KREW_DOC_TYP_APP_STAT_CAT_T (DOC_TYP_ID, CAT_NM)
/

ALTER TABLE KREW_DOC_TYP_APP_STAT_CAT_T
    ADD CONSTRAINT KREW_DOC_TYP_APP_STAT_CAT_FK1 FOREIGN KEY (DOC_TYP_ID)
    REFERENCES KREW_DOC_TYP_T (DOC_TYP_ID)
/

ALTER TABLE KREW_PPL_FLW_ATTR_T
    ADD CONSTRAINT KREW_PPL_FLW_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KREW_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KREW_PPL_FLW_ATTR_T
    ADD CONSTRAINT KREW_PPL_FLW_ATTR_FK1 FOREIGN KEY (PPL_FLW_ID)
    REFERENCES KREW_PPL_FLW_T (PPL_FLW_ID)
/

ALTER TABLE KREW_PPL_FLW_DLGT_T
    ADD CONSTRAINT KREW_PPL_FLW_DLGT_FK1 FOREIGN KEY (PPL_FLW_MBR_ID)
    REFERENCES KREW_PPL_FLW_MBR_T (PPL_FLW_MBR_ID)
/

ALTER TABLE KREW_PPL_FLW_MBR_T
    ADD CONSTRAINT KREW_PPL_FLW_MBR_FK1 FOREIGN KEY (PPL_FLW_ID)
    REFERENCES KREW_PPL_FLW_T (PPL_FLW_ID)
/

ALTER TABLE KREW_PPL_FLW_T
    ADD CONSTRAINT KREW_PPL_FLW_FK1 FOREIGN KEY (TYP_ID)
    REFERENCES KREW_TYP_T (TYP_ID)
/

ALTER TABLE KREW_RULE_T
    ADD CONSTRAINT KREW_RULE_TR1 FOREIGN KEY (RULE_EXPR_ID)
    REFERENCES KREW_RULE_EXPR_T (RULE_EXPR_ID)
/

ALTER TABLE KREW_TYP_ATTR_T
    ADD CONSTRAINT KREW_TYP_ATTR_FK1 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KREW_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KREW_TYP_ATTR_T
    ADD CONSTRAINT KREW_TYP_ATTR_FK2 FOREIGN KEY (TYP_ID)
    REFERENCES KREW_TYP_T (TYP_ID)
/

ALTER TABLE KRIM_DLGN_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/

ALTER TABLE KRIM_DLGN_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_DLGN_MBR_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_DLGN_MBR_T
    ADD CONSTRAINT KRIM_DLGN_MBR_TR2 FOREIGN KEY (DLGN_ID)
    REFERENCES KRIM_DLGN_T (DLGN_ID)
/

ALTER TABLE KRIM_DLGN_T
    ADD CONSTRAINT KRIM_DLGN_TR1 FOREIGN KEY (ROLE_ID)
    REFERENCES KRIM_ROLE_T (ROLE_ID)
/

ALTER TABLE KRIM_DLGN_T
    ADD CONSTRAINT KRIM_DLGN_TR2 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_ENTITY_ADDR_T
    ADD CONSTRAINT KRIM_ENTITY_ADDR_TR1 FOREIGN KEY (ENT_TYP_CD, ENTITY_ID)
    REFERENCES KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_ADDR_T
    ADD CONSTRAINT KRIM_ENTITY_ADDR_TR2 FOREIGN KEY (ADDR_TYP_CD)
    REFERENCES KRIM_ADDR_TYP_T (ADDR_TYP_CD)
/

ALTER TABLE KRIM_ENTITY_AFLTN_T
    ADD CONSTRAINT KRIM_ENTITY_AFLTN_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_AFLTN_T
    ADD CONSTRAINT KRIM_ENTITY_AFLTN_TR2 FOREIGN KEY (AFLTN_TYP_CD)
    REFERENCES KRIM_AFLTN_TYP_T (AFLTN_TYP_CD)
/

ALTER TABLE KRIM_ENTITY_CTZNSHP_T
    ADD CONSTRAINT KRIM_ENTITY_CTZNSHP_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_CTZNSHP_T
    ADD CONSTRAINT KRIM_ENTITY_CTZNSHP_TR2 FOREIGN KEY (CTZNSHP_STAT_CD)
    REFERENCES KRIM_CTZNSHP_STAT_T (CTZNSHP_STAT_CD)
/

ALTER TABLE KRIM_ENTITY_EMAIL_T
    ADD CONSTRAINT KRIM_ENTITY_EMAIL_TR1 FOREIGN KEY (ENT_TYP_CD, ENTITY_ID)
    REFERENCES KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_EMP_INFO_T
    ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR4 FOREIGN KEY (ENTITY_AFLTN_ID)
    REFERENCES KRIM_ENTITY_AFLTN_T (ENTITY_AFLTN_ID)
/

ALTER TABLE KRIM_ENTITY_EMP_INFO_T
    ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_EMP_INFO_T
    ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR2 FOREIGN KEY (EMP_STAT_CD)
    REFERENCES KRIM_EMP_STAT_T (EMP_STAT_CD)
/

ALTER TABLE KRIM_ENTITY_EMP_INFO_T
    ADD CONSTRAINT KRIM_ENTITY_EMP_INFO_TR3 FOREIGN KEY (EMP_TYP_CD)
    REFERENCES KRIM_EMP_TYP_T (EMP_TYP_CD)
/

ALTER TABLE KRIM_ENTITY_ENT_TYP_T
    ADD CONSTRAINT KRIM_ENTITY_ENT_TYP_TR2 FOREIGN KEY (ENT_TYP_CD)
    REFERENCES KRIM_ENT_TYP_T (ENT_TYP_CD)
/

ALTER TABLE KRIM_ENTITY_ENT_TYP_T
    ADD CONSTRAINT KRIM_ENTITY_ENT_TYP_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_ETHNIC_T
    ADD CONSTRAINT KRIM_ENTITY_ETHNIC_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
/

ALTER TABLE KRIM_ENTITY_EXT_ID_T
    ADD CONSTRAINT KRIM_ENTITY_EXT_ID_TR2 FOREIGN KEY (EXT_ID_TYP_CD)
    REFERENCES KRIM_EXT_ID_TYP_T (EXT_ID_TYP_CD)
/

ALTER TABLE KRIM_ENTITY_EXT_ID_T
    ADD CONSTRAINT KRIM_ENTITY_EXT_ID_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_NM_T
    ADD CONSTRAINT KRIM_ENTITY_NM_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_PHONE_T
    ADD CONSTRAINT KRIM_ENTITY_PHONE_TR1 FOREIGN KEY (ENT_TYP_CD, ENTITY_ID)
    REFERENCES KRIM_ENTITY_ENT_TYP_T (ENT_TYP_CD, ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ENTITY_PHONE_T
    ADD CONSTRAINT KRIM_ENTITY_PHONE_TR2 FOREIGN KEY (PHONE_TYP_CD)
    REFERENCES KRIM_PHONE_TYP_T (PHONE_TYP_CD)
/

ALTER TABLE KRIM_ENTITY_RESIDENCY_T
    ADD CONSTRAINT KRIM_ENTITY_RESIDENCY_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
/

ALTER TABLE KRIM_ENTITY_VISA_T
    ADD CONSTRAINT KRIM_ENTITY_VISA_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
/

ALTER TABLE KRIM_GRP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_GRP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TR3 FOREIGN KEY (GRP_ID)
    REFERENCES KRIM_GRP_T (GRP_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_GRP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_GRP_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/

ALTER TABLE KRIM_GRP_MBR_T
    ADD CONSTRAINT KRIM_GRP_MBR_TR1 FOREIGN KEY (GRP_ID)
    REFERENCES KRIM_GRP_T (GRP_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_GRP_T
    ADD CONSTRAINT KRIM_GRP_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_PERM_ATTR_DATA_T
    ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_PERM_ATTR_DATA_T
    ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TR3 FOREIGN KEY (PERM_ID)
    REFERENCES KRIM_PERM_T (PERM_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_PERM_ATTR_DATA_T
    ADD CONSTRAINT KRIM_PERM_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/

ALTER TABLE KRIM_PERM_T
    ADD CONSTRAINT KRIM_PERM_TR1 FOREIGN KEY (PERM_TMPL_ID)
    REFERENCES KRIM_PERM_TMPL_T (PERM_TMPL_ID)
/

ALTER TABLE KRIM_PERM_TMPL_T
    ADD CONSTRAINT KRIM_PERM_TMPL_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_PND_AFLTN_MT
    ADD CONSTRAINT KRIM_PND_AFLTN_MT_FK1 FOREIGN KEY (FDOC_NBR)
    REFERENCES KRIM_PERSON_DOCUMENT_T (FDOC_NBR)
/

ALTER TABLE KRIM_PRNCPL_T
    ADD CONSTRAINT KRIM_PRNCPL_TR1 FOREIGN KEY (ENTITY_ID)
    REFERENCES KRIM_ENTITY_T (ENTITY_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ROLE_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_ROLE_MBR_ATTR_DATA_T
    ADD CONSTRAINT KRIM_ROLE_MBR_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/

ALTER TABLE KRIM_ROLE_MBR_T
    ADD CONSTRAINT KRIM_ROLE_MBR_TR1 FOREIGN KEY (ROLE_ID)
    REFERENCES KRIM_ROLE_T (ROLE_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_ROLE_PERM_T
    ADD CONSTRAINT KRIM_ROLE_PERM_TR1 FOREIGN KEY (PERM_ID)
    REFERENCES KRIM_PERM_T (PERM_ID)
/

ALTER TABLE KRIM_ROLE_RSP_T
    ADD CONSTRAINT KRIM_ROLE_RSP_TR1 FOREIGN KEY (RSP_ID)
    REFERENCES KRIM_RSP_T (RSP_ID)
/

ALTER TABLE KRIM_ROLE_T
    ADD CONSTRAINT KRIM_ROLE_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_RSP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TR3 FOREIGN KEY (RSP_ID)
    REFERENCES KRIM_RSP_T (RSP_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_RSP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_RSP_ATTR_DATA_T
    ADD CONSTRAINT KRIM_RSP_ATTR_DATA_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
/

ALTER TABLE KRIM_RSP_T
    ADD CONSTRAINT KRIM_RSP_TR1 FOREIGN KEY (RSP_TMPL_ID)
    REFERENCES KRIM_RSP_TMPL_T (RSP_TMPL_ID)
/

ALTER TABLE KRIM_RSP_TMPL_T
    ADD CONSTRAINT KRIM_RSP_TMPL_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
/

ALTER TABLE KRIM_TYP_ATTR_T
    ADD CONSTRAINT KRIM_TYP_ATTRIBUTE_TR1 FOREIGN KEY (KIM_TYP_ID)
    REFERENCES KRIM_TYP_T (KIM_TYP_ID)
ON DELETE CASCADE
/

ALTER TABLE KRIM_TYP_ATTR_T
    ADD CONSTRAINT KRIM_TYP_ATTR_TR2 FOREIGN KEY (KIM_ATTR_DEFN_ID)
    REFERENCES KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID)
ON DELETE CASCADE
/

ALTER TABLE KRLC_CMP_T
    ADD CONSTRAINT SH_CAMPUS_TR1 FOREIGN KEY (CAMPUS_TYP_CD)
    REFERENCES KRLC_CMP_TYP_T (CAMPUS_TYP_CD)
/

ALTER TABLE KRLC_CNTY_T
    ADD CONSTRAINT KRLC_CNTY_TR1 FOREIGN KEY (STATE_CD, POSTAL_CNTRY_CD)
    REFERENCES KRLC_ST_T (POSTAL_STATE_CD, POSTAL_CNTRY_CD)
/

ALTER TABLE KRLC_PSTL_CD_T
    ADD CONSTRAINT KR_POSTAL_CODE_TR3 FOREIGN KEY (COUNTY_CD, POSTAL_STATE_CD, POSTAL_CNTRY_CD)
    REFERENCES KRLC_CNTY_T (COUNTY_CD, STATE_CD, POSTAL_CNTRY_CD)
/

ALTER TABLE KRLC_PSTL_CD_T
    ADD CONSTRAINT KR_POSTAL_CODE_TR2 FOREIGN KEY (POSTAL_STATE_CD, POSTAL_CNTRY_CD)
    REFERENCES KRLC_ST_T (POSTAL_STATE_CD, POSTAL_CNTRY_CD)
/

ALTER TABLE KRLC_PSTL_CD_T
    ADD CONSTRAINT KR_POSTAL_CODE_TR1 FOREIGN KEY (POSTAL_CNTRY_CD)
    REFERENCES KRLC_CNTRY_T (POSTAL_CNTRY_CD)
/

ALTER TABLE KRLC_ST_T
    ADD CONSTRAINT KR_STATE_TR1 FOREIGN KEY (POSTAL_CNTRY_CD)
    REFERENCES KRLC_CNTRY_T (POSTAL_CNTRY_CD)
/

ALTER TABLE KRMS_ACTN_ATTR_T
    ADD CONSTRAINT KRMS_ACTN_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_ACTN_ATTR_T
    ADD CONSTRAINT KRMS_ACTN_ATTR_FK1 FOREIGN KEY (ACTN_ID)
    REFERENCES KRMS_ACTN_T (ACTN_ID)
/

ALTER TABLE KRMS_ACTN_T
    ADD CONSTRAINT KRMS_ACTN_FK1 FOREIGN KEY (RULE_ID)
    REFERENCES KRMS_RULE_T (RULE_ID)
/

ALTER TABLE KRMS_AGENDA_ATTR_T
    ADD CONSTRAINT KRMS_AGENDA_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_AGENDA_ATTR_T
    ADD CONSTRAINT KRMS_AGENDA_ATTR_FK1 FOREIGN KEY (AGENDA_ID)
    REFERENCES KRMS_AGENDA_T (AGENDA_ID)
/

ALTER TABLE KRMS_AGENDA_ITM_T
    ADD CONSTRAINT KRMS_AGENDA_ITM_FK5 FOREIGN KEY (WHEN_FALSE)
    REFERENCES KRMS_AGENDA_ITM_T (AGENDA_ITM_ID)
/

ALTER TABLE KRMS_AGENDA_ITM_T
    ADD CONSTRAINT KRMS_AGENDA_ITM_FK6 FOREIGN KEY (ALWAYS)
    REFERENCES KRMS_AGENDA_ITM_T (AGENDA_ITM_ID)
/

ALTER TABLE KRMS_AGENDA_ITM_T
    ADD CONSTRAINT KRMS_AGENDA_ITM_FK3 FOREIGN KEY (SUB_AGENDA_ID)
    REFERENCES KRMS_AGENDA_T (AGENDA_ID)
/

ALTER TABLE KRMS_AGENDA_ITM_T
    ADD CONSTRAINT KRMS_AGENDA_ITM_FK4 FOREIGN KEY (WHEN_TRUE)
    REFERENCES KRMS_AGENDA_ITM_T (AGENDA_ITM_ID)
/

ALTER TABLE KRMS_AGENDA_ITM_T
    ADD CONSTRAINT KRMS_AGENDA_ITM_FK2 FOREIGN KEY (AGENDA_ID)
    REFERENCES KRMS_AGENDA_T (AGENDA_ID)
/

ALTER TABLE KRMS_AGENDA_ITM_T
    ADD CONSTRAINT KRMS_AGENDA_ITM_FK1 FOREIGN KEY (RULE_ID)
    REFERENCES KRMS_RULE_T (RULE_ID)
/

ALTER TABLE KRMS_AGENDA_T
    ADD CONSTRAINT KRMS_AGENDA_FK1 FOREIGN KEY (CNTXT_ID)
    REFERENCES KRMS_CNTXT_T (CNTXT_ID)
/

ALTER TABLE KRMS_CMPND_PROP_PROPS_T
    ADD CONSTRAINT KRMS_CMPND_PROP_PROPS_FK1 FOREIGN KEY (PROP_ID)
    REFERENCES KRMS_PROP_T (PROP_ID)
/

ALTER TABLE KRMS_CMPND_PROP_PROPS_T
    ADD CONSTRAINT KRMS_CMPND_PROP_PROPS_FK2 FOREIGN KEY (CMPND_PROP_ID)
    REFERENCES KRMS_PROP_T (PROP_ID)
/

ALTER TABLE KRMS_CNTXT_ATTR_T
    ADD CONSTRAINT KRMS_CNTXT_ATTR_FK1 FOREIGN KEY (CNTXT_ID)
    REFERENCES KRMS_CNTXT_T (CNTXT_ID)
/

ALTER TABLE KRMS_CNTXT_ATTR_T
    ADD CONSTRAINT KRMS_CNTXT_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_CNTXT_VLD_ACTN_TYP_T
    ADD CONSTRAINT KRMS_CNTXT_VLD_ACTN_TYP_FK1 FOREIGN KEY (CNTXT_ID)
    REFERENCES KRMS_CNTXT_T (CNTXT_ID)
/

ALTER TABLE KRMS_CNTXT_VLD_AGENDA_TYP_T
    ADD CONSTRAINT KRMS_CNTXT_VLD_AGENDA_TYP_FK1 FOREIGN KEY (CNTXT_ID)
    REFERENCES KRMS_CNTXT_T (CNTXT_ID)
/

ALTER TABLE KRMS_CNTXT_VLD_FUNC_T
    ADD CONSTRAINT KRMS_CNTXT_VLD_FUNC_FK1 FOREIGN KEY (CNTXT_ID)
    REFERENCES KRMS_CNTXT_T (CNTXT_ID)
/

ALTER TABLE KRMS_CNTXT_VLD_RULE_TYP_T
    ADD CONSTRAINT KRMS_CNTXT_VLD_RULE_TYP_FK1 FOREIGN KEY (CNTXT_ID)
    REFERENCES KRMS_CNTXT_T (CNTXT_ID)
/

ALTER TABLE KRMS_CNTXT_VLD_TERM_SPEC_T
    ADD CONSTRAINT KRMS_CNTXT_VLD_TERM_SPEC_TI2 FOREIGN KEY (TERM_SPEC_ID)
    REFERENCES KRMS_TERM_SPEC_T (TERM_SPEC_ID)
/

ALTER TABLE KRMS_CNTXT_VLD_TERM_SPEC_T
    ADD CONSTRAINT KRMS_CNTXT_VLD_TERM_SPEC_TI1 FOREIGN KEY (CNTXT_ID)
    REFERENCES KRMS_CNTXT_T (CNTXT_ID)
/

ALTER TABLE KRMS_FUNC_CTGRY_T
    ADD CONSTRAINT KRMS_FUNC_CTGRY_FK1 FOREIGN KEY (FUNC_ID)
    REFERENCES KRMS_FUNC_T (FUNC_ID)
/

ALTER TABLE KRMS_FUNC_CTGRY_T
    ADD CONSTRAINT KRMS_FUNC_CTGRY_FK2 FOREIGN KEY (CTGRY_ID)
    REFERENCES KRMS_CTGRY_T (CTGRY_ID)
/

ALTER TABLE KRMS_FUNC_PARM_T
    ADD CONSTRAINT KRMS_FUNC_PARM_FK1 FOREIGN KEY (FUNC_ID)
    REFERENCES KRMS_FUNC_T (FUNC_ID)
/

ALTER TABLE KRMS_FUNC_T
    ADD CONSTRAINT KRMS_FUNC_FK1 FOREIGN KEY (TYP_ID)
    REFERENCES KRMS_TYP_T (TYP_ID)
/

ALTER TABLE KRMS_NL_TMPL_ATTR_T
    ADD CONSTRAINT KRMS_NL_TMPL_ATTR_FK1 FOREIGN KEY (NL_TMPL_ID)
    REFERENCES KRMS_NL_TMPL_T (NL_TMPL_ID)
/

ALTER TABLE KRMS_NL_TMPL_ATTR_T
    ADD CONSTRAINT KRMS_NL_TMPL_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_NL_TMPL_T
    ADD CONSTRAINT KRMS_TYP_T FOREIGN KEY (TYP_ID)
    REFERENCES KRMS_TYP_T (TYP_ID)
/

ALTER TABLE KRMS_NL_TMPL_T
    ADD CONSTRAINT KRMS_NL_TMPL_FK1 FOREIGN KEY (NL_USAGE_ID)
    REFERENCES KRMS_NL_USAGE_T (NL_USAGE_ID)
/

ALTER TABLE KRMS_NL_USAGE_ATTR_T
    ADD CONSTRAINT KRMS_NL_USAGE_ATTR_FK1 FOREIGN KEY (NL_USAGE_ID)
    REFERENCES KRMS_NL_USAGE_T (NL_USAGE_ID)
/

ALTER TABLE KRMS_NL_USAGE_ATTR_T
    ADD CONSTRAINT KRMS_NL_USAGE_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_PROP_PARM_T
    ADD CONSTRAINT KRMS_PROP_PARM_FK1 FOREIGN KEY (PROP_ID)
    REFERENCES KRMS_PROP_T (PROP_ID)
/

ALTER TABLE KRMS_PROP_T
    ADD CONSTRAINT KRMS_PROP_FK2 FOREIGN KEY (TYP_ID)
    REFERENCES KRMS_TYP_T (TYP_ID)
/

ALTER TABLE KRMS_RULE_ATTR_T
    ADD CONSTRAINT KRMS_RULE_ATTR_FK1 FOREIGN KEY (RULE_ID)
    REFERENCES KRMS_RULE_T (RULE_ID)
/

ALTER TABLE KRMS_RULE_ATTR_T
    ADD CONSTRAINT KRMS_RULE_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_RULE_T
    ADD CONSTRAINT KRMS_RULE_FK1 FOREIGN KEY (PROP_ID)
    REFERENCES KRMS_PROP_T (PROP_ID)
/

ALTER TABLE KRMS_TERM_PARM_T
    ADD CONSTRAINT KRMS_TERM_PARM_FK1 FOREIGN KEY (TERM_ID)
    REFERENCES KRMS_TERM_T (TERM_ID)
/

ALTER TABLE KRMS_TERM_RSLVR_ATTR_T
    ADD CONSTRAINT KRMS_TERM_RSLVR_ATTR_FK1 FOREIGN KEY (TERM_RSLVR_ID)
    REFERENCES KRMS_TERM_RSLVR_T (TERM_RSLVR_ID)
/

ALTER TABLE KRMS_TERM_RSLVR_ATTR_T
    ADD CONSTRAINT KRMS_TERM_RSLVR_ATTR_FK2 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_TERM_RSLVR_INPUT_SPEC_T
    ADD CONSTRAINT KRMS_INPUT_ASSET_FK2 FOREIGN KEY (TERM_SPEC_ID)
    REFERENCES KRMS_TERM_SPEC_T (TERM_SPEC_ID)
/

ALTER TABLE KRMS_TERM_RSLVR_INPUT_SPEC_T
    ADD CONSTRAINT KRMS_INPUT_ASSET_FK1 FOREIGN KEY (TERM_RSLVR_ID)
    REFERENCES KRMS_TERM_RSLVR_T (TERM_RSLVR_ID)
/

ALTER TABLE KRMS_TERM_RSLVR_PARM_SPEC_T
    ADD CONSTRAINT KRMS_TERM_RESLV_PARM_FK1 FOREIGN KEY (TERM_RSLVR_ID)
    REFERENCES KRMS_TERM_RSLVR_T (TERM_RSLVR_ID)
/

ALTER TABLE KRMS_TERM_RSLVR_T
    ADD CONSTRAINT KRMS_TERM_RSLVR_FK1 FOREIGN KEY (OUTPUT_TERM_SPEC_ID)
    REFERENCES KRMS_TERM_SPEC_T (TERM_SPEC_ID)
/

ALTER TABLE KRMS_TERM_RSLVR_T
    ADD CONSTRAINT KRMS_TERM_RSLVR_FK3 FOREIGN KEY (TYP_ID)
    REFERENCES KRMS_TYP_T (TYP_ID)
/

ALTER TABLE KRMS_TERM_SPEC_CTGRY_T
    ADD CONSTRAINT KRMS_TERM_SPEC_CTGRY_FK2 FOREIGN KEY (CTGRY_ID)
    REFERENCES KRMS_CTGRY_T (CTGRY_ID)
/

ALTER TABLE KRMS_TERM_SPEC_CTGRY_T
    ADD CONSTRAINT KRMS_TERM_SPEC_CTGRY_FK1 FOREIGN KEY (TERM_SPEC_ID)
    REFERENCES KRMS_TERM_SPEC_T (TERM_SPEC_ID)
/

ALTER TABLE KRMS_TERM_T
    ADD CONSTRAINT KRMS_TERM_T__FK1 FOREIGN KEY (TERM_SPEC_ID)
    REFERENCES KRMS_TERM_SPEC_T (TERM_SPEC_ID)
/

ALTER TABLE KRMS_TYP_ATTR_T
    ADD CONSTRAINT KRMS_TYP_ATTR_FK1 FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_TYP_ATTR_T
    ADD CONSTRAINT KRMS_TYP_ATTR_FK2 FOREIGN KEY (TYP_ID)
    REFERENCES KRMS_TYP_T (TYP_ID)
/

ALTER TABLE KRMS_TYP_RELN_T
    ADD CONSTRAINT KRMS_TYP_RELN_FK1 FOREIGN KEY (FROM_TYP_ID)
    REFERENCES KRMS_TYP_T (TYP_ID)
/

ALTER TABLE KRMS_TYP_RELN_T
    ADD CONSTRAINT KRMS_TYP_RELN_FK2 FOREIGN KEY (TO_TYP_ID)
    REFERENCES KRMS_TYP_T (TYP_ID)
/

ALTER TABLE KRSB_SVC_DEF_T
    ADD CONSTRAINT KRSB_SVC_DEF_FK1 FOREIGN KEY (SVC_DSCRPTR_ID)
    REFERENCES KRSB_SVC_DSCRPTR_T (SVC_DSCRPTR_ID)
ON DELETE CASCADE
/