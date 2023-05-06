package kz.trankwilizator.tafl.dao.role;

import kz.trankwilizator.tafl.entity.role.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "permissions")
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}