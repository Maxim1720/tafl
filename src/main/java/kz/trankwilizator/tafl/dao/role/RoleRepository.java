package kz.trankwilizator.tafl.dao.role;


import kz.trankwilizator.tafl.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNameIgnoreCase(String name);
}
