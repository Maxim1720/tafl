package kz.trankwilizator.tafl.dao.user.temp;

import kz.trankwilizator.tafl.entity.user.temp.TemporaryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempUserRepository extends JpaRepository<TemporaryUser, Long> {
    Optional<TemporaryUser> findByUsername(String username);
}
