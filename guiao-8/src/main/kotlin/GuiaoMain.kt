import io.github.nickacpt.nipitc.dsl.devices
import io.github.nickacpt.nipitc.dsl.project

fun main() {
    project("Guião 8") {
        devices {
            val pc1 = vpcs("PC1") {
                ip = "192.168.1.2"
                gateway = "192.168.1.1"
            }
            println(pc1)
            println(pc1.ip)
            println(pc1.gateway)
            println(pc1.ip?.network)
        }
    }
}