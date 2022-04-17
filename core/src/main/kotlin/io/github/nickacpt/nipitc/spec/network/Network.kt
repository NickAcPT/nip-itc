package io.github.nickacpt.nipitc.spec.network

import io.github.nickacpt.nipitc.utils.address.Address
import io.github.nickacpt.nipitc.utils.address.AddressMask
import kotlin.math.pow

data class Network constructor(
    val address: Address, val mask: AddressMask
) {

    init {
        // Check if the network address is valid
        val expectedAddress = mask and address
        if (expectedAddress != address) {
            throw IllegalArgumentException("Network address is not a valid network address. Expected \"$expectedAddress\" but got \"$address\"")
        }
    }

    val hostAddressMask = mask.invertedAddress
    val broadcastAddress = (address or hostAddressMask)

    val hostsPerSubnetCount = ((2.toDouble().pow(hostAddressMask.bitSet.cardinality())) - 2).toInt()
    fun contains(address: Address): Boolean {
        return mask and address == this.address
    }

    override fun toString() = "$address${mask}"

    companion object {

        fun fromString(string: String): Network {
            val split = string.split("/")
            if (split.size != 2) throw IllegalArgumentException("Invalid network string: $string")

            val address = Address.fromString(split[0])
            val mask = AddressMask.fromString(split[1], hasSlashPrefix = false)

            return Network(address, mask)
        }
    }
}
