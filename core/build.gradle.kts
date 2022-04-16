dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
}

tasks.test {
    useJUnitPlatform()
}