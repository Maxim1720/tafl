package kz.trankwilizator.tafl.dao.launchable;

import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "run-entity-type")
public interface RunnableEntityTypeRepository extends JpaRepository<RunnableEntityType, Long> {
    Optional<RunnableEntityType> findByNameIgnoreCase(String name);
}
