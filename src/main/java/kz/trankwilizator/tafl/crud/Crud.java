package kz.trankwilizator.tafl.crud;

public interface Crud<E> {
    E getById(Long id);
    E save(E e);
}
