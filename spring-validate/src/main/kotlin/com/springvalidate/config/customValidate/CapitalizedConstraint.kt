package com.springvalidate.config.customValidate

import org.springframework.util.StringUtils
import java.lang.annotation.Documented
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass


@Documented
@Constraint(validatedBy = arrayOf(CapitalizedValidator::class))
@Target(*[AnnotationTarget.FIELD,AnnotationTarget.FUNCTION])
@Retention(AnnotationRetention.RUNTIME)
annotation class CapitalizedConstraint(
    val message: String = "Chữ đầu tiên phải được in hoa!",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<Any>> = []
)

class CapitalizedValidator : ConstraintValidator<CapitalizedConstraint?, String?> {
//    override fun initialize(constraintAnnotation: CapitalizedConstraint?) {
//        ConstraintValidator.su.initialize(constraintAnnotation)
//    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if (!StringUtils.hasLength(value)) return false
        if (!Character.isUpperCase(value!!.get(0))) return false
        return true
    }
}