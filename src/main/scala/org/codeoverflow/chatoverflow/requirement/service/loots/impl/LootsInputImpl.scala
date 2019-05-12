package org.codeoverflow.chatoverflow.requirement.service.loots.impl

import com.google.gson.{JsonArray, JsonElement, JsonObject, JsonParser}
import java.util

import org.codeoverflow.chatoverflow.api.io.input.event.EventInput
import org.codeoverflow.chatoverflow.registry.Impl
import org.codeoverflow.chatoverflow.api.io.output.Output
import org.codeoverflow.chatoverflow.requirement.Connection
import org.codeoverflow.chatoverflow.requirement.service.loots.LootsInputConnector

import scala.collection.immutable.IndexedSeq.Impl

/**
  * loots output (donation messages) using websocket.
  */
@Impl(impl = classOf[EventInput], connector = classOf[LootsInputConnector])
class LootsInputImpl extends Connection[LootsInputConnector] with EventInput {


    override def init(): Boolean = ???

    override def serialize(): String = ???

    override def deserialize(value: String): Unit = ???

    def getLoots(text: String) = {

        val jsonO = new JsonParser
        val jsonElem: JsonObject = jsonO.parse(text).getAsJsonObject
        val okArray: JsonArray = jsonElem.getAsJsonObject("data").getAsJsonArray("ok")
        val msgInfo = new util.ArrayList[(String, String, Int)]()
        okArray.forEach(elem => {
            //leave out messages without author (standard bot messages)
            if (!elem.getAsJsonObject.get("from").isJsonNull) {
                val from = elem.getAsJsonObject.getAsJsonObject("from").getAsJsonObject("account").get("name").getAsString
                val msg = elem.getAsJsonObject.getAsJsonObject("attachments").get("message").getAsString
                val timestamp = elem.getAsJsonObject.get("modified").getAsInt
                msgInfo.add((msg, from, timestamp))
            }
        })
        msgInfo
    }
}