package io.github.nickacpt.nipitc.spec.devices

import java.util.*

abstract class AbstractDevice(val name: String) {
    val id = UUID.randomUUID()
}