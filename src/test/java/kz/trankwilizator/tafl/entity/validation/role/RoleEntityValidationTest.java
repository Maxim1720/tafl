package kz.trankwilizator.tafl.entity.validation.role;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.role.Role;
import kz.trankwilizator.tafl.entity.config.role.RoleTestConfig;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.name.ConstantNameArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

@Import(value = RoleTestConfig.class)
@ContextConfiguration(classes = RoleTestConfig.class)
public class RoleEntityValidationTest extends ValidationTest<Role> {

    @Override
    protected Role createInstance() {
        return RoleTestConfig.INSTANCE.toBuilder().build();
    }


    @BeforeEach
    @Override
    public void setUpEntity() {
        super.setUpEntity();
    }

    @Test
    public void givenNullPermissions_whenValidate_thenConstraintViolationsNotEmpty(){
        getEntity().setPermissions(null);
        whenValidate_thenHasConstraintViolation("permissions", true);
    }

    @Test
    public void givenCorrectRole_whenValidate_thenConstraintViolationsIsEmpty(){
        Arrays.stream(Role.class.getFields()).forEach(f-> whenValidate_thenHasConstraintViolation(f.getName(), false));
    }



    @ParameterizedTest(name = "test {index}: name={0}, result={1}")
    @ArgumentsSource(ConstantNameArgumentsProvider.class)
    public void givenName_whenValidate_thenHasConstraintViolations(String name, ValidationResult result){
        getEntity().setName(name);
        whenValidate_thenHasConstraintViolation("name", result);
    }
}
