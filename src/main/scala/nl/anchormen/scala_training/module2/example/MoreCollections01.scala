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
  List(1, 2, 3, 4).reduce((acc, el) => acc + el) // Sums all elements to 10
  1 to 4 reduce(_+_) // Also 10

  /*** Fold left and right ***/
  1 to 4 foldLeft("")((acc : String, el : Int) => acc + el)

}
