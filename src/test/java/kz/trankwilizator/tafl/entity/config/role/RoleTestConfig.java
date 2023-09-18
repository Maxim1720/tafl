package kz.trankwilizator.tafl.entity.config.role;

import kz.trankwilizator.tafl.dao.role.PermissionRepository;
import kz.trankwilizator.tafl.entity.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@TestConfiguration
public class RoleTestConfig {

    @Autowired
    private PermissionRepository permissionRepository;

    public static Role INSTANCE;

    static {
        INSTANCE = new Role();
        INSTANCE.setName("TEST_ROLE");
        INSTANCE.setPermissions(new HashSet<>());
    }
    @Bean
    public Role testRole(){
        Role role = new Role();
        role.setName("TEST_ROLE");
        role.setPermissions(new HashSet<>(permissionRepository.findAll()));
        return role;
    }




}
