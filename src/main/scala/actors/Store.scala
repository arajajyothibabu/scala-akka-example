package actors

import akka.actor.Actor

/**
  * Created by jyothi on 30/8/17.
  */
class Store extends Actor {

  var list = List.empty[String]

  override def receive: Receive = {
    case msg: String =>
      list = msg :: list
  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    println(list.reverse)
  }

}
