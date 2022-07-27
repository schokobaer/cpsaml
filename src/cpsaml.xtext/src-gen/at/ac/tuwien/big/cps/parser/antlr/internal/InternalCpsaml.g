/*
 * generated by Xtext 2.22.0
 */
grammar InternalCpsaml;

options {
	superClass=AbstractInternalAntlrParser;
}

@lexer::header {
package at.ac.tuwien.big.cps.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package at.ac.tuwien.big.cps.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import at.ac.tuwien.big.cps.services.CpsamlGrammarAccess;

}

@parser::members {

 	private CpsamlGrammarAccess grammarAccess;

    public InternalCpsamlParser(TokenStream input, CpsamlGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "CpsSystem";
   	}

   	@Override
   	protected CpsamlGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRuleCpsSystem
entryRuleCpsSystem returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getCpsSystemRule()); }
	iv_ruleCpsSystem=ruleCpsSystem
	{ $current=$iv_ruleCpsSystem.current; }
	EOF;

// Rule CpsSystem
ruleCpsSystem returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getCpsSystemAccess().getTopDescSystemParserRuleCall_0());
		}
		this_TopDescSystem_0=ruleTopDescSystem
		{
			$current = $this_TopDescSystem_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getCpsSystemAccess().getInlineDescSystemParserRuleCall_1());
		}
		this_InlineDescSystem_1=ruleInlineDescSystem
		{
			$current = $this_InlineDescSystem_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleTopDescSystem
entryRuleTopDescSystem returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getTopDescSystemRule()); }
	iv_ruleTopDescSystem=ruleTopDescSystem
	{ $current=$iv_ruleTopDescSystem.current; }
	EOF;

// Rule TopDescSystem
ruleTopDescSystem returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getTopDescSystemAccess().getCpsSystemAction_0(),
					$current);
			}
		)
		(
			(
				lv_description_1_0=RULE_ML_DESCRIPTION
				{
					newLeafNode(lv_description_1_0, grammarAccess.getTopDescSystemAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getTopDescSystemRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_1_0,
						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
				}
			)
		)
		otherlv_2='cps-system'
		{
			newLeafNode(otherlv_2, grammarAccess.getTopDescSystemAccess().getCpsSystemKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getTopDescSystemAccess().getNameNameParserRuleCall_3_0());
				}
				lv_name_3_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getTopDescSystemRule());
					}
					set(
						$current,
						"name",
						lv_name_3_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_4=':'
		{
			newLeafNode(otherlv_4, grammarAccess.getTopDescSystemAccess().getColonKeyword_4());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getTopDescSystemAccess().getCpsCpsParserRuleCall_5_0_0());
					}
					lv_cps_5_0=ruleCps
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getTopDescSystemRule());
						}
						add(
							$current,
							"cps",
							lv_cps_5_0,
							"at.ac.tuwien.big.cps.Cpsaml.Cps");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getTopDescSystemAccess().getCommandsCommandParserRuleCall_5_1_0());
					}
					lv_commands_6_0=ruleCommand
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getTopDescSystemRule());
						}
						add(
							$current,
							"commands",
							lv_commands_6_0,
							"at.ac.tuwien.big.cps.Cpsaml.Command");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleInlineDescSystem
entryRuleInlineDescSystem returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getInlineDescSystemRule()); }
	iv_ruleInlineDescSystem=ruleInlineDescSystem
	{ $current=$iv_ruleInlineDescSystem.current; }
	EOF;

