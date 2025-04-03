package br.com.zup.controllers.exceptions

class ErrorMessage {
    @JvmField
    var httpStatus: String? = null
    @JvmField
    var errorMessage: String? = null
    var errors: List<String>? = null
}
