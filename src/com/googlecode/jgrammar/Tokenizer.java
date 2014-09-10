/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jgrammar;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdesmet
 */
class Tokenizer<T> {
  private List<Pattern> patterns;
  private List<Rule<T,? extends T>> rules;

  public Tokenizer() {
    patterns = new ArrayList<>();
    rules = new ArrayList<>();
  }
  
  void ignore(String s) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  Rule<T,? extends T> add(Rule<T,? extends T> rule) {
    rules.add(rule);
    return rule;
  }

  Pattern add(Pattern pattern) {
    patterns.add(pattern);
    return pattern;
  }

  T parse(String string) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