// Rule InlineDescSystem
ruleInlineDescSystem returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getInlineDescSystemAccess().getCpsSystemAction_0(),
					$current);
			}
		)
		otherlv_1='cps-system'
		{
			newLeafNode(otherlv_1, grammarAccess.getInlineDescSystemAccess().getCpsSystemKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getInlineDescSystemAccess().getNameNameParserRuleCall_2_0());
				}
				lv_name_2_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInlineDescSystemRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_3=':'
		{
			newLeafNode(otherlv_3, grammarAccess.getInlineDescSystemAccess().getColonKeyword_3());
		}
		(
			(
				lv_description_4_0=RULE_SL_DESCRIPTION
				{
					newLeafNode(lv_description_4_0, grammarAccess.getInlineDescSystemAccess().getDescriptionSL_DESCRIPTIONTerminalRuleCall_4_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getInlineDescSystemRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_4_0,
						"at.ac.tuwien.big.cps.Cpsaml.SL_DESCRIPTION");
				}
			)
		)?
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getInlineDescSystemAccess().getCpsCpsParserRuleCall_5_0_0());
					}
					lv_cps_5_0=ruleCps
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getInlineDescSystemRule());
						}
						add(
							$current,
							"cps",
							lv_cps_5_0,
							"at.ac.tuwien.big.cps.Cpsaml.Cps");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getInlineDescSystemAccess().getCommandsCommandParserRuleCall_5_1_0());
					}
					lv_commands_6_0=ruleCommand
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getInlineDescSystemRule());
						}
						add(
							$current,
							"commands",
							lv_commands_6_0,
							"at.ac.tuwien.big.cps.Cpsaml.Command");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
	)
;

// Entry rule entryRuleCommand
entryRuleCommand returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getCommandRule()); }
	iv_ruleCommand=ruleCommand
	{ $current=$iv_ruleCommand.current; }
	EOF;

// Rule Command
ruleCommand returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getCommandAccess().getTopDescCommandParserRuleCall_0());
		}
		this_TopDescCommand_0=ruleTopDescCommand
		{
			$current = $this_TopDescCommand_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getCommandAccess().getInlineDescCommandParserRuleCall_1());
		}
		this_InlineDescCommand_1=ruleInlineDescCommand
		{
			$current = $this_InlineDescCommand_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleTopDescCommand
entryRuleTopDescCommand returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getTopDescCommandRule()); }
	iv_ruleTopDescCommand=ruleTopDescCommand
	{ $current=$iv_ruleTopDescCommand.current; }
	EOF;

// Rule TopDescCommand
ruleTopDescCommand returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getTopDescCommandAccess().getCommandAction_0(),
					$current);
			}
		)
		(
			(
				lv_description_1_0=RULE_ML_DESCRIPTION
				{
					newLeafNode(lv_description_1_0, grammarAccess.getTopDescCommandAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getTopDescCommandRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_1_0,
						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
				}
			)
		)?
		otherlv_2='command'
		{
			newLeafNode(otherlv_2, grammarAccess.getTopDescCommandAccess().getCommandKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getTopDescCommandAccess().getNameNameParserRuleCall_3_0());
				}
				lv_name_3_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getTopDescCommandRule());
					}
					set(
						$current,
						"name",
						lv_name_3_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleInlineDescCommand
entryRuleInlineDescCommand returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getInlineDescCommandRule()); }
	iv_ruleInlineDescCommand=ruleInlineDescCommand
	{ $current=$iv_ruleInlineDescCommand.current; }
	EOF;

// Rule InlineDescCommand
ruleInlineDescCommand returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getInlineDescCommandAccess().getCommandAction_0(),
					$current);
			}
		)
		otherlv_1='command'
		{
			newLeafNode(otherlv_1, grammarAccess.getInlineDescCommandAccess().getCommandKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getInlineDescCommandAccess().getNameNameParserRuleCall_2_0());
				}
				lv_name_2_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInlineDescCommandRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			(
				lv_description_3_0=RULE_SL_DESCRIPTION
				{
					newLeafNode(lv_description_3_0, grammarAccess.getInlineDescCommandAccess().getDescriptionSL_DESCRIPTIONTerminalRuleCall_3_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getInlineDescCommandRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_3_0,
						"at.ac.tuwien.big.cps.Cpsaml.SL_DESCRIPTION");
				}
			)
		)
	)
;

