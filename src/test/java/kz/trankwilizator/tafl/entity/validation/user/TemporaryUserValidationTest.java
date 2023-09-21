package kz.trankwilizator.tafl.entity.validation.user;

import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.DateInPastArgumentsProvider;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.name.TempUsernameArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TemporaryUserValidationTest extends ValidationTest<TemporaryUser> {
    @Override
    protected TemporaryUser createInstance() {
        TemporaryUser temporaryUser = new TemporaryUser();
        temporaryUser.setExpiryAt(Date.from(new Date().toInstant().plus(1, ChronoUnit.HOURS)));
        temporaryUser.setUsername("231456465");
        temporaryUser.setBalance(BigDecimal.valueOf(0.0));
        temporaryUser.setEnabled(true);
        return temporaryUser;
    }

    @ParameterizedTest(name = "test {index}: username={0}, result={1}")
    @ArgumentsSource(TempUsernameArgumentsProvider.class)
    public void givenUsername_whenValidate_thenValidationResult(String username, ValidationResult result){
        getEntity().setUsername(username);
        whenValidate_thenHasConstraintViolation("username", result);
    }

    @ParameterizedTest(name = "test {index}: date={0}, result={1}")
    @ArgumentsSource(DateInPastArgumentsProvider.class)
    public void givenExpiryAt_whenValidate_thenValidationResultIs(Date expiryAt, ValidationResult result){
        getEntity().setExpiryAt(expiryAt);
        whenValidate_thenHasConstraintViolation("expiryAt", result);
    }
}
