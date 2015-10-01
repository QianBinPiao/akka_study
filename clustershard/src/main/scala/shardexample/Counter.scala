package shardexample

import akka.actor.ReceiveTimeout
import akka.persistence.PersistentActor
import scala.concurrent.duration._

case object Increment
case object Decrement
case class Get(counterId: Long)
case class EntryEnvelope(id: Long, payload: Any)

case object Stop
case class CounterChanged(delta: Int)

class Counter extends PersistentActor{
    import akka.contrib.pattern.ShardRegion.Passivate

    context.setReceiveTimeout(120.seconds)

    override def persistenceId: String = self.path.parent.name + "-" + self.path.name

    var count = 0

    def updateState(event: CounterChanged): Unit = count += event.delta

    override def receiveRecover: Receive = {
        case evt: CounterChanged => updateState(evt)
    }

    override def receiveCommand: Receive = {
        case Increment =>
            persist(CounterChanged(+1))(updateState)
        case Decrement =>
            persist(CounterChanged(-1))(updateState)
        case Get(_) => sender() ! count
        case ReceiveTimeout => context.parent ! Passivate(stopMessage = Stop)
        case Stop => context.stop(self)
    }
}
