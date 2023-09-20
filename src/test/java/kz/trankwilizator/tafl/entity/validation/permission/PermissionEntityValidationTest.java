package kz.trankwilizator.tafl.entity.validation.permission;

import kz.trankwilizator.tafl.entity.role.Permission;
import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.name.ConstantNameArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.HashSet;

public class PermissionEntityValidationTest extends ValidationTest<Permission> {

    @BeforeEach
    @Override
    public void setUpEntity() {
        super.setUpEntity();
    }

    @Override
    protected Permission createInstance() {
        Permission permission = new Permission();
        permission.setRoles(new HashSet<>());
        permission.setName("TEST_PERMISSION");
        return permission;
    }


    @ParameterizedTest(name = "test {index}: name={0}, result={1}")
    @ArgumentsSource(ConstantNameArgumentsProvider.class)
    public void givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation(String name, ValidationResult result){
        getEntity().setName(name);
        whenValidate_thenHasConstraintViolation("name", result);
    }

}
