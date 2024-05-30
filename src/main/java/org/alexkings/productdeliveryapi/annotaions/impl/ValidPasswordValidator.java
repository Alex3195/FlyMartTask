package org.alexkings.productdeliveryapi.annotaions.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.alexkings.productdeliveryapi.annotaions.ValidPassword;

import java.util.regex.Pattern;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {

    // Regular expression to check the password validity
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%\\^&\\*]).{8,}$"
    );

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        // Initialization code if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Check if the password matches the pattern
        return PASSWORD_PATTERN.matcher(value).matches();
    }
}
