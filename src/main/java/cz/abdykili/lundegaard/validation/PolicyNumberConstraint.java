package cz.abdykili.lundegaard.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PolicyNumberValidator.class)
public @interface PolicyNumberConstraint {

    String message() default "Policy number must contain only alphanumeric letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
