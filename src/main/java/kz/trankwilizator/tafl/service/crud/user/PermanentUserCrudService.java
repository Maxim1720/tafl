package kz.trankwilizator.tafl.service.crud.user;

import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import org.springframework.stereotype.Service;

@Service
public class PermanentUserCrudService extends UserCrudService<PermanentUser>{

    protected PermanentUserCrudService(UserRepository<PermanentUser> repository) {
        super(repository);
    }
}
