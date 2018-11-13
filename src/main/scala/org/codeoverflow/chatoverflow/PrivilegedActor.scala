package org.codeoverflow.chatoverflow

import akka.actor.Actor

class PrivilegedActor extends Actor {
  override def receive: Receive = {
    case "Do!" =>
      try {
        println("\nTest #4: This should WORK (static code inside a privileged actor, started from the framework)")
        val output = scala.io.Source.fromURL("https://skate702.de")
        println(output != null)
        sender ! output
      } catch {
        case e: Exception => println(s"No rights. Message: ${e.getMessage}")
      }
      // Takes a function and returns it output value (This is very powerful. BUT only visible from the framework)
    case func: (() => Any) =>
      sender ! func()
  }
}
