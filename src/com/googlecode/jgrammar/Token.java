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
class Token {
  final private String string;
  final private Pattern pattern;
  
  Token(String string) {
    this.string = string;
    this.pattern = null;
  }

  Token(Pattern p, String string) {
    this.string = string;
    this.pattern = p;
  }

  boolean isTokenized() {
    return pattern != null;
  }
  
  public String getString() {
    return this.string;
  }
  
  public Pattern getPattern() {
    return this.pattern;
  }
  
}
