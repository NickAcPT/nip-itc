package io.github.nickacpt.nipitc.tests.utils

import io.github.nickacpt.nipitc.utils.address.Address
import io.github.nickacpt.nipitc.utils.fromOctetBinaryRepresentation
import io.github.nickacpt.nipitc.utils.toAddressFromBinaryRepresentation
import io.github.nickacpt.nipitc.utils.toBinaryRepresentation
import io.github.nickacpt.nipitc.utils.toOctetBinaryRepresentation
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class BinaryConversionUtilsTests {

    @ParameterizedTest(name = "Binary representation of {0} should be {1}")
    @MethodSource("binaryRepresentationProvider")
    fun `Binary representation is correct`(input: Int, expected: String) {
        val actual = input.toUByte().toOctetBinaryRepresentation()
        assertEquals(expected, actual, "Failed for $input")
    }

    @ParameterizedTest(name = "Binary representation from {0} should be {1}")
    @MethodSource("binaryRepresentationProvider")
    fun `Binary representation from String is correct`(expected: Int, input: String) {
        val actual = input.fromOctetBinaryRepresentation()
        assertEquals(expected.toUByte(), actual, "Failed for $input")
    }

    @ParameterizedTest(name = "Address {0} converted to binary representation should be \"{1}\"")
    @MethodSource("addressProvider")
    fun `Address conversion to binary representation is correct`(input: Address, expected: String) {
        val actual = input.toBinaryRepresentation(".")
        assertEquals(expected, actual, "Failed for $input")
    }

    @ParameterizedTest(name = "Address \"{1}\" converted from binary representation should be {0}")
    @MethodSource("addressProvider")
    fun `Address conversion from binary representation is correct`(expected: Address, input: String) {
        val actual = input.toAddressFromBinaryRepresentation(".")
        assertEquals(expected, actual, "Failed for $input")
    }

    companion object {
        @JvmStatic
        fun addressProvider() = arrayOf(
            "172.16.4.1" to "10101100.00010000.00000100.00000001",
            "255.255.255.0" to "11111111.11111111.11111111.00000000",
            "192.0.0.1" to "11000000.00000000.00000000.00000001",
            "192.168.1.0" to "11000000.10101000.00000001.00000000",
        ).map { Arguments.of(Address.fromString(it.first), it.second) }


        @JvmStatic
        fun binaryRepresentationProvider(): List<Arguments> = arrayOf(
            172 to "10101100",
            16 to "00010000",
            4 to "00000100",
            1 to "00000001",

            255 to "11111111",
            0 to "00000000",

            192 to "11000000",

            172 to "10101100"
        ).map {
            Arguments.of(it.first, it.second)
        }.toList()
    }

}