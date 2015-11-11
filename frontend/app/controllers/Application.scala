package controllers

import play.api.Play.current
import play.api._
import play.api.libs.ws.WS
import play.api.mvc._


class Application extends Controller {
  val actorSystem = current.actorSystem


  import actorSystem.dispatcher


  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def getSolution = Action.async(parse.json) { request =>
    WS
      .url(s"http://${sys.env("BACKEND_HOSTNAME")}:8080")
      .post(request.body)
      .map { response =>
        Status(response.status)(response.json)
      }
  }
}
