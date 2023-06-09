package kz.trankwilizator.tafl.dao;

import kz.trankwilizator.tafl.entity.JwtToken;
import kz.trankwilizator.tafl.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;

@RepositoryRestResource(path = "jwt-tokens")
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Set<JwtToken> findByUser(@NonNull User user);

    Set<JwtToken> findJwtTokensByUserUsername(String username);

    Optional<JwtToken> findByToken(String token);
}
