package kz.trankwilizator.tafl.entity.persist;

import jakarta.persistence.PersistenceException;
import kz.trankwilizator.tafl.dao.user.PermanentUserRepository;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.entity.config.user.PermanentUserEntityTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Import({PermanentUserEntityTestConfig.class})
public class PermanentUserEntityPersistTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private PermanentUserRepository repository;

    private PermanentUser permanentUser;


    @BeforeEach
    public void setUp(@Autowired PermanentUser permanentUserInstance) {
        this.permanentUser = permanentUserInstance.toBuilder().build();
    }

    @Test
    public void givenEmail_whenSaveNewUserWithSameEmail_thenThrowException(@Autowired PermanentUser testPermanentUser){
        PermanentUser p = testPermanentUser.toBuilder().build();
        p.setUsername("dwadawd");
        whenSaveNewUserWithMainUser_thenThrowException(p);
    }

    @Test
    public void givenUsername_whenSaveNewUserWithSameUsername_thenThrowException(@Autowired PermanentUser test){
        PermanentUser p = test.toBuilder().build();
        p.setEmail("dwadaw@fwafdaw.ru");
        whenSaveNewUserWithMainUser_thenThrowException(p);
    }
    private void whenSaveNewUserWithMainUser_thenThrowException(PermanentUser newUser){
        testEntityManager.persistAndFlush(permanentUser);
        Assertions.assertThrows(
                PersistenceException.class,
                ()->testEntityManager.persistAndFlush(newUser)
        );
    }

    @Test
    public void givenNullId_whenSave_thenIdNotNull() {
        Assertions.assertNotNull(testEntityManager.persist(permanentUser).getId());
    }

    @Test
    public void givenNullCreatedAt_whenSave_thenGenerateCreatedAt() {
        permanentUser = testEntityManager.persistAndFlush(permanentUser);
        Assertions.assertNotNull(permanentUser.getCreatedAt());
    }


    //todo: not actual test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    @Test
    public void givenUser_whenSaveAgainThrowsException() {
        repository.save(permanentUser);

        Assertions.assertThrows(DataIntegrityViolationException.class, () ->
                permanentUser = repository.save(permanentUser)
        );
        repository.deleteAll();
    }
}
