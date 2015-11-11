import scala.util.{Failure, Success}

import com.twitter.finagle.Http
import com.twitter.util.Await

import io.finch._
import io.finch.request._
import io.finch.circe._
import io.circe.generic.auto._

import demo.{Input, Output}


object Server extends App {
  val api: Endpoint[Output] = post(/ ? body.as[Input]) { input: Input =>
    BinPackingAndCombinators.processInput(input) match {
      case Success(output) => Ok(output)
      case Failure(t)      => InternalServerError.copy(message = Map("error" -> t.toString))
    }
  }.handle {
    case t =>
      BadRequest.copy(message = Map("invalid" -> t.toString))
  }

  val server = Http.serve(":8080", api.toService)
  Await.result(server)
}
