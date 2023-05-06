package kz.trankwilizator.tafl.dao.user;

import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;

import java.util.Optional;

@NoRepositoryBean
@RestResource(path = "users")
public interface UserRepository<U extends User> extends JpaRepository<U, Long>, JpaSpecificationExecutor<PermanentUser> {
    Optional<U> findByUsernameIgnoreCase(@NonNull String login);

}