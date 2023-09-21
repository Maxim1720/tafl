package kz.trankwilizator.tafl.entity.validation.user;

import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.DiscountArgumentsProvider;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.EmailArgumentsProvider;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.MoneyArgumentsProvider;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.name.NameArgumentsProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigDecimal;


public class PermanentUserEntityValidationTest extends ValidationTest<PermanentUser> {

    @Test
    public void givenNullRole_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setRole(null);
        whenValidate_thenHasConstraintViolation("role", true);
    }

    @ParameterizedTest(name = "test {index}: firstname={0}, result={1}")
    @ArgumentsSource(NameArgumentsProvider.class)
    public void givenFirstname_whenValidate_thenHasConstraintViolations(String firstname, ValidationResult result) {
        getEntity().setFirstname(firstname);
        whenValidate_thenHasConstraintViolation("firstname", result);
    }


    @ParameterizedTest(name = "test {index}: lastname={0}, result={1}")
    @ArgumentsSource(NameArgumentsProvider.class)
    public void givenLastname_whenValidate_thenHasConstraintViolations(String lastname, ValidationResult result) {
        getEntity().setLastname(lastname);
        whenValidate_thenHasConstraintViolation("lastname", result);
    }


    @ParameterizedTest(name = "test {index}: username={0}, result={1}")
    @ArgumentsSource(NameArgumentsProvider.class)
    public void givenUsername_whenValidate_thenHasConstraintViolations(String username, ValidationResult result) {
        getEntity().setUsername(username);
        whenValidate_thenHasConstraintViolation("username", result);
    }

    @ParameterizedTest(name = "test {index}: email={0}, result={1}")
    @ArgumentsSource(EmailArgumentsProvider.class)
    public void givenEmail_whenValidate_thenHasConstraintViolations(String email, ValidationResult result) {
        getEntity().setEmail(email);
        whenValidate_thenHasConstraintViolation("email", result);
    }

    @ParameterizedTest(name = "test {index}: discount={0}, result={1}")
    @ArgumentsSource(DiscountArgumentsProvider.class)
    public void givenDiscount_whenValidate_thenViolationsSetIsNotEmpty(Double discount, ValidationResult result){
        getEntity().setDiscount(discount);
        whenValidate_thenHasConstraintViolation("discount", result);
    }

    @ParameterizedTest(name = "test {index}: balance={0}, result={1}")
    @ArgumentsSource(MoneyArgumentsProvider.class)
    public void givenBalance_whenValidate_thenViolationsSetIsNotEmpty(BigDecimal balance, ValidationResult result){
        getEntity().setBalance(balance);
        whenValidate_thenHasConstraintViolation("balance", result);
    }


    @Test
    public void givenShortPassword_whenValidate_thenViolationsSetIsNotEmpty() {
        getEntity().setPassword("testestes".toCharArray());
        whenValidate_thenHasConstraintViolation("password", ValidationResult.ERROR);
    }



    @Override
    protected PermanentUser createInstance() {
        return PermanentUser.builder().build();
    }
}
