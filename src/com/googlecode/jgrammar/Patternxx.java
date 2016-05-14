/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jgrammar;

import java.util.regex.Matcher;

/**
 *
 * @author jdesmet
 */
public class Patternxx {
  // Maybe we should disassociate "Name" from  "Pattern", as there can be nameless patterns.
  // It seems that name should only be associated to a pattern when adding to it's Parent (Tokenizer etc).
  // Huh ... curious ... then all what is left is java.util.regex.Pattern !! Maybe we can remove
  // this class ans use pattern directly??
  private final String name;
  private final java.util.regex.Pattern pattern;

  public Patternxx(String name, String pattern) {
    this.name = name;
    this.pattern = java.util.regex.Pattern.compile(pattern);
  }

  Matcher matcher(String string) {
    return pattern.matcher(string);
  }

  public String getName() {
    return this.name;
  }
  
}
