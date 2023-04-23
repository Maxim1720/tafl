package kz.trankwilizator.tafl.dao.user.temp;

import kz.trankwilizator.tafl.entity.user.temp.JwtToken;
import kz.trankwilizator.tafl.entity.user.temp.TemporaryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Set<JwtToken> findByTemporaryUser(@NonNull TemporaryUser temporaryUser);
    Optional<JwtToken> findByToken(String token);
}
