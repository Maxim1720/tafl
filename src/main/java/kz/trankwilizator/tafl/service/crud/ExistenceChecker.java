package kz.trankwilizator.tafl.service.crud;

public interface ExistenceChecker<T, ID>{
  public boolean exists(T t);
  public boolean existsWithId(ID id);
}
