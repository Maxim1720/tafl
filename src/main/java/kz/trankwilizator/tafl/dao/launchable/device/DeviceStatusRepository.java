package kz.trankwilizator.tafl.dao.launchable.device;

import kz.trankwilizator.tafl.entity.launchable.device.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface DeviceStatusRepository extends JpaRepository<DeviceStatus, Long> {
    Optional<DeviceStatus> findByNameIgnoreCase(@NonNull String name);

}
