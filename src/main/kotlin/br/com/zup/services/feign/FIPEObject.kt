package br.com.zup.services.feign

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/* Objeto que recebe os dados JSON da requisição */
@JsonIgnoreProperties(ignoreUnknown = true)
class FIPEObject (
    @JsonProperty("Valor")
    var value: String,

    @JsonProperty("Marca")
    var brand: String,

    @JsonProperty("Modelo")
    var model: String
)
