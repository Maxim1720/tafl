package kz.trankwilizator.tafl.entity.user.permanent;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Set;

@DataJpaTest
@Import({PermanentUserEntityTestConfig.class})
public class PermanentUserEntityValidationTest {

    private PermanentUser permanentUser;

    private Validator validator ;

    @BeforeEach
    public void setUp(@Autowired PermanentUser permanentUserInstance) {
        this.permanentUser = new PermanentUser(permanentUserInstance.getFirstname(),
                permanentUserInstance.getLastname(),
                permanentUserInstance.getSecondName(),
                permanentUserInstance.getEmail(),
                permanentUserInstance.getPassword(),
                permanentUserInstance.getDiscount(),
                permanentUserInstance.getUpdatedAt(),
                permanentUserInstance.getRole());
        permanentUser.setUsername(permanentUserInstance.getUsername());
        permanentUser.setEnabled(permanentUserInstance.getEnabled());
        permanentUser.setBalance(permanentUserInstance.getBalance());
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void givenNullRole_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setRole(null);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenNullFirstname_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setFirstname(null);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenEmptyFirstname_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setFirstname("");
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenBlankFirstname_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setFirstname("       ");
        whenValidate_thenViolationsSetIsNotEmpty();
    }


    @Test
    public void givenShortPassword_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setPassword("testestes".toCharArray());
        whenValidate_thenViolationsSetIsNotEmpty();
    }


    @Test
    public void givenNullLastname_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setLastname(null);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenEmptyLastname_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setLastname("");
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenBlankLastname_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setLastname("  \n\n\n  ");
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenNullUsername_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setUsername(null);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenEmptyUsername_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setUsername("");
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenBlankUsername_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setUsername("    \n\n\n     \t\t");
        whenValidate_thenViolationsSetIsNotEmpty();
    }


    @Test
    public void givenNullEmail_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setEmail(null);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenEmptyEmail_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setEmail("");
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenBlankEmail_whenValidate_thenViolationsSetIsNotEmpty() {
        permanentUser.setEmail("    \n\n\n     \t\t");
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenNullDiscount_whenValidate_thenViolationsSetIsNotEmpty(){
        permanentUser.setDiscount(null);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void given1dot1Discount_whenValidate_thenViolationsSetIsNotEmpty(){
        permanentUser.setDiscount(1.1);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenDiscount0_whenValidate_thenViolationsSetIsEmpty(){
        permanentUser.setDiscount(0.0);
        Set<ConstraintViolation<PermanentUser>> constraintViolations = validator.validate(permanentUser);
        constraintViolations.forEach(System.out::println);
        Assertions.assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void givenNegativeDiscount_whenValidate_thenViolationsSetIsNotEmpty(){
        permanentUser.setDiscount(-1.0);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenNegativeBalance_whenValidate_thenViolationsSetIsNotEmpty(){
        permanentUser.setBalance(-1.0);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    @Test
    public void givenNullBalance_whenValidate_thenViolationsSetIsNotEmpty(){
        permanentUser.setBalance(null);
        whenValidate_thenViolationsSetIsNotEmpty();
    }

    private void whenValidate_thenViolationsSetIsNotEmpty() {
        Assertions.assertFalse(validator.validate(permanentUser).isEmpty());
    }


}
