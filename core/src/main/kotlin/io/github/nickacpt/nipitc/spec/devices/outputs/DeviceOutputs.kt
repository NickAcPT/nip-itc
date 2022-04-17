package io.github.nickacpt.nipitc.spec.devices.outputs

import io.github.nickacpt.nipitc.spec.devices.AbstractDevice

interface DeviceOutputs<D: AbstractDevice<*>> {
    val device: D

    fun gatherOutputs(): Sequence<String>
}