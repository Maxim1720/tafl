package kz.trankwilizator.tafl.repository.save;

import kz.trankwilizator.tafl.repository.RepositoryTest;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class RepositorySaveTest<E, ID> extends RepositoryTest<E, ID> {
    protected RepositorySaveTest(JpaRepository<E, ID> repository) {
        super(repository);
    }


    protected void whenSave() {
        setE(getRepository().save(getE()));
    }
}
