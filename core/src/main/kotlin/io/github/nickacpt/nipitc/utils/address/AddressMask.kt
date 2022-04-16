package io.github.nickacpt.nipitc.utils.address

import io.github.nickacpt.nipitc.utils.maskAddressFromMostSignificantBitCount

data class AddressMask(
    val mostSignificantBits: Int,
) {
    init {
        if (mostSignificantBits !in 0..32) throw IllegalArgumentException("bitCount must be >= 0 and <= 32")
    }

    val maskAddress = maskAddressFromMostSignificantBitCount(mostSignificantBits)

    fun apply(address: Address): Address {
        return address and maskAddress
    }

    override fun toString(): String = "/$mostSignificantBits"

    companion object {
        fun fromString(str: String): AddressMask {
            if (str.first() != '/') throw IllegalArgumentException("Invalid address mask string: $str")
            return AddressMask(str.drop(1).toInt())
        }
    }
}
