package kz.trankwilizator.tafl.repository.update;

import kz.trankwilizator.tafl.repository.RepositoryTest;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class UpdateTest<E, ID> extends RepositoryTest<E, ID> {

    protected UpdateTest(JpaRepository<E, ID> repository) {
        super(repository);
    }

    @Override
    public void setUp(E e) {
        super.setUp(e);
        setE(getRepository().save(getE()));
    }


    protected void update(E e){
        setE(getRepository().saveAndFlush(e));
    }
}
