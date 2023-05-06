package kz.trankwilizator.tafl.service.crud.user;

import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.entity.user.TemporaryUser;
import org.springframework.stereotype.Service;

@Service
public class TemporaryUserCrudService extends UserCrudService<TemporaryUser>{
    protected TemporaryUserCrudService(UserRepository<TemporaryUser> repository) {
        super(repository);
    }
}
