package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import anorm._
import models._
import views._
import java.util.Date

object OutcomeControls extends Controller{
  val outcomeForm = Form(
    mapping(
      "id" -> text.transform((x: String) => if (x.toLong >= 0) Id(x.toLong): Pk[Long] else NotAssigned, (x: Pk[Long]) => x.getOrElse("-1").toString),
      "account" -> ignored(1L),
      "name" -> nonEmptyText,
      "amount" -> text.transform((x: String) => x.toDouble, (x: Double) => x.toString),
      "date" -> date,
      "desc" -> optional(text)
    )(Outcome.apply)(Outcome.unapply)
  )

  def outcomes = Action {
    Ok(views.html.alone("Доходы")(views.html.outcome(Outcome.getAll)))
  }

  def readOutcome(id: Long) = Action {
    Ok(views.html.edit(Outcome.findByID(id)))
  }

  def newOutcome = Action {
    Ok(views.html.create(Outcome(NotAssigned, 1, "Цель расхода", 0.0, new Date(), None)))
  }

  def createOutcome() = Action { implicit request =>
    outcomeForm.bindFromRequest.fold(
      errors => { println(errors); BadRequest },

      out => { Outcome.insert(out); Redirect("/overview") }
    )
  }

  def updateOutcome(id: Long) = Action { implicit request =>
    outcomeForm.bindFromRequest.fold(
      errors => { println(errors); BadRequest },

      inc => { Outcome.update(id, inc); Redirect("/overview") }
    )
  }

  def deleteOutcome(id: Long) = Action {
    Outcome.delete(id)
    Redirect("/overview")
  }
}
