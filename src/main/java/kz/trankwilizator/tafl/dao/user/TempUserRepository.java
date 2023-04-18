package kz.trankwilizator.tafl.dao.user;

import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempUserRepository extends JpaRepository<TemporaryUser, Long> {
    Optional<TemporaryUser> findByUsername(String username);
}
