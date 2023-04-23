package kz.trankwilizator.tafl.crud;

import jakarta.persistence.EntityExistsException;
import kz.trankwilizator.tafl.dao.user.temp.TempUserRepository;
import kz.trankwilizator.tafl.entity.user.temp.TemporaryUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemporaryUserCrudService implements Crud<TemporaryUser> {

    private final TempUserRepository tempUserRepository;

    public TemporaryUserCrudService(TempUserRepository tempUserRepository) {
        this.tempUserRepository = tempUserRepository;
    }

    @Override
    public TemporaryUser getById(Long id) {
        return getByOptional(tempUserRepository.findById(id));
    }

    @Override
    public TemporaryUser save(TemporaryUser temporaryUser) {
        return tempUserRepository.save(temporaryUser);
    }

    public TemporaryUser getByUsername(String username){
        try {
            return getByOptional(tempUserRepository.findByUsername(username));
        }
        catch (EntityExistsException e){
            e.addSuppressed(new EntityExistsException(String.format("with username: %s", username)));
            throw e;
        }
    }

    private TemporaryUser getByOptional(Optional<TemporaryUser> optionalTemporaryUser){
        return optionalTemporaryUser.orElseThrow(()->new EntityExistsException("temp user doesn't exists"));
    }
}
