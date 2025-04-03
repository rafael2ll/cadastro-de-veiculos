package br.com.zup.controllers.exceptions

import java.io.Serializable

class FieldMessage : Serializable {
    @JvmField
    var name: String? = null
    @JvmField
    var message: String? = null

    constructor()
    constructor(name: String?, message: String?) {
        this.name = name
        this.message = message
    }
}
