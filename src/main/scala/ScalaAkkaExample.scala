import actors.{Printer, Sender, Store}
import akka.actor.{ActorSystem, PoisonPill, Props}

import scala.Utils.Trigger
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Created by jyothi on 30/8/17.
  */
object ScalaAkkaExample extends App {

  val system = ActorSystem()
  val store = system.actorOf(Props(new Store))

  def actorExample(): Unit = {
    val sender1 = system.actorOf(Props(new Sender(store, 1)))
    val sender2 = system.actorOf(Props(new Sender(store, 2)))
    val sender3 = system.actorOf(Props(new Sender(store, 3)))

    sender1 ! "trigger"
    sender2 ! "trigger"
    sender3 ! "trigger"

    system.scheduler.scheduleOnce(2 seconds)({store ! PoisonPill; system.terminate})
  }

  def schedulerExample(): Unit = {

    val printer = system.actorOf(Props(new Printer))

    system.scheduler.schedule(0 seconds, 5 seconds, printer, Trigger("Just Printed"))
    
  }

  //actorExample()

  schedulerExample()

}
