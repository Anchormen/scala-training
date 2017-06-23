package nl.anchormen.scala_training.module2.example

import scala.annotation.tailrec
import scala.collection.immutable

/**
  * Shows the use of for-comprehensions and (tail) recursion
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
object ForComprehensionsRecursion05 {
  /*** For comprehensions ***/
  def even(from: Int, to: Int): List[Int] =
    for (i <- (from to to).toList if i % 2 == 0) yield i

  /*** Translations ***/
  val c1, c2, c3 = (1 to 10).toList
  for(x <- c1; y <- c2; z <-c3) {
    println((x, y, z).toString)
  }
  c1.foreach(
    x => c2.foreach(
      y => c3.foreach(
        z => {
          println((x, y, z).toString)
        }
  )))

  val l1: List[(Int, Int, Int)] = for(x <- c1; y <- c2; z <- c3) yield {
    (x, y, z)
  }
  val l2 = c1.flatMap(
    x => c2.flatMap(y => c3.map(z => (x, y, z)))
  )

  val l3: List[Int] = for(x <- c1; if x % 2 == 0) yield {
    x * 2
  }
  val l4: List[Int] = c1.withFilter(x => x % 2 == 0).map(x => x * 2)
  val l5: List[Int] = c1.filter(x => x % 2 == 0).map(x => x * 2)

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
