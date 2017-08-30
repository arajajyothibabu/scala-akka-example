import actors.{Sender, Store}
import akka.actor.{ActorSystem, PoisonPill, Props}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

/**
  * Created by jyothi on 30/8/17.
  */
object ScalaAkkaExample extends App {

  val system = ActorSystem()
  val store = system.actorOf(Props(new Store))

  val sender1 = system.actorOf(Props(new Sender(store, 1)))
  val sender2 = system.actorOf(Props(new Sender(store, 2)))
  val sender3 = system.actorOf(Props(new Sender(store, 3)))

  sender1 ! "trigger"
  sender2 ! "trigger"
  sender3 ! "trigger"

  system.scheduler.scheduleOnce(2 seconds)({store ! PoisonPill; system.terminate})

}
