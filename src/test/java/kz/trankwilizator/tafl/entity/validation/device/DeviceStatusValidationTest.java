package kz.trankwilizator.tafl.entity.validation.device;

import kz.trankwilizator.tafl.entity.launchable.device.DeviceStatus;
import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.name.ConstantNameArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class DeviceStatusValidationTest extends ValidationTest<DeviceStatus> {
    @Override
    protected DeviceStatus createInstance() {
        DeviceStatus deviceStatus = new DeviceStatus();
        deviceStatus.setName("TEST NAME");
        return deviceStatus;
    }
    @ParameterizedTest(name = "test {index}: name={0}, result={1}")
    @ArgumentsSource(ConstantNameArgumentsProvider.class)
    public void givenNullName_whenValidate_thenConstraintViolationsExist(String name, ValidationResult result){
        validateName(name, result.getBoolResult());
    }
    public void validateName(String name, boolean violationsExist){
        getEntity().setName(name);
        whenValidate_thenHasConstraintViolation("name", violationsExist);
    }
}
