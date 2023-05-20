package kz.trankwilizator.tafl.dao.launchable;

import kz.trankwilizator.tafl.entity.launchable.device.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "devices")
public interface DeviceRepository extends JpaRepository<Device, Long> {

}
