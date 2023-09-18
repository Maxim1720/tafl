package kz.trankwilizator.tafl.entity.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public class EntityPropertyValidator<E> {

    private final Validator validator;

    public EntityPropertyValidator(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public boolean validate(E entity, String property){
        Set<ConstraintViolation<E>> constraintViolations = validator.validate(entity);
        return constraintViolations.stream().anyMatch(c -> c.getPropertyPath().toString().equals(property));
    }
}
