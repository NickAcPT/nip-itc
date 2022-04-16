package io.github.nickacpt.nipitc.tests.utils

import io.github.nickacpt.nipitc.utils.address.Address
import io.github.nickacpt.nipitc.utils.address.AddressMask
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class AddressMaskTests {

    @ParameterizedTest(name = "Mask Address with {0} significant bits should be \"{1}\"")
    @MethodSource("addressMaskProvider")
    fun `Address Mask should be correct`(mostSignificantBits: Int, expected: Address) {
        val actual = AddressMask(mostSignificantBits)
        assertEquals(expected, actual.maskAddress)
    }

    companion object {
        @JvmStatic
        fun addressMaskProvider() =
            arrayOf(
                32 to "255.255.255.255",

                24 to "255.255.255.0",
                16 to "255.255.0.0",

                25 to "255.255.255.128",

                26 to "255.255.255.192",

                27 to "255.255.255.224"
            ).map { (bitCount, address) -> Arguments.of(bitCount, Address.fromString(address)) }
    }
}