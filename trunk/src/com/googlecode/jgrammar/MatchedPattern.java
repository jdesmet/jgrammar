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
public class MatchedPattern extends NamedToken {
  final private Pattern pattern;
  final private String string;
  
  MatchedPattern(Pattern pattern,String string) {
    this.string = string;
    this.pattern = pattern;
  }

  @Override
  String getName() {
    return pattern.getName();
  }

  @Override
  public String getString() {
    return this.string;
  }
  
}
