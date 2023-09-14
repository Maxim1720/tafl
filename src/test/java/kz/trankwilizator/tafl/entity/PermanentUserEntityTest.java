package kz.trankwilizator.tafl.entity;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import kz.trankwilizator.tafl.dao.role.RoleRepository;
import kz.trankwilizator.tafl.dao.user.PermanentUserRepository;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(properties = "spring.jpa.properties.hibernate.check_nullability=true")
//@SpringBootTest
public class PermanentUserEntityTest {

    private PermanentUser permanentUser;

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
        permanentUser.setPassword("$2a$12$mgn/TIcF3e/SIKb3o8uoIuKPxAOlQwRiK3QVHDs3UX8Os3XUN468W".toCharArray());
        permanentUser.setEnabled(false);
    }

    @Test
    public void givenNullId_whenSave_thenIdNotNull(){
        setRoleToUser();
        Assertions.assertNotNull(permanentUserRepository.saveAndFlush(permanentUser).getId());
    }

    @Test
    public void givenNullRole_whenSave_thenThrowsPersistenceException(){
        permanentUser.setRole(null);
        whenSave_thenThrowException(DataIntegrityViolationException.class);
    }

    @Test
    public void givenNullCreatedAt_whenSave_thenGenerateCreatedAt(){
        setRoleToUser();
        permanentUser.setCreatedAt(null);
        permanentUser = permanentUserRepository.saveAndFlush(permanentUser);
        Assertions.assertNotNull(permanentUser.getCreatedAt());
    }

    @Test
    public void givenNullUpdatedAt_whenUpdate_thenGenerateUpdatedAt(){
        setRoleToUser();
        permanentUser = permanentUserRepository.saveAndFlush(permanentUser);
        permanentUser.setFirstname("new firstname");
        permanentUser = permanentUserRepository.saveAndFlush(permanentUser);
        Assertions.assertNotNull(permanentUser.getUpdatedAt());
    }

    @Test
    public void givenNullFirstname_whenSave_thenThrowException() {
        setRoleToUser();
        permanentUser.setFirstname(null);
        whenSave_thenThrowException(DataIntegrityViolationException.class);
    }

    @Test
    public void givenEmptyFirstname_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setFirstname("");
        whenSave_thenThrowException(ConstraintViolationException.class);
    }

    @Test
    public void givenBlankFirstname_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setFirstname("       ");
        whenSave_thenThrowException(ConstraintViolationException.class);
    }


    @Test
    public void givenShortPassword_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setPassword("testestes".toCharArray());
        whenSave_thenThrowException(ConstraintViolationException.class);
    }


    @Test
    public void givenNullLastname_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setLastname(null);
        whenSave_thenThrowException(DataIntegrityViolationException.class);
    }
    @Test
    public void givenEmptyLastname_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setLastname("");
        whenSave_thenThrowException(ConstraintViolationException.class);
    }
    @Test
    public void givenBlankLastname_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setLastname("  \n\n\n  ");
        whenSave_thenThrowException(ConstraintViolationException.class);
    }

    @Test
    public void givenNullUsername_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setUsername(null);
        whenSave_thenThrowException(DataIntegrityViolationException.class);
    }
    @Test
    public void givenEmptyUsername_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setUsername("");
        whenSave_thenThrowException(ConstraintViolationException.class);
    }
    @Test
    public void givenBlankUsername_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setUsername("    \n\n\n     \t\t");
        whenSave_thenThrowException(ConstraintViolationException.class);
    }


    @Test
    public void givenNullEmail_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setEmail(null);
        whenSave_thenThrowException(DataIntegrityViolationException.class);
    }
    @Test
    public void givenEmptyEmail_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setEmail("");
        whenSave_thenThrowException(ConstraintViolationException.class);
    }
    @Test
    public void givenBlankEmail_whenSave_thenThrowException(){
        setRoleToUser();
        permanentUser.setEmail("    \n\n\n     \t\t");
        whenSave_thenThrowException(ConstraintViolationException.class);
    }

    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void givenUsername_whenSetNewUsernameAndSave_thenThrowException() {
        setRoleToUser();
        permanentUser.setUsername("test username");
        permanentUser = testEntityManager.persistAndFlush(permanentUser);
        permanentUser.setUsername("new username");
        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> permanentUser = testEntityManager.merge(permanentUser)
        );
    }

    private void whenSave_thenThrowException(Class<? extends RuntimeException> e){
        Assertions.assertThrows(e,
                ()->permanentUser = permanentUserRepository.saveAndFlush(permanentUser));
    }
    private void setRoleToUser(){
        permanentUser.setRole(roleRepository.findByNameIgnoreCase("PERMANENT").orElseThrow(EntityNotFoundException::new));
    }
}
