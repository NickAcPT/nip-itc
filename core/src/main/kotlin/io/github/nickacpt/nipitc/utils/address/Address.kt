package io.github.nickacpt.nipitc.utils.address

import io.github.nickacpt.nipitc.utils.ADDRESS_BIT_LENGTH
import io.github.nickacpt.nipitc.utils.addressBitSetFromOctets
import java.util.BitSet

data class Address(
    val bitSet: BitSet
) {
    constructor(
        firstOctet: UByte,
        secondOctet: UByte,
        thirdOctet: UByte,
        fourthOctet: UByte
    ) : this(addressBitSetFromOctets(firstOctet, secondOctet, thirdOctet, fourthOctet))

    val firstOctet: UByte = getOctet(0, 8)
    val secondOctet: UByte = getOctet(8, 16)
    val thirdOctet: UByte = getOctet(16, 24)
    val fourthOctet: UByte = getOctet(24, 32)

    private fun getOctet(start: Int, end: Int): UByte {
        return bitSet[ADDRESS_BIT_LENGTH - end, ADDRESS_BIT_LENGTH - start].toByteArray().firstOrNull()?.toUByte() ?: 0u
    }

    override fun toString(): String {
        return "$firstOctet.$secondOctet.$thirdOctet.$fourthOctet"
    }

    infix fun and(other: Address): Address {
        val newSet = this.bitSet.clone() as BitSet
        newSet.and(other.bitSet)
        return Address(newSet)
    }

    companion object {
        /**
         * Parses a string into an ipv4 address object
         */
        fun fromString(string: String): Address {
            val octets = string.split(".")
            if (octets.size != 4) throw IllegalArgumentException("Invalid address format \"$string\"")
            return Address(
                octets[0].toUByte(),
                octets[1].toUByte(),
                octets[2].toUByte(),
                octets[3].toUByte()
            )
        }
    }
}