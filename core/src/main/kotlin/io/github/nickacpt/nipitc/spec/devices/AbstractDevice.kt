package io.github.nickacpt.nipitc.spec.devices

import io.github.nickacpt.nipitc.spec.devices.outputs.DeviceOutputs
import java.util.*

abstract class AbstractDevice<O: DeviceOutputs>(val name: String) {
    val id = UUID.randomUUID()
    abstract val outputs: O
}