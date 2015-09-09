package edu.qianbin

import akka.actor.{Address, Actor, ActorLogging}
import akka.cluster.{MemberStatus, Cluster}
import akka.cluster.ClusterEvent.{MemberEvent, MemberRemoved, MemberUp, CurrentClusterState}


class MemberListener extends Actor with ActorLogging{

  val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberEvent])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  var nodes = Set.empty[Address]

  def receive = {
    case state: CurrentClusterState =>
      nodes = state.members.collect {
        case m if m.status == MemberStatus.Up => m.address
      }
    case MemberUp(member) =>
      nodes += member.address
      log.info("Member is Up: {}. {} nodes in cluster", member.address, nodes.size)
    case MemberRemoved(member, _) =>
      nodes -= member.address
      log.info("Member is Removed: {}. {} nodes cluster", member.address, nodes.size)
    case _: MemberEvent =>
  }
}
