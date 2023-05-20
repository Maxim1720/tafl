package kz.trankwilizator.tafl.service.crud.device;

import kz.trankwilizator.tafl.dao.launchable.DeviceRepository;
import kz.trankwilizator.tafl.entity.launchable.device.Device;
import kz.trankwilizator.tafl.service.crud.CrudService;
import org.springframework.stereotype.Service;

@Service
public class DeviceCrudService extends CrudService<Device> {

    public DeviceCrudService(DeviceRepository deviceRepository) {
        super(deviceRepository);
    }
}
