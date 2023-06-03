package kz.trankwilizator.tafl.service.crud.launch.device;

import jakarta.persistence.EntityNotFoundException;
import kz.trankwilizator.tafl.dao.launchable.device.DeviceStatusRepository;
import kz.trankwilizator.tafl.entity.launchable.device.DeviceStatus;
import kz.trankwilizator.tafl.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class DeviceStatusCrudService extends CrudService<DeviceStatus> {

    private final DeviceStatusRepository deviceStatusRepository;
    protected DeviceStatusCrudService(DeviceStatusRepository repository) {
        super(repository);
        deviceStatusRepository = repository;
    }

    public DeviceStatus getByName(String name){
        try {
            return getFromOptional(deviceStatusRepository.findByNameIgnoreCase(name));
        }
        catch (EntityNotFoundException e){
            e.addSuppressed(new EntityNotFoundException("unknown status with name: " + name));
            throw e;
        }
    }
}