// Entry rule entryRuleResource
entryRuleResource returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getResourceRule()); }
	iv_ruleResource=ruleResource
	{ $current=$iv_ruleResource.current; }
	EOF;

// Rule Resource
ruleResource returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getResourceAccess().getTopDescResourceParserRuleCall_0());
		}
		this_TopDescResource_0=ruleTopDescResource
		{
			$current = $this_TopDescResource_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getResourceAccess().getInlineDescResourceParserRuleCall_1());
		}
		this_InlineDescResource_1=ruleInlineDescResource
		{
			$current = $this_InlineDescResource_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleTopDescResource
entryRuleTopDescResource returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getTopDescResourceRule()); }
	iv_ruleTopDescResource=ruleTopDescResource
	{ $current=$iv_ruleTopDescResource.current; }
	EOF;

// Rule TopDescResource
ruleTopDescResource returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getTopDescResourceAccess().getResourceAction_0(),
					$current);
			}
		)
		(
			(
				lv_description_1_0=RULE_ML_DESCRIPTION
				{
					newLeafNode(lv_description_1_0, grammarAccess.getTopDescResourceAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getTopDescResourceRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_1_0,
						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
				}
			)
		)
		otherlv_2='produces'
		{
			newLeafNode(otherlv_2, grammarAccess.getTopDescResourceAccess().getProducesKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getTopDescResourceAccess().getNameNameParserRuleCall_3_0());
				}
				lv_name_3_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getTopDescResourceRule());
					}
					set(
						$current,
						"name",
						lv_name_3_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleInlineDescResource
entryRuleInlineDescResource returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getInlineDescResourceRule()); }
	iv_ruleInlineDescResource=ruleInlineDescResource
	{ $current=$iv_ruleInlineDescResource.current; }
	EOF;

// Rule InlineDescResource
ruleInlineDescResource returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getInlineDescResourceAccess().getResourceAction_0(),
					$current);
			}
		)
		otherlv_1='produces'
		{
			newLeafNode(otherlv_1, grammarAccess.getInlineDescResourceAccess().getProducesKeyword_1());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getInlineDescResourceAccess().getNameNameParserRuleCall_2_0());
				}
				lv_name_2_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getInlineDescResourceRule());
					}
					set(
						$current,
						"name",
						lv_name_2_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			(
				lv_description_3_0=RULE_SL_DESCRIPTION
				{
					newLeafNode(lv_description_3_0, grammarAccess.getInlineDescResourceAccess().getDescriptionSL_DESCRIPTIONTerminalRuleCall_3_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getInlineDescResourceRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_3_0,
						"at.ac.tuwien.big.cps.Cpsaml.SL_DESCRIPTION");
				}
			)
		)?
	)
;

// Entry rule entryRuleCps
entryRuleCps returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getCpsRule()); }
	iv_ruleCps=ruleCps
	{ $current=$iv_ruleCps.current; }
	EOF;

// Rule Cps
ruleCps returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getCpsAccess().getSingletonCpsParserRuleCall_0());
		}
		this_SingletonCps_0=ruleSingletonCps
		{
			$current = $this_SingletonCps_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getCpsAccess().getMultiCpsParserRuleCall_1());
		}
		this_MultiCps_1=ruleMultiCps
		{
			$current = $this_MultiCps_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleSingletonCps
entryRuleSingletonCps returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSingletonCpsRule()); }
	iv_ruleSingletonCps=ruleSingletonCps
	{ $current=$iv_ruleSingletonCps.current; }
	EOF;

