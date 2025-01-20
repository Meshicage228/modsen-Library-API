package by.meshicage.annotation;

import by.meshicage.validators.ISBNValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = ISBNValidator.class)
public @interface UniqueISBN {
    String message() default "ISBN for that book is not unique";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
