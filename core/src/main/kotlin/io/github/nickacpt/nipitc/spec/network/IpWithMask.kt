package io.github.nickacpt.nipitc.spec.network

import io.github.nickacpt.nipitc.utils.address.Address
import io.github.nickacpt.nipitc.utils.address.AddressMask

open class IpWithMask(
    val address: Address, val mask: AddressMask
) {
    val network by lazy { Network(mask and address, mask) }

    override fun toString() = "$address${mask}"

    companion object {
        fun fromString(address: String, defaultAddressMask: AddressMask? = null): IpWithMask {
            val split = address.split("/")
            val addressMask =
                (if (split.size == 2) AddressMask.fromString(split[1], hasSlashPrefix = false) else defaultAddressMask)
                    ?: throw IllegalArgumentException("Invalid ip with mask: $address")

            val ip = Address.fromString(split[0])

            return IpWithMask(ip, addressMask)
        }
    }
}