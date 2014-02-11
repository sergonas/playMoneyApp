package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import anorm.NotAssigned
import models.Income

object Application extends Controller {
  def index = Action {
    Ok(views.html.index())
  }
  
  def showMessage(msg: String) = Action {
//    Ok(views.html.message(msg))
    NotFound("This page no more available")
  }

  def takeMessage = Action { implicit request =>
//    val postParams = request.body.asFormUrlEncoded.get
//    val msg = postParams("message")
//    Ok(views.html.message(msg(0)))
    NotFound("This page no more available")
  }
}