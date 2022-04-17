package io.github.nickacpt.nipitc.spec.devices

import java.util.*

abstract class AbstractDevice(open val name: String) {
    val id = UUID.randomUUID()
}