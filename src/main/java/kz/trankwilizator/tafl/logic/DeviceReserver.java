package kz.trankwilizator.tafl.logic;

import kz.trankwilizator.tafl.entity.launchable.device.DeviceStatus;
import kz.trankwilizator.tafl.mapper.DeviceMapper;
import kz.trankwilizator.tafl.service.crud.launch.device.DeviceCrudService;
import kz.trankwilizator.tafl.service.crud.launch.device.DeviceStatusCrudService;
import org.springframework.stereotype.Service;

@Service
public class DeviceReserver extends DeviceStatusChanger {

    private final DeviceStatusCrudService deviceStatusCrudService;

    public DeviceReserver(DeviceStatusCrudService deviceStatusCrudService, DeviceCrudService deviceCrudService, DeviceMapper deviceMapper) {
        super(deviceCrudService, deviceMapper);
        this.deviceStatusCrudService = deviceStatusCrudService;
    }

    @Override
    protected DeviceStatus getStatus() {
        return deviceStatusCrudService.getByName("busy");
    }
}
