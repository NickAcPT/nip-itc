package io.github.nickacpt.nipitc.tests

import io.github.nickacpt.nipitc.spec.network.Network
import io.github.nickacpt.nipitc.utils.address.Address
import io.github.nickacpt.nipitc.utils.address.AddressMask
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NetworkTests {

    @ParameterizedTest(name = "Network \"{0}\" should contain address \"{1}\"")
    @MethodSource("networkAddressContainerProvider")
    fun `Network should contain address`(network: Network, address: Address) {
        assertTrue(network.contains(address), "Network does not contain address")
    }

    @ParameterizedTest(name = "Network \"{0}\" should not contain address \"{1}\"")
    @MethodSource("negativeNetworkAddressContainerProvider")
    fun `Network should not contain address`(network: Network, address: Address) {
        assertFalse(network.contains(address), "Network does not contain address")
    }

    @ParameterizedTest(name = "Network \"{0}\" should have broadcast address \"{1}\"")
    @MethodSource("networkAddressBroadcastProvider")
    fun `Network broadcast address should be correct`(network: Network, expectedBroadcast: Address) {
        assertEquals(expectedBroadcast, network.broadcastAddress)
    }

    @ParameterizedTest(name = "Network with mask \"{0}\" should have \"{1}\" hosts")
    @MethodSource("networkHostsCountProvider")
    fun `Network hosts count should be correct`(addressMask: AddressMask, expectedHostsCount: Int) {
        val networkAddr = Address.fromString("127.0.0.0")
        val network = Network(networkAddr, addressMask)

        assertEquals(expectedHostsCount, network.hostsPerSubnetCount)
    }

    companion object {
        @JvmStatic
        fun networkHostsCountProvider() =
            arrayOf(
                25 to 126,
                26 to 62,
                27 to 30,
                28 to 14,
                29 to 6,
                30 to 2
            ).map { (bits, hostsCount) ->
                Arguments.of(AddressMask(bits), hostsCount)
            }


        @JvmStatic
        fun networkAddressBroadcastProvider() =
            arrayOf(
                "192.168.1.0/25" to "192.168.1.127",
                "192.168.1.128/25" to "192.168.1.255",

                "192.168.1.0/26" to "192.168.1.63",
                "192.168.1.64/26" to "192.168.1.127",
                "192.168.1.128/26" to "192.168.1.191",
                "192.168.1.192/26" to "192.168.1.255",

                "192.168.1.0/27" to "192.168.1.31",
                "192.168.1.32/27" to "192.168.1.63",
                "192.168.1.64/27" to "192.168.1.95",
                "192.168.1.96/27" to "192.168.1.127",
                "192.168.1.128/27" to "192.168.1.159",
                "192.168.1.160/27" to "192.168.1.191",
                "192.168.1.192/27" to "192.168.1.223",
                "192.168.1.224/27" to "192.168.1.255",
            ).map { (network, broadcast) ->
                Arguments.of(Network.fromString(network), Address.fromString(broadcast))
            }

        @JvmStatic
        fun networkAddressContainerProvider() =
            createNetworkArgumentsFromList(
                arrayOf(
                    "192.168.1.0/24" to listOf("192.168.1.100", "192.168.1.255"),
                    "192.168.1.0/25" to listOf("192.168.1.1", "192.168.1.126", "192.168.1.127"),
                    "192.168.1.128/25" to listOf("192.168.1.129", "192.168.1.254", "192.168.1.255"),
                )
            )

        @JvmStatic
        fun negativeNetworkAddressContainerProvider() =
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