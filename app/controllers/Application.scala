package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  def index = Action {
    Ok(views.html.index())
  }
  
  def showMessage(msg: String) = Action {
    Ok(views.html.message(msg))
  }

  def takeMessage = Action { implicit request =>
    val postParams = request.body.asFormUrlEncoded.get
    val msg = postParams("message")
    Ok(views.html.message(msg(0)))
  }
}