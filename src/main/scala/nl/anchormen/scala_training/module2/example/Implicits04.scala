package nl.anchormen.scala_training.module2.example

/**
  * Examples of how to use implicits.
  *
  * @author Jeroen Vlek <j.vlek@anchormen.nl>
  */
object Implicits04 extends App {
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
