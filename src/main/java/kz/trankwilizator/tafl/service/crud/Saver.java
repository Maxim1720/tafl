package kz.trankwilizator.tafl.service.crud;

public interface Saver<ST>{
  ST save(ST value);
  Collection<ST> saveAll(Collection<ST> value);
}
