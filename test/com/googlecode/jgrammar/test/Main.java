/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlecode.jgrammar.test;

import com.googlecode.jgrammar.Pattern;
import com.googlecode.jgrammar.Rule;
import com.googlecode.jgrammar.RuleContext;
import com.googlecode.jgrammar.Tokenizer;

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
        new Rule<Expression,Variable>("EXPRESSION","{ID}")
          .create(Variable::new)
          .fill((o,c)->{o.setName(c.getString());})
      )
      .add(
        new Rule<Expression,Value>("EXPRESSION","{NUMBER}")
          .create(Value::new)
          .fill((o,c)->{o.setValue(c.getString());})
      )
      .add(new Rule<Expression,Expression>("EXPRESSION","({EXPRESSION:expression})").create((c)->c.getReference("expression")))
      .add(
        new Rule<Expression,BinaryOperator>("EXPRESSION","{EXPRESSION:left}[[*/]:operator]{EXPRESSION}")
          .create(BinaryOperator::new)
          .fill((o,c)->{o.setOperator(c.getMatch("operator"));})
          .fill((o,c)->{o.setLeftOperand(c.getReference("left"));})
          .fill((o,c)->{o.setRightOperand(c.getReference("right"));})
      )
      .add(
        new Rule<Expression,BinaryOperator>("EXPRESSION","{EXPRESSION:left}[[+-]:operator]{EXPRESSION}")
          .create(BinaryOperator::new)
          .fill((o,c)->{o.setOperator(c.getMatch("operator"));})
          .fill((o,c)->{o.setLeftOperand(c.getReference("left"));})
          .fill((o,c)->{o.setRightOperand(c.getReference("right"));})
      )
      .add(new Rule("EXPRESSION","{ID:value}"))
      .add(new Rule("EXPRESSION","{NUMBER:value}"))
      .add(new Pattern("ID","[a-zA-Z]+"))
      .add(new Pattern("NUMBER","[0-9]+"));
    
    //tokenizer.ignore("[ ]*");

    Expression expression = tokenizer.parse("1+2*3/value");
    
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
