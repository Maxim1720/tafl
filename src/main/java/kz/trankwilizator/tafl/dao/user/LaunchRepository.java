package kz.trankwilizator.tafl.dao.user;

import kz.trankwilizator.tafl.entity.launchable.Launch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "launches")
public interface LaunchRepository extends JpaRepository<Launch, Long> {

}
