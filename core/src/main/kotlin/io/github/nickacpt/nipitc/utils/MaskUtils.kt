package io.github.nickacpt.nipitc.utils

import io.github.nickacpt.nipitc.utils.address.Address
import java.util.*

fun maskAddressFromMostSignificantBitCount(count: Int): Address {
    return Address(BitSet(ADDRESS_BIT_LENGTH).apply {
        (0 until count).forEach { set(ADDRESS_BIT_LENGTH - it - 1) }
    })
}

fun Address.invert(): Address {
    val newBitSet = bitSet.clone() as BitSet
    newBitSet.flip(0, ADDRESS_BIT_LENGTH)

    return Address(newBitSet)
}