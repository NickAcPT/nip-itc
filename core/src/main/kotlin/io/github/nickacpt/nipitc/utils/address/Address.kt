package io.github.nickacpt.nipitc.utils.address

data class  Address(
    val firstOctet: UByte,
    val secondOctet: UByte,
    val thirdOctet: UByte,
    val fourthOctet: UByte
) {
    val octetArray = arrayOf(firstOctet, secondOctet, thirdOctet, fourthOctet)

    override fun toString(): String {
        return "$firstOctet.$secondOctet.$thirdOctet.$fourthOctet"
    }

    companion object {
        /**
         * Parses a string into an ipv4 address object
         */
        fun fromString(string: String): Address {
            val octets = string.split(".")
            if (octets.size != 4) throw IllegalArgumentException("Invalid address format")
            return Address(
                octets[0].toUByte(),
                octets[1].toUByte(),
                octets[2].toUByte(),
                octets[3].toUByte()
            )
        }
    }
}