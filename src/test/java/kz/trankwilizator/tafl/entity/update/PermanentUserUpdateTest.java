package kz.trankwilizator.tafl.entity.update;

import kz.trankwilizator.tafl.dao.user.PermanentUserRepository;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.entity.config.user.PermanentUserEntityTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Import({PermanentUserEntityTestConfig.class})
public class PermanentUserUpdateTest {

    private PermanentUser permanentUser;

    @Autowired
    private PermanentUserRepository repository;


    @BeforeEach
    public void setUp(@Autowired PermanentUser permanentUserInstance) {
        permanentUser = permanentUserInstance.toBuilder().build();
        repository.deleteAll();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Test
    public void givenNullUpdatedAt_whenUpdate_thenGenerateUpdatedAt() {
        permanentUser = repository.save(permanentUser);
        permanentUser.setFirstname("new firstname");
        permanentUser = repository.save(permanentUser);
        System.out.println(permanentUser);
        Assertions.assertNotNull(permanentUser.getUpdatedAt());
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Test
    public void givenUsername_whenUpdate_thenCantUpdate(){
        permanentUser = repository.save(permanentUser);
        String newUsername = "test@dwadwadawd.ru";
        permanentUser.setUsername(newUsername);
        permanentUser = repository.save(permanentUser);
        Assertions.assertFalse(
                repository.findByUsernameIgnoreCase(newUsername).isPresent()
        );
    }
}
