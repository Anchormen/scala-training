package nl.anchormen.scala_training.module1.example

/**
  * Shows the use of traits.
  *
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
object TraitExamples05 extends App {
  trait HasWheels {
    var frontLeft, frontRight, backLeft, backRight : Int = 0
  }

  trait HasEngine {
    val speed = 10
    def drive : Unit
  }

  trait HasSteer {
    var steerPosition : Int = 0
    def steerLeft : Unit = steerPosition = -90
    def steerRight : Unit = steerPosition = 90
  }

  class Car extends HasWheels with HasSteer with HasEngine {
    def drive() : Unit = {
      frontLeft  = (frontLeft + speed) % 360
      frontRight =(frontRight + speed) % 360
      backLeft  = (backLeft + speed) % 360
      backRight = (backRight + speed) % 360
    }
  }

  val aFasterCar = new Car {
    override val speed = 50
  }
}
