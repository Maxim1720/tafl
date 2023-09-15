package kz.trankwilizator.tafl.entity;

import jakarta.validation.ConstraintViolationException;
import kz.trankwilizator.tafl.dao.role.PermissionRepository;
import kz.trankwilizator.tafl.dao.role.RoleRepository;
import kz.trankwilizator.tafl.entity.role.Permission;
import kz.trankwilizator.tafl.entity.role.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@DataJpaTest(properties = "spring.jpa.properties.hibernate.check_nullability=true")
public class RoleEntityTest {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    private Permission permission;
    private Role role;

    @Autowired
    public RoleEntityTest(RoleRepository roleRepository, PermissionRepository permissionRepository){
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        roleRepository.deleteAll();
        permissionRepository.deleteAll();

        permission = new Permission();
        permission.setName("TEST PERMISSION");
        permission.setId(4516L);
        permission = this.permissionRepository.save(permission);
    }

    /*@BeforeEach
    public void setUpPermission(){
        permission = new Permission();
        permission.setName("TEST PERMISSION");
        permission = permissionRepository.save(permission);
    }*/


    @Test
    public void givenIdNull_whenSave_thenIdNotNull(){
        role = new Role();
        role.setPermissions(Set.of(permission));
        role.setName("TEST ROLE");
        role = roleRepository.saveAndFlush(role);
        Assertions.assertNotNull(role.getId());
    }

    @Test
    public void givenNullPermission_whenSave_thenThrowException() {
        role = new Role();
        role.setPermissions(null);
        role.setName("TEST ROLE");
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> role = roleRepository.saveAndFlush(role));
    }

}
