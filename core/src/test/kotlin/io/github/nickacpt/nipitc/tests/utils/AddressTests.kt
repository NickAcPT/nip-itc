package io.github.nickacpt.nipitc.tests.utils

import io.github.nickacpt.nipitc.utils.address.Address
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class AddressTests {

    @ParameterizedTest(name = "toString of \"{0}\" should be \"{1}\"")
    @MethodSource("addressInputProvider")
    fun `Address input is the same as address output`(address: Address, expected: String) {
        assertEquals(expected, address.toString())
    }

    companion object {
        @JvmStatic
        fun addressInputProvider() = listOf(
            "192.168.50.100", "127.50.100.20", "255.255.255.0", "172.16.4.1"
        ).map { Arguments.of(Address.fromString(it), it) }
    }
}