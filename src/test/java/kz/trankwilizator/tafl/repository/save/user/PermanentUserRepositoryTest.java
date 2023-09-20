package kz.trankwilizator.tafl.repository.save.user;

import kz.trankwilizator.tafl.dao.user.PermanentUserRepository;
import kz.trankwilizator.tafl.entity.config.user.PermanentUserEntityTestConfig;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.repository.RepositoryTest;
import kz.trankwilizator.tafl.repository.save.RepositorySaveTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Import(value = PermanentUserEntityTestConfig.class)
public class PermanentUserRepositoryTest extends RepositorySaveTest<PermanentUser, Long> {

    @Autowired
    public PermanentUserRepositoryTest(PermanentUserRepository repository) {
        super(repository);
    }
    @Autowired
    @Override
    public void setUp(PermanentUser permanentUser) {
        super.setUp(permanentUser);
        getRepository().deleteAll();
    }
    @Test
    @Override
    protected void givenEntity_whenSave_thenReturnNotNull() {
        super.givenEntity_whenSave_thenReturnNotNull();
    }
    @Test
    public void givenEntity_whenSave_thenReturnNotNullId(){
        whenSave();
        Assertions.assertNotNull(getE().getId());
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Test
    public void givenEntity_whenSave_thenReturnNotNullCreatedAt(){
        whenSave();
        Assertions.assertNotNull(getE().getCreatedAt());
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void givenSavedEntity_whenChangeFirstnameAndUpdate_thenReturnNotNullUpdatedAt(){
        whenSave();
        getE().setFirstname("newName");
        whenSave();
        Assertions.assertNotNull(getE().getUpdatedAt());
    }
}
