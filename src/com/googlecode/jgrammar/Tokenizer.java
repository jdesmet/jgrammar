/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jgrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 *
 * @author jdesmet
 */
public class Tokenizer<T> {
  private final List<Pattern> patterns;
  private final List<Pattern> ignores;
  private final List<Rule<T,? extends T>> rules;

  public Tokenizer() {
    patterns = new ArrayList<>();
    rules = new ArrayList<>();
    ignores = new ArrayList<>();
  }
  
  void ignore(Pattern pattern) {
    ignores.add(pattern);
  }

  public Tokenizer add(Rule<T,? extends T> rule) {
    rules.add(rule);
    return this;
  }

  public Tokenizer add(Pattern pattern) {
    patterns.add(pattern);
    return this;
  }

  public T parse(String string) {
    //tokens = new LinkedList<>();
    //tokens.add(new Token(string));
    List<Token> tokens = tokenize(string);
    tokens.forEach((t)->{System.out.println("Token "+(t instanceof NamedToken?((NamedToken)t).getName():"STRING")+"=\""+t.getString()+"\"");});
    return null;
  }
  
  List<Token> tokenize(String string) {
    // Highly inefficient recursive tokenizer
    List<Token> tokens = new ArrayList<>();
    for (Pattern p:patterns) {
      Matcher m = p.matcher(string);
      if (m.find()) {
        if (m.start() > 0) tokens.addAll(tokenize(string.substring(0, m.start())));
        tokens.add(new MatchedPattern(p,string.substring(m.start(), m.end())));
        if (m.end() < string.length()) tokens.addAll(tokenize(string.substring(m.end())));
        return tokens;
      }
    }
    tokens.add(new UnmatchedLiteral(string));
    return tokens;
  }
  
}
