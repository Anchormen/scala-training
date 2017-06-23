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
  type Statistics = String
  type Logger = String
  type Smtp = String

  def manipulateData() : Unit = {
    implicit val dbConnection : DbConnection= "someDbConnection"
    implicit val statistics : Statistics = "someStatistics"
    implicit val logger : Logger = "someLogger"
    implicit val email : Smtp = "emailConnection"

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
