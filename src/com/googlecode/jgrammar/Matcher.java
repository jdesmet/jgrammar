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
public class Matcher<T,U extends T> {
  // You cannot stick it to a particular rule, as multiple rules can carry the same name.

  
  public Matcher<T, U> capture(String expression) {
    return this;
  }
  
  static public Matcher forPattern(String pattern) {
    return new PatternMatcher<>(pattern);
  }
  
  static public Matcher forRule(String rule) {
    return new RuleMatcher<>(rule);
  }
}
