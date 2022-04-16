package io.github.nickacpt.nipitc.tests

import io.github.nickacpt.nipitc.spec.network.Network
import io.github.nickacpt.nipitc.utils.address.Address
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NetworkTests {

    @ParameterizedTest(name = "Network \"{0}\" should contain address \"{1}\"")
    @MethodSource("networkAddressProvider")
    fun `Network should contain address`(network: Network, address: Address) {
        assertTrue(network.contains(address), "Network does not contain address")
    }

    @ParameterizedTest(name = "Network \"{0}\" should not contain address \"{1}\"")
    @MethodSource("negativeNetworkAddressProvider")
    fun `Network should not contain address`(network: Network, address: Address) {
        assertFalse(network.contains(address), "Network does not contain address")
    }

    companion object {
        @JvmStatic
        fun networkAddressProvider() =
            createNetworkArgumentsFromList(
                arrayOf(
                    "192.168.1.0/24" to listOf("192.168.1.100", "192.168.1.255"),
                    "192.168.1.0/25" to listOf("192.168.1.1", "192.168.1.126", "192.168.1.127"),
                    "192.168.1.128/25" to listOf("192.168.1.129", "192.168.1.254", "192.168.1.255"),
                )
            )

        @JvmStatic
        fun negativeNetworkAddressProvider() =
            createNetworkArgumentsFromList(
                arrayOf(
                    "192.168.1.0/24" to listOf("192.168.2.1", "192.168.0.255"),
                    "192.168.1.0/25" to listOf("192.168.1.128", "192.168.1.129", "192.168.1.255"),
                    "192.168.1.128/25" to listOf("192.168.1.1", "192.168.1.2", "192.168.1.127"),
                )
            )

        private fun createNetworkArgumentsFromList(addressPairs: Array<Pair<String, List<String>>>) =
            addressPairs.flatMap { (address, ips) ->
                ips.map { ip ->
                    Arguments.of(
                        Network.fromString(address),
                        Address.fromString(ip)
                    )
                }
            }
    }
}