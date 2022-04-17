package io.github.nickacpt.nipitc.dsl

import io.github.nickacpt.nipitc.spec.NetworkingProject

/*
Model the following DSL:
project("name") {
    devices {
        val pc1 = PC("PC1") {
            ip = "" (parse as Network)
            gateway = "" (parse as Address)
        }
    }
}
 */

@ProjectDslMarker
fun project(name: String, block: NetworkingProject.() -> Unit): NetworkingProject {
    val project = NetworkingProject(name)
    project.block()
    return project
}

@ProjectDslMarker
fun NetworkingProject.devices(block: NetworkingProjectDevicesBuilder.() -> Unit) {
    devicesBuilder.block()
}

