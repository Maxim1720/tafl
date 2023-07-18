package kz.trankwilizator.tafl.logic;

import kz.trankwilizator.tafl.dto.response.DeviceResponseDto;
import kz.trankwilizator.tafl.entity.launchable.device.Device;
import kz.trankwilizator.tafl.entity.launchable.device.DeviceStatus;
import kz.trankwilizator.tafl.exception.BadOperationException;
import kz.trankwilizator.tafl.mapper.DeviceMapper;
import kz.trankwilizator.tafl.service.crud.launch.device.DeviceCrudService;


public abstract class DeviceStatusChanger {
    private final DeviceCrudService deviceCrudService;
    private final DeviceMapper deviceMapper;

    protected DeviceStatusChanger(DeviceCrudService deviceCrudService, DeviceMapper deviceMapper) {
        this.deviceCrudService = deviceCrudService;
        this.deviceMapper = deviceMapper;
    }

    public DeviceResponseDto change(Long id){
        Device device = deviceCrudService.findById(id);
        if(device.getDeviceStatus().getName().equals(getStatus().getName())){
            throw new BadOperationException("This device status already set upped", id);
        }
        device.setDeviceStatus(getStatus());
        deviceCrudService.save(device);
        return deviceMapper.toResponse(device);
    }

    protected abstract DeviceStatus getStatus();
}
