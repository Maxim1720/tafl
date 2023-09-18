package kz.trankwilizator.tafl.entity.validation.permission;

import kz.trankwilizator.tafl.entity.role.Permission;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void givenNullName_whenValidate_thenNamePropertyHasConstraintViolation(){
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation(null, true);
    }

    @Test
    public void givenBlankName_whenValidate_thenNamePropertyHasConstraintViolation(){
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation("\n\n\n   ", true);
    }

    @Test
    public void givenEmptyName_whenValidate_thenNamePropertyHasConstraintViolation(){
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation("", true);
    }

    @Test
    public void givenNameTEST_TEST_TEST_whenValidate_thenNamePropertyHasNotConstraintViolation() {
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation("TEST_TEST_TEST", false);
    }

    @Test
    public void givenName_TEST_TEST_TEST_whenValidate_thenNamePropertyHasConstraintViolation() {
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation("_TEST_TEST_TEST", true);
    }

    @Test
    public void givenName_TEST_TEST_TEST__whenValidate_thenNamePropertyHasConstraintViolation() {
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation("_TEST_TEST_TEST_", true);
    }

    @Test
    public void givenName_TEST__TEST_TEST__whenValidate_thenNamePropertyHasConstraintViolation() {
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation("_TEST__TEST_TEST_", true);
    }

    @Test
    public void givenNameTEST_TEST__whenValidate_thenNamePropertyHasConstraintViolation() {
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation("TEST_TEST_", true);
    }

    @Test
    public void givenName_TEST_whenValidate_thenNamePropertyHasConstraintViolation() {
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation("_TEST", true);
    }

    @Test
    public void givenNameTEST__whenValidate_thenNamePropertyHasConstraintViolation() {
        givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation("TEST_", true);
    }

    private void givenPermissionName_whenValidate_thenNamePropertyHasConstraintViolation(String name, boolean exists){
        getEntity().setName(name);
//        validationTester.thenHasConstraintViolations(getEntity(), "name", exists);
        whenValidate_thenExistsConstraintViolation("name", exists);
    }

}
