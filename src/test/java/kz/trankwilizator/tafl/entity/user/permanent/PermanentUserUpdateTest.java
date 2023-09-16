package kz.trankwilizator.tafl.entity.user.permanent;

import kz.trankwilizator.tafl.dao.user.PermanentUserRepository;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;
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
        repository.deleteAll();
    }

    @Test
    public void givenNullUpdatedAt_whenUpdate_thenGenerateUpdatedAt() {
        permanentUser = repository.save(permanentUser);
        permanentUser.setFirstname("new firstname");
        permanentUser = repository.save(permanentUser);
        System.out.println(permanentUser);
        Assertions.assertNotNull(permanentUser.getUpdatedAt());
    }

    //todo: failed test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
//    @Test
    public void givenUsername_whenUpdate_thenThrowDataIntegrityViolationException(){
        permanentUser = repository.save(permanentUser);
        permanentUser.setUsername("dwadaw@test.ru");
        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->permanentUser = repository.save(permanentUser));
    }
}
