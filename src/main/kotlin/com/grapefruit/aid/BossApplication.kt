package com.grapefruit.aid

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BossApplication

fun main(args: Array<String>) {
    runApplication<BossApplication>(*args)
}
