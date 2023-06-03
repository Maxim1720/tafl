package kz.trankwilizator.tafl.dao.schedule;

import kz.trankwilizator.tafl.entity.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "schedules")
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
