package kz.trankwilizator.tafl.entity.validation.jwttoken;

import kz.trankwilizator.tafl.entity.JwtToken;
import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtTokenValidationTest extends ValidationTest<JwtToken> {
    @Override
    protected JwtToken createInstance() {
        return JwtToken.builder()
                .token("sometoken")
                .expiryAt(Date.from(new Date().toInstant().plus(1, ChronoUnit.DAYS)))
                .user(new TemporaryUser())
                .build();
    }
    @Test
    public void givenNullUser_whenValidate_thenHasConstraintViolations(){
        getEntity().setUser(null);
        whenValidate_thenHasConstraintViolation("user", true);
    }
    @Test
    public void givenNullToken_whenValidate_thenHasConstraintViolations(){
        validateToken(null, true);
    }
    @Test
    public void givenNullExpireAt_whenValidate_thenHasConstraintViolations(){
        getEntity().setExpiryAt(null);
        whenValidate_thenHasConstraintViolation("expiryAt", true);
    }
    @Test
    public void givenTooLongToken_whenValidate_thenHasConstraintViolations(){
        validateToken("T".repeat(251), true);
    }
    @Test
    public void givenEmptyToken_whenValidate_thenHasConstraintViolations(){
        validateToken("", true);
    }
    @Test
    public void givenBlankToken_whenValidate_thenHasConstraintViolations(){
        validateToken("\n\n\n    \t \t", true);
    }

    @Test
    public void givenCorrectToken_whenValidate_thenHasNotConstraintViolations() {
        validateToken("SOME TOKEN WHOAW", false);
    }
    private void validateToken(String token, boolean exists){
        getEntity().setToken(token);
        whenValidate_thenHasConstraintViolation("token", exists);
    }


}
