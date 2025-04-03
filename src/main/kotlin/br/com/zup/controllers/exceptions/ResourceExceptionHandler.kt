package br.com.zup.controllers.exceptions

import br.com.zup.services.exceptions.DataErrorException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ResourceExceptionHandler {
    @ExceptionHandler(DataErrorException::class)
    fun dataException(e: DataErrorException): ResponseEntity<ErrorMessage> {
        val errors: MutableList<String> = ArrayList()
        errors.add(e.message!!)
        val errorMessage = ErrorMessage()
        errorMessage.errorMessage = "ERRO 404 - Falha ao buscar dados"
        errorMessage.errors = errors
        errorMessage.httpStatus = HttpStatus.NOT_FOUND.toString()
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validException(e: MethodArgumentNotValidException): ResponseEntity<ErrorMessage> {
        val errors: MutableList<String> = ArrayList()
        for (f in e.bindingResult.fieldErrors) {
            errors.add(f.field + ": -> " + f.defaultMessage)
        }
        val errorMessage = ErrorMessage()
        errorMessage.errorMessage = "ERRO 400 - Erro de validação de dados"
        errorMessage.errors = errors
        errorMessage.httpStatus = HttpStatus.valueOf(400).toString()
        return ResponseEntity.status(HttpStatus.valueOf(400)).body(errorMessage)
    }
}
