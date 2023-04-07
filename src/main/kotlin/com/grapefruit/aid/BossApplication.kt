package com.grapefruit.aid

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class BossApplication

fun main(args: Array<String>) {
    runApplication<BossApplication>(*args)
}
