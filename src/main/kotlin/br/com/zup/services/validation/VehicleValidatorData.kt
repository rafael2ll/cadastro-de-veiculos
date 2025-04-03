package br.com.zup.services.validation

import br.com.zup.controllers.exceptions.FieldMessage
import br.com.zup.dto.VehicleDTO
import br.com.zup.services.feign.FIPEClient
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class VehicleValidatorData(
    val fipe: FIPEClient
) : ConstraintValidator<VehicleValidData, VehicleDTO> {

    override fun isValid(dto: VehicleDTO, context: ConstraintValidatorContext): Boolean {
        val list: MutableList<FieldMessage> = ArrayList()
        if (fipe.getFipeBrand(dto.brand).status() == 404) {
            list.add(FieldMessage("brand", "Marca incompatível com a base da FIPE"))
        } else {
            if (fipe.getFipeModel(dto.brand, dto.model).status() == 404) {
                list.add(FieldMessage("model", "Modelo incompatível com a base da FIPE"))
            } else {
                if (fipe.getFipeYearAndFuel(dto.brand, dto.model, dto.yearAndFuel).status() == 404) {
                    list.add(FieldMessage("yearAndFuel", "Ano-combustível incompatível com a base da FIPE"))
                }
            }
        }
        for (f in list) {
            context.disableDefaultConstraintViolation()
            context.buildConstraintViolationWithTemplate(f.message).addPropertyNode(f.name).addConstraintViolation()
        }
        return list.isEmpty()
    }
}
