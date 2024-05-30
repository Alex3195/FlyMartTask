package org.alexkings.productdeliveryapi.annotaions;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.alexkings.productdeliveryapi.annotaions.impl.ValidStringValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidStringValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidString {
    String message() default "Invalid string: must contain only letters and cannot have three identical letters in a row";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
