package nl.anchormen.scala_training.module2.example

/** Basic Pattern matching */
object Basic extends App {

  import scala.util.Random

  val x: Boolean = Random.nextBoolean()
  x match {
    case true => println("true")
    case false => println("false")
  }
}

/** Pattern matching on variables, values and types */
object ValuesVariableTypes extends App {
  for {
    x <- Seq(1, 2, 2.7, "one", "two", 'four) //Seq[Any]
  } {
    //pattern matching returns result as any expression in Scala
    val result = x match {
      case 1 => "Int 1"
      case i: Int => s"Int other than 1: $i"
      case d: Double => "A Double $d"
      case "one" => "String one"
      case s: String => s"String other than one: $s"
      case other => s"Something not listed above: $other"
    }
    println(result)
  }
}

/** Anonymous pattern matching and pattern combining */
object MorePatternMatching extends App {
  //Anonymous pattern matching and combining patterns with or
  for {
    x <- Seq(1, 2, 2.7, "one", "two", 'four)
  } {
    val result = x match {
      case _: Int | _: Double => "Int or Double" //combining 2 patterns to match
      case "one" => "string one"
      case s: String => s"String other than one: $s"
      case _ => s"something not listed above"
    }
    println(result)
  }
}

/** Guards in case statement in pattern matching */
object Guards extends App {
  for (i <- 0 to 5) {
    i match {
      case _ if i % 2 == 0 => println(s"$i is even")
      case _ => println(s"$i is odd")
    }
  }
}

/** Pattern matching on Tuples */
object Tuples extends App {
  val employees = Seq(
    ("John", "Smith", 27),
    ("Bill", "Wanes", 45),
    ("Mary", "Johnson", 34)
  )
  for (employee <- employees) {
    employee match {
      case ("Bill", _, _) => println("Found Bill")
      case (firstName, lastName, age) => println(s"Found $firstName $lastName, who is $age years old")
    }
  }
}

/** Pattern matching on collections */
object Collections extends App {

  import scala.collection.mutable

  //(Tail)-recursive function returning the length of a sequence
  //Beware Seq is not necessary immutable!!!
  def length[A](seq: Seq[A]): Int = seq match {
    case Nil => 0
    case _ +: tail => 1 + length(tail)
  }

  //Different types of sequences,
  val nonEmptySeq = Seq(1, 2, 3, 4, 5)
  val emptySeq = Seq.empty[Int]
  val nonEmptyList = List('1', '2', '3', '4', '5')
  val emptyList = Nil
  val nonEmptyVector = Vector(0.1, 0.2, 0.3, 0.4)
  val nonEmptyMutableList = mutable.MutableList("one", "two", "three")
  val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
  //Sequence of sequences
  val sequences = Seq(nonEmptySeq, emptySeq, nonEmptyList, emptyList, nonEmptyVector,
    nonEmptyMutableList, nonEmptyMap.toSeq)
  //Let's print lengths of all of them
  sequences.foreach(seq => println(length(seq)))
}

/** Pattern matching on case classes */
object CaseClasses extends App {

  case class Address(street: String, city: String, country: String)

  case class Person(name: String, age: Int, address: Address)

  val alice = Person("Alice", 35, Address("1 Nesh Lane", "Leiden", "Netherlands"))
  val bob = Person("Bob", 27, Address("2 Hilbert Ave.", "Leon", "France"))
  val charlie = Person("Charlie", 65, Address("3 Dijkstra St.", "Bergen", "Finland"))

  for (person <- Seq(alice, bob, charlie)) {
    person match {
      case Person("Alice", 35, Address(_, "Leiden", _)) => println("Hi Alice!")
      case Person(name, 27, address) => println(s"Hi $name, you order will be delivered to $address")
      case p@Person(_, _, _) => println(s"$p found")
    }
  }
}

/** Pattern matching on regular expressions */
object RegExp extends App {
  val dateRegexp = """(\d\d\d\d)-(\d\d)-(\d\d)""".r
  val timeRegexp = """^(\d\d):(\d\d)""".r
  //Some dates with time among them
  val dates = Seq("2004-01-20", "12:40", "1812-12-17")
  //Extract years from dates and skip time elements
  for (date <- dates) {
    date match {
      case dateRegexp(year, month, day) => println(s"Year $year was successful")
      case timeRegexp(_*) => println("Time found instead of date")
    }
  }
}

/** Pattern matching in exception handling */
object ExceptionHandling extends App {
  try {
    val s: String = null
    println(s.length())
  } catch { //exceptions are listed from more specific to more generic
    case _: NullPointerException => println("String was null")
    case e: RuntimeException => println(s"Runtime exception: ${e.getMessage}")
    case e: Exception => println(e.getCause)
    case _: Throwable => println("Unknown exception caught")
  } finally {
    println("Always executes")
  }
}

/** Pattern matching for high order functions */
object HighOrderFunctions extends App {
  val dates: Seq[(Int, String)] = Seq(
    1687 -> "Newton published Principia Mathematica",
    1776 -> "American declaration signed",
    1789 -> "French revolution started")
  //hard to read
  dates.map(pair => s"${pair._2} in ${pair._1}").foreach(println)
  //easy to read
  dates.map {
    case (year, event) => s"$event in $year"
  }.foreach(println)
}

object PartiallyDefinedFunctions extends App {
  val explain1687: PartialFunction[Int, String] = {
    case 1687 => "Newton published Principia Mathematica"
  }
  if (explain1687.isDefinedAt(1687)) { //true
    println(explain1687.apply(1687))
  }
  if (explain1687.isDefinedAt(1776)) { //false
    println(explain1687(1776)) //would give scala.MatchError: 3
  }
  val explain1776: PartialFunction[Int, String] = {
    case 1776 => "American declaration signed"
  }
  println(explain1776(1776))
  //combining partial functions
  val explainAll: PartialFunction[Int, String] =
    explain1687 orElse explain1776 orElse { case _ => "Unknown date" }
  //defined everywhere
  println(explainAll(1687))
  println(explainAll(1776))
  println(explainAll(2017))
}