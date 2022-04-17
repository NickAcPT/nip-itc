package io.github.nickacpt.nipitc.utils.address

import io.github.nickacpt.nipitc.utils.invert
import io.github.nickacpt.nipitc.utils.maskAddressFromMostSignificantBitCount

data class AddressMask(
    val mostSignificantBits: Int,
) {
    init {
        if (mostSignificantBits !in 0..32) throw IllegalArgumentException("bitCount must be >= 0 and <= 32")
    }

    val maskAddress = maskAddressFromMostSignificantBitCount(mostSignificantBits)
    val invertedAddress = maskAddress.invert()

    infix fun and(address: Address): Address {
        return address and maskAddress
    }

    override fun toString(): String = "/$mostSignificantBits"

    companion object {

        fun fromAddress(address: Address): AddressMask {
            return AddressMask(address.bitSet.cardinality())
        }

        fun fromString(str: String, hasSlashPrefix: Boolean = true): AddressMask {
            if (hasSlashPrefix && str.first() != '/') throw IllegalArgumentException("Invalid address mask string: $str")

            return AddressMask((str.drop(if (hasSlashPrefix) 1 else 0)).toInt())
        }
    }
}
