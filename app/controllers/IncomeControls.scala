package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import anorm._
import models._
import views._
import java.util.Date
import sun.util.resources.LocaleNames_fi

object IncomeControls extends Controller {
  val incomeForm = Form(
    mapping(
      "id" -> text.transform((x: String) => if (x.toLong >= 0) Id(x.toLong): Pk[Long] else NotAssigned, (x: Pk[Long]) => x.getOrElse("-1").toString),
      "account" -> ignored(1L),
      "name" -> nonEmptyText,
      "amount" -> text.transform((x: String) => x.toDouble, (x: Double) => x.toString),
      "date" -> date,
      "desc" -> optional(text)
    )(Income.apply)(Income.unapply)
  )

  def incomes = Action {
    Ok(views.html.alone("Доходы")(views.html.income(Income.getAll)))
  }

  def readIncome(id: Long) = Action {
    Ok(views.html.edit(Income.findByID(id)))
  }

  def newIncome = Action {
    Ok(views.html.create(Income(NotAssigned, 1, "Источник дохода", 0.0, new Date(), None)))
  }

  def createIncome() = Action { implicit request =>
    incomeForm.bindFromRequest.fold(
      errors => { println(errors); BadRequest },

      inc => { println(inc); Income.insert(inc); Redirect("/overview") }
    )
  }

  def updateIncome(id: Long) = Action { implicit request =>
    incomeForm.bindFromRequest.fold(
      errors => { println(errors); BadRequest },

      inc => { Income.update(id, inc); Redirect("/overview") }
    )
  }

  def deleteIncome(id: Long) = Action {
    Income.delete(id)
    Redirect("/overview")
  }
}
