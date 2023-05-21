package kz.trankwilizator.tafl.dao.launchable;

import kz.trankwilizator.tafl.entity.launchable.Launch;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntityDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "launches")
public interface LaunchRepository extends JpaRepository<Launch, Long> {
    Optional<Launch> findByRunnableEntityDetails(RunnableEntityDetails runnableEntityDetails);

}
