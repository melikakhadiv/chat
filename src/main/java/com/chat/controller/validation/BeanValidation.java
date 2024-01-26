package com.chat.controller.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.HashMap;
import java.util.Map;

public class BeanValidation<T> {
    public Map<String, String> validator(T t) {
        ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validationFactory.getValidator();
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<T> validation : validator.validate(t)) {
            errors.put(validation.getPropertyPath().toString(), validation.getMessage());
        }
        return (errors.isEmpty()) ? null : errors;
    }
}
