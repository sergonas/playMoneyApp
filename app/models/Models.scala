package models

import java.util.{Date}
import play.api.db._
import play.api.Play.current
import anorm._
import anorm.SqlParser._
import scala.language.postfixOps

case class Income(id: Pk[Long] = NotAssigned, balanceId: Long, name: String, amount: Double, date: Date, description: Option[String])
case class Outcome(id: Pk[Long] = NotAssigned, balanceId: Long, name: String, amount: Double, date: Date, description: Option[String])
case class Balance(id: Pk[Long] = NotAssigned, name: String, balance: Double, description: Option[String])

object Income {
  var dbPlaceholder = List(Income(Id(1), 1, "Зарплата", 30000.0, new Date(), Option("фыловл")),
      Income(Id(2), 1, "Взятка", 300.0, new Date(), Option("ФВФЫВ")))
  def findByID(id: Long) = {
    dbPlaceholder.filter(_.id.get == id).head
  }

  def getFirstTen = {
    dbPlaceholder
  }

  def insert(obj: Income) {
    val maxId = dbPlaceholder.foldLeft(0L)(_ max _.id.get) + 1
    dbPlaceholder = (obj.copy(id = Id(maxId)) :: dbPlaceholder).sortBy(_.id.get).reverse
  }
}

object Outcome {
  var dbPlaceholder = List(Outcome(Id(4), 1, "Пиво", 200.0, new Date(), Option("Лала")), Outcome(Id(2), 1, "Водка", 300.0, new Date(), Option("ФВФЫВ")),Outcome(Id(0), 1, "Еда", 500.0, new Date(), Option("Фыр фыр фыр")))
  def findByID(id: Long) = {
    dbPlaceholder.filter(_.id.get == id).head
  }

  def getFirstTen = {
    dbPlaceholder
  }

  def insert(obj: Outcome) {
    dbPlaceholder = obj :: dbPlaceholder
  }
}

object Balance {
  var dbPlaceholder = List(Balance(Id(1), "Наличные", 30000.0, Option("фыловл")),
    Balance(Id(2), "Карта", 300.0, Option("ФВФЫВ")))

  def findByID(id: Long) = {
    dbPlaceholder.filter(_.id.get == id).head
  }

  def getFirstTen = {
    dbPlaceholder
  }

  def insert(obj: Balance) {
    dbPlaceholder = obj :: dbPlaceholder
  }
}

