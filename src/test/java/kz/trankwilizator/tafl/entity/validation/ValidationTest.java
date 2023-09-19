package kz.trankwilizator.tafl.entity.validation;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;


public abstract class ValidationTest<E> {
    @Getter
    private E entity;
    private final EntityPropertyValidator<E> entityPropertyValidator;
    public ValidationTest(){
        entityPropertyValidator = new EntityPropertyValidator<>();
        entity = createInstance();
    }
    public void setUpEntity(){
        this.entity = createInstance();
    }
    protected abstract E createInstance();

    public void whenValidate_thenExistsConstraintViolation(String propertyName, boolean exists){
        Assertions.assertEquals(exists, entityPropertyValidator.validate(getEntity(),propertyName));
    }

    public boolean hasConstraintViolations(E e, String propertyName){
        return entityPropertyValidator.validate(e,propertyName);
    }
}
