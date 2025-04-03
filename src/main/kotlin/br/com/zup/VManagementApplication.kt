package br.com.zup

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import java.time.ZoneOffset
import java.util.*

@SpringBootApplication
@EnableFeignClients
@ComponentScan("br.com.zup")
@ImportAutoConfiguration(FeignAutoConfiguration::class)
abstract class VManagementApplication {
    @PostConstruct
    fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC))
    }
}
fun main(args: Array<String>) {
    runApplication<VManagementApplication>(*args)
}