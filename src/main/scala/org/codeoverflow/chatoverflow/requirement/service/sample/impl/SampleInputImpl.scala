package org.codeoverflow.chatoverflow.requirement.service.sample.impl

import org.codeoverflow.chatoverflow.WithLogger
import org.codeoverflow.chatoverflow.api.io.input.SampleInput
import org.codeoverflow.chatoverflow.registry.Impl
import org.codeoverflow.chatoverflow.requirement.InputImpl
import org.codeoverflow.chatoverflow.requirement.service.sample.SampleConnector

@Impl(impl = classOf[SampleInput], connector = classOf[SampleConnector])
class SampleInputImpl extends InputImpl[SampleConnector] with SampleInput with WithLogger {

  override protected var requiredContent: List[String] = List()
  override protected var optionalContent: List[String] = List()

  override def start(): Boolean = true
}
