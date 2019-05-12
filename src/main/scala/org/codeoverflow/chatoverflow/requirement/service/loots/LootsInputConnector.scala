package org.codeoverflow.chatoverflow.requirement.service.loots

import org.codeoverflow.chatoverflow.WithLogger
import org.codeoverflow.chatoverflow.connector.Connector
import org.pircbotx.hooks.events.MessageEvent

class LootsInputConnector(override val sourceIdentifier: String) extends Connector(sourceIdentifier) with WithLogger {
  private val lootsListener = new LootsListener
  override protected var requiredCredentialKeys: List[String] = List(loginName, loginPassword)
  override protected var optionalCredentialKeys: List[String] = List()


  def addLootEventListener(listener: MessageEvent => Unit): Unit = {
    lootsListener.addMessageEventListener(listener)
  }

  //todo make loots

  /**
    * Starts the connector, e.g. creates a connection with its platform.
    */
  override def start(): Boolean = ???

  /**
    * This stops the activity of the connector, e.g. by closing the platform connection.
    */
  override def stop(): Boolean = ???
}
