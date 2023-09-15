package kz.trankwilizator.tafl.entity.user.permanent;

import jakarta.validation.Validation;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({PermanentUserEntityTestConfig.class})
public class PermanentUserUpdateTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private PermanentUser permanentUser;


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
    }

    @Test
    public void givenNullUpdatedAt_whenUpdate_thenGenerateUpdatedAt() {
        permanentUser = testEntityManager.persistAndFlush(permanentUser);
        permanentUser.setFirstname("new firstname");
        permanentUser = testEntityManager.persistAndFlush(permanentUser);
        Assertions.assertNotNull(permanentUser.getUpdatedAt());
    }
}
