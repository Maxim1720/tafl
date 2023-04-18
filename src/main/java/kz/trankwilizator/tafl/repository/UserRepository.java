package kz.trankwilizator.tafl.repository;

import kz.trankwilizator.tafl.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
