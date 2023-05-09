package kz.trankwilizator.tafl.dao.user;

import kz.trankwilizator.tafl.entity.JwtToken;
import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import kz.trankwilizator.tafl.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Set<JwtToken> findByUser(@NonNull User user);

    Set<JwtToken> findJwtTokensByUserUsername(String username);

    Optional<JwtToken> findByToken(String token);
}
