package com.slo0ey.townhall

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TownhallBackendApplication

fun main(args: Array<String>) {
	runApplication<TownhallBackendApplication>(*args)
}
