package io.github.nickacpt.nipitc.utils

import io.github.nickacpt.nipitc.utils.address.Address

fun maskAddressFromMostSignificantBitCount(count: Int): Address {
    "1".repeat(count).padEnd(32, '0').let {
        return it.toAddressFromBinaryRepresentation("")
    }
}