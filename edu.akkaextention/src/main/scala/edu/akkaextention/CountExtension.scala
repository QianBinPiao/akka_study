package edu.akkaextention

import akka.actor.{ActorSystem, ExtendedActorSystem, ExtensionIdProvider, ExtensionId}


object CountExtension
  extends ExtensionId[CountExtensionImpl]
  with ExtensionIdProvider {

  //The lookup method is required by ExtensionIdProvider,
  // so we return ourselves here, this allows us
  // to configure our extension to be loaded when
  // the ActorSystem starts up
  override def lookup = CountExtension

  //This method will be called by Akka
  // to instantiate our Extension
  override def createExtension(system: ExtendedActorSystem) = new CountExtensionImpl

  /**
    * Java API: retrieve the Count extension for the given system.
    */
  override def get(system: ActorSystem): CountExtensionImpl = super.get(system)
}
