package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import anorm._
import models._
import views._
import java.util.Date

object Money extends Controller {
  def index = Action {
    Ok(views.html.index())
  }

  def overview = Action {
    Ok(views.html.overview())
  }

  def incomes = Action {
    Ok(views.html.alone("Доходы")(views.html.income(Income.getFirstTen)))
  }

  def income(id: Long) = Action {
    Ok(views.html.items.income(Income.findByID(id)))
  }

  def createIncome = Action {
    Ok(views.html.items.income(Income(NotAssigned, 1, "Источник дохода", 0.0, new Date(), None)))
  }

  def outcomes = Action {
    Ok(views.html.alone("Расходы")(views.html.outcome(Outcome.getFirstTen)))
  }

  def outcome(id: Long) = Action {
    Ok(views.html.items.outcome(Outcome.findByID(id)))
  }

  def createOutcome = Action {
    Ok(views.html.items.outcome(Outcome(NotAssigned, 1, "Цель расхода", 0.0, new Date(), None)))
  }

  def balances = Action {
    Ok(views.html.alone("Баланс")(views.html.balance(Balance.getFirstTen)))
  }

  def balance(id: Long) = Action {
    Ok(views.html.items.balance(Balance.findByID(id)))
  }

  def createBalance = Action {
    Ok(views.html.items.balance(Balance(NotAssigned, "Название счета", .0, None)))
  }
}
