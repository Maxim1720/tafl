package kz.trankwilizator.tafl.mapper;

import kz.trankwilizator.tafl.dto.request.DeviceRequestDto;
import kz.trankwilizator.tafl.dto.response.DeviceResponseDto;
import kz.trankwilizator.tafl.entity.launchable.device.Device;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DeviceMapper {


    @Mappings (
            value = {
                    @Mapping(target = "id", source = "id"),
                    @Mapping(target = "zoneId", source = "zone.id"),
                    @Mapping(target = "deviceStatusId", source = "deviceStatus.id")
            }
    )
    DeviceResponseDto toResponse(Device device);

    @Mappings(
            value = {
                    @Mapping(target = "id", source = "id"),
                    @Mapping(target = "zoneId", source = "zoneId"),
                    @Mapping(target = "deviceStatusId", source = "deviceStatusId")
            }
    )
    DeviceResponseDto toResponse(DeviceRequestDto requestDto);


    @Mappings(
            value = {
                    @Mapping(target = "deviceStatus.id", source = "deviceStatusId"),
                    @Mapping(target = "zone.id", source = "zoneId")
            }
    )
    Device toEntity(DeviceRequestDto deviceRequestDto);


    @InheritInverseConfiguration(name = "toResponse")
    Device toEntity(DeviceResponseDto responseDto);


//    Device update(DeviceRequestDto dto, Device device);
}

