/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jgrammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author jdesmet
 */
class Rule<T,U extends T> {
  private Function<RuleContext<T>,U> constructor = null;
  private List<BiConsumer<U,RuleContext<T>>> fillers = Collections.emptyList();
  
  public Rule(String name,String pattern) { }
  
  public Rule<T,U> create(Function<RuleContext<T>,U> f) {
    if (constructor != null) throw new IllegalStateException("create can only be used once");
    constructor = f;
    return this;
  }
  
  public Rule<T,U> create(Supplier<U> f) {
    // Wrap Supplier into Function (ignoring parameter)
    return create((r)->f.get());
  }
  
  public Rule<T,U> fill(BiConsumer<U,RuleContext<T>> c) {
    if (fillers.isEmpty()) fillers = new ArrayList<>();
    return this;
  }

  public Rule<T,U> fill(Consumer<U> c) {
    return fill((t,r)->c.accept(t));
  }

}
