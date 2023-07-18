package kz.trankwilizator.tafl.service.crud.user;

import jakarta.persistence.EntityNotFoundException;
import kz.trankwilizator.tafl.dao.user.UserRepository;
import kz.trankwilizator.tafl.entity.user.User;
import kz.trankwilizator.tafl.service.crud.Crud;
import kz.trankwilizator.tafl.service.crud.Saver;
import kz.trankwilizator.tafl.service.crud.Finder;
import kz.trankwilizator.tafl.service.crud.ExistenceChecker;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Collection;

import java.util.Optional;

public abstract class UserCrudService<U extends User> implements ExistenceChecker<U, Long>, Saver<U>, Finder<U, Long> {
    private final UserRepository<U> repository;

    protected UserCrudService(UserRepository<U> repository) {
        this.repository = repository;
    }

    @Override
    public U findById(Long id) {
        return getFromOptional(repository.findById(id));
    }

    @Override
    public Collection<U> findAll(){
        return repository.findAll();
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

    @Override
    public boolean existsById(Long id){
        return repository.findById(id).isPresent();
    }
    
    public boolean existsByUsername(String username) {
        return repository.findByUsernameIgnoreCase(username).isPresent();
    }
}
