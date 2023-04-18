package kz.trankwilizator.tafl.dao.user.role;


import kz.trankwilizator.tafl.entity.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
