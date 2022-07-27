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



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalCpsamlParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ML_DESCRIPTION", "RULE_SL_DESCRIPTION", "RULE_STRING", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'cps-system'", "':'", "'command'", "'produces'", "'cps-singleton'", "'{'", "'}'", "'cps'", "'instances'", "','", "'actuator'", "'sensor'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ML_DESCRIPTION=4;
    public static final int RULE_ID=7;
    public static final int RULE_WS=10;
    public static final int RULE_SL_DESCRIPTION=5;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalCpsamlParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalCpsamlParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalCpsamlParser.tokenNames; }
    public String getGrammarFileName() { return "InternalCpsaml.g"; }



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




    // $ANTLR start "entryRuleCpsSystem"
    // InternalCpsaml.g:64:1: entryRuleCpsSystem returns [EObject current=null] : iv_ruleCpsSystem= ruleCpsSystem EOF ;
    public final EObject entryRuleCpsSystem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCpsSystem = null;


        try {
            // InternalCpsaml.g:64:50: (iv_ruleCpsSystem= ruleCpsSystem EOF )
            // InternalCpsaml.g:65:2: iv_ruleCpsSystem= ruleCpsSystem EOF
            {
             newCompositeNode(grammarAccess.getCpsSystemRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCpsSystem=ruleCpsSystem();

            state._fsp--;

             current =iv_ruleCpsSystem; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCpsSystem"


    // $ANTLR start "ruleCpsSystem"
    // InternalCpsaml.g:71:1: ruleCpsSystem returns [EObject current=null] : (this_TopDescSystem_0= ruleTopDescSystem | this_InlineDescSystem_1= ruleInlineDescSystem ) ;
    public final EObject ruleCpsSystem() throws RecognitionException {
        EObject current = null;

        EObject this_TopDescSystem_0 = null;

        EObject this_InlineDescSystem_1 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:77:2: ( (this_TopDescSystem_0= ruleTopDescSystem | this_InlineDescSystem_1= ruleInlineDescSystem ) )
            // InternalCpsaml.g:78:2: (this_TopDescSystem_0= ruleTopDescSystem | this_InlineDescSystem_1= ruleInlineDescSystem )
            {
            // InternalCpsaml.g:78:2: (this_TopDescSystem_0= ruleTopDescSystem | this_InlineDescSystem_1= ruleInlineDescSystem )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ML_DESCRIPTION) ) {
                alt1=1;
            }
            else if ( (LA1_0==12) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalCpsaml.g:79:3: this_TopDescSystem_0= ruleTopDescSystem
                    {

                    			newCompositeNode(grammarAccess.getCpsSystemAccess().getTopDescSystemParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_TopDescSystem_0=ruleTopDescSystem();

                    state._fsp--;


                    			current = this_TopDescSystem_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalCpsaml.g:88:3: this_InlineDescSystem_1= ruleInlineDescSystem
                    {

                    			newCompositeNode(grammarAccess.getCpsSystemAccess().getInlineDescSystemParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_InlineDescSystem_1=ruleInlineDescSystem();

                    state._fsp--;


                    			current = this_InlineDescSystem_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCpsSystem"


    // $ANTLR start "entryRuleTopDescSystem"
    // InternalCpsaml.g:100:1: entryRuleTopDescSystem returns [EObject current=null] : iv_ruleTopDescSystem= ruleTopDescSystem EOF ;
    public final EObject entryRuleTopDescSystem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTopDescSystem = null;


        try {
            // InternalCpsaml.g:100:54: (iv_ruleTopDescSystem= ruleTopDescSystem EOF )
            // InternalCpsaml.g:101:2: iv_ruleTopDescSystem= ruleTopDescSystem EOF
            {
             newCompositeNode(grammarAccess.getTopDescSystemRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTopDescSystem=ruleTopDescSystem();

            state._fsp--;

             current =iv_ruleTopDescSystem; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTopDescSystem"


    // $ANTLR start "ruleTopDescSystem"
    // InternalCpsaml.g:107:1: ruleTopDescSystem returns [EObject current=null] : ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'cps-system' ( (lv_name_3_0= ruleName ) ) otherlv_4= ':' ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )* ) ;
    public final EObject ruleTopDescSystem() throws RecognitionException {
        EObject current = null;

        Token lv_description_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        EObject lv_cps_5_0 = null;

        EObject lv_commands_6_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:113:2: ( ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'cps-system' ( (lv_name_3_0= ruleName ) ) otherlv_4= ':' ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )* ) )
            // InternalCpsaml.g:114:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'cps-system' ( (lv_name_3_0= ruleName ) ) otherlv_4= ':' ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )* )
            {
            // InternalCpsaml.g:114:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'cps-system' ( (lv_name_3_0= ruleName ) ) otherlv_4= ':' ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )* )
            // InternalCpsaml.g:115:3: () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'cps-system' ( (lv_name_3_0= ruleName ) ) otherlv_4= ':' ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )*
            {
            // InternalCpsaml.g:115:3: ()
            // InternalCpsaml.g:116:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTopDescSystemAccess().getCpsSystemAction_0(),
            					current);
            			

            }

            // InternalCpsaml.g:122:3: ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )
            // InternalCpsaml.g:123:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
            {
            // InternalCpsaml.g:123:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
            // InternalCpsaml.g:124:5: lv_description_1_0= RULE_ML_DESCRIPTION
            {
            lv_description_1_0=(Token)match(input,RULE_ML_DESCRIPTION,FOLLOW_3); 

            					newLeafNode(lv_description_1_0, grammarAccess.getTopDescSystemAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTopDescSystemRule());
            					}
            					setWithLastConsumed(
            						current,
            						"description",
            						lv_description_1_0,
            						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getTopDescSystemAccess().getCpsSystemKeyword_2());
            		
            // InternalCpsaml.g:144:3: ( (lv_name_3_0= ruleName ) )
            // InternalCpsaml.g:145:4: (lv_name_3_0= ruleName )
            {
            // InternalCpsaml.g:145:4: (lv_name_3_0= ruleName )
            // InternalCpsaml.g:146:5: lv_name_3_0= ruleName
            {

            					newCompositeNode(grammarAccess.getTopDescSystemAccess().getNameNameParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_5);
            lv_name_3_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTopDescSystemRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_3_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getTopDescSystemAccess().getColonKeyword_4());
            		
            // InternalCpsaml.g:167:3: ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )*
            loop2:
            do {
                int alt2=3;
                switch ( input.LA(1) ) {
                case RULE_ML_DESCRIPTION:
                    {
                    int LA2_2 = input.LA(2);

                    if ( (LA2_2==16||LA2_2==19) ) {
                        alt2=1;
                    }
                    else if ( (LA2_2==14) ) {
                        alt2=2;
                    }


                    }
                    break;
                case 16:
                case 19:
                    {
                    alt2=1;
                    }
                    break;
                case 14:
                    {
                    alt2=2;
                    }
                    break;

                }

                switch (alt2) {
            	case 1 :
            	    // InternalCpsaml.g:168:4: ( (lv_cps_5_0= ruleCps ) )
            	    {
            	    // InternalCpsaml.g:168:4: ( (lv_cps_5_0= ruleCps ) )
            	    // InternalCpsaml.g:169:5: (lv_cps_5_0= ruleCps )
            	    {
            	    // InternalCpsaml.g:169:5: (lv_cps_5_0= ruleCps )
            	    // InternalCpsaml.g:170:6: lv_cps_5_0= ruleCps
            	    {

            	    						newCompositeNode(grammarAccess.getTopDescSystemAccess().getCpsCpsParserRuleCall_5_0_0());
            	    					
            	    pushFollow(FOLLOW_6);
            	    lv_cps_5_0=ruleCps();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getTopDescSystemRule());
            	    						}
            	    						add(
            	    							current,
            	    							"cps",
            	    							lv_cps_5_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Cps");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCpsaml.g:188:4: ( (lv_commands_6_0= ruleCommand ) )
            	    {
            	    // InternalCpsaml.g:188:4: ( (lv_commands_6_0= ruleCommand ) )
            	    // InternalCpsaml.g:189:5: (lv_commands_6_0= ruleCommand )
            	    {
            	    // InternalCpsaml.g:189:5: (lv_commands_6_0= ruleCommand )
            	    // InternalCpsaml.g:190:6: lv_commands_6_0= ruleCommand
            	    {

            	    						newCompositeNode(grammarAccess.getTopDescSystemAccess().getCommandsCommandParserRuleCall_5_1_0());
            	    					
            	    pushFollow(FOLLOW_6);
            	    lv_commands_6_0=ruleCommand();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getTopDescSystemRule());
            	    						}
            	    						add(
            	    							current,
            	    							"commands",
            	    							lv_commands_6_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Command");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTopDescSystem"


    // $ANTLR start "entryRuleInlineDescSystem"
    // InternalCpsaml.g:212:1: entryRuleInlineDescSystem returns [EObject current=null] : iv_ruleInlineDescSystem= ruleInlineDescSystem EOF ;
    public final EObject entryRuleInlineDescSystem() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInlineDescSystem = null;


        try {
            // InternalCpsaml.g:212:57: (iv_ruleInlineDescSystem= ruleInlineDescSystem EOF )
            // InternalCpsaml.g:213:2: iv_ruleInlineDescSystem= ruleInlineDescSystem EOF
            {
             newCompositeNode(grammarAccess.getInlineDescSystemRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInlineDescSystem=ruleInlineDescSystem();

            state._fsp--;

             current =iv_ruleInlineDescSystem; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInlineDescSystem"


    // $ANTLR start "ruleInlineDescSystem"
    // InternalCpsaml.g:219:1: ruleInlineDescSystem returns [EObject current=null] : ( () otherlv_1= 'cps-system' ( (lv_name_2_0= ruleName ) ) otherlv_3= ':' ( (lv_description_4_0= RULE_SL_DESCRIPTION ) )? ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )* ) ;
    public final EObject ruleInlineDescSystem() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token lv_description_4_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_cps_5_0 = null;

        EObject lv_commands_6_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:225:2: ( ( () otherlv_1= 'cps-system' ( (lv_name_2_0= ruleName ) ) otherlv_3= ':' ( (lv_description_4_0= RULE_SL_DESCRIPTION ) )? ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )* ) )
            // InternalCpsaml.g:226:2: ( () otherlv_1= 'cps-system' ( (lv_name_2_0= ruleName ) ) otherlv_3= ':' ( (lv_description_4_0= RULE_SL_DESCRIPTION ) )? ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )* )
            {
            // InternalCpsaml.g:226:2: ( () otherlv_1= 'cps-system' ( (lv_name_2_0= ruleName ) ) otherlv_3= ':' ( (lv_description_4_0= RULE_SL_DESCRIPTION ) )? ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )* )
            // InternalCpsaml.g:227:3: () otherlv_1= 'cps-system' ( (lv_name_2_0= ruleName ) ) otherlv_3= ':' ( (lv_description_4_0= RULE_SL_DESCRIPTION ) )? ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )*
            {
            // InternalCpsaml.g:227:3: ()
            // InternalCpsaml.g:228:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getInlineDescSystemAccess().getCpsSystemAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getInlineDescSystemAccess().getCpsSystemKeyword_1());
            		
            // InternalCpsaml.g:238:3: ( (lv_name_2_0= ruleName ) )
            // InternalCpsaml.g:239:4: (lv_name_2_0= ruleName )
            {
            // InternalCpsaml.g:239:4: (lv_name_2_0= ruleName )
            // InternalCpsaml.g:240:5: lv_name_2_0= ruleName
            {

            					newCompositeNode(grammarAccess.getInlineDescSystemAccess().getNameNameParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_5);
            lv_name_2_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getInlineDescSystemRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_7); 

            			newLeafNode(otherlv_3, grammarAccess.getInlineDescSystemAccess().getColonKeyword_3());
            		
            // InternalCpsaml.g:261:3: ( (lv_description_4_0= RULE_SL_DESCRIPTION ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_SL_DESCRIPTION) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalCpsaml.g:262:4: (lv_description_4_0= RULE_SL_DESCRIPTION )
                    {
                    // InternalCpsaml.g:262:4: (lv_description_4_0= RULE_SL_DESCRIPTION )
                    // InternalCpsaml.g:263:5: lv_description_4_0= RULE_SL_DESCRIPTION
                    {
                    lv_description_4_0=(Token)match(input,RULE_SL_DESCRIPTION,FOLLOW_6); 

                    					newLeafNode(lv_description_4_0, grammarAccess.getInlineDescSystemAccess().getDescriptionSL_DESCRIPTIONTerminalRuleCall_4_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getInlineDescSystemRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"description",
                    						lv_description_4_0,
                    						"at.ac.tuwien.big.cps.Cpsaml.SL_DESCRIPTION");
                    				

                    }


                    }
                    break;

            }

            // InternalCpsaml.g:279:3: ( ( (lv_cps_5_0= ruleCps ) ) | ( (lv_commands_6_0= ruleCommand ) ) )*
            loop4:
            do {
                int alt4=3;
                switch ( input.LA(1) ) {
                case RULE_ML_DESCRIPTION:
                    {
                    int LA4_2 = input.LA(2);

                    if ( (LA4_2==16||LA4_2==19) ) {
                        alt4=1;
                    }
                    else if ( (LA4_2==14) ) {
                        alt4=2;
                    }


                    }
                    break;
                case 16:
                case 19:
                    {
                    alt4=1;
                    }
                    break;
                case 14:
                    {
                    alt4=2;
                    }
                    break;

                }

                switch (alt4) {
            	case 1 :
            	    // InternalCpsaml.g:280:4: ( (lv_cps_5_0= ruleCps ) )
            	    {
            	    // InternalCpsaml.g:280:4: ( (lv_cps_5_0= ruleCps ) )
            	    // InternalCpsaml.g:281:5: (lv_cps_5_0= ruleCps )
            	    {
            	    // InternalCpsaml.g:281:5: (lv_cps_5_0= ruleCps )
            	    // InternalCpsaml.g:282:6: lv_cps_5_0= ruleCps
            	    {

            	    						newCompositeNode(grammarAccess.getInlineDescSystemAccess().getCpsCpsParserRuleCall_5_0_0());
            	    					
            	    pushFollow(FOLLOW_6);
            	    lv_cps_5_0=ruleCps();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getInlineDescSystemRule());
            	    						}
            	    						add(
            	    							current,
            	    							"cps",
            	    							lv_cps_5_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Cps");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCpsaml.g:300:4: ( (lv_commands_6_0= ruleCommand ) )
            	    {
            	    // InternalCpsaml.g:300:4: ( (lv_commands_6_0= ruleCommand ) )
            	    // InternalCpsaml.g:301:5: (lv_commands_6_0= ruleCommand )
            	    {
            	    // InternalCpsaml.g:301:5: (lv_commands_6_0= ruleCommand )
            	    // InternalCpsaml.g:302:6: lv_commands_6_0= ruleCommand
            	    {

            	    						newCompositeNode(grammarAccess.getInlineDescSystemAccess().getCommandsCommandParserRuleCall_5_1_0());
            	    					
            	    pushFollow(FOLLOW_6);
            	    lv_commands_6_0=ruleCommand();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getInlineDescSystemRule());
            	    						}
            	    						add(
            	    							current,
            	    							"commands",
            	    							lv_commands_6_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Command");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInlineDescSystem"


    // $ANTLR start "entryRuleCommand"
    // InternalCpsaml.g:324:1: entryRuleCommand returns [EObject current=null] : iv_ruleCommand= ruleCommand EOF ;
    public final EObject entryRuleCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCommand = null;


        try {
            // InternalCpsaml.g:324:48: (iv_ruleCommand= ruleCommand EOF )
            // InternalCpsaml.g:325:2: iv_ruleCommand= ruleCommand EOF
            {
             newCompositeNode(grammarAccess.getCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCommand=ruleCommand();

            state._fsp--;

             current =iv_ruleCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCommand"


    // $ANTLR start "ruleCommand"
    // InternalCpsaml.g:331:1: ruleCommand returns [EObject current=null] : (this_TopDescCommand_0= ruleTopDescCommand | this_InlineDescCommand_1= ruleInlineDescCommand ) ;
    public final EObject ruleCommand() throws RecognitionException {
        EObject current = null;

        EObject this_TopDescCommand_0 = null;

        EObject this_InlineDescCommand_1 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:337:2: ( (this_TopDescCommand_0= ruleTopDescCommand | this_InlineDescCommand_1= ruleInlineDescCommand ) )
            // InternalCpsaml.g:338:2: (this_TopDescCommand_0= ruleTopDescCommand | this_InlineDescCommand_1= ruleInlineDescCommand )
            {
            // InternalCpsaml.g:338:2: (this_TopDescCommand_0= ruleTopDescCommand | this_InlineDescCommand_1= ruleInlineDescCommand )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ML_DESCRIPTION) ) {
                alt5=1;
            }
            else if ( (LA5_0==14) ) {
                int LA5_2 = input.LA(2);

                if ( (LA5_2==RULE_STRING) ) {
                    int LA5_3 = input.LA(3);

                    if ( (LA5_3==EOF||LA5_3==RULE_ML_DESCRIPTION||(LA5_3>=14 && LA5_3<=16)||(LA5_3>=18 && LA5_3<=19)||(LA5_3>=22 && LA5_3<=23)) ) {
                        alt5=1;
                    }
                    else if ( (LA5_3==RULE_SL_DESCRIPTION) ) {
                        alt5=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 3, input);

                        throw nvae;
                    }
                }
                else if ( (LA5_2==RULE_ID) ) {
                    int LA5_4 = input.LA(3);

                    if ( (LA5_4==EOF||LA5_4==RULE_ML_DESCRIPTION||(LA5_4>=14 && LA5_4<=16)||(LA5_4>=18 && LA5_4<=19)||(LA5_4>=22 && LA5_4<=23)) ) {
                        alt5=1;
                    }
                    else if ( (LA5_4==RULE_SL_DESCRIPTION) ) {
                        alt5=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 4, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalCpsaml.g:339:3: this_TopDescCommand_0= ruleTopDescCommand
                    {

                    			newCompositeNode(grammarAccess.getCommandAccess().getTopDescCommandParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_TopDescCommand_0=ruleTopDescCommand();

                    state._fsp--;


                    			current = this_TopDescCommand_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalCpsaml.g:348:3: this_InlineDescCommand_1= ruleInlineDescCommand
                    {

                    			newCompositeNode(grammarAccess.getCommandAccess().getInlineDescCommandParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_InlineDescCommand_1=ruleInlineDescCommand();

                    state._fsp--;


                    			current = this_InlineDescCommand_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCommand"


    // $ANTLR start "entryRuleTopDescCommand"
    // InternalCpsaml.g:360:1: entryRuleTopDescCommand returns [EObject current=null] : iv_ruleTopDescCommand= ruleTopDescCommand EOF ;
    public final EObject entryRuleTopDescCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTopDescCommand = null;


        try {
            // InternalCpsaml.g:360:55: (iv_ruleTopDescCommand= ruleTopDescCommand EOF )
            // InternalCpsaml.g:361:2: iv_ruleTopDescCommand= ruleTopDescCommand EOF
            {
             newCompositeNode(grammarAccess.getTopDescCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTopDescCommand=ruleTopDescCommand();

            state._fsp--;

             current =iv_ruleTopDescCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTopDescCommand"


    // $ANTLR start "ruleTopDescCommand"
    // InternalCpsaml.g:367:1: ruleTopDescCommand returns [EObject current=null] : ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'command' ( (lv_name_3_0= ruleName ) ) ) ;
    public final EObject ruleTopDescCommand() throws RecognitionException {
        EObject current = null;

        Token lv_description_1_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:373:2: ( ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'command' ( (lv_name_3_0= ruleName ) ) ) )
            // InternalCpsaml.g:374:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'command' ( (lv_name_3_0= ruleName ) ) )
            {
            // InternalCpsaml.g:374:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'command' ( (lv_name_3_0= ruleName ) ) )
            // InternalCpsaml.g:375:3: () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'command' ( (lv_name_3_0= ruleName ) )
            {
            // InternalCpsaml.g:375:3: ()
            // InternalCpsaml.g:376:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTopDescCommandAccess().getCommandAction_0(),
            					current);
            			

            }

            // InternalCpsaml.g:382:3: ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ML_DESCRIPTION) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalCpsaml.g:383:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    {
                    // InternalCpsaml.g:383:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    // InternalCpsaml.g:384:5: lv_description_1_0= RULE_ML_DESCRIPTION
                    {
                    lv_description_1_0=(Token)match(input,RULE_ML_DESCRIPTION,FOLLOW_8); 

                    					newLeafNode(lv_description_1_0, grammarAccess.getTopDescCommandAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getTopDescCommandRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"description",
                    						lv_description_1_0,
                    						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getTopDescCommandAccess().getCommandKeyword_2());
            		
            // InternalCpsaml.g:404:3: ( (lv_name_3_0= ruleName ) )
            // InternalCpsaml.g:405:4: (lv_name_3_0= ruleName )
            {
            // InternalCpsaml.g:405:4: (lv_name_3_0= ruleName )
            // InternalCpsaml.g:406:5: lv_name_3_0= ruleName
            {

            					newCompositeNode(grammarAccess.getTopDescCommandAccess().getNameNameParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_name_3_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTopDescCommandRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_3_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTopDescCommand"


    // $ANTLR start "entryRuleInlineDescCommand"
    // InternalCpsaml.g:427:1: entryRuleInlineDescCommand returns [EObject current=null] : iv_ruleInlineDescCommand= ruleInlineDescCommand EOF ;
    public final EObject entryRuleInlineDescCommand() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInlineDescCommand = null;


        try {
            // InternalCpsaml.g:427:58: (iv_ruleInlineDescCommand= ruleInlineDescCommand EOF )
            // InternalCpsaml.g:428:2: iv_ruleInlineDescCommand= ruleInlineDescCommand EOF
            {
             newCompositeNode(grammarAccess.getInlineDescCommandRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInlineDescCommand=ruleInlineDescCommand();

            state._fsp--;

             current =iv_ruleInlineDescCommand; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInlineDescCommand"


    // $ANTLR start "ruleInlineDescCommand"
    // InternalCpsaml.g:434:1: ruleInlineDescCommand returns [EObject current=null] : ( () otherlv_1= 'command' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) ) ) ;
    public final EObject ruleInlineDescCommand() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_description_3_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:440:2: ( ( () otherlv_1= 'command' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) ) ) )
            // InternalCpsaml.g:441:2: ( () otherlv_1= 'command' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) ) )
            {
            // InternalCpsaml.g:441:2: ( () otherlv_1= 'command' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) ) )
            // InternalCpsaml.g:442:3: () otherlv_1= 'command' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) )
            {
            // InternalCpsaml.g:442:3: ()
            // InternalCpsaml.g:443:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getInlineDescCommandAccess().getCommandAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getInlineDescCommandAccess().getCommandKeyword_1());
            		
            // InternalCpsaml.g:453:3: ( (lv_name_2_0= ruleName ) )
            // InternalCpsaml.g:454:4: (lv_name_2_0= ruleName )
            {
            // InternalCpsaml.g:454:4: (lv_name_2_0= ruleName )
            // InternalCpsaml.g:455:5: lv_name_2_0= ruleName
            {

            					newCompositeNode(grammarAccess.getInlineDescCommandAccess().getNameNameParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_9);
            lv_name_2_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getInlineDescCommandRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalCpsaml.g:472:3: ( (lv_description_3_0= RULE_SL_DESCRIPTION ) )
            // InternalCpsaml.g:473:4: (lv_description_3_0= RULE_SL_DESCRIPTION )
            {
            // InternalCpsaml.g:473:4: (lv_description_3_0= RULE_SL_DESCRIPTION )
            // InternalCpsaml.g:474:5: lv_description_3_0= RULE_SL_DESCRIPTION
            {
            lv_description_3_0=(Token)match(input,RULE_SL_DESCRIPTION,FOLLOW_2); 

            					newLeafNode(lv_description_3_0, grammarAccess.getInlineDescCommandAccess().getDescriptionSL_DESCRIPTIONTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getInlineDescCommandRule());
            					}
            					setWithLastConsumed(
            						current,
            						"description",
            						lv_description_3_0,
            						"at.ac.tuwien.big.cps.Cpsaml.SL_DESCRIPTION");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInlineDescCommand"


    // $ANTLR start "entryRuleResource"
    // InternalCpsaml.g:494:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // InternalCpsaml.g:494:49: (iv_ruleResource= ruleResource EOF )
            // InternalCpsaml.g:495:2: iv_ruleResource= ruleResource EOF
            {
             newCompositeNode(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResource=ruleResource();

            state._fsp--;

             current =iv_ruleResource; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleResource"


    // $ANTLR start "ruleResource"
    // InternalCpsaml.g:501:1: ruleResource returns [EObject current=null] : (this_TopDescResource_0= ruleTopDescResource | this_InlineDescResource_1= ruleInlineDescResource ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        EObject this_TopDescResource_0 = null;

        EObject this_InlineDescResource_1 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:507:2: ( (this_TopDescResource_0= ruleTopDescResource | this_InlineDescResource_1= ruleInlineDescResource ) )
            // InternalCpsaml.g:508:2: (this_TopDescResource_0= ruleTopDescResource | this_InlineDescResource_1= ruleInlineDescResource )
            {
            // InternalCpsaml.g:508:2: (this_TopDescResource_0= ruleTopDescResource | this_InlineDescResource_1= ruleInlineDescResource )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_ML_DESCRIPTION) ) {
                alt7=1;
            }
            else if ( (LA7_0==15) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalCpsaml.g:509:3: this_TopDescResource_0= ruleTopDescResource
                    {

                    			newCompositeNode(grammarAccess.getResourceAccess().getTopDescResourceParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_TopDescResource_0=ruleTopDescResource();

                    state._fsp--;


                    			current = this_TopDescResource_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalCpsaml.g:518:3: this_InlineDescResource_1= ruleInlineDescResource
                    {

                    			newCompositeNode(grammarAccess.getResourceAccess().getInlineDescResourceParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_InlineDescResource_1=ruleInlineDescResource();

                    state._fsp--;


                    			current = this_InlineDescResource_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleResource"


    // $ANTLR start "entryRuleTopDescResource"
    // InternalCpsaml.g:530:1: entryRuleTopDescResource returns [EObject current=null] : iv_ruleTopDescResource= ruleTopDescResource EOF ;
    public final EObject entryRuleTopDescResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTopDescResource = null;


        try {
            // InternalCpsaml.g:530:56: (iv_ruleTopDescResource= ruleTopDescResource EOF )
            // InternalCpsaml.g:531:2: iv_ruleTopDescResource= ruleTopDescResource EOF
            {
             newCompositeNode(grammarAccess.getTopDescResourceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTopDescResource=ruleTopDescResource();

            state._fsp--;

             current =iv_ruleTopDescResource; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTopDescResource"


    // $ANTLR start "ruleTopDescResource"
    // InternalCpsaml.g:537:1: ruleTopDescResource returns [EObject current=null] : ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'produces' ( (lv_name_3_0= ruleName ) ) ) ;
    public final EObject ruleTopDescResource() throws RecognitionException {
        EObject current = null;

        Token lv_description_1_0=null;
        Token otherlv_2=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:543:2: ( ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'produces' ( (lv_name_3_0= ruleName ) ) ) )
            // InternalCpsaml.g:544:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'produces' ( (lv_name_3_0= ruleName ) ) )
            {
            // InternalCpsaml.g:544:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'produces' ( (lv_name_3_0= ruleName ) ) )
            // InternalCpsaml.g:545:3: () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) ) otherlv_2= 'produces' ( (lv_name_3_0= ruleName ) )
            {
            // InternalCpsaml.g:545:3: ()
            // InternalCpsaml.g:546:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTopDescResourceAccess().getResourceAction_0(),
            					current);
            			

            }

            // InternalCpsaml.g:552:3: ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )
            // InternalCpsaml.g:553:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
            {
            // InternalCpsaml.g:553:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
            // InternalCpsaml.g:554:5: lv_description_1_0= RULE_ML_DESCRIPTION
            {
            lv_description_1_0=(Token)match(input,RULE_ML_DESCRIPTION,FOLLOW_10); 

            					newLeafNode(lv_description_1_0, grammarAccess.getTopDescResourceAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTopDescResourceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"description",
            						lv_description_1_0,
            						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
            				

            }


            }

            otherlv_2=(Token)match(input,15,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getTopDescResourceAccess().getProducesKeyword_2());
            		
            // InternalCpsaml.g:574:3: ( (lv_name_3_0= ruleName ) )
            // InternalCpsaml.g:575:4: (lv_name_3_0= ruleName )
            {
            // InternalCpsaml.g:575:4: (lv_name_3_0= ruleName )
            // InternalCpsaml.g:576:5: lv_name_3_0= ruleName
            {

            					newCompositeNode(grammarAccess.getTopDescResourceAccess().getNameNameParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_name_3_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTopDescResourceRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_3_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTopDescResource"


    // $ANTLR start "entryRuleInlineDescResource"
    // InternalCpsaml.g:597:1: entryRuleInlineDescResource returns [EObject current=null] : iv_ruleInlineDescResource= ruleInlineDescResource EOF ;
    public final EObject entryRuleInlineDescResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInlineDescResource = null;


        try {
            // InternalCpsaml.g:597:59: (iv_ruleInlineDescResource= ruleInlineDescResource EOF )
            // InternalCpsaml.g:598:2: iv_ruleInlineDescResource= ruleInlineDescResource EOF
            {
             newCompositeNode(grammarAccess.getInlineDescResourceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInlineDescResource=ruleInlineDescResource();

            state._fsp--;

             current =iv_ruleInlineDescResource; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInlineDescResource"


    // $ANTLR start "ruleInlineDescResource"
    // InternalCpsaml.g:604:1: ruleInlineDescResource returns [EObject current=null] : ( () otherlv_1= 'produces' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) )? ) ;
    public final EObject ruleInlineDescResource() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_description_3_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:610:2: ( ( () otherlv_1= 'produces' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) )? ) )
            // InternalCpsaml.g:611:2: ( () otherlv_1= 'produces' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) )? )
            {
            // InternalCpsaml.g:611:2: ( () otherlv_1= 'produces' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) )? )
            // InternalCpsaml.g:612:3: () otherlv_1= 'produces' ( (lv_name_2_0= ruleName ) ) ( (lv_description_3_0= RULE_SL_DESCRIPTION ) )?
            {
            // InternalCpsaml.g:612:3: ()
            // InternalCpsaml.g:613:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getInlineDescResourceAccess().getResourceAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,15,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getInlineDescResourceAccess().getProducesKeyword_1());
            		
            // InternalCpsaml.g:623:3: ( (lv_name_2_0= ruleName ) )
            // InternalCpsaml.g:624:4: (lv_name_2_0= ruleName )
            {
            // InternalCpsaml.g:624:4: (lv_name_2_0= ruleName )
            // InternalCpsaml.g:625:5: lv_name_2_0= ruleName
            {

            					newCompositeNode(grammarAccess.getInlineDescResourceAccess().getNameNameParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_11);
            lv_name_2_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getInlineDescResourceRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalCpsaml.g:642:3: ( (lv_description_3_0= RULE_SL_DESCRIPTION ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_SL_DESCRIPTION) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalCpsaml.g:643:4: (lv_description_3_0= RULE_SL_DESCRIPTION )
                    {
                    // InternalCpsaml.g:643:4: (lv_description_3_0= RULE_SL_DESCRIPTION )
                    // InternalCpsaml.g:644:5: lv_description_3_0= RULE_SL_DESCRIPTION
                    {
                    lv_description_3_0=(Token)match(input,RULE_SL_DESCRIPTION,FOLLOW_2); 

                    					newLeafNode(lv_description_3_0, grammarAccess.getInlineDescResourceAccess().getDescriptionSL_DESCRIPTIONTerminalRuleCall_3_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getInlineDescResourceRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"description",
                    						lv_description_3_0,
                    						"at.ac.tuwien.big.cps.Cpsaml.SL_DESCRIPTION");
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInlineDescResource"


    // $ANTLR start "entryRuleCps"
    // InternalCpsaml.g:664:1: entryRuleCps returns [EObject current=null] : iv_ruleCps= ruleCps EOF ;
    public final EObject entryRuleCps() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCps = null;


        try {
            // InternalCpsaml.g:664:44: (iv_ruleCps= ruleCps EOF )
            // InternalCpsaml.g:665:2: iv_ruleCps= ruleCps EOF
            {
             newCompositeNode(grammarAccess.getCpsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCps=ruleCps();

            state._fsp--;

             current =iv_ruleCps; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCps"


    // $ANTLR start "ruleCps"
    // InternalCpsaml.g:671:1: ruleCps returns [EObject current=null] : (this_SingletonCps_0= ruleSingletonCps | this_MultiCps_1= ruleMultiCps ) ;
    public final EObject ruleCps() throws RecognitionException {
        EObject current = null;

        EObject this_SingletonCps_0 = null;

        EObject this_MultiCps_1 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:677:2: ( (this_SingletonCps_0= ruleSingletonCps | this_MultiCps_1= ruleMultiCps ) )
            // InternalCpsaml.g:678:2: (this_SingletonCps_0= ruleSingletonCps | this_MultiCps_1= ruleMultiCps )
            {
            // InternalCpsaml.g:678:2: (this_SingletonCps_0= ruleSingletonCps | this_MultiCps_1= ruleMultiCps )
            int alt9=2;
            switch ( input.LA(1) ) {
            case RULE_ML_DESCRIPTION:
                {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==16) ) {
                    alt9=1;
                }
                else if ( (LA9_1==19) ) {
                    alt9=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
                }
                break;
            case 16:
                {
                alt9=1;
                }
                break;
            case 19:
                {
                alt9=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }

            switch (alt9) {
                case 1 :
                    // InternalCpsaml.g:679:3: this_SingletonCps_0= ruleSingletonCps
                    {

                    			newCompositeNode(grammarAccess.getCpsAccess().getSingletonCpsParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SingletonCps_0=ruleSingletonCps();

                    state._fsp--;


                    			current = this_SingletonCps_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalCpsaml.g:688:3: this_MultiCps_1= ruleMultiCps
                    {

                    			newCompositeNode(grammarAccess.getCpsAccess().getMultiCpsParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_MultiCps_1=ruleMultiCps();

                    state._fsp--;


                    			current = this_MultiCps_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCps"


    // $ANTLR start "entryRuleSingletonCps"
    // InternalCpsaml.g:700:1: entryRuleSingletonCps returns [EObject current=null] : iv_ruleSingletonCps= ruleSingletonCps EOF ;
    public final EObject entryRuleSingletonCps() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSingletonCps = null;


        try {
            // InternalCpsaml.g:700:53: (iv_ruleSingletonCps= ruleSingletonCps EOF )
            // InternalCpsaml.g:701:2: iv_ruleSingletonCps= ruleSingletonCps EOF
            {
             newCompositeNode(grammarAccess.getSingletonCpsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSingletonCps=ruleSingletonCps();

            state._fsp--;

             current =iv_ruleSingletonCps; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSingletonCps"


    // $ANTLR start "ruleSingletonCps"
    // InternalCpsaml.g:707:1: ruleSingletonCps returns [EObject current=null] : ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps-singleton' ( (lv_name_3_0= ruleName ) ) otherlv_4= '{' ( ( (lv_commands_5_0= ruleCommand ) ) | ( (lv_resources_6_0= ruleResource ) ) | ( (lv_hardware_7_0= ruleHardware ) ) )* otherlv_8= '}' ) ;
    public final EObject ruleSingletonCps() throws RecognitionException {
        EObject current = null;

        Token lv_description_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        EObject lv_commands_5_0 = null;

        EObject lv_resources_6_0 = null;

        EObject lv_hardware_7_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:713:2: ( ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps-singleton' ( (lv_name_3_0= ruleName ) ) otherlv_4= '{' ( ( (lv_commands_5_0= ruleCommand ) ) | ( (lv_resources_6_0= ruleResource ) ) | ( (lv_hardware_7_0= ruleHardware ) ) )* otherlv_8= '}' ) )
            // InternalCpsaml.g:714:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps-singleton' ( (lv_name_3_0= ruleName ) ) otherlv_4= '{' ( ( (lv_commands_5_0= ruleCommand ) ) | ( (lv_resources_6_0= ruleResource ) ) | ( (lv_hardware_7_0= ruleHardware ) ) )* otherlv_8= '}' )
            {
            // InternalCpsaml.g:714:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps-singleton' ( (lv_name_3_0= ruleName ) ) otherlv_4= '{' ( ( (lv_commands_5_0= ruleCommand ) ) | ( (lv_resources_6_0= ruleResource ) ) | ( (lv_hardware_7_0= ruleHardware ) ) )* otherlv_8= '}' )
            // InternalCpsaml.g:715:3: () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps-singleton' ( (lv_name_3_0= ruleName ) ) otherlv_4= '{' ( ( (lv_commands_5_0= ruleCommand ) ) | ( (lv_resources_6_0= ruleResource ) ) | ( (lv_hardware_7_0= ruleHardware ) ) )* otherlv_8= '}'
            {
            // InternalCpsaml.g:715:3: ()
            // InternalCpsaml.g:716:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSingletonCpsAccess().getSingletonCpsAction_0(),
            					current);
            			

            }

            // InternalCpsaml.g:722:3: ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ML_DESCRIPTION) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalCpsaml.g:723:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    {
                    // InternalCpsaml.g:723:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    // InternalCpsaml.g:724:5: lv_description_1_0= RULE_ML_DESCRIPTION
                    {
                    lv_description_1_0=(Token)match(input,RULE_ML_DESCRIPTION,FOLLOW_12); 

                    					newLeafNode(lv_description_1_0, grammarAccess.getSingletonCpsAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getSingletonCpsRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"description",
                    						lv_description_1_0,
                    						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,16,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getSingletonCpsAccess().getCpsSingletonKeyword_2());
            		
            // InternalCpsaml.g:744:3: ( (lv_name_3_0= ruleName ) )
            // InternalCpsaml.g:745:4: (lv_name_3_0= ruleName )
            {
            // InternalCpsaml.g:745:4: (lv_name_3_0= ruleName )
            // InternalCpsaml.g:746:5: lv_name_3_0= ruleName
            {

            					newCompositeNode(grammarAccess.getSingletonCpsAccess().getNameNameParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_13);
            lv_name_3_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSingletonCpsRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_3_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,17,FOLLOW_14); 

            			newLeafNode(otherlv_4, grammarAccess.getSingletonCpsAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalCpsaml.g:767:3: ( ( (lv_commands_5_0= ruleCommand ) ) | ( (lv_resources_6_0= ruleResource ) ) | ( (lv_hardware_7_0= ruleHardware ) ) )*
            loop11:
            do {
                int alt11=4;
                switch ( input.LA(1) ) {
                case RULE_ML_DESCRIPTION:
                    {
                    switch ( input.LA(2) ) {
                    case 14:
                        {
                        alt11=1;
                        }
                        break;
                    case 15:
                        {
                        alt11=2;
                        }
                        break;
                    case 22:
                    case 23:
                        {
                        alt11=3;
                        }
                        break;

                    }

                    }
                    break;
                case 14:
                    {
                    alt11=1;
                    }
                    break;
                case 15:
                    {
                    alt11=2;
                    }
                    break;
                case 22:
                case 23:
                    {
                    alt11=3;
                    }
                    break;

                }

                switch (alt11) {
            	case 1 :
            	    // InternalCpsaml.g:768:4: ( (lv_commands_5_0= ruleCommand ) )
            	    {
            	    // InternalCpsaml.g:768:4: ( (lv_commands_5_0= ruleCommand ) )
            	    // InternalCpsaml.g:769:5: (lv_commands_5_0= ruleCommand )
            	    {
            	    // InternalCpsaml.g:769:5: (lv_commands_5_0= ruleCommand )
            	    // InternalCpsaml.g:770:6: lv_commands_5_0= ruleCommand
            	    {

            	    						newCompositeNode(grammarAccess.getSingletonCpsAccess().getCommandsCommandParserRuleCall_5_0_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_commands_5_0=ruleCommand();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getSingletonCpsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"commands",
            	    							lv_commands_5_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Command");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCpsaml.g:788:4: ( (lv_resources_6_0= ruleResource ) )
            	    {
            	    // InternalCpsaml.g:788:4: ( (lv_resources_6_0= ruleResource ) )
            	    // InternalCpsaml.g:789:5: (lv_resources_6_0= ruleResource )
            	    {
            	    // InternalCpsaml.g:789:5: (lv_resources_6_0= ruleResource )
            	    // InternalCpsaml.g:790:6: lv_resources_6_0= ruleResource
            	    {

            	    						newCompositeNode(grammarAccess.getSingletonCpsAccess().getResourcesResourceParserRuleCall_5_1_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_resources_6_0=ruleResource();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getSingletonCpsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"resources",
            	    							lv_resources_6_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Resource");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalCpsaml.g:808:4: ( (lv_hardware_7_0= ruleHardware ) )
            	    {
            	    // InternalCpsaml.g:808:4: ( (lv_hardware_7_0= ruleHardware ) )
            	    // InternalCpsaml.g:809:5: (lv_hardware_7_0= ruleHardware )
            	    {
            	    // InternalCpsaml.g:809:5: (lv_hardware_7_0= ruleHardware )
            	    // InternalCpsaml.g:810:6: lv_hardware_7_0= ruleHardware
            	    {

            	    						newCompositeNode(grammarAccess.getSingletonCpsAccess().getHardwareHardwareParserRuleCall_5_2_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_hardware_7_0=ruleHardware();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getSingletonCpsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"hardware",
            	    							lv_hardware_7_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Hardware");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            otherlv_8=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getSingletonCpsAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSingletonCps"


    // $ANTLR start "entryRuleMultiCps"
    // InternalCpsaml.g:836:1: entryRuleMultiCps returns [EObject current=null] : iv_ruleMultiCps= ruleMultiCps EOF ;
    public final EObject entryRuleMultiCps() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiCps = null;


        try {
            // InternalCpsaml.g:836:49: (iv_ruleMultiCps= ruleMultiCps EOF )
            // InternalCpsaml.g:837:2: iv_ruleMultiCps= ruleMultiCps EOF
            {
             newCompositeNode(grammarAccess.getMultiCpsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMultiCps=ruleMultiCps();

            state._fsp--;

             current =iv_ruleMultiCps; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMultiCps"


    // $ANTLR start "ruleMultiCps"
    // InternalCpsaml.g:843:1: ruleMultiCps returns [EObject current=null] : ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( ( (lv_commands_9_0= ruleCommand ) ) | ( (lv_resources_10_0= ruleResource ) ) | ( (lv_hardware_11_0= ruleHardware ) ) )* otherlv_12= '}' ) ;
    public final EObject ruleMultiCps() throws RecognitionException {
        EObject current = null;

        Token lv_description_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_12=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_instances_5_0 = null;

        AntlrDatatypeRuleToken lv_instances_7_0 = null;

        EObject lv_commands_9_0 = null;

        EObject lv_resources_10_0 = null;

        EObject lv_hardware_11_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:849:2: ( ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( ( (lv_commands_9_0= ruleCommand ) ) | ( (lv_resources_10_0= ruleResource ) ) | ( (lv_hardware_11_0= ruleHardware ) ) )* otherlv_12= '}' ) )
            // InternalCpsaml.g:850:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( ( (lv_commands_9_0= ruleCommand ) ) | ( (lv_resources_10_0= ruleResource ) ) | ( (lv_hardware_11_0= ruleHardware ) ) )* otherlv_12= '}' )
            {
            // InternalCpsaml.g:850:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( ( (lv_commands_9_0= ruleCommand ) ) | ( (lv_resources_10_0= ruleResource ) ) | ( (lv_hardware_11_0= ruleHardware ) ) )* otherlv_12= '}' )
            // InternalCpsaml.g:851:3: () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'cps' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( ( (lv_commands_9_0= ruleCommand ) ) | ( (lv_resources_10_0= ruleResource ) ) | ( (lv_hardware_11_0= ruleHardware ) ) )* otherlv_12= '}'
            {
            // InternalCpsaml.g:851:3: ()
            // InternalCpsaml.g:852:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getMultiCpsAccess().getMultiCpsAction_0(),
            					current);
            			

            }

            // InternalCpsaml.g:858:3: ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ML_DESCRIPTION) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalCpsaml.g:859:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    {
                    // InternalCpsaml.g:859:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    // InternalCpsaml.g:860:5: lv_description_1_0= RULE_ML_DESCRIPTION
                    {
                    lv_description_1_0=(Token)match(input,RULE_ML_DESCRIPTION,FOLLOW_15); 

                    					newLeafNode(lv_description_1_0, grammarAccess.getMultiCpsAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getMultiCpsRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"description",
                    						lv_description_1_0,
                    						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,19,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getMultiCpsAccess().getCpsKeyword_2());
            		
            // InternalCpsaml.g:880:3: ( (lv_name_3_0= ruleName ) )
            // InternalCpsaml.g:881:4: (lv_name_3_0= ruleName )
            {
            // InternalCpsaml.g:881:4: (lv_name_3_0= ruleName )
            // InternalCpsaml.g:882:5: lv_name_3_0= ruleName
            {

            					newCompositeNode(grammarAccess.getMultiCpsAccess().getNameNameParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_16);
            lv_name_3_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMultiCpsRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_3_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalCpsaml.g:899:3: (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==20) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalCpsaml.g:900:4: otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )*
                    {
                    otherlv_4=(Token)match(input,20,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getMultiCpsAccess().getInstancesKeyword_4_0());
                    			
                    // InternalCpsaml.g:904:4: ( (lv_instances_5_0= ruleName ) )
                    // InternalCpsaml.g:905:5: (lv_instances_5_0= ruleName )
                    {
                    // InternalCpsaml.g:905:5: (lv_instances_5_0= ruleName )
                    // InternalCpsaml.g:906:6: lv_instances_5_0= ruleName
                    {

                    						newCompositeNode(grammarAccess.getMultiCpsAccess().getInstancesNameParserRuleCall_4_1_0());
                    					
                    pushFollow(FOLLOW_17);
                    lv_instances_5_0=ruleName();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getMultiCpsRule());
                    						}
                    						add(
                    							current,
                    							"instances",
                    							lv_instances_5_0,
                    							"at.ac.tuwien.big.cps.Cpsaml.Name");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalCpsaml.g:923:4: (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==21) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // InternalCpsaml.g:924:5: otherlv_6= ',' ( (lv_instances_7_0= ruleName ) )
                    	    {
                    	    otherlv_6=(Token)match(input,21,FOLLOW_4); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getMultiCpsAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalCpsaml.g:928:5: ( (lv_instances_7_0= ruleName ) )
                    	    // InternalCpsaml.g:929:6: (lv_instances_7_0= ruleName )
                    	    {
                    	    // InternalCpsaml.g:929:6: (lv_instances_7_0= ruleName )
                    	    // InternalCpsaml.g:930:7: lv_instances_7_0= ruleName
                    	    {

                    	    							newCompositeNode(grammarAccess.getMultiCpsAccess().getInstancesNameParserRuleCall_4_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_17);
                    	    lv_instances_7_0=ruleName();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getMultiCpsRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"instances",
                    	    								lv_instances_7_0,
                    	    								"at.ac.tuwien.big.cps.Cpsaml.Name");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,17,FOLLOW_14); 

            			newLeafNode(otherlv_8, grammarAccess.getMultiCpsAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalCpsaml.g:953:3: ( ( (lv_commands_9_0= ruleCommand ) ) | ( (lv_resources_10_0= ruleResource ) ) | ( (lv_hardware_11_0= ruleHardware ) ) )*
            loop15:
            do {
                int alt15=4;
                switch ( input.LA(1) ) {
                case RULE_ML_DESCRIPTION:
                    {
                    switch ( input.LA(2) ) {
                    case 14:
                        {
                        alt15=1;
                        }
                        break;
                    case 15:
                        {
                        alt15=2;
                        }
                        break;
                    case 22:
                    case 23:
                        {
                        alt15=3;
                        }
                        break;

                    }

                    }
                    break;
                case 14:
                    {
                    alt15=1;
                    }
                    break;
                case 15:
                    {
                    alt15=2;
                    }
                    break;
                case 22:
                case 23:
                    {
                    alt15=3;
                    }
                    break;

                }

                switch (alt15) {
            	case 1 :
            	    // InternalCpsaml.g:954:4: ( (lv_commands_9_0= ruleCommand ) )
            	    {
            	    // InternalCpsaml.g:954:4: ( (lv_commands_9_0= ruleCommand ) )
            	    // InternalCpsaml.g:955:5: (lv_commands_9_0= ruleCommand )
            	    {
            	    // InternalCpsaml.g:955:5: (lv_commands_9_0= ruleCommand )
            	    // InternalCpsaml.g:956:6: lv_commands_9_0= ruleCommand
            	    {

            	    						newCompositeNode(grammarAccess.getMultiCpsAccess().getCommandsCommandParserRuleCall_6_0_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_commands_9_0=ruleCommand();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMultiCpsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"commands",
            	    							lv_commands_9_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Command");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalCpsaml.g:974:4: ( (lv_resources_10_0= ruleResource ) )
            	    {
            	    // InternalCpsaml.g:974:4: ( (lv_resources_10_0= ruleResource ) )
            	    // InternalCpsaml.g:975:5: (lv_resources_10_0= ruleResource )
            	    {
            	    // InternalCpsaml.g:975:5: (lv_resources_10_0= ruleResource )
            	    // InternalCpsaml.g:976:6: lv_resources_10_0= ruleResource
            	    {

            	    						newCompositeNode(grammarAccess.getMultiCpsAccess().getResourcesResourceParserRuleCall_6_1_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_resources_10_0=ruleResource();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMultiCpsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"resources",
            	    							lv_resources_10_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Resource");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalCpsaml.g:994:4: ( (lv_hardware_11_0= ruleHardware ) )
            	    {
            	    // InternalCpsaml.g:994:4: ( (lv_hardware_11_0= ruleHardware ) )
            	    // InternalCpsaml.g:995:5: (lv_hardware_11_0= ruleHardware )
            	    {
            	    // InternalCpsaml.g:995:5: (lv_hardware_11_0= ruleHardware )
            	    // InternalCpsaml.g:996:6: lv_hardware_11_0= ruleHardware
            	    {

            	    						newCompositeNode(grammarAccess.getMultiCpsAccess().getHardwareHardwareParserRuleCall_6_2_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_hardware_11_0=ruleHardware();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMultiCpsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"hardware",
            	    							lv_hardware_11_0,
            	    							"at.ac.tuwien.big.cps.Cpsaml.Hardware");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            otherlv_12=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_12, grammarAccess.getMultiCpsAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMultiCps"


    // $ANTLR start "entryRuleHardware"
    // InternalCpsaml.g:1022:1: entryRuleHardware returns [EObject current=null] : iv_ruleHardware= ruleHardware EOF ;
    public final EObject entryRuleHardware() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHardware = null;


        try {
            // InternalCpsaml.g:1022:49: (iv_ruleHardware= ruleHardware EOF )
            // InternalCpsaml.g:1023:2: iv_ruleHardware= ruleHardware EOF
            {
             newCompositeNode(grammarAccess.getHardwareRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHardware=ruleHardware();

            state._fsp--;

             current =iv_ruleHardware; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHardware"


    // $ANTLR start "ruleHardware"
    // InternalCpsaml.g:1029:1: ruleHardware returns [EObject current=null] : (this_Actuator_0= ruleActuator | this_Sensor_1= ruleSensor ) ;
    public final EObject ruleHardware() throws RecognitionException {
        EObject current = null;

        EObject this_Actuator_0 = null;

        EObject this_Sensor_1 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:1035:2: ( (this_Actuator_0= ruleActuator | this_Sensor_1= ruleSensor ) )
            // InternalCpsaml.g:1036:2: (this_Actuator_0= ruleActuator | this_Sensor_1= ruleSensor )
            {
            // InternalCpsaml.g:1036:2: (this_Actuator_0= ruleActuator | this_Sensor_1= ruleSensor )
            int alt16=2;
            switch ( input.LA(1) ) {
            case RULE_ML_DESCRIPTION:
                {
                int LA16_1 = input.LA(2);

                if ( (LA16_1==22) ) {
                    alt16=1;
                }
                else if ( (LA16_1==23) ) {
                    alt16=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }
                }
                break;
            case 22:
                {
                alt16=1;
                }
                break;
            case 23:
                {
                alt16=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // InternalCpsaml.g:1037:3: this_Actuator_0= ruleActuator
                    {

                    			newCompositeNode(grammarAccess.getHardwareAccess().getActuatorParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Actuator_0=ruleActuator();

                    state._fsp--;


                    			current = this_Actuator_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalCpsaml.g:1046:3: this_Sensor_1= ruleSensor
                    {

                    			newCompositeNode(grammarAccess.getHardwareAccess().getSensorParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Sensor_1=ruleSensor();

                    state._fsp--;


                    			current = this_Sensor_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHardware"


    // $ANTLR start "entryRuleActuator"
    // InternalCpsaml.g:1058:1: entryRuleActuator returns [EObject current=null] : iv_ruleActuator= ruleActuator EOF ;
    public final EObject entryRuleActuator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleActuator = null;


        try {
            // InternalCpsaml.g:1058:49: (iv_ruleActuator= ruleActuator EOF )
            // InternalCpsaml.g:1059:2: iv_ruleActuator= ruleActuator EOF
            {
             newCompositeNode(grammarAccess.getActuatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleActuator=ruleActuator();

            state._fsp--;

             current =iv_ruleActuator; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleActuator"


    // $ANTLR start "ruleActuator"
    // InternalCpsaml.g:1065:1: ruleActuator returns [EObject current=null] : ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'actuator' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_commands_9_0= ruleCommand ) )* otherlv_10= '}' ) ;
    public final EObject ruleActuator() throws RecognitionException {
        EObject current = null;

        Token lv_description_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_instances_5_0 = null;

        AntlrDatatypeRuleToken lv_instances_7_0 = null;

        EObject lv_commands_9_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:1071:2: ( ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'actuator' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_commands_9_0= ruleCommand ) )* otherlv_10= '}' ) )
            // InternalCpsaml.g:1072:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'actuator' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_commands_9_0= ruleCommand ) )* otherlv_10= '}' )
            {
            // InternalCpsaml.g:1072:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'actuator' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_commands_9_0= ruleCommand ) )* otherlv_10= '}' )
            // InternalCpsaml.g:1073:3: () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'actuator' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_commands_9_0= ruleCommand ) )* otherlv_10= '}'
            {
            // InternalCpsaml.g:1073:3: ()
            // InternalCpsaml.g:1074:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getActuatorAccess().getActuatorAction_0(),
            					current);
            			

            }

            // InternalCpsaml.g:1080:3: ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_ML_DESCRIPTION) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalCpsaml.g:1081:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    {
                    // InternalCpsaml.g:1081:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    // InternalCpsaml.g:1082:5: lv_description_1_0= RULE_ML_DESCRIPTION
                    {
                    lv_description_1_0=(Token)match(input,RULE_ML_DESCRIPTION,FOLLOW_18); 

                    					newLeafNode(lv_description_1_0, grammarAccess.getActuatorAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getActuatorRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"description",
                    						lv_description_1_0,
                    						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,22,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getActuatorAccess().getActuatorKeyword_2());
            		
            // InternalCpsaml.g:1102:3: ( (lv_name_3_0= ruleName ) )
            // InternalCpsaml.g:1103:4: (lv_name_3_0= ruleName )
            {
            // InternalCpsaml.g:1103:4: (lv_name_3_0= ruleName )
            // InternalCpsaml.g:1104:5: lv_name_3_0= ruleName
            {

            					newCompositeNode(grammarAccess.getActuatorAccess().getNameNameParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_16);
            lv_name_3_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getActuatorRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_3_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalCpsaml.g:1121:3: (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==20) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalCpsaml.g:1122:4: otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )*
                    {
                    otherlv_4=(Token)match(input,20,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getActuatorAccess().getInstancesKeyword_4_0());
                    			
                    // InternalCpsaml.g:1126:4: ( (lv_instances_5_0= ruleName ) )
                    // InternalCpsaml.g:1127:5: (lv_instances_5_0= ruleName )
                    {
                    // InternalCpsaml.g:1127:5: (lv_instances_5_0= ruleName )
                    // InternalCpsaml.g:1128:6: lv_instances_5_0= ruleName
                    {

                    						newCompositeNode(grammarAccess.getActuatorAccess().getInstancesNameParserRuleCall_4_1_0());
                    					
                    pushFollow(FOLLOW_17);
                    lv_instances_5_0=ruleName();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getActuatorRule());
                    						}
                    						add(
                    							current,
                    							"instances",
                    							lv_instances_5_0,
                    							"at.ac.tuwien.big.cps.Cpsaml.Name");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalCpsaml.g:1145:4: (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==21) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalCpsaml.g:1146:5: otherlv_6= ',' ( (lv_instances_7_0= ruleName ) )
                    	    {
                    	    otherlv_6=(Token)match(input,21,FOLLOW_4); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getActuatorAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalCpsaml.g:1150:5: ( (lv_instances_7_0= ruleName ) )
                    	    // InternalCpsaml.g:1151:6: (lv_instances_7_0= ruleName )
                    	    {
                    	    // InternalCpsaml.g:1151:6: (lv_instances_7_0= ruleName )
                    	    // InternalCpsaml.g:1152:7: lv_instances_7_0= ruleName
                    	    {

                    	    							newCompositeNode(grammarAccess.getActuatorAccess().getInstancesNameParserRuleCall_4_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_17);
                    	    lv_instances_7_0=ruleName();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getActuatorRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"instances",
                    	    								lv_instances_7_0,
                    	    								"at.ac.tuwien.big.cps.Cpsaml.Name");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,17,FOLLOW_19); 

            			newLeafNode(otherlv_8, grammarAccess.getActuatorAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalCpsaml.g:1175:3: ( (lv_commands_9_0= ruleCommand ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_ML_DESCRIPTION||LA20_0==14) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalCpsaml.g:1176:4: (lv_commands_9_0= ruleCommand )
            	    {
            	    // InternalCpsaml.g:1176:4: (lv_commands_9_0= ruleCommand )
            	    // InternalCpsaml.g:1177:5: lv_commands_9_0= ruleCommand
            	    {

            	    					newCompositeNode(grammarAccess.getActuatorAccess().getCommandsCommandParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_19);
            	    lv_commands_9_0=ruleCommand();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getActuatorRule());
            	    					}
            	    					add(
            	    						current,
            	    						"commands",
            	    						lv_commands_9_0,
            	    						"at.ac.tuwien.big.cps.Cpsaml.Command");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            otherlv_10=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getActuatorAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleActuator"


    // $ANTLR start "entryRuleSensor"
    // InternalCpsaml.g:1202:1: entryRuleSensor returns [EObject current=null] : iv_ruleSensor= ruleSensor EOF ;
    public final EObject entryRuleSensor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSensor = null;


        try {
            // InternalCpsaml.g:1202:47: (iv_ruleSensor= ruleSensor EOF )
            // InternalCpsaml.g:1203:2: iv_ruleSensor= ruleSensor EOF
            {
             newCompositeNode(grammarAccess.getSensorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSensor=ruleSensor();

            state._fsp--;

             current =iv_ruleSensor; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSensor"


    // $ANTLR start "ruleSensor"
    // InternalCpsaml.g:1209:1: ruleSensor returns [EObject current=null] : ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'sensor' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_resources_9_0= ruleResource ) )* otherlv_10= '}' ) ;
    public final EObject ruleSensor() throws RecognitionException {
        EObject current = null;

        Token lv_description_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_instances_5_0 = null;

        AntlrDatatypeRuleToken lv_instances_7_0 = null;

        EObject lv_resources_9_0 = null;



        	enterRule();

        try {
            // InternalCpsaml.g:1215:2: ( ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'sensor' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_resources_9_0= ruleResource ) )* otherlv_10= '}' ) )
            // InternalCpsaml.g:1216:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'sensor' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_resources_9_0= ruleResource ) )* otherlv_10= '}' )
            {
            // InternalCpsaml.g:1216:2: ( () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'sensor' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_resources_9_0= ruleResource ) )* otherlv_10= '}' )
            // InternalCpsaml.g:1217:3: () ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )? otherlv_2= 'sensor' ( (lv_name_3_0= ruleName ) ) (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )? otherlv_8= '{' ( (lv_resources_9_0= ruleResource ) )* otherlv_10= '}'
            {
            // InternalCpsaml.g:1217:3: ()
            // InternalCpsaml.g:1218:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSensorAccess().getSensorAction_0(),
            					current);
            			

            }

            // InternalCpsaml.g:1224:3: ( (lv_description_1_0= RULE_ML_DESCRIPTION ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==RULE_ML_DESCRIPTION) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalCpsaml.g:1225:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    {
                    // InternalCpsaml.g:1225:4: (lv_description_1_0= RULE_ML_DESCRIPTION )
                    // InternalCpsaml.g:1226:5: lv_description_1_0= RULE_ML_DESCRIPTION
                    {
                    lv_description_1_0=(Token)match(input,RULE_ML_DESCRIPTION,FOLLOW_20); 

                    					newLeafNode(lv_description_1_0, grammarAccess.getSensorAccess().getDescriptionML_DESCRIPTIONTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getSensorRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"description",
                    						lv_description_1_0,
                    						"at.ac.tuwien.big.cps.Cpsaml.ML_DESCRIPTION");
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,23,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getSensorAccess().getSensorKeyword_2());
            		
            // InternalCpsaml.g:1246:3: ( (lv_name_3_0= ruleName ) )
            // InternalCpsaml.g:1247:4: (lv_name_3_0= ruleName )
            {
            // InternalCpsaml.g:1247:4: (lv_name_3_0= ruleName )
            // InternalCpsaml.g:1248:5: lv_name_3_0= ruleName
            {

            					newCompositeNode(grammarAccess.getSensorAccess().getNameNameParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_16);
            lv_name_3_0=ruleName();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSensorRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_3_0,
            						"at.ac.tuwien.big.cps.Cpsaml.Name");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalCpsaml.g:1265:3: (otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )* )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==20) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalCpsaml.g:1266:4: otherlv_4= 'instances' ( (lv_instances_5_0= ruleName ) ) (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )*
                    {
                    otherlv_4=(Token)match(input,20,FOLLOW_4); 

                    				newLeafNode(otherlv_4, grammarAccess.getSensorAccess().getInstancesKeyword_4_0());
                    			
                    // InternalCpsaml.g:1270:4: ( (lv_instances_5_0= ruleName ) )
                    // InternalCpsaml.g:1271:5: (lv_instances_5_0= ruleName )
                    {
                    // InternalCpsaml.g:1271:5: (lv_instances_5_0= ruleName )
                    // InternalCpsaml.g:1272:6: lv_instances_5_0= ruleName
                    {

                    						newCompositeNode(grammarAccess.getSensorAccess().getInstancesNameParserRuleCall_4_1_0());
                    					
                    pushFollow(FOLLOW_17);
                    lv_instances_5_0=ruleName();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getSensorRule());
                    						}
                    						add(
                    							current,
                    							"instances",
                    							lv_instances_5_0,
                    							"at.ac.tuwien.big.cps.Cpsaml.Name");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalCpsaml.g:1289:4: (otherlv_6= ',' ( (lv_instances_7_0= ruleName ) ) )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==21) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // InternalCpsaml.g:1290:5: otherlv_6= ',' ( (lv_instances_7_0= ruleName ) )
                    	    {
                    	    otherlv_6=(Token)match(input,21,FOLLOW_4); 

                    	    					newLeafNode(otherlv_6, grammarAccess.getSensorAccess().getCommaKeyword_4_2_0());
                    	    				
                    	    // InternalCpsaml.g:1294:5: ( (lv_instances_7_0= ruleName ) )
                    	    // InternalCpsaml.g:1295:6: (lv_instances_7_0= ruleName )
                    	    {
                    	    // InternalCpsaml.g:1295:6: (lv_instances_7_0= ruleName )
                    	    // InternalCpsaml.g:1296:7: lv_instances_7_0= ruleName
                    	    {

                    	    							newCompositeNode(grammarAccess.getSensorAccess().getInstancesNameParserRuleCall_4_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_17);
                    	    lv_instances_7_0=ruleName();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getSensorRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"instances",
                    	    								lv_instances_7_0,
                    	    								"at.ac.tuwien.big.cps.Cpsaml.Name");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,17,FOLLOW_21); 

            			newLeafNode(otherlv_8, grammarAccess.getSensorAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalCpsaml.g:1319:3: ( (lv_resources_9_0= ruleResource ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_ML_DESCRIPTION||LA24_0==15) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalCpsaml.g:1320:4: (lv_resources_9_0= ruleResource )
            	    {
            	    // InternalCpsaml.g:1320:4: (lv_resources_9_0= ruleResource )
            	    // InternalCpsaml.g:1321:5: lv_resources_9_0= ruleResource
            	    {

            	    					newCompositeNode(grammarAccess.getSensorAccess().getResourcesResourceParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_21);
            	    lv_resources_9_0=ruleResource();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getSensorRule());
            	    					}
            	    					add(
            	    						current,
            	    						"resources",
            	    						lv_resources_9_0,
            	    						"at.ac.tuwien.big.cps.Cpsaml.Resource");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            otherlv_10=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getSensorAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSensor"


    // $ANTLR start "entryRuleName"
    // InternalCpsaml.g:1346:1: entryRuleName returns [String current=null] : iv_ruleName= ruleName EOF ;
    public final String entryRuleName() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleName = null;


        try {
            // InternalCpsaml.g:1346:44: (iv_ruleName= ruleName EOF )
            // InternalCpsaml.g:1347:2: iv_ruleName= ruleName EOF
            {
             newCompositeNode(grammarAccess.getNameRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleName=ruleName();

            state._fsp--;

             current =iv_ruleName.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleName"


    // $ANTLR start "ruleName"
    // InternalCpsaml.g:1353:1: ruleName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleName() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;


        	enterRule();

        try {
            // InternalCpsaml.g:1359:2: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // InternalCpsaml.g:1360:2: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // InternalCpsaml.g:1360:2: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==RULE_STRING) ) {
                alt25=1;
            }
            else if ( (LA25_0==RULE_ID) ) {
                alt25=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }
            switch (alt25) {
                case 1 :
                    // InternalCpsaml.g:1361:3: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			current.merge(this_STRING_0);
                    		

                    			newLeafNode(this_STRING_0, grammarAccess.getNameAccess().getSTRINGTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalCpsaml.g:1369:3: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FOLLOW_2); 

                    			current.merge(this_ID_1);
                    		

                    			newLeafNode(this_ID_1, grammarAccess.getNameAccess().getIDTerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleName"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000094012L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000094032L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000CDC010L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000120000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000220000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x00000000000D4010L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000048010L});

}