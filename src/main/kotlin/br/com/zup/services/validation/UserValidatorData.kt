package br.com.zup.services.validation

import br.com.zup.controllers.exceptions.FieldMessage
import br.com.zup.dto.UserDTO
import br.com.zup.repositories.UserRepository
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.beans.factory.annotation.Autowired

class UserValidatorData : ConstraintValidator<UserValidData?, UserDTO> {
    @Autowired
    private val repository: UserRepository? = null
    override fun isValid(dto: UserDTO, context: ConstraintValidatorContext): Boolean {
        val list: MutableList<FieldMessage> = ArrayList()
        if (repository!!.findByEmail(dto.email) != null) {
            list.add(FieldMessage("email", "Email já existe no banco de dados"))
        }
        if (repository.findById(dto.cpf).isPresent) {
            list.add(FieldMessage("cpf", "CPF já existe no banco de dados"))
        }
        for (f in list) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate(f.message).addPropertyNode(f.name).addConstraintViolation()
        }
        return list.isEmpty()
    }
}
