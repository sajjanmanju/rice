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
-- make combo of nm & nmspc_cd unique in all applicable KRMS tables
-- and drop nmspc_cd where it doesn't make sense
--

--
-- break direct fk to krms_cntxt_t from krms_term_rslvr_t & krms_term_spec_t
--

-- remove cntxt_id from krms_term_rslvr_t, fix unique constraint
alter table krms_term_rslvr_t drop index krms_term_rslvr_tc1;
alter table krms_term_rslvr_t add constraint krms_term_rslvr_tc1 unique (nm, nmspc_cd);
alter table krms_term_rslvr_t drop foreign key krms_term_rslvr_fk2;
alter table krms_term_rslvr_t drop column cntxt_id;

-- remove fk from krms_term_spec_t to krms_cntxt_t
alter table krms_term_spec_t add column nmspc_cd varchar(40) not null;
alter table krms_term_spec_t DROP FOREIGN KEY krms_asset_fk1;
alter table krms_term_spec_t drop key krms_asset_ti1;
alter table krms_term_spec_t drop index krms_asset_tc1;
alter table krms_term_spec_t add constraint krms_term_spec_tc1 unique (nm, nmspc_cd);
alter table krms_term_spec_t drop column cntxt_id;

--
-- refactor krms_cntxt_term_spec_prereq_t to be a valid term specs table instead
--
-- rename krms_cntxt_term_spec_prereq_t to krms_cntxt_vld_term_spec_t
-- and add prereq column
alter table krms_cntxt_term_spec_prereq_t DROP FOREIGN KEY krms_cntxt_asset_prereq_fk1;
alter table krms_cntxt_term_spec_prereq_t drop index krms_cntxt_asset_prereq_ti1;
alter table krms_cntxt_term_spec_prereq_t DROP FOREIGN KEY krms_cntxt_asset_prereq_fk2;
alter table krms_cntxt_term_spec_prereq_t drop index krms_cntxt_asset_prereq_ti2;
rename table krms_cntxt_term_spec_prereq_t to krms_cntxt_vld_term_spec_t;
alter table krms_cntxt_vld_term_spec_t add column prereq varchar(1) default 'n';
alter table krms_cntxt_vld_term_spec_t add constraint krms_cntxt_vld_term_spec_ti1 foreign key (cntxt_id) references krms_cntxt_t(cntxt_id);
alter table krms_cntxt_vld_term_spec_t add constraint krms_cntxt_vld_term_spec_ti2 foreign key (term_spec_id) references krms_term_spec_t(term_spec_id);

--
-- set up some missing unique constraints
--
-- wow, Oracle and MySQL support the same syntax here
alter table krms_cntxt_t add constraint krms_cntxt_tc1 unique (nm, nmspc_cd);
alter table krms_func_t add constraint krms_func_tc1 unique (nm, nmspc_cd);

-- drop namespace code from krms_agenda_t
alter table krms_agenda_t drop column nmspc_cd;
alter table krms_agenda_t add constraint krms_agenda_tc1 unique (nm, cntxt_id);

alter table krms_typ_t add constraint krms_typ_tc1 unique (nm, nmspc_cd);
alter table krms_attr_defn_t add constraint krms_attr_defn_tc1 unique (nm, nmspc_cd);
alter table krms_rule_t add constraint krms_rule_tc1 unique (nm, nmspc_cd);

--
-- clean up some crufty index and constraint names
--

alter table krms_term_rslvr_attr_t drop foreign key krms_asset_rslvr_attr_fk1;
alter table krms_term_rslvr_attr_t drop foreign key krms_asset_rslvr_attr_fk2;
alter table krms_term_rslvr_attr_t drop index krms_asset_rslvr_attr_ti1;
create index krms_asset_rslvr_attr_ti1 on krms_term_rslvr_attr_t (term_rslvr_id);
alter table krms_term_rslvr_attr_t drop index krms_asset_rslvr_attr_ti2;
create index krms_term_rslvr_attr_ti2 on krms_term_rslvr_attr_t (attr_defn_id);
alter table krms_term_rslvr_attr_t add constraint krms_term_rslvr_attr_fk1 foreign key (term_rslvr_id) references krms_term_rslvr_t (term_rslvr_id);
alter table krms_term_rslvr_attr_t add constraint krms_term_rslvr_attr_fk2 foreign key (attr_defn_id) references krms_attr_defn_t (attr_defn_id);

