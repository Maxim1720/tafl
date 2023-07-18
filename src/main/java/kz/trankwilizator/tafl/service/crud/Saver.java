package kz.trankwilizator.tafl.service.crud;
import java.util.Collection;

public interface Saver<ST>{
  ST save(ST value);
  Collection<ST> saveAll(Collection<ST> value);
}
