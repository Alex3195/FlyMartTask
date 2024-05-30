package org.alexkings.productdeliveryapi.annotaions.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.alexkings.productdeliveryapi.annotaions.ValidString;

import java.util.regex.Pattern;

public class ValidStringValidator implements ConstraintValidator<ValidString, String> {

    // Regular expression to check if the string contains only letters
    private static final Pattern ONLY_LETTERS_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    // Regular expression to check for three consecutive identical letters
    private static final Pattern THREE_IDENTICAL_LETTERS_PATTERN = Pattern.compile("(.)\\1\\1");

    @Override
    public void initialize(ValidString constraintAnnotation) {
        // Initialization code if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Check if the string contains only letters
        if (!ONLY_LETTERS_PATTERN.matcher(value).matches()) {
            return false;
        }

        // Check for three consecutive identical letters
        return !THREE_IDENTICAL_LETTERS_PATTERN.matcher(value).find();
    }
}
