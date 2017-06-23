package nl.anchormen.scala_training.module2.example

import scala.annotation.tailrec

/**
  * Shows the use of for-comprehensions and (tail) recursion
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
object ForComprehensionsRecursion05 {
  /*** For comprehensions ***/
  def even(from: Int, to: Int): List[Int] =
    for (i <- (from to to).toList if i % 2 == 0) yield i

  /*** Recursion ***/
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case x :: tail => x + sum(tail)
  }

  /*** Tail Recursion ***/
  def sum2(ints: List[Int]): Int = {
    @tailrec
    def sumAccumulator(ints: List[Int], accum: Int): Int = {
      ints match {
        case Nil => accum
        case x :: tail => sumAccumulator(tail, accum + x)
      }
    }
    sumAccumulator(ints, 0)
  }
}
