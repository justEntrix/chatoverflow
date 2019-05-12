package org.codeoverflow.chatoverflow.requirement.service.loots

import org.codeoverflow.chatoverflow.WithLogger
import org.codeoverflow.chatoverflow.connector.Connector

class LootsInputConnector(override val sourceIdentifier: String) extends Connector(sourceIdentifier) with WithLogger {
  override protected var requiredCredentialKeys: List[String] = _
  override protected var optionalCredentialKeys: List[String] = _

  /**
    * Starts the connector, e.g. creates a connection with its platform.
    */
  override def start(): Boolean = ???

  /**
    * This stops the activity of the connector, e.g. by closing the platform connection.
    */
  override def stop(): Boolean = ???
}
