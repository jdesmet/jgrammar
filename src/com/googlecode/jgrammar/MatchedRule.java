/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jgrammar;

/**
 *
 * @author jdesmet
 */
public class MatchedRule extends NamedToken {
  final private Rule rule;
  
  MatchedRule(Rule rule) {
    this.rule = rule;
  }

  @Override
  String getName() {
    return rule.getName();
  }

  @Override
  public String getString() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
