package kz.trankwilizator.tafl.repository;

import kz.trankwilizator.tafl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
