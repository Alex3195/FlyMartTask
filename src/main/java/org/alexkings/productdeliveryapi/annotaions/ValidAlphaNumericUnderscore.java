package org.alexkings.productdeliveryapi.annotaions;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.alexkings.productdeliveryapi.annotaions.impl.AlphaNumericUnderscoreValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AlphaNumericUnderscoreValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAlphaNumericUnderscore {
    String message() default "Invalid input: must contain only letters, numbers, and underscores";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
