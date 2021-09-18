package cz.abdykili.lundegaard.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContactSurnameValidator.class)
public @interface ContactSurnameConstraint {
    String message() default "Surname contains numbers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
