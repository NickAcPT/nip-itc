package io.github.nickacpt.nipitc.spec.devices.misc

import io.github.nickacpt.nipitc.spec.devices.AbstractDevice
import io.github.nickacpt.nipitc.spec.devices.outputs.VirtualPcDeviceOutputs
import io.github.nickacpt.nipitc.spec.network.IpWithMask
import io.github.nickacpt.nipitc.utils.address.Address

class VirtualPcDevice(name: String) : AbstractDevice(name) {
    var ip: IpWithMask? = null
    var gateway: Address? = null

}