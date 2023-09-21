package kz.trankwilizator.tafl.entity.persist;

import kz.trankwilizator.tafl.dao.user.PermanentUserRepository;
import kz.trankwilizator.tafl.entity.config.user.PermanentUserEntityTestConfig;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

@DataJpaTest
@Import({PermanentUserEntityTestConfig.class})
public class PermanentUserEntityPersistTest {

    /*@Autowired
    private TestEntityManager testEntityManager;*/
    @Autowired
    private PermanentUserRepository repository;

    private PermanentUser permanentUser;


    @BeforeEach
    public void setUp(@Autowired PermanentUser permanentUserInstance) {
        repository.deleteAll();
        repository.flush();
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
//        testEntityManager.persistAndFlush(permanentUser);
        repository.saveAndFlush(permanentUser);

        Assertions.assertThrows(
                DataIntegrityViolationException.class,
//                ()->testEntityManager.persistAndFlush(newUser)
                ()->repository.saveAndFlush(newUser)
        );
    }

    @Test
    public void givenNullId_whenSave_thenIdNotNull() {
        Assertions.assertNotNull(
//                testEntityManager.persist(permanentUser).getId()
                repository.save(permanentUser).getId()
        );
    }

    @Test
    public void givenNullCreatedAt_whenSave_thenGenerateCreatedAt() {
//        permanentUser = testEntityManager.persistAndFlush(permanentUser);
        permanentUser = repository.saveAndFlush(permanentUser);
        Assertions.assertNotNull(permanentUser.getCreatedAt());
    }
}
