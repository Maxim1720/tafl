package kz.trankwilizator.tafl.entity.user.permanent;

import jakarta.persistence.EntityNotFoundException;
import kz.trankwilizator.tafl.dao.role.RoleRepository;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class PermanentUserEntityTestConfig {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public PermanentUser permanentUserInstance(){
        PermanentUser permanentUser = new PermanentUser();
        permanentUser.setUsername("test username");
        permanentUser.setFirstname("test firstname");
        permanentUser.setLastname("test lastname");
        permanentUser.setSecondName("test second name");
        permanentUser.setEmail("test@test.test");
        permanentUser.setDiscount(0.0);
        permanentUser.setBalance(0.0);
        permanentUser.setPassword("$2a$12$mgn/TIcF3e/SIKb3o8uoIuKPxAOlQwRiK3QVHDs3UX8Os3XUN468W".toCharArray());
        permanentUser.setEnabled(false);
        permanentUser.setRole(roleRepository.findByNameIgnoreCase("PERMANENT").orElseThrow(EntityNotFoundException::new));
        return permanentUser;
    }

}
