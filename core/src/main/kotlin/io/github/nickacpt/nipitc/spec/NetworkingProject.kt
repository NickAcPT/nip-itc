package io.github.nickacpt.nipitc.spec

import io.github.nickacpt.nipitc.dsl.NetworkingProjectDevicesBuilder
import io.github.nickacpt.nipitc.spec.devices.AbstractDevice

data class NetworkingProject(
    val name: String,
    val devices: MutableList<AbstractDevice> = mutableListOf(),
) {

    internal val devicesBuilder = NetworkingProjectDevicesBuilder(this)

}
