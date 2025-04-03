package br.com.zup.controllers

import br.com.zup.dto.UserDTO
import br.com.zup.services.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping(value = ["/users"])
class UserController(
    val userService: UserService
) {

    @GetMapping(value = ["/{cpf}"])
    fun getUser(@PathVariable cpf: String): ResponseEntity<UserDTO> {
        return ResponseEntity.ok().body(userService.getUser(cpf))
    }

    @PostMapping
    fun addUser(@RequestBody user: @Valid UserDTO): ResponseEntity<UserDTO?> {
        userService.insert(user)
        val uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cpf}").buildAndExpand(user.cpf).toUri()
        return ResponseEntity.created(uri).body(user)
    }
}
