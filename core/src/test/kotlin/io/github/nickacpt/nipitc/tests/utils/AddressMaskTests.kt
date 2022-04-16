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

    @ParameterizedTest(name = "Address \"{1}\" with mask \"{0}\" applied should be \"{2}\"")
    @MethodSource("addressAndMaskApplyProvider")
    fun `Address Mask should be correct when applied`(mask: AddressMask, address: Address, expected: Address) {
        val actual = mask.apply(address)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun addressAndMaskApplyProvider() =
            arrayOf(
                24 to "172.16.4.1|172.16.4.0",
                16 to "192.0.0.1|192.0.0.0",

                20 to "172.16.132.70|172.16.128.0"
            ).map { (bitCount, addresses) ->
                val (address, mask) = addresses.split("|")
                Arguments.of(AddressMask(bitCount), Address.fromString(address), Address.fromString(mask))
            }

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