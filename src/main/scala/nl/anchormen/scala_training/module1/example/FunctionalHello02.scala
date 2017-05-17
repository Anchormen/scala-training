package nl.anchormen.scala_training.module1.example

/**
  * A simple functional program displaying several uses of a function value.
  *
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
object FunctionalHello02 extends App {

  val func : (String) => Unit = (line : String) => println(line)
  func("Hello Anchormen!")

  val aCollection = Seq("Hello",  "Anchormen!")
  for(el <- aCollection) {
    func(el)
  }

  aCollection.map(func)
  aCollection map func
}
