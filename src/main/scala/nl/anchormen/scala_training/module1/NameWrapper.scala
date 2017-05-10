package nl.anchormen.scala_training.module1

/**
  * A simple string wrapper.
  *
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
class SimpleNameWrapper(val name: String)

object SimpleNameWrapperDemo {
  def main(args : Array[String]) : Unit = {
    val wrapper = new SimpleNameWrapper("Anchormen")
    println(s"Wrapper contains: ${wrapper.name}")
  }
}

/**
  * A name wrapper that shows a few Scala features.
  *
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
class NameWrapper(val firstName : String,
                  val lastName : String = "",
                  val formal : Boolean = false) {

    val fullName = if(lastName.isEmpty) firstName else firstName + " "

    def salutation() : String = {
      val greeting = if(this.formal) "Dear " else "Hi "
      greeting + fullName
    }
}



