package models

import java.util.{Date}
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import scala.language.postfixOps

case class Income(id: Pk[Long] = NotAssigned, name: String, amount: Double, date: Date, description: Option[String])
case class Outcome(id: Pk[Long] = NotAssigned, name: String, amount: Double, date: Date, description: Option[String])
case class Balance(id: Pk[Long] = NotAssigned, name: String, balance: Double, description: Option[String])

object Income {
  val dbPlaceholder = List(Income(Id(1), "Зарплата", 30000.0, new Date(), Option("фыловл")),
      Income(Id(2), "Взятка", 300.0, new Date(), Option("ФВФЫВ")))
  def findByID(id: Long) = {
    dbPlaceholder(id.toInt - 1)
  }

  def getFirstTen = {
    dbPlaceholder
  }
}

object Outcome {
  val dbPlaceholder = List(Outcome(Id(1), "Пиво", 200.0, new Date(), Option("Лала")), Outcome(Id(2), "Водка", 300.0, new Date(), Option("ФВФЫВ")),Outcome(Id(3), "Еда", 500.0, new Date(), Option("Фыр фыр фыр")))
  def findByID(id: Long) = {
    dbPlaceholder(id.toInt - 1)
  }

  def getFirstTen = {
    dbPlaceholder
  }
}

object Balance {
  val dbPlaceholder = List(Balance(Id(1), "Наличные", 30000.0, Option("фыловл")),
    Balance(Id(2), "Карта", 300.0, Option("ФВФЫВ")))

  def findByID(id: Long) = {
    dbPlaceholder(id.toInt - 1)
  }

  def getFirstTen = {
    dbPlaceholder
  }
}