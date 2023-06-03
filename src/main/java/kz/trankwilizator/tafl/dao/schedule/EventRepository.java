package kz.trankwilizator.tafl.dao.schedule;

import kz.trankwilizator.tafl.entity.schedule.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "events")
public interface EventRepository extends JpaRepository<Event, Long> {

}

