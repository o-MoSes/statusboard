package rocks.monsees.statusboard.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = OneStatusPerPeriodValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OneStatusPerPeriodConstraint {

	String message() default "Sorry, but you already set a status for this period!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
