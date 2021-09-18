package cz.abdykili.lundegaard.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContactNameValidator.class)
public @interface ContactNameConstraint {

    String message() default "Name contains numbers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
