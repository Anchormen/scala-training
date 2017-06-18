package nl.anchormen.scala_training.module1.example

/**
  * Shows the use of classes in Scala.
  *
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
object ClassExamples04 extends App {
  /*** Class instantiation ***/
  class SomeClass
  class HasOneValue {
    val theValue = "One"
  }

  /*** Members ***/
  val anInstance : SomeClass = new SomeClass
  val oneValue = new HasOneValue
  println(s"The value: ${oneValue.theValue}")

  /*** Methods ***/
  class HasOnePrivateValue {
    private val theValue = "Private One"
    def showPrivates : Unit = println(s"The private value: $theValue")
  }
  val privateOneValue = new HasOnePrivateValue
  // privateOneValue.theValue // Error
  privateOneValue.showPrivates

  /*** Access modifiers ***/
  class HasAFriend {
    private[ClassExamples04] val theValue = "Less private One"
  }
  val aFriend = new HasAFriend
  println(s"The less private value: ${aFriend.theValue}")

  /*** Constructors ***/
  class BothWays(val left : String, inaccessible : String) {
    val right = inaccessible
  }
  val bothWays = new BothWays("Rick", "Morty")
  // bothWays.inaccessible // Error
  println(s"History is made by who is ${bothWays.left}, not by who is ${bothWays.right}...")

  /*** The object keyword ***/
  object OneAndOnly {
    val contains = "Bird Person"
  }
  // val secondInstance = new OneAndOnly // Error
  println(s"Who is this person? It is: ${OneAndOnly.contains}")

  /*** Case classes ***/
  case class Unity(assimilated : String)
  val first = Unity("A giraffe")
  val second = first copy()
  println(s"First $first has same content as second $second: ${first == second}")
  println(s"But they are different objects: ${System.identityHashCode(first) != System.identityHashCode(second)}")

  /*** Companion objects ***/
  object Unity {
    var unsafeCounter = 0
    def apply(i : Int) : Unity = {
      unsafeCounter += 1
      Unity(s"Assimilation #$i")
    }
  }
  val third = Unity(3)

  /*** Inheritance ***/
  abstract class SmithFamilyMember(val name : String = ("A Smith")) {
    def universe : String = "Universe A"
  }
  class MortySmith extends SmithFamilyMember {
    override val name = "Morty Smith"
    override def universe : String = "Universe B"
    def superUniverse : String = super.universe
  }
  val aMorty = new MortySmith
  println(s"The person ${aMorty.name} is in ${aMorty.universe}, not ${aMorty.superUniverse}")
  val castedMorty : SmithFamilyMember = aMorty.asInstanceOf[SmithFamilyMember]
  println(s"Name: ${castedMorty.name} is in ${castedMorty.universe}")

}

