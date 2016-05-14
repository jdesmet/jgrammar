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
public class UnmatchedLiteral implements Token {
  private final String string;
  
  UnmatchedLiteral(String string) {
    this.string = string;
  }

  @Override
  public String getString() {
    return this.string;
  }
}
