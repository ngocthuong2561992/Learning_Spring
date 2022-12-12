package com.validation.configuration.customValidate;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapitalizedValidator implements ConstraintValidator<CapitalizedConstraint, String> {
    @Override
    public void initialize(CapitalizedConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!StringUtils.hasLength(value)) return Boolean.FALSE;
        if(!Character.isUpperCase(value.charAt(0))) return Boolean.FALSE;

        return Boolean.TRUE;
    }
}
