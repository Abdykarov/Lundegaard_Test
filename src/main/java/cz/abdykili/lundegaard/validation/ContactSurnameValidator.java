package cz.abdykili.lundegaard.validation;

import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;

public class ContactSurnameValidator implements ConstraintValidator<ContactSurnameConstraint, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[a-zA-Z]+");
    }
}
