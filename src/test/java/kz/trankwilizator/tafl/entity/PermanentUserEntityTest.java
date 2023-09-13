package kz.trankwilizator.tafl.entity;

import kz.trankwilizator.tafl.dao.role.RoleRepository;
import kz.trankwilizator.tafl.dao.user.PermanentUserRepository;
import kz.trankwilizator.tafl.entity.role.Role;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(properties = "spring.jpa.properties.hibernate.check_nullability=true")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class PermanentUserEntityTest {

    private PermanentUser permanentUser;
    private Role role;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermanentUserRepository permanentUserRepository;

    @BeforeEach
    public void setUp(){

        permanentUser = new PermanentUser();
        permanentUser.setUsername("test username");
        permanentUser.setFirstname("test firstname");
        permanentUser.setLastname("test lastname");
        permanentUser.setSecondName("test second name");
        permanentUser.setEmail("test@test.test");
        permanentUser.setDiscount(0.0);
        permanentUser.setBalance(0.0);
        permanentUser.setPassword("test password".toCharArray());
    }
    @AfterEach
    public void destroy(){
        permanentUserRepository.delete(permanentUser);
    }


    @Test
    public void givenNullId_whenSave_thenIdNotNull(){
        setRoleToUser();
        permanentUser = permanentUserRepository.save(permanentUser);
        Assertions.assertNotNull(permanentUser.getId());
    }

    @Test
    public void givenNullRole_whenSave_thenThrowsDataIntegrityViolationException(){
        permanentUser.setRole(null);
        Executable executable = () -> permanentUser = permanentUserRepository.save(permanentUser);
        Assertions.assertThrows(DataIntegrityViolationException.class,executable
        );
    }

    @Test
    public void givenNullCreatedAt_whenSave_thenGenerateCreatedAt(){
        setRoleToUser();
        permanentUser = permanentUserRepository.save(permanentUser);
        Assertions.assertNotNull(permanentUser.getCreatedAt());
    }

    @Test
    public void givenNullUpdatedAt_whenUpdate_thenGenerateUpdatedAt(){
        setRoleToUser();
        permanentUser = permanentUserRepository.save(permanentUser);
        permanentUser.setFirstname("new firstname");
        permanentUser = permanentUserRepository.save(permanentUser);
        Assertions.assertNotNull(permanentUser.getUpdatedAt());
    }

    private void setRoleToUser(){
        permanentUser.setRole(roleRepository.findByNameIgnoreCase("PERMANENT").get());
    }
}
