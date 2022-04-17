package io.github.nickacpt.nipitc.dsl.devices.impl

import io.github.nickacpt.nipitc.dsl.devices.DeviceBuilder
import io.github.nickacpt.nipitc.spec.devices.misc.VirtualPCSimulatorDevice
import io.github.nickacpt.nipitc.spec.network.IpWithMask
import io.github.nickacpt.nipitc.utils.address.Address
import io.github.nickacpt.nipitc.utils.address.AddressMask

data class VirtualPCSimulatorDeviceBuilder(
    val name: String,
    var ip: String? = null,
    var gateway: String? = null

) : DeviceBuilder<VirtualPCSimulatorDevice> {
    override fun build(): VirtualPCSimulatorDevice {
        return VirtualPCSimulatorDevice(name).apply {
            ip = this@VirtualPCSimulatorDeviceBuilder.ip?.let { IpWithMask.fromString(it, AddressMask(24)) }
            gateway = this@VirtualPCSimulatorDeviceBuilder.gateway?.let { Address.fromString(it) }
        }
    }
}
