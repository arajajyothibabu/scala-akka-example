package actors

import Utils.Trigger
import akka.actor.Actor

/**
  * Created by jyothi on 12/9/17.
  */
class Printer extends Actor {

  override def receive: Receive = {
    case Trigger(message: String) =>
      println(message)
  }

}
