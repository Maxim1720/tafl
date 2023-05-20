package kz.trankwilizator.tafl.service.crud;

public interface Crud<E> {
    E getById(Long id);
    E save(E e);

    boolean exists(E e);
}
