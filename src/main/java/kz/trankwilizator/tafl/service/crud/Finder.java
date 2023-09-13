package kz.trankwilizator.tafl.service.crud;

import java.util.Collection;

public interface Finder<FT, ID>{
  public FT findById(ID id);
  public Collection<FT> findAll();
}
