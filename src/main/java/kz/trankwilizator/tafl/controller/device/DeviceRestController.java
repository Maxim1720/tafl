package kz.trankwilizator.tafl.controller.device;

import jakarta.servlet.http.HttpServletRequest;
import kz.trankwilizator.tafl.dto.response.ResponseDto;
import kz.trankwilizator.tafl.logic.DeviceReleaser;
import kz.trankwilizator.tafl.logic.DeviceReserver;
import kz.trankwilizator.tafl.service.crud.launch.device.DeviceCrudService;
import kz.trankwilizator.tafl.util.ResponseDtoFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceRestController {

    private final DeviceReserver deviceReserver;
    private final DeviceReleaser deviceReleaser;

    private final DeviceCrudService deviceCrudService;

    public DeviceRestController(DeviceReserver deviceReserver, DeviceReleaser deviceReleaser,
                                DeviceCrudService deviceCrudService) {
        this.deviceReserver = deviceReserver;
        this.deviceReleaser = deviceReleaser;
        this.deviceCrudService = deviceCrudService;
    }

    @PostMapping("/{id}/reserve")
    public ResponseEntity<ResponseDto> reserve(@PathVariable("id") Long id, HttpServletRequest request){
        return ResponseEntity.ok(ResponseDtoFactory.ok(request.getRequestURI(),
                deviceReserver.change(id),
                "Device is occupied"));
    }

    @PostMapping("/{id}/release")
    public ResponseEntity<ResponseDto> release(@PathVariable("id") Long id, HttpServletRequest request){
        return ResponseEntity.ok(ResponseDtoFactory.ok(
                request.getRequestURI(),
                deviceReleaser.change(id),
                "Device released"
        ));
    }
    @GetMapping("/{id}/status")
    public ResponseEntity<ResponseDto> status(@PathVariable("id") Long id, HttpServletRequest request){
        return ResponseEntity.ok(
          ResponseDtoFactory.ok(
                  request.getRequestURI(),
                  deviceCrudService.findById(id).getDeviceStatus(),
                  "Currently status of device 'true' - is occupied, 'false' - is free."
          )
        );
    }
}
