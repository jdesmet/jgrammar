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
class RuleMatcher<T,U extends T> extends Matcher<T,U> {
  final private String rule;
  
  RuleMatcher(String rule) {
    this.rule = rule;
  }
}
