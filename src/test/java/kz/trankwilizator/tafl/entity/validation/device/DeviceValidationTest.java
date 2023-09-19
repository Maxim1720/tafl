package kz.trankwilizator.tafl.entity.validation.device;

import kz.trankwilizator.tafl.entity.launchable.device.Device;
import kz.trankwilizator.tafl.entity.launchable.device.DeviceStatus;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.zone.Zone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class DeviceValidationTest extends ValidationTest<Device> {
    @Override
    protected Device createInstance() {
        return Device.builder()
                .deviceStatus(new DeviceStatus())
                .zone(new Zone())
                .number(1L).build();
    }


    @Test
    public void givenNullDeviceStatus_whenValidate_thenHasConstraintViolations(){
        getEntity().setDeviceStatus(null);
        whenValidate_thenExistsConstraintViolation("deviceStatus", true);
    }
    @Test
    public void givenNullZone_whenValidate_thenHasConstraintViolations(){
        getEntity().setZone(null);
        whenValidate_thenExistsConstraintViolation("zone", true);
    }
    @Test
    public void givenNullNumber_whenValidate_thenHasConstraintViolations(){
        getEntity().setNumber(null);
        whenValidate_thenExistsConstraintViolation("number", true);
    }

    @Test
    public void givenNegativeNumber_whenValidate_thenHasConstraintViolations(){
        getEntity().setNumber(-1L);
        whenValidate_thenExistsConstraintViolation("number", true);
    }

    @Test
    public void givenCorrectInitializedDevice_whenValidate_thenHasNotConstraintViolations(){
        Arrays.stream(getEntity().getClass().getFields()).forEach(f->Assertions.assertFalse(
                hasConstraintViolations(f.getName())
        ));
    }
}
