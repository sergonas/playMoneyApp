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

}
