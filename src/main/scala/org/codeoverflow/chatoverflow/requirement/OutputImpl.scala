package org.codeoverflow.chatoverflow.requirement

import org.codeoverflow.chatoverflow.WithLogger
import org.codeoverflow.chatoverflow.api.io.output.Output
import org.codeoverflow.chatoverflow.connector.Connector

import scala.collection.mutable

abstract class OutputImpl[C <: Connector] extends Connection[C] with Output with WithLogger {


  protected val content: mutable.Map[String, String] = mutable.Map[String, String]()
  protected var requiredContent: List[String]
  protected var optionalContent: List[String]


  /**
    * Inits this connection, checks if teh source connector is defined, and can be inited, then calls start
    *
    * @return if this input could be successfully inited
    */
  override def init(): Boolean = {
    if (sourceConnector.isDefined) {
      if (sourceConnector.get.init()) {
        start()
      } else false
    } else {
      logger warn "Source connector not set."
      false
    }
  }


  /**
    * Start the input, called after source connector did init
    *
    * @return true if starting the input was successful, false if some problems occurred
    */
  def start(): Boolean


  /**
    * Serializes this object into a string to save it to a config
    *
    * @return serialized
    */
  override def serialize(): String = {
    var s = s"<sourceIdentifier>$getSourceIdentifier</sourceIdentifier>"
    for ((key, value) <- content) {
      s += s"<$key>$value</$key>"
    }
    s
  }

  /**
    * Deserialize a string to apply provided config settings to this object
    *
    * @param value should be serialized
    */
  override def deserialize(value: String): Unit = {
    val elem = xml.XML.loadString(value)
    elem.foreach(node => content += (node.label -> node.text))
    content.get("sourceIdentifier") match {
      case Some(sourceIdentifier) => setSourceConnector(sourceIdentifier)
      case None => new IllegalArgumentException("sourceIdentifier is not set")
    }
    val missing = requiredContent.filter(content.get(_).isEmpty)
    if (missing.nonEmpty) {
      logger warn s"No all required content is set: ${missing.mkString(", ")}"
      throw new IllegalArgumentException("Missing required content")
    }
    if (optionalContent.exists(content.get(_).isEmpty)) {
      logger info "Not all optional content is set"
    }
  }
}
