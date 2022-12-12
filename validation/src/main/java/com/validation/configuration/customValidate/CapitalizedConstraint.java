package com.validation.configuration.customValidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CapitalizedValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CapitalizedConstraint {
    String message() default "in hoa!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
