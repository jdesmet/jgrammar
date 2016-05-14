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
class PatternMatcher<T,U extends T> extends Matcher<T,U> {
  final private Pattern pattern;
  
  PatternMatcher(Pattern pattern) {
    this.pattern = pattern;
  }
  
  PatternMatcher(String pattern) {
    this.pattern = Pattern.compile(pattern);
  }

}
