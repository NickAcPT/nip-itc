package io.github.nickacpt.nipitc.dsl

import io.github.nickacpt.nipitc.dsl.devices.impl.VirtualPCSimulatorDeviceBuilder
import io.github.nickacpt.nipitc.spec.NetworkingProject
import io.github.nickacpt.nipitc.spec.devices.misc.VirtualPCSimulatorDevice

@ProjectDslMarker
class NetworkingProjectDevicesBuilder(private val project: NetworkingProject) {

    fun vpcs(name: String, init: (VirtualPCSimulatorDeviceBuilder.() -> Unit) = {}): VirtualPCSimulatorDevice {
        val vpcs = VirtualPCSimulatorDeviceBuilder(name)
        vpcs.init()
        with(vpcs.build()) {
            project.devices.add(this)
            return this
        }
    }
}