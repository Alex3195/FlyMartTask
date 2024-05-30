package org.alexkings.productdeliveryapi.annotaions.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.alexkings.productdeliveryapi.annotaions.ValidAlphaNumericUnderscore;

import java.util.regex.Pattern;

public class AlphaNumericUnderscoreValidator implements ConstraintValidator<ValidAlphaNumericUnderscore, String> {

    // Regular expression to check if the string contains only letters, numbers, and underscores
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9_]*$");

    @Override
    public void initialize(ValidAlphaNumericUnderscore constraintAnnotation) {
        // Initialization code if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        // Check if the string matches the pattern
        return PATTERN.matcher(value).matches();
    }
}
