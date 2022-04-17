package io.github.nickacpt.nipitc.dsl.devices

import io.github.nickacpt.nipitc.spec.devices.AbstractDevice

interface DeviceBuilder<D : AbstractDevice> {
    fun build(): D
}