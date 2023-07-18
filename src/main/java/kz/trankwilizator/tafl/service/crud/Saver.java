package kz.trankwilizator.tafl.service.crud;

public interface Saver<ST>{
  ST save(ST);
  Collection<ST> saveAll(Collection<ST> value);
}
