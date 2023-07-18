package kz.trankwilizator.tafl.service.crud.user;

import jakarta.persistence.EntityNotFoundException;
import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.entity.user.User;
import kz.trankwilizator.tafl.service.crud.Crud;
import kz.trankwilizator.tafl.service.crud.Saver;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Optional;

public abstract class UserCrudService<U extends User> implements Crud<U>, Saver<U> {
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

    @Override
    public Collection<U> saveAll(Collection<U> values){
        return repository.saveAll(values);
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
        return u.orElseThrow(()->new EntityNotFoundException("doesn't exists"));
    }


    @Override
    public boolean exists(U u) {
        return repository.exists(new Example<>() {
            @Override
            public U getProbe() {
                return u;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return ExampleMatcher.matchingAll();
            }
        });
    }
    public boolean existsByUsername(String username) {
        return repository.findByUsernameIgnoreCase(username).isPresent();
    }
}
