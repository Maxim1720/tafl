package kz.trankwilizator.tafl.entity.validation;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Set;

@Log
public abstract class ValidationTest<E> {
    @Getter
    private E entity;
    private final EntityPropertyValidator<E> entityPropertyValidator;
    public ValidationTest(){
        entityPropertyValidator = new EntityPropertyValidator<>();
        entity = createInstance();
    }
    @BeforeEach
    public void setUpEntity(){
        this.entity = createInstance();
    }
    protected abstract E createInstance();

    public void whenValidate_thenHasConstraintViolation(String propertyName, boolean exists){
        Set<ConstraintViolation<E>> constraintViolations = entityPropertyValidator.violations(getEntity(),
                propertyName);
        constraintViolations.forEach(c->log.warning(c::toString));
        Assertions.assertEquals(exists, !constraintViolations.isEmpty());
    }

    public void whenValidate_thenHasConstraintViolation(String propertyName, ValidationResult result){
        Set<ConstraintViolation<E>> constraintViolations = entityPropertyValidator.violations(getEntity(),
                propertyName);
        constraintViolations.forEach(c->log.warning(c::toString));
        Assertions.assertEquals(result.getBoolResult(), !constraintViolations.isEmpty());
    }

    public void whenValidate_thenHasNotConstraintViolations(){
        Arrays.stream(getEntity().getClass().getFields())
                .forEach(f-> whenValidate_thenHasConstraintViolation(f.getName(),false));
    }

    public boolean hasConstraintViolations(String propertyName){
        return entityPropertyValidator.validate(getEntity(), propertyName);
    }
}
