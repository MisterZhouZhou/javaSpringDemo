package com.zw.kotlinjpa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.zw.kotlinjpa"])
class KotlinjpaApplication

fun main(args: Array<String>) {
    runApplication<KotlinjpaApplication>(*args)
}