// Rule SingletonCps
ruleSingletonCps returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getSingletonCpsAccess().getSingletonCpsAction_0(),
					$current);
			}
		)
		(
			(
				lv_description_1_0=RULE_ML_DESCRIPTION
				{
					newLeafNode(lv_description_1_0, grammarAccess.getSingletonCpsAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getSingletonCpsRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_1_0,
						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
				}
			)
		)?
		otherlv_2='cps-singleton'
		{
			newLeafNode(otherlv_2, grammarAccess.getSingletonCpsAccess().getCpsSingletonKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSingletonCpsAccess().getNameNameParserRuleCall_3_0());
				}
				lv_name_3_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSingletonCpsRule());
					}
					set(
						$current,
						"name",
						lv_name_3_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_4='{'
		{
			newLeafNode(otherlv_4, grammarAccess.getSingletonCpsAccess().getLeftCurlyBracketKeyword_4());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getSingletonCpsAccess().getCommandsCommandParserRuleCall_5_0_0());
					}
					lv_commands_5_0=ruleCommand
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getSingletonCpsRule());
						}
						add(
							$current,
							"commands",
							lv_commands_5_0,
							"at.ac.tuwien.big.cps.Cpsaml.Command");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getSingletonCpsAccess().getResourcesResourceParserRuleCall_5_1_0());
					}
					lv_resources_6_0=ruleResource
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getSingletonCpsRule());
						}
						add(
							$current,
							"resources",
							lv_resources_6_0,
							"at.ac.tuwien.big.cps.Cpsaml.Resource");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getSingletonCpsAccess().getHardwareHardwareParserRuleCall_5_2_0());
					}
					lv_hardware_7_0=ruleHardware
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getSingletonCpsRule());
						}
						add(
							$current,
							"hardware",
							lv_hardware_7_0,
							"at.ac.tuwien.big.cps.Cpsaml.Hardware");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		otherlv_8='}'
		{
			newLeafNode(otherlv_8, grammarAccess.getSingletonCpsAccess().getRightCurlyBracketKeyword_6());
		}
	)
;

// Entry rule entryRuleMultiCps
entryRuleMultiCps returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getMultiCpsRule()); }
	iv_ruleMultiCps=ruleMultiCps
	{ $current=$iv_ruleMultiCps.current; }
	EOF;

// Rule MultiCps
ruleMultiCps returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getMultiCpsAccess().getMultiCpsAction_0(),
					$current);
			}
		)
		(
			(
				lv_description_1_0=RULE_ML_DESCRIPTION
				{
					newLeafNode(lv_description_1_0, grammarAccess.getMultiCpsAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getMultiCpsRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_1_0,
						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
				}
			)
		)?
		otherlv_2='cps'
		{
			newLeafNode(otherlv_2, grammarAccess.getMultiCpsAccess().getCpsKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getMultiCpsAccess().getNameNameParserRuleCall_3_0());
				}
				lv_name_3_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getMultiCpsRule());
					}
					set(
						$current,
						"name",
						lv_name_3_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='instances'
			{
				newLeafNode(otherlv_4, grammarAccess.getMultiCpsAccess().getInstancesKeyword_4_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getMultiCpsAccess().getInstancesNameParserRuleCall_4_1_0());
					}
					lv_instances_5_0=ruleName
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getMultiCpsRule());
						}
						add(
							$current,
							"instances",
							lv_instances_5_0,
							"at.ac.tuwien.big.cps.Cpsaml.Name");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_6=','
				{
					newLeafNode(otherlv_6, grammarAccess.getMultiCpsAccess().getCommaKeyword_4_2_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getMultiCpsAccess().getInstancesNameParserRuleCall_4_2_1_0());
						}
						lv_instances_7_0=ruleName
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getMultiCpsRule());
							}
							add(
								$current,
								"instances",
								lv_instances_7_0,
								"at.ac.tuwien.big.cps.Cpsaml.Name");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
		otherlv_8='{'
		{
			newLeafNode(otherlv_8, grammarAccess.getMultiCpsAccess().getLeftCurlyBracketKeyword_5());
		}
		(
			(
				(
					{
						newCompositeNode(grammarAccess.getMultiCpsAccess().getCommandsCommandParserRuleCall_6_0_0());
					}
					lv_commands_9_0=ruleCommand
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getMultiCpsRule());
						}
						add(
							$current,
							"commands",
							lv_commands_9_0,
							"at.ac.tuwien.big.cps.Cpsaml.Command");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getMultiCpsAccess().getResourcesResourceParserRuleCall_6_1_0());
					}
					lv_resources_10_0=ruleResource
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getMultiCpsRule());
						}
						add(
							$current,
							"resources",
							lv_resources_10_0,
							"at.ac.tuwien.big.cps.Cpsaml.Resource");
						afterParserOrEnumRuleCall();
					}
				)
			)
			    |
			(
				(
					{
						newCompositeNode(grammarAccess.getMultiCpsAccess().getHardwareHardwareParserRuleCall_6_2_0());
					}
					lv_hardware_11_0=ruleHardware
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getMultiCpsRule());
						}
						add(
							$current,
							"hardware",
							lv_hardware_11_0,
							"at.ac.tuwien.big.cps.Cpsaml.Hardware");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)*
		otherlv_12='}'
		{
			newLeafNode(otherlv_12, grammarAccess.getMultiCpsAccess().getRightCurlyBracketKeyword_7());
		}
	)
