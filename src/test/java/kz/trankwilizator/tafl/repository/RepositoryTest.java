package kz.trankwilizator.tafl.repository;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
@DataJpaTest
public abstract class RepositoryTest<E, ID> {

    @Setter
    private E e;
    private final JpaRepository<E, ID> repository;

    @BeforeEach
    public void setUp(E e){
        this.e = e;
    }

    protected RepositoryTest(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    protected void givenEntity_whenSave_thenReturnNotNull(){
        e = repository.save(e);
        Assertions.assertNotNull(e);
    }

}
