package kz.trankwilizator.tafl.service.crud.user;

import jakarta.persistence.EntityExistsException;
import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.entity.user.User;
import kz.trankwilizator.tafl.service.crud.Crud;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCrudService<U extends User> implements Crud<U> {
    private final UserRepository<U> repository;

    protected UserCrudService(UserRepository<U> repository) {
        this.repository = repository;
    }

    @Override
    public U getById(Long id) {
        return getFromOptional(repository.findById(id));
    }

    @Override
    public U save(U u) {
        return repository.save(u);
    }

    public U getByUsername(String username){
        return getFromOptional(repository.findByUsernameIgnoreCase(username));
    }

    private U getFromOptional(Optional<U> u){
        return u.orElseThrow(()->new EntityExistsException("doesn't exists"));
    }
}
