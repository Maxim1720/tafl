package kz.trankwilizator.tafl.entity.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;
import java.util.stream.Collectors;

public class EntityPropertyValidator<E> {

    private final Validator validator;

    public EntityPropertyValidator(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public boolean validate(E entity, String property){
        return violations(entity, property).stream().anyMatch(c -> c.getPropertyPath().toString().equals(property));
    }

    public Set<ConstraintViolation<E>> violations(E e, String property){
        Set<ConstraintViolation<E>> constraintViolations = violations(e);
        return constraintViolations.stream().filter(c -> c.getPropertyPath().toString().equals(property))
                .collect(Collectors.toSet());
    }

    public Set<ConstraintViolation<E>> violations(E e){
        return validator.validate(e);
    }
}
