package samples

import akka.actor.{ActorRef, Props, ActorSystem}
import akka.testkit.{ImplicitSender, TestKit}
import com.egn.faulttoleranceexample.{Child, Supervisor}
import com.typesafe.config.ConfigFactory
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
 * Created by ypiao on 8/6/15.
 */
class FaultHandlingDocSpec(_system: ActorSystem)
  extends TestKit(_system)
  with ImplicitSender
  with WordSpecLike
  with Matchers
  with BeforeAndAfterAll {


  def this() = this(ActorSystem(
    "FaultHandlingDocSpec",
    ConfigFactory.parseString(
      """
        |akka {
        | loggers = ["akka.testkit.TestEventListener"]
        | loglevel = "WARNING"
        |}
      """.stripMargin)
  ))

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  "A supervisor" must {

    "apply the chosen strategy for its child" in {
      val supervisor = system.actorOf(Props[Supervisor], "supervisor")

      supervisor ! Props[Child]
      val child = expectMsgType[ActorRef]

      child ! 42 // set state to 42
      child ! "get"
      expectMsg(42)

      child ! new ArithmeticException // crash it
      child ! "get"
      expectMsg(42)
    }
  }


}
