package controllers

import play.api._
import play.api.mvc._

object Money extends Controller {
  def overview = Action {
    Ok(views.html.overview())
  }
}
