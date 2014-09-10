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
public class Main {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    Tokenizer<Expression> tokenizer = new Tokenizer<>();
    tokenizer.ignore("[ ]*");
    tokenizer.add(new Rule<Expression,Expression>("EXPRESSION","({EXPRESSION})").create(Expression::new));
    tokenizer.add(
      new Rule<Expression,BinaryOperator>("EXPRESSION","{EXPRESSION:left}[[*/]:operator]{EXPRESSION}")
        .create(BinaryOperator::new)
        .fill((o,c)->{o.setOperator(c.getMatch("operator"));})
        .fill((o,c)->{o.setLeftOperand(c.getReference("left"));})
        .fill((o,c)->{o.setRightOperand(c.getReference("right"));})
    );
    tokenizer.add(
      new Rule<Expression,BinaryOperator>("EXPRESSION","{EXPRESSION:left}[[+-]:operator]{EXPRESSION}")
        .create(BinaryOperator::new)
        .fill((o,c)->{o.setOperator(c.getMatch("operator"));})
        .fill((o,c)->{o.setLeftOperand(c.getReference("left"));})
        .fill((o,c)->{o.setRightOperand(c.getReference("right"));})
    );
    tokenizer.add(new Rule("EXPRESSION","{ID:value}"));
    tokenizer.add(new Rule("EXPRESSION","{NUMBER:value}"));
    tokenizer.add(new Pattern("ID","[a-zA-Z]+"));
    tokenizer.add(new Pattern("NUMBER","[0-9]+(.[0-9])?"));
    
    Expression expression = tokenizer.parse("1+2*3");
    
    Object value = expression.evaluate();

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