;

// Entry rule entryRuleHardware
entryRuleHardware returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getHardwareRule()); }
	iv_ruleHardware=ruleHardware
	{ $current=$iv_ruleHardware.current; }
	EOF;

// Rule Hardware
ruleHardware returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getHardwareAccess().getActuatorParserRuleCall_0());
		}
		this_Actuator_0=ruleActuator
		{
			$current = $this_Actuator_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getHardwareAccess().getSensorParserRuleCall_1());
		}
		this_Sensor_1=ruleSensor
		{
			$current = $this_Sensor_1.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRuleActuator
entryRuleActuator returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getActuatorRule()); }
	iv_ruleActuator=ruleActuator
	{ $current=$iv_ruleActuator.current; }
	EOF;

// Rule Actuator
ruleActuator returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getActuatorAccess().getActuatorAction_0(),
					$current);
			}
		)
		(
			(
				lv_description_1_0=RULE_ML_DESCRIPTION
				{
					newLeafNode(lv_description_1_0, grammarAccess.getActuatorAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getActuatorRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_1_0,
						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
				}
			)
		)?
		otherlv_2='actuator'
		{
			newLeafNode(otherlv_2, grammarAccess.getActuatorAccess().getActuatorKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getActuatorAccess().getNameNameParserRuleCall_3_0());
				}
				lv_name_3_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getActuatorRule());
					}
					set(
						$current,
						"name",
						lv_name_3_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='instances'
			{
				newLeafNode(otherlv_4, grammarAccess.getActuatorAccess().getInstancesKeyword_4_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getActuatorAccess().getInstancesNameParserRuleCall_4_1_0());
					}
					lv_instances_5_0=ruleName
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getActuatorRule());
						}
						add(
							$current,
							"instances",
							lv_instances_5_0,
							"at.ac.tuwien.big.cps.Cpsaml.Name");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_6=','
				{
					newLeafNode(otherlv_6, grammarAccess.getActuatorAccess().getCommaKeyword_4_2_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getActuatorAccess().getInstancesNameParserRuleCall_4_2_1_0());
						}
						lv_instances_7_0=ruleName
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getActuatorRule());
							}
							add(
								$current,
								"instances",
								lv_instances_7_0,
								"at.ac.tuwien.big.cps.Cpsaml.Name");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
		otherlv_8='{'
		{
			newLeafNode(otherlv_8, grammarAccess.getActuatorAccess().getLeftCurlyBracketKeyword_5());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getActuatorAccess().getCommandsCommandParserRuleCall_6_0());
				}
				lv_commands_9_0=ruleCommand
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getActuatorRule());
					}
					add(
						$current,
						"commands",
						lv_commands_9_0,
						"at.ac.tuwien.big.cps.Cpsaml.Command");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_10='}'
		{
			newLeafNode(otherlv_10, grammarAccess.getActuatorAccess().getRightCurlyBracketKeyword_7());
		}
	)
