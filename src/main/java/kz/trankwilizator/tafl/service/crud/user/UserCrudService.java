package kz.trankwilizator.tafl.service.crud.user;

import jakarta.persistence.EntityExistsException;
import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.entity.user.User;
import kz.trankwilizator.tafl.service.crud.Crud;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Optional;

public abstract class UserCrudService<U extends User> implements Crud<U> {
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
        try {
            return getFromOptional(repository.findByUsernameIgnoreCase(username));
        }
        catch (EntityNotFoundException e){
            e.addSuppressed(new EntityNotFoundException("with username: " + username));
            throw e;
        }
    }

    private U getFromOptional(Optional<U> u){
        return u.orElseThrow(()->new EntityExistsException("doesn't exists"));
    }
}
