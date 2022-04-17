package io.github.nickacpt.nipitc.dsl.devices.impl

import io.github.nickacpt.nipitc.dsl.devices.DeviceBuilder
import io.github.nickacpt.nipitc.spec.devices.misc.VirtualPcDevice
import io.github.nickacpt.nipitc.spec.network.IpWithMask
import io.github.nickacpt.nipitc.spec.network.Network
import io.github.nickacpt.nipitc.utils.address.Address
import io.github.nickacpt.nipitc.utils.address.AddressMask

data class VirtualPcDeviceBuilder(
    val name: String,
    var ip: String? = null,
    var gateway: String? = null

) : DeviceBuilder<VirtualPcDevice> {
    override fun build(): VirtualPcDevice {
        return VirtualPcDevice(name).apply {
            ip = this@VirtualPcDeviceBuilder.ip?.let { IpWithMask.fromString(it, AddressMask(24)) }
            gateway = this@VirtualPcDeviceBuilder.gateway?.let { Address.fromString(it) }
        }
    }
}
