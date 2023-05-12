package kz.trankwilizator.tafl.dao.tariff;

import kz.trankwilizator.tafl.entity.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "tariffs")
public interface TariffRepository extends JpaRepository<Tariff, Long> {
}
