package org.shockfrosted.hotrss.boundary;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = MinSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MinSizeConstraint {
    String message() default "The input list cannot contain more than 4 movies.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}