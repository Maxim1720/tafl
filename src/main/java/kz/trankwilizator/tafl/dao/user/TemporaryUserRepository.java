package kz.trankwilizator.tafl.dao.user;

import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "temporary-users")
public interface TemporaryUserRepository extends UserRepository<TemporaryUser>{

}
