package io.github.nickacpt.nipitc.spec.devices.misc

import io.github.nickacpt.nipitc.spec.devices.AbstractDevice
import io.github.nickacpt.nipitc.spec.network.IpWithMask
import io.github.nickacpt.nipitc.utils.address.Address

data class VirtualPcDevice(override val name: String) : AbstractDevice(name) {
    var ip: IpWithMask? = null
    var gateway: Address? = null

}