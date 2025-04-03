package br.com.zup.services.feign

import feign.Response
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(name = "\${feign.name}", url = "\${feign.url}")
interface FIPEClient {
    @RequestMapping(method = [RequestMethod.GET], value = ["carros/marcas/{brand}/modelos/{model}/anos/{yearAndFuel}"])
    fun getFipeInformations(
        @PathVariable brand: String,
        @PathVariable model: String,
        @PathVariable yearAndFuel: String
    ): String

    @RequestMapping(method = [RequestMethod.GET], value = ["carros/marcas/{brand}/modelos"])
    fun getFipeBrand(@PathVariable brand: String): Response

    @RequestMapping(method = [RequestMethod.GET], value = ["carros/marcas/{brand}/modelos/{model}/anos"])
    fun getFipeModel(@PathVariable brand: String, @PathVariable model: String?): Response

    @RequestMapping(method = [RequestMethod.GET], value = ["carros/marcas/{brand}/modelos/{model}/anos/{yearAndFuel}"])
    fun getFipeYearAndFuel(
        @PathVariable brand: String,
        @PathVariable model: String,
        @PathVariable yearAndFuel: String
    ): Response
}
