/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jgrammar.test;

import com.googlecode.jgrammar.Matcher;
import com.googlecode.jgrammar.Rule;
import com.googlecode.jgrammar.RuleContext;
import com.googlecode.jgrammar.Tokenizer;
import java.util.regex.Pattern;

/**
 *
 * @author jdesmet
 */
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    Tokenizer<Expression> tokenizer = new Tokenizer<Expression>()
      .add(
        new Rule<Expression,Variable>("EXPRESSION",Matcher.forRule("ID"))
          .create(Variable::new)
          .fill((o,c)->{o.setName(c.getString());})
      )
      .add(
        new Rule<Expression,Value>("EXPRESSION",Matcher.forRule("NUMBER"))
          .create(Value::new)
          .fill((o,c)->{o.setValue(c.getString());})
      )
      .add(
        new Rule<Expression,Expression>("EXPRESSION",Matcher.forRule("EXPRESSION").capture("expression"))
          .create((c)->c.getReference("expression"))
      )
      .add(
        new Rule<Expression,BinaryOperator>("EXPRESSION",
          Matcher.forRule("EXPRESSION").capture("left"),
          Matcher.forPattern("[*/]").capture("operator"),
          Matcher.forRule("EXPRESSION").capture("right")
        )
          .create(BinaryOperator::new)
          .fill((o,c)->{o.setOperator(c.getMatch("operator"));})
          .fill((o,c)->{o.setLeftOperand(c.getReference("left"));})
          .fill((o,c)->{o.setRightOperand(c.getReference("right"));})
      )
      .add(
        new Rule<Expression,BinaryOperator>("EXPRESSION",
          Matcher.forRule("EXPRESSION").capture("left"),
          Matcher.forPattern("[+-]").capture("operator"),
          Matcher.forRule("EXPRESSION").capture("right")
        )
          .create(BinaryOperator::new)
          .fill((o,c)->{o.setOperator(c.getMatch("operator"));})
          .fill((o,c)->{o.setLeftOperand(c.getReference("left"));})
          .fill((o,c)->{o.setRightOperand(c.getReference("right"));})
      )
      .add("ID",Pattern.compile("[a-zA-Z]+"))
      .add("NUMBER",Pattern.compile("[0-9]+"));
    
    //tokenizer.ignore("[ ]*");

    Expression expression = tokenizer.parse("1+20*3/value");
    
    //Object value = expression.evaluate();

    // [1+2*3]
    // NUM(value=1) [+] NUM(value=2) [*] NUM(value=3)
    // NUM(value=1) [+] MUL(NUM(value=2),NUM(value=3))
    // ADD(NUM(value=1),MUL(NUM(value=2),NUM(value=3)))
    
    // (1+2)*3
    // [(] NUM(value=1) [+] NUM(value=2) [)*] NUM(value=3)
    // [(] ADD(NUM(value=1),NUM(value=2)) [)*] NUM(value=3)
    // EXPRESSION(ADD(NUM(value=1),NUM(value=2))) [*] NUM(value=3)
    // MUL(EXPRESSION(ADD(NUM(value=1),NUM(value=2))),NUM(value=3))
    // Extra edit to simplify:
    // MUL(ADD(NUM(value=1),NUM(value=2)),NUM(value=3))
  
    
  }
  
}
