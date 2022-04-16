package io.github.nickacpt.nipitc.utils

import io.github.nickacpt.nipitc.utils.address.Address
import java.util.BitSet

const val OCTET_LENGTH = 8
const val ADDRESS_BIT_LENGTH = 32

internal fun UByte.toOctetBinaryRepresentation(): String {
    return toString(2).padStart(8, '0')
}

internal fun String.fromOctetBinaryRepresentation(): UByte {
    return toUByte(2)
}

fun Address.toBinaryRepresentation(separator: String = "."): String {
    return (0 until 32)
        .reversed()
        .map { if (this.bitSet[it]) "1" else "0" }
        .chunked(OCTET_LENGTH)
        .joinToString(separator) { it.joinToString("") }
}

internal fun addressBitSetFromOctets(
    firstOctet: UByte,
    secondOctet: UByte,
    thirdOctet: UByte,
    fourthOctet: UByte
): BitSet {
    return BitSet(ADDRESS_BIT_LENGTH).apply {
        arrayOf(firstOctet, secondOctet, thirdOctet, fourthOctet).joinToString("") { it.toOctetBinaryRepresentation() }
            .mapIndexed { index, c ->
                set(ADDRESS_BIT_LENGTH - index - 1, c == '1')
            }
    }
}

internal fun String.toAddressFromBinaryRepresentation(separator: String = "."): Address {
    val octets =
        if (separator.isNotEmpty()) split(separator) else this.chunked(8)
    val octetsConverted = octets.map { it.fromOctetBinaryRepresentation() }

    if (octetsConverted.size != 4) throw IllegalArgumentException("Invalid binary representation")

    return Address(octetsConverted[0], octetsConverted[1], octetsConverted[2], octetsConverted[3])
}
