package kz.trankwilizator.tafl.dao.user;

import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "permanent-users")
public interface PermanentUserRepository extends UserRepository<PermanentUser>{

}
