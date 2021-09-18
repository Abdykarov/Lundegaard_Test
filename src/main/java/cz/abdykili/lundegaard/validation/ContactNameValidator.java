package cz.abdykili.lundegaard.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNameValidator implements ConstraintValidator<ContactNameConstraint, String> {

    /**
     * Validate if string contains only letters, numbers are not allowed
     * @param s - incoming string from dto
     * @param constraintValidatorContext - Validator context
     * @return - boolean data type, return true if doesn't contain numbers
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("[a-zA-Z]+");
    }

}
