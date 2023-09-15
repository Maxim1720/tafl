package kz.trankwilizator.tafl.entity.user.permanent;

import jakarta.persistence.PersistenceException;
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
public class PermanentUserEntityPersistTest {

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
    public void givenEmail_whenSaveNewUserWithSameEmail_throwException(){
        PermanentUser p = new PermanentUser();
        p.setEmail(permanentUser.getEmail());
        p.setFirstname("dwadwa");
        p.setLastname("dwadwa");
        p.setRole(permanentUser.getRole());
        p.setBalance(permanentUser.getBalance());
        p.setDiscount(permanentUser.getDiscount());
        p.setEnabled(permanentUser.getEnabled());
        p.setUsername("dwadwadwa");
        p.setPassword(permanentUser.getPassword());

        testEntityManager.persistAndFlush(permanentUser);
        Assertions.assertThrows(
                PersistenceException.class,
                ()->testEntityManager.persistAndFlush(p)
        );
    }

    @Test
    public void givenNullId_whenSave_thenIdNotNull() {
        Assertions.assertNotNull(testEntityManager.persist(permanentUser).getId());
    }

    @Test
    public void givenNullCreatedAt_whenSave_thenGenerateCreatedAt() {
        permanentUser.setCreatedAt(null);
        permanentUser = testEntityManager.persistAndFlush(permanentUser);
        Assertions.assertNotNull(permanentUser.getCreatedAt());
    }

}
