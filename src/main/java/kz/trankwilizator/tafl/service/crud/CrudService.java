package kz.trankwilizator.tafl.service.crud;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Collection;

public abstract class CrudService<E> implements ExistenceChecker<E, Long>, Saver<E>, Finder<E, Long> {

    private final JpaRepository<E, Long> repository;

    protected CrudService(JpaRepository<E, Long> repository) {
        this.repository = repository;
    }

    @Override
    public boolean exists(E e) {
        return repository.exists(new Example<>() {
            @Override
            public E getProbe() {
                return e;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return ExampleMatcher.matchingAll();
            }
        });
    }

    @Override
    public boolean existsWithId(Long id){
        return repository.findById(id).isPresent();
    }

    @Override
    public E save(E e) {
        return repository.save(e);
    }

    @Override 
    public Collection<E> saveAll(Collection<E> value){
        return repository.saveAll(value);
    }

    @Override
    public E findById(Long id) {
        return getFromOptional(repository.findById(id));
    }

    @Override
    public Collection<E> findAll(){
        return repository.findAll();
    }

    protected E getFromOptional(Optional<E> eOptional){
        return eOptional.orElseThrow(()->new EntityNotFoundException("doesn't exists"));
    }
}
