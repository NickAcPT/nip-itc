package io.github.nickacpt.nipitc.spec.devices.outputs

import io.github.nickacpt.nipitc.spec.devices.misc.VirtualPcDevice

class VirtualPcDeviceOutputs(override val device: VirtualPcDevice) : DeviceOutputs<VirtualPcDevice> {

    val config by lazy { gatherOutputs().joinToString(System.lineSeparator()) }

    override fun gatherOutputs(): Sequence<String> = sequence {
        with(device) {
            if (ip != null) {

            }
        }


    }

}