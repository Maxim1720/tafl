package kz.trankwilizator.tafl.entity.role;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

@Import(value = RoleTestConfig.class)
@ContextConfiguration(classes = RoleTestConfig.class)
public class RoleEntityValidationTest {

    private final Validator validator;
    private Role role;
    private final Role testRole;

    public RoleEntityValidationTest(){
        this.testRole = RoleTestConfig.INSTANCE;
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @BeforeEach
    public void setUp(){
        role = new Role();
        role.setName(testRole.getName());
        role.setPermissions(testRole.getPermissions());
        role.setId(testRole.getId());
    }
    @Test
    public void givenNullName_whenValidate_thenConstraintViolationsNotEmpty(){
        role.setName(null);
        whenValidate_thenConstraintViolationsIsEmpty(false);
    }

    @Test
    public void givenNullPermissions_whenValidate_thenConstraintViolationsNotEmpty(){
        role.setPermissions(null);
        whenValidate_thenConstraintViolationsIsEmpty(false);
    }

    @Test
    public void givenCorrectRole_whenValidate_thenConstraintViolationsIsEmpty(){
        whenValidate_thenConstraintViolationsIsEmpty(true);
    }

    @Test
    public void givenShortRoleName_whenValidate_thenConstraintViolationsNotEmpty(){
        validateName("R", false);
    }

    @Test
    public void givenTooLongRoleName_whenValidate_thenConstraintViolationsNotEmpty(){
        String roleName = "R".repeat(76);
        validateName(roleName, false);
    }

    @Test
    public void givenR_Name_whenValidate_thenConstraintViolationsNotEmpty(){
        validateName("R_", false);
    }

    @Test
    public void givenr_Name_whenValidate_thenConstraintViolationsNotEmpty(){
        validateName("r_",false);
    }

    @Test
    public void givenTEST_PERMANENT_USER_whenValidate_thenConstraintViolationsIsEmpty(){
        validateName("TEST_PERMANENT_USER", true);
    }

    @Test
    public void givenBlankRoleName_whenValidate_thenConstraintViolationsIsNotEmpty(){
        validateName("   \n\t\r", false);
    }

    @Test
    public void givenEmptyRoleName_whenValidate_thenConstraintViolationsIsEmpty(){
        validateName("", false);
    }



    private void validateName(String name, boolean constraintViolationsIsEmpty){
        role.setName(name);
        whenValidate_thenConstraintViolationsIsEmpty(constraintViolationsIsEmpty);
    }

    private void whenValidate_thenConstraintViolationsIsEmpty(boolean isEmpty){
        Set<ConstraintViolation<Role>> constraintViolations = validator.validate(role);
        constraintViolations.forEach(System.out::println);
        Assertions.assertEquals(isEmpty,constraintViolations.isEmpty());
    }
}
