package kz.trankwilizator.tafl.entity.validation.role;

import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.role.Role;
import kz.trankwilizator.tafl.entity.config.role.RoleTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void givenNullName_whenValidate_thenConstraintViolationsNotEmpty(){
        validateName(null, false);
    }

    @Test
    public void givenNullPermissions_whenValidate_thenConstraintViolationsNotEmpty(){
        getEntity().setPermissions(null);
        whenValidate_thenExistsConstraintViolation("permissions", true);
    }

    @Test
    public void givenCorrectRole_whenValidate_thenConstraintViolationsIsEmpty(){
        Arrays.stream(Role.class.getFields()).forEach(f->whenValidate_thenExistsConstraintViolation(f.getName(), false));
    }

    @Test
    public void givenShortRoleName_whenValidate_thenConstraintViolationsNotEmpty(){
        validateName("R", false);
    }

    @Test
    public void givenTooLongRoleName_whenValidate_thenConstraintViolationsNotEmpty(){
        String roleName = "R".repeat(76);
        validateName(roleName, false);
    }

    @Test
    public void givenR_Name_whenValidate_thenConstraintViolationsNotEmpty(){
        validateName("R_", false);
    }

    @Test
    public void givenr_Name_whenValidate_thenConstraintViolationsNotEmpty(){
        validateName("r_",false);
    }

    @Test
    public void givenTEST_PERMANENT_USER_whenValidate_thenConstraintViolationsIsEmpty(){
        validateName("TEST_PERMANENT_USER", true);
    }

    @Test
    public void givenName_TEST__whenValidate_thenConstraintViolationsExists(){
        validateName("_TEST__", false);
    }

    @Test
    public void givenNameTEST__TEST_whenValidate_thenConstraintViolationsExists(){
        validateName("TEST__TEST", false);
    }

    @Test
    public void givenNameTEST_TEST__whenValidate_thenConstraintViolationsExists(){
        validateName("TEST_TEST_", false);
    }

    @Test
    public void givenNameTEST_TEST_TEST_whenValidate_thenConstraintViolationsNotExists(){
        validateName("TEST_TEST_TEST", true);
    }

    @Test
    public void givenBlankRoleName_whenValidate_thenConstraintViolationsIsNotEmpty(){
        validateName("   \n\t\r", false);
    }

    @Test
    public void givenEmptyRoleName_whenValidate_thenConstraintViolationsIsEmpty(){
        validateName("", false);
    }

    private void validateName(String name, boolean constraintViolationsIsEmpty){
        getEntity().setName(name);
//        new ValidationTester<Role>().thenHasConstraintViolations(getEntity(), "name", !constraintViolationsIsEmpty);
        whenValidate_thenExistsConstraintViolation("name",!constraintViolationsIsEmpty);
    }
}
