package kz.trankwilizator.tafl.repository.update;

import kz.trankwilizator.tafl.dao.user.PermanentUserRepository;
import kz.trankwilizator.tafl.entity.config.user.PermanentUserEntityTestConfig;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@Import(PermanentUserEntityTestConfig.class)
public class PermanentUserUpdateTest extends UpdateTest<PermanentUser, Long> {

    @Autowired
    protected PermanentUserUpdateTest(JpaRepository<PermanentUser, Long> repository) {
        super(repository);
    }

    @BeforeEach
    @Override
    public void setUp(@Autowired PermanentUser permanentUser) {
        getRepository().deleteAll();
        super.setUp(permanentUser);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Test
    public void givenUsername_whenUpdate_thenNotUpdating(){
        String newUsername = "123321";
        getE().setUsername(newUsername);
//        update(getE());
        getRepository().saveAndFlush(getE());
        Assertions.assertTrue(((PermanentUserRepository)getRepository()).findByUsernameIgnoreCase(newUsername).isEmpty());
    }
}
