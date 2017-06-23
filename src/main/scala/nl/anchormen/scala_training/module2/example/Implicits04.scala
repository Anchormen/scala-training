package nl.anchormen.scala_training.module2.example

import scala.concurrent.{ExecutionContext, Future}

/**
  * Examples of how to use implicits.
  *
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
object Implicits04 extends App {
  /*** Implicits 1 ***/
  type Employee = String
  type Role = String
  type EmployeeWithRole = String

  def getEmployee(id: Int)(implicit e: ExecutionContext): Future[Employee] = ???
  def getRole(employee :Employee)(implicit e: ExecutionContext): Future[Role] = ???

  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
  val bigEmployee: Future[EmployeeWithRole] =
    getEmployee(100).flatMap { e =>
      getRole(e).map { r =>
        "EmployeeRole: " + r
      }
    }

  /*** Implicit conversion ***/
  implicit def agentCodename(i: Int) = s"00$i"
  def hello(name: String) = s"Hello, $name!"
  hello(7)  // "Hello, 007!"

  /*** Implicit wrapping ***/
  implicit class Agent(code: Int) {
    def codename = s"00$code"
  }
  def hello2(agent: Agent) = s"Hello, ${agent.codename}!"
  hello2(7)  // "Hello, 007!"

  type Json = String
  trait Jsonable[T]{
    def serialize(t: T): Json
  }
  object Jsonable{
    implicit object StringJsonable extends Jsonable[String]{
      def serialize(t: String) = t
    }
    implicit object DoubleJsonable extends Jsonable[Double]{
      def serialize(t: Double) = t.toString
    }
    implicit object IntJsonable extends Jsonable[Int]{
      def serialize(t: Int) = t.toString
    }
  }

  def convertToJson[T](x: T)(implicit converter: Jsonable[T]): Json = {
    converter.serialize(x)
  }

  convertToJson("hello")
  convertToJson(123)
  convertToJson(123.56)
//  convertToJson('a') // Error

}

/**
  * An example with a lot of the same parameters necessary for subsequent function calls.
  */
object Motivation {
  type DbConnection = String
  type Statistics = String
  type Logger = String
  type Smtp = String

  def manipulateData() : Unit = {
    val dbConnection : DbConnection= "someDbConnection"
    val statistics : Statistics = "someStatistics"
    val logger : Logger = "someLogger"
    val email : Smtp = "emailConnection"

    val tick = System.currentTimeMillis()
    val result = performSomeQuery("DROP DATABASE", dbConnection, statistics, logger, email)
    val transformed = transformResult(result, dbConnection, statistics, logger, email)
    writeBack(transformed, dbConnection, statistics, logger, email)

    val tock = System.currentTimeMillis()
    recordRuntime(tick - tock, dbConnection, statistics, logger, email)
  }

  def performSomeQuery(query : String, dbConnection: DbConnection, statistics: Statistics, logger: Logger, email : Smtp ) : Option[String] = {
    return Some("result")
  }

  def transformResult(result : Option[String], dbConnection: DbConnection, statistics: Statistics, logger: Logger, email : Smtp ) : Option[String] = {
    return Some("transformed")
  }

  def writeBack(result : Option[String], dbConnection: DbConnection, statistics: Statistics, logger: Logger, email : Smtp ) : Unit = {
    println("write back")
  }

  def recordRuntime(time : Long, dbConnection: DbConnection, statistics: Statistics, logger: Logger, email : Smtp) : Unit = {
    println(s"Took $time microseconds")
  }

}

/**
  * The example from above cleaned up with implicits.
  */
object MotivationWithImplicits {
  type DbConnection = String
  class Statistics
  class Logger
  class Smtp

  def manipulateData() : Unit = {
    implicit val dbConnection : DbConnection= "someDbConnection"
    implicit val statistics : Statistics = new Statistics
    implicit val logger : Logger = new Logger
    implicit val email : Smtp = new Smtp

    val tick = System.currentTimeMillis()
    val result = performSomeQuery("DROP DATABASE")
    val transformed = transformResult(result)
    writeBack(transformed)

    val tock = System.currentTimeMillis()
    recordRuntime(tick - tock)
  }

  def performSomeQuery(query : String)
                      (implicit dbConnection: DbConnection,
                       statistics: Statistics,
                       logger: Logger,
                       email : Smtp ) : Option[String] = {
    return Some("result")
  }

  def transformResult(result : Option[String])(implicit dbConnection: DbConnection, statistics: Statistics, logger: Logger, email : Smtp ) : Option[String] = {
    return Some("transformed")
  }

  def writeBack(result : Option[String])(implicit dbConnection: DbConnection, statistics: Statistics, logger: Logger, email : Smtp ) : Unit = {
    println("write back")
  }

  def recordRuntime(time : Long)(implicit dbConnection: DbConnection, statistics: Statistics, logger: Logger, email : Smtp ) : Unit = {
    println(s"Took $time microseconds")
  }

}
