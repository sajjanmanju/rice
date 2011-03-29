package org.kuali.rice.krms.impl.repository

import org.kuali.rice.kns.bo.PersistableBusinessObjectBase

import org.kuali.rice.krms.api.repository.Rule
import org.kuali.rice.krms.api.repository.RuleContract
import org.kuali.rice.krms.api.repository.RuleAttribute


public class RuleBo extends PersistableBusinessObjectBase implements RuleContract {

	def String ruleId
	def String namespace
	def String name
	def String typeId
	def String propId
	def String actionId

	def PropositionBo proposition;
	def List<RuleAttributeBo> attributes
	def ActionBo action
	
	
	/**
	* Converts a mutable bo to it's immutable counterpart
	* @param bo the mutable business object
	* @return the immutable object
	*/
   static Rule to(RuleBo bo) {
	   if (bo == null) { return null }
	   return org.kuali.rice.krms.api.repository.Rule.Builder.create(bo).build()
   }

   /**
	* Converts a immutable object to it's mutable bo counterpart
	* @param im immutable object
	* @return the mutable bo
	*/
   static RuleBo from(Rule im) {
	   if (im == null) { return null }

	   RuleBo bo = new RuleBo()
	   bo.ruleId = im.ruleId
	   bo.namespace = im.namespace
	   bo.name = im.name
	   bo.typeId = im.typeId
	   bo.actionId = im.actionId
	   bo.propId = im.propId
	   bo.attributes = new ArrayList<RuleAttributeBo>()
	   for (attr in im.attributes){
		   bo.attributes.add ( RuleAttributeBo.from(attr) )
	   }
	   bo.
	   bo.action = ActionBo.from(im.action)
	   return bo
   }
 
   public PropositionBo getProposition(){
	   return proposition
   }
   
   public ActionBo getAction(){
	   return action
   }
} 