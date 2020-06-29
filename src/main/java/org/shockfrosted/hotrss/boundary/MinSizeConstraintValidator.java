package org.shockfrosted.hotrss.boundary;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MinSizeConstraintValidator implements ConstraintValidator<MinSizeConstraint, List<String>> {
    @Override
    public boolean isValid(List<String> values, ConstraintValidatorContext context) {
        return values.size() >= 2;
    }
}