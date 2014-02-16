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
  val simple = {
    get[Pk[Long]]("incomes.id") ~
      get[Long]("incomes.balance_id") ~
      get[String]("incomes.name") ~
      get[Double]("incomes.amount") ~
      get[Date]("incomes.date") ~
      get[Option[String]]("incomes.description") map {
      case id~balanceId~name~amount~date~description => Income(id, balanceId, name, amount, date, description)
    }
  }

  def findByID(id: Long) = {
    DB.withConnection { implicit connection =>
      SQL("select * from incomes where id = {id};").on('id -> id).as(Income.simple.single)
    }
  }

  def getLastTen = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM incomes ORDER BY id DESC LIMIT 10;").as(Income.simple *)
    }
  }

  def getAll = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM incomes ORDER BY date DESC, id;").as(Income.simple *)
    }
  }

  def insert(obj: Income): Long = {
    println("!!! " + obj + " !!!")
    DB.withConnection { implicit connection =>
      SQL("insert into incomes (balance_id, name, amount, date, description) VALUES ({balance_id}, {name}, {amount}, {date}, {description});").on(
        'balance_id -> obj.balanceId,
        'date -> obj.date,
        'name -> obj.name,
        'amount -> obj.amount,
        'description -> obj.description
      ).executeUpdate()
    }
  }

  def update(id: Long, obj: Income) {
    DB.withConnection { implicit connection =>
      SQL("update incomes set balance_id = {balance_id}, date = {date}, name = {name}, amount = {amount}, description = {description} where id = {id};").on(
        'id -> id,
        'balance_id -> obj.balanceId,
        'date -> obj.date,
        'name -> obj.name,
        'amount -> obj.amount,
        'description -> obj.description
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit connection =>
      SQL("delete from incomes where id = {id};").on('id -> id).executeUpdate()
    }
  }
}

object Outcome {
  val simple = {
      get[Pk[Long]]("outcomes.id") ~
      get[Long]("outcomes.balance_id") ~
      get[String]("outcomes.name") ~
      get[Double]("outcomes.amount") ~
      get[Date]("outcomes.date") ~
      get[Option[String]]("outcomes.description") map {
      case id~balanceId~name~amount~date~description => Outcome(id, balanceId, name, amount, date, description)
    }
  }

  def findByID(id: Long) = {
    DB.withConnection { implicit connection =>
      SQL("select * from outcomes where id = {id};").on('id -> id).as(Outcome.simple.single)
    }
  }

  def getLastTen = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM outcomes ORDER BY id DESC LIMIT 10;").as(Outcome.simple *)
    }
  }

  def getAll = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM outcomes ORDER BY date DESC, id;").as(Outcome.simple *)
    }
  }

  def insert(obj: Outcome): Long = {
    DB.withConnection { implicit connection =>
      SQL("insert into outcomes (balance_id, name, amount, date, description) VALUES ({balance_id}, {name}, {amount}, {date}, {description});").on(
        'balance_id -> obj.balanceId,
        'date -> obj.date,
        'name -> obj.name,
        'amount -> obj.amount,
        'description -> obj.description
      ).executeUpdate()
    }
  }

  def update(id: Long, obj: Outcome) {
    DB.withConnection { implicit connection =>
      SQL("update outcomes set balance_id = {balance_id}, date = {date}, name = {name}, amount = {amount}, description = {description} where id = {id};").on(
        'id -> id,
        'balance_id -> obj.balanceId,
        'date -> obj.date,
        'name -> obj.name,
        'amount -> obj.amount,
        'description -> obj.description
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit connection =>
      SQL("delete from outcomes where id = {id};").on('id -> id).executeUpdate()
    }
  }
}

object Balance {
  val simple = {
      get[Pk[Long]]("accounts.id") ~
      get[String]("accounts.name") ~
      get[Double]("accounts.balance") ~
      get[Option[String]]("accounts.description") map {
      case id~name~balance~description => Balance(id, name, balance, description)
    }
  }

  def findByID(id: Long) = {
    DB.withConnection { implicit connection =>
      SQL("select * from accounts where id = {id};").on('id -> id).as(Balance.simple.single)
    }
  }

  def getLastTen = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM accounts ORDER BY id DESC LIMIT 10;").as(Balance.simple *)
    }
  }

  def getAll = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM accounts ORDER BY id DESC;").as(Balance.simple *)
    }
  }

  def insert(obj: Balance): Long = {
    DB.withConnection { implicit connection =>
      SQL("insert into accounts (name, balance, description) VALUES ({name}, {balance}, {description});").on(
        'name -> obj.name,
        'balance -> obj.balance,
        'description -> obj.description
      ).executeUpdate()
    }
  }

  def update(id: Long, obj: Balance) {
    DB.withConnection { implicit connection =>
      SQL("update accounts set name = {name}, balance = {balance}, description = {description} where id = {id};").on(
        'id -> id,
        'name -> obj.name,
        'balance -> obj.balance,
        'description -> obj.description
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit connection =>
      SQL("delete from accounts where id = {id};").on('id -> id).executeUpdate()
    }
  }
}