;

// Entry rule entryRuleSensor
entryRuleSensor returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSensorRule()); }
	iv_ruleSensor=ruleSensor
	{ $current=$iv_ruleSensor.current; }
	EOF;

// Rule Sensor
ruleSensor returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getSensorAccess().getSensorAction_0(),
					$current);
			}
		)
		(
			(
				lv_description_1_0=RULE_ML_DESCRIPTION
				{
					newLeafNode(lv_description_1_0, grammarAccess.getSensorAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getSensorRule());
					}
					setWithLastConsumed(
						$current,
						"description",
						lv_description_1_0,
						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
				}
			)
		)?
		otherlv_2='sensor'
		{
			newLeafNode(otherlv_2, grammarAccess.getSensorAccess().getSensorKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSensorAccess().getNameNameParserRuleCall_3_0());
				}
				lv_name_3_0=ruleName
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSensorRule());
					}
					set(
						$current,
						"name",
						lv_name_3_0,
						"at.ac.tuwien.big.cps.Cpsaml.Name");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			otherlv_4='instances'
			{
				newLeafNode(otherlv_4, grammarAccess.getSensorAccess().getInstancesKeyword_4_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getSensorAccess().getInstancesNameParserRuleCall_4_1_0());
					}
					lv_instances_5_0=ruleName
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getSensorRule());
						}
						add(
							$current,
							"instances",
							lv_instances_5_0,
							"at.ac.tuwien.big.cps.Cpsaml.Name");
						afterParserOrEnumRuleCall();
					}
				)
			)
			(
				otherlv_6=','
				{
					newLeafNode(otherlv_6, grammarAccess.getSensorAccess().getCommaKeyword_4_2_0());
				}
				(
					(
						{
							newCompositeNode(grammarAccess.getSensorAccess().getInstancesNameParserRuleCall_4_2_1_0());
						}
						lv_instances_7_0=ruleName
						{
							if ($current==null) {
								$current = createModelElementForParent(grammarAccess.getSensorRule());
							}
							add(
								$current,
								"instances",
								lv_instances_7_0,
								"at.ac.tuwien.big.cps.Cpsaml.Name");
							afterParserOrEnumRuleCall();
						}
					)
				)
			)*
		)?
		otherlv_8='{'
		{
			newLeafNode(otherlv_8, grammarAccess.getSensorAccess().getLeftCurlyBracketKeyword_5());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getSensorAccess().getResourcesResourceParserRuleCall_6_0());
				}
				lv_resources_9_0=ruleResource
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSensorRule());
					}
					add(
						$current,
						"resources",
						lv_resources_9_0,
						"at.ac.tuwien.big.cps.Cpsaml.Resource");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_10='}'
		{
			newLeafNode(otherlv_10, grammarAccess.getSensorAccess().getRightCurlyBracketKeyword_7());
		}
	)
;

// Entry rule entryRuleName
entryRuleName returns [String current=null]:
	{ newCompositeNode(grammarAccess.getNameRule()); }
	iv_ruleName=ruleName
	{ $current=$iv_ruleName.current.getText(); }
	EOF;

// Rule Name
ruleName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		this_STRING_0=RULE_STRING
		{
			$current.merge(this_STRING_0);
		}
		{
			newLeafNode(this_STRING_0, grammarAccess.getNameAccess().getSTRINGTerminalRuleCall_0());
		}
		    |
		this_ID_1=RULE_ID
		{
			$current.merge(this_ID_1);
		}
		{
			newLeafNode(this_ID_1, grammarAccess.getNameAccess().getIDTerminalRuleCall_1());
		}
	)
;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_STRING : '\'' ('\\' .|~(('\\'|'\'')))* '\'';

RULE_ML_COMMENT : '***' ( options {greedy=false;} : . )*'***';

RULE_SL_COMMENT : '#' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_ML_DESCRIPTION : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_DESCRIPTION : '--' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
