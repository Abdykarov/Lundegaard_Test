package cz.abdykili.lundegaard.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PolicyNumberValidator implements ConstraintValidator<PolicyNumberConstraint, String> {

    /**
     * Validate if string contains only alphanumeric : letters and numbers.
     * @param s - incoming string from dto
     * @param constraintValidatorContext - Validator context
     * @return - boolean data type, return true if contains only alphanumeric values
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("^[a-zA-Z0-9]*$");
    }
}
