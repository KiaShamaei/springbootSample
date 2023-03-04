package com.example.springbootsample.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD , ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomValidtorDes.class)
public @interface CustomeAnnotation {
    String value() default "off";
    String message() default "code with off";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
