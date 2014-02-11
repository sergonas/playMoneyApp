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
  val incomeForm = Form(
    mapping(
      "id" -> ignored(NotAssigned: anorm.Pk[Long]),
      "account" -> ignored(1L),
      "name" -> nonEmptyText,
      "amount" -> text.transform((x: String) => (x.toDouble), (x: Double) => (x.toString)),
      "date" -> date,
      "desc" -> optional(text)
    )(Income.apply)(Income.unapply)
  )
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
    Ok(views.html.edit(Income.findByID(id)))
  }

  def newIncome = Action {
    Ok(views.html.create(Income(NotAssigned, 1, "Источник дохода", 0.0, new Date(), None)))
  }

  def insertIncome = Action { implicit request =>
    incomeForm.bindFromRequest.fold(
      // Form has errors, redisplay it
      errors => {println(errors); BadRequest},

      // We got a valid User value, display the summary
      inc => {Income.insert(inc); Redirect("/overview")}
    )
  }

  def outcomes = Action {
    Ok(views.html.alone("Расходы")(views.html.outcome(Outcome.getFirstTen)))
  }

  def outcome(id: Long) = Action {
    Ok(views.html.edit(Outcome.findByID(id)))
  }

  def newOutcome = Action {
    Ok(views.html.create(Outcome(NotAssigned, 1, "Цель расхода", 0.0, new Date(), None)))
  }

  def balances = Action {
    Ok(views.html.alone("Баланс")(views.html.balance(Balance.getFirstTen)))
  }

  def balance(id: Long) = Action {
    Ok(views.html.edit(Balance.findByID(id)))
  }

  def newBalance = Action {
    Ok(views.html.create(Balance(NotAssigned, "Название счета", .0, None)))
  }
}
