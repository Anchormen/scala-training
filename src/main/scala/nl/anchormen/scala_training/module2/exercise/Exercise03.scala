package nl.anchormen.scala_training.module2.exercise

/**
  * Exercise for pattern matching.
  * In this exercise you are to compute simple arithmetical expressions. It is handy to represent such expressions
  * as trees. Consider type hierarchy below: Tree, Value, Plus. Arithmetical expression 3+(5+9) can be represented by
  * a tree: Plus(Value(3), Plus(Value(5), Value(9)))
  *
  * Assignment:
  * 1. Implement function compute(tree: Tree): Int so that it computes the value of any arithmetical expression
  * represented as a tree build with Value and Plus case classes. Make sure assertions are satisfied.
  * 2. Extend the class hierarchy and the compute function to support subtraction (-) and multiplication (*).
  * 3. Build trees for the following expressions and compute them:
  * a) 8-4
  * b) 9*7
  * c) 8-(10*2)
  * d) (10-7)*(9+2)
  * e) (15*(3+4))-(7*8)+3
  *
  * @author Borys Biletskyy <b.biletskyy@anchormen.nl>
  */
object Exercise03 extends App {

  /** Base trait representing a tree */
  sealed trait Tree

  /** Represents integer value in arithmetical expressions */
  case class Value(value: Int) extends Tree

  /** Represents addition operation in arithmetical expressions */
  case class Plus(left: Tree, right: Tree) extends Tree

  def compute(tree: Tree): Int = ???

  //Tests
  assert(2 == compute(Value(2)))
  assert(3 + 5 == compute(Plus(Value(3), Value(5))))
  assert(1 + (5 + 3) == compute(Plus(Value(1), Plus(Value(5), Value(3)))))
}
