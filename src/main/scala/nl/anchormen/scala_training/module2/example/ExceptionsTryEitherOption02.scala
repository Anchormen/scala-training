package nl.anchormen.scala_training.module2.example

import scala.util.{Failure, Success, Try}

/**
  * Display the use of exceptions, Try, Either and Option.
  *
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
object ExceptionsTryEitherOption02 extends App {
  /*** Exceptions ***/
  try {
    throw new IllegalArgumentException("I got thrown")
  } catch {
    case e : IllegalArgumentException => println(s"e: $e")
  }

  try {
    throw new IllegalArgumentException("I got thrown")
  } catch {
    case e : IllegalArgumentException => println(s"e: $e")
    case t : Throwable => throw new RuntimeException(t)
  }

  try {
    throw new IllegalArgumentException("I got thrown")
  } catch {
    case _ : Throwable => Unit // Now you really swallowed it
  }

  /*** Try ***/
  def divide(num : Int, den : Int) : Int = num / den

  val unholy = Try(
    divide(10, 0)
  )

  val eitherWay = unholy getOrElse(Int.MaxValue)

  def checkResult(result : Try[Int]) : Unit = result match {
    case Success(answer) => {
      println(s"The answer is: $answer")
    }
    case Failure(e) => {
      println(s"Did you divide by zero? $e")
    }
  }
  checkResult(unholy)

  /*** Either ***/
  val tuttiFrutti  = List("true", "false", "false", "yes", "no", "x", "true")
  val parsed : List[Either[Boolean, String]] = tuttiFrutti.map(
    (s : String) => {
      if(s == "true" || s == "false")
        Left(s.toBoolean)
      else
        Right(s)
    }
  )

  /*** Option ***/
  val some : Option[Int] = Some(2)
  some map(_ + 2) // Some(4)
  val nope : Option[Int] = None
  nope map(_ + 2)
  // nope get // Exception
  nope getOrElse(4)

  val mixedResults = List(Some(1), Some(2), None, None, Some(3))
  mixedResults map(
    (opt : Option[Int]) => opt.map(_ * 2)
  ) // List(Some(2), Some(4), None, None, Some(6))

  val listOfWork = List(
    Try(divide(4, 2)).toOption,
    Try(divide(6, 3)).toOption,
    unholy.toOption
  )

  val validResults = listOfWork.flatten
  println(s"Got the following results: $validResults")


}
