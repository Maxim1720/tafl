package kz.trankwilizator.tafl.dao.tariff;

import kz.trankwilizator.tafl.entity.zone.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "zones")
public interface ZoneRepository extends JpaRepository<Zone, Long> {
}
