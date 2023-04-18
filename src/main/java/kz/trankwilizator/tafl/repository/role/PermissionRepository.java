package kz.trankwilizator.tafl.repository.role;

import kz.trankwilizator.tafl.entity.role.Permission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
