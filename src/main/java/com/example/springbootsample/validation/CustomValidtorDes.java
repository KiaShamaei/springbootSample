package com.example.springbootsample.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomValidtorDes implements ConstraintValidator<CustomeAnnotation , String> {
    String desPrefix;
    @Override
    public void initialize(CustomeAnnotation constraintAnnotation) {
        this.desPrefix = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        var result = s.startsWith(desPrefix);
        return result;
    }
}
