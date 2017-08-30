package actors

import akka.actor.{Actor, ActorRef, PoisonPill}

/**
  * Created by jyothi on 30/8/17.
  */
class Sender(store: ActorRef, id: Int) extends Actor {

  var count = 0

  /**
    * A partial function overrides the Actor receive method
    * ! symbols indicates "send" from actor's language.
    * @return
    */
  override def receive: Receive = {
    case "trigger" =>
      println(s"$id sends $count to the store")
      store ! s"$id => $count"
      count += 1
      if (count < 5)
        self ! "trigger"
      else self ! PoisonPill
  }

}
