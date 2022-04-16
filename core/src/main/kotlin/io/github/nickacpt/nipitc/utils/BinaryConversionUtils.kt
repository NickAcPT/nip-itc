package io.github.nickacpt.nipitc.utils

import io.github.nickacpt.nipitc.utils.address.Address

internal fun UByte.toOctetBinaryRepresentation(): String {
    return toString(2).padStart(8, '0')
}

internal fun String.fromOctetBinaryRepresentation(): UByte {
    return toUByte(2)
}

internal fun Address.toBinaryRepresentation(separator: String = "."): String {
    return this.octetArray.joinToString(separator) { it.toOctetBinaryRepresentation() }
}

internal fun String.toAddressFromBinaryRepresentation(separator: String = "."): Address {
    val octets =
        if (separator.isNotEmpty()) split(separator) else this.chunked(8)
    val octetsConverted = octets.map { it.fromOctetBinaryRepresentation() }

    if (octetsConverted.size != 4) throw IllegalArgumentException("Invalid binary representation")

    return Address(octetsConverted[0], octetsConverted[1], octetsConverted[2], octetsConverted[3])
}
