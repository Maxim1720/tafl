package kz.trankwilizator.tafl.service.crud.launch.device;

import kz.trankwilizator.tafl.dao.launchable.device.DeviceRepository;
import kz.trankwilizator.tafl.entity.launchable.device.Device;
import kz.trankwilizator.tafl.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class DeviceCrudService extends CrudService<Device> {

    public DeviceCrudService(DeviceRepository deviceRepository) {
        super(deviceRepository);
    }
}
