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
public class Pattern {
  private final String name;
  private final java.util.regex.Pattern pattern;

  public Pattern(String name, String pattern) {
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
