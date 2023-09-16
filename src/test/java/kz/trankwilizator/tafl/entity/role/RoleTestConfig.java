package kz.trankwilizator.tafl.entity.role;

import kz.trankwilizator.tafl.dao.role.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.stream.Collectors;

@TestConfiguration
public class RoleTestConfig {

    @Autowired
    private PermissionRepository permissionRepository;

    @Bean
    public Role testRole(){
        Role role = new Role();
        role.setName("TEST_ROLE");
        role.setPermissions(new HashSet<>(permissionRepository.findAll()));
        return role;
    }




}
