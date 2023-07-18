package kz.trankwilizator.tafl.service.crud;

public interface Crud<E> {
    E getById(Long id);
    boolean exists(E e);
}
