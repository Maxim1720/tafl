package kz.trankwilizator.tafl.dao.user;

import kz.trankwilizator.tafl.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.lang.NonNull;

import java.util.Optional;

@RepositoryRestController(path = "users")
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsernameIgnoreCase(@NonNull String login);

}