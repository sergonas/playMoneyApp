package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import anorm._
import models._
import views._
import java.util.Date

object BalanceControls extends Controller {
  val balanceForm = Form(
    mapping(
      "id" -> text.transform((x: String) => if (x.toLong >= 0) Id(x.toLong): Pk[Long] else NotAssigned, (x: Pk[Long]) => x.getOrElse("-1").toString),
      "name" -> nonEmptyText,
      "balance" -> text.transform((x: String) => x.toDouble, (x: Double) => x.toString),
      "desc" -> optional(text)
    )(Balance.apply)(Balance.unapply)
  )

  def balances = Action {
    Ok(views.html.alone("Баланс")(views.html.balance(Balance.getLastTen)))
  }

  def readBalance(id: Long) = Action {
    Ok(views.html.edit(Balance.findByID(id)))
  }

  def newBalance = Action {
    Ok(views.html.create(Balance(NotAssigned, "Название счета", .0, None)))
  }

  def createBalance() = Action { implicit request =>
    balanceForm.bindFromRequest.fold(
      errors => { println(errors); BadRequest },

      inc => { Balance.insert(inc); Redirect("/overview") }
    )
  }

  def updateBalance(id: Long) = Action { implicit request =>
    balanceForm.bindFromRequest.fold(
      errors => { println(errors); BadRequest },

      acc => { Balance.update(id, acc); Redirect("/overview") }
    )
  }

  def deleteBalance(id: Long) = Action {
    Balance.delete(id)
    Redirect("/overview")
  }
}
