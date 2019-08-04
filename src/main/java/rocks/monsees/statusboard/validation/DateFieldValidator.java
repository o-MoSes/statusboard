package rocks.monsees.statusboard.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import rocks.monsees.statusboard.model.Status;

public class DateFieldValidator implements ConstraintValidator<DateFieldConstraint, Status> {

	@Override
	public void initialize(DateFieldConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Status status, ConstraintValidatorContext context) {
		boolean isValid = status.getBegin().isBefore(status.getEnd());

		if (!isValid) { //bin error message to property begin instead of status entity itself
			context.disableDefaultConstraintViolation(); 
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode("begin")
					.addConstraintViolation();
		}
		return isValid;
	}

}
