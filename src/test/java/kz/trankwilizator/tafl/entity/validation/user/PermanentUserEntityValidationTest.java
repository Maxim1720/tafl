package kz.trankwilizator.tafl.entity.validation.user;

import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PermanentUserEntityValidationTest extends ValidationTest<PermanentUser> {


    @BeforeEach
    @Override
    public void setUpEntity() {
        super.setUpEntity();
    }

    @Test
    public void givenNullRole_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setRole(null);
//        whenValidate_thenViolationsSetIsNotEmpty();
        whenValidateProperty_thenConstraintViolationsExists("role", true);
    }

    @Test
    public void givenNullFirstname_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setFirstname(null);
//        whenValidate_thenViolationsSetIsNotEmpty();
        whenValidateProperty_thenConstraintViolationsExists("firstname", true);
    }

    @Test
    public void givenEmptyFirstname_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setFirstname("");
//        whenValidate_thenViolationsSetIsNotEmpty();
        whenValidateProperty_thenConstraintViolationsExists("firstname", true);
    }

    @Test
    public void givenBlankFirstname_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setFirstname("       ");
        whenValidateProperty_thenConstraintViolationsExists("firstname", true);
    }


    @Test
    public void givenShortPassword_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setPassword("testestes".toCharArray());
        whenValidateProperty_thenConstraintViolationsExists("password", true);
    }


    @Test
    public void givenNullLastname_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setLastname(null);
        whenValidateProperty_thenConstraintViolationsExists("lastname", true);
    }

    @Test
    public void givenEmptyLastname_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setLastname("");
        whenValidateProperty_thenConstraintViolationsExists("lastname", true);
    }

    @Test
    public void givenBlankLastname_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setLastname("  \n\n\n  ");
        whenValidateProperty_thenConstraintViolationsExists("lastname", true);
    }

    @Test
    public void givenNullUsername_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setUsername(null);
        whenValidateProperty_thenConstraintViolationsExists("username", true);
    }

    @Test
    public void givenEmptyUsername_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setUsername("");
        whenValidateProperty_thenConstraintViolationsExists("username", true);
    }

    @Test
    public void givenBlankUsername_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setUsername("    \n\n\n     \t\t");
        whenValidateProperty_thenConstraintViolationsExists("username", true);
    }


    @Test
    public void givenNullEmail_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setEmail(null);
        whenValidateProperty_thenConstraintViolationsExists("email", true);
    }

    @Test
    public void givenEmptyEmail_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setEmail("");
        whenValidateProperty_thenConstraintViolationsExists("email", true);
    }

    @Test
    public void givenBlankEmail_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setEmail("    \n\n\n     \t\t");
        whenValidateProperty_thenConstraintViolationsExists("email", true);
    }

    @Test
    public void givenNullDiscount_whenValidate_thenViolationsSetIsNotEmpty(){
        getEntity().setDiscount(null);
        whenValidateProperty_thenConstraintViolationsExists("discount", true);
    }

    @Test
    public void given1dot1Discount_whenValidate_thenViolationsSetIsNotEmpty(){
        getEntity().setDiscount(1.1);
        whenValidateProperty_thenConstraintViolationsExists("discount", true);
    }

    @Test
    public void givenDiscount0_whenValidate_thenViolationsSetIsEmpty(){
        getEntity().setDiscount(0.0);
        whenValidate_thenExistsConstraintViolation("discount", false);
    }

    @Test
    public void givenNegativeDiscount_whenValidate_thenViolationsSetIsNotEmpty(){
        getEntity().setDiscount(-1.0);
        whenValidateProperty_thenConstraintViolationsExists("discount", true);
    }

    @Test
    public void givenNegativeBalance_whenValidate_thenViolationsSetIsNotEmpty(){
        getEntity().setBalance(-1.0);
        whenValidateProperty_thenConstraintViolationsExists("balance", true);
    }

    @Test
    public void givenNullBalance_whenValidate_thenViolationsSetIsNotEmpty(){
        getEntity().setBalance(null);
        whenValidateProperty_thenConstraintViolationsExists("balance", true);
    }

    private void whenValidateProperty_thenConstraintViolationsExists(String property, boolean exists){
        whenValidate_thenExistsConstraintViolation(property, exists);
    }

    @Override
    protected PermanentUser createInstance() {
        return PermanentUser.builder().build();
//        return PermanentUserEntityTestConfig.INSTANCE.toBuilder().build();
    }
}
