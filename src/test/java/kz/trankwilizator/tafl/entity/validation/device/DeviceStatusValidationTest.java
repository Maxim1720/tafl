package kz.trankwilizator.tafl.entity.validation.device;

import kz.trankwilizator.tafl.entity.launchable.device.DeviceStatus;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import org.junit.jupiter.api.Test;

public class DeviceStatusValidationTest extends ValidationTest<DeviceStatus> {
    @Override
    protected DeviceStatus createInstance() {
        DeviceStatus deviceStatus = new DeviceStatus();
        deviceStatus.setName("TEST NAME");
        return deviceStatus;
    }

    @Test
    public void givenNullName_whenValidate_thenConstraintViolationsExist(){
        validateName(null, true);
    }

    @Test
    public void givenEmptyName_whenValidate_thenConstraintViolationsExist(){
        validateName("", true);
    }

    @Test
    public void givenBlankName_whenValidate_thenConstraintViolationsExist(){
        validateName("\n\n\n\n    ", true);
    }

    @Test
    public void givenTESTName_whenValidate_thenConstraintViolationsNotExist(){
        validateName("TEST", false);
    }

    @Test
    public void givenTLetter50countName_whenValidate_thenConstraintViolationsNotExist(){
        validateName("T".repeat(50), false);
    }

    @Test
    public void givenTName_whenValidate_thenConstraintViolationsNotExist(){
        validateName("T", false);
    }

    @Test
    public void givenTLetter51countName_whenValidate_thenConstraintViolationsNotExist(){
        validateName("T".repeat(51), true);
    }


    public void validateName(String name, boolean violationsExist){
        getEntity().setName(name);
        whenValidate_thenExistsConstraintViolation("name", violationsExist);
    }
}
