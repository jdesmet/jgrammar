/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jgrammar;

import java.util.regex.Pattern;

/**
 *
 * @author jdesmet
 */
public class MatchedPattern extends NamedToken {
  final private Pattern pattern;
  final private String string;
  final private String name;
  
  MatchedPattern(String name,Pattern pattern,String string) {
    this.string = string;
    this.pattern = pattern;
    this.name = name;
  }

  @Override
  String getName() {
    return this.name;
  }

  @Override
  public String getString() {
    return this.string;
  }
  
}
