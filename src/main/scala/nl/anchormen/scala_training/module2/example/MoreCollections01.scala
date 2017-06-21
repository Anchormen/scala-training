package nl.anchormen.scala_training.module2.example

/**
  * Displays more collections types.
  *
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
object MoreCollections01 extends App {
  /*** Map ***/
  val l = List(1, 2, 3)
  l map (
    (i : Int) => i + 2
  ) // List(3, 4, 5)
  l map (_ + 2) // same thing

  /*** Flatten ***/
  val thickList = List(
    List(1, 2, 3),
    List(4, 5, 6),
    List(7, 8, 9)
  )
  thickList flatten // List(1, 2, 3, 4, 5, 6, 7, 8, 9)

  /*** Flat Map ***/
  thickList flatMap(
    (l : List[Int]) => l.map(
      (i : Int) => i * 10
    )
  ) // List(10, 20, 30, 40, 50, 60, 70, 80, 90)
  thickList flatMap (_.map(_* 10))  // same thing

  /*** Filter ***/
  thickList.flatten.filter(_ % 2 == 0) // List(2, 4, 6, 8)

  /*** Reduce ***/
  List(1, 2, 3, 4).reduce(
    (acc, el) => acc + el
  ) // Sums all elements to 10
  (1 to 4) reduce(_+_) // Also 10

  Map(
    Tuple2("a", 1), // Creating a Tuple with 2 elements
    Tuple2("b", 2),
    "c" -> 3,       // Syntactic sugar for a Tuple with 2 elements
    "d" -> 4
  ).reduce(
    (acc : (String, Int), el : (String, Int)) => { // 2x type Tuple2[String, Int]
      val key = acc._1 + el._1  // Element n of a tuple is accessed via _n
      val value = acc._2 + el._2
      (key, value)              // Third way of creating tuples
    }
  )

  /*** Fold left and right ***/
  (1 to 4).foldLeft("")(
    (acc : String, el : Int) => acc + el.toString
  )
  (1 to 4).foldRight("")(
    (el : Int, acc : String) => acc + el.toString
  )

}
