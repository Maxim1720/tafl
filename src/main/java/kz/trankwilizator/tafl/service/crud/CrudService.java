package kz.trankwilizator.tafl.service.crud;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class CrudService<E> implements Crud<E> {

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
    public E save(E e) {
        return repository.save(e);
    }

    @Override
    public E getById(Long id) {
        return getFromOptional(repository.findById(id));
    }

    protected E getFromOptional(Optional<E> eOptional){
        return eOptional.orElseThrow(()->new EntityNotFoundException("doesn't exists"));
    }
}
