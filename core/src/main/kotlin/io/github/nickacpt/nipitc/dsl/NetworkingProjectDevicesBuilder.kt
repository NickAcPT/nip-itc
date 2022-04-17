package io.github.nickacpt.nipitc.dsl

import io.github.nickacpt.nipitc.dsl.devices.impl.VirtualPcDeviceBuilder
import io.github.nickacpt.nipitc.spec.NetworkingProject
import io.github.nickacpt.nipitc.spec.devices.misc.VirtualPcDevice

@ProjectDslMarker
class NetworkingProjectDevicesBuilder(private val project: NetworkingProject) {

    fun vpcs(name: String, init: (VirtualPcDeviceBuilder.() -> Unit) = {}): VirtualPcDevice {
        val vpcs = VirtualPcDeviceBuilder(name)
        vpcs.init()
        with(vpcs.build()) {
            project.devices.add(this)
            return this
        }
    }
}