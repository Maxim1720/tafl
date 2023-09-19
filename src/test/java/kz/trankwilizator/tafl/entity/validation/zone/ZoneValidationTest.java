package kz.trankwilizator.tafl.entity.validation.zone;

import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.zone.Zone;
import org.junit.jupiter.api.Test;

public class ZoneValidationTest extends ValidationTest<Zone> {
    @Override
    protected Zone createInstance() {
        return Zone.builder()
                .name("TEST")
                .build();
    }
    @Test
    public void givenNullName_whenValidate_thenHasConstraintViolations(){
        validateName(null, true);
    }
    @Test
    public void givenEmptyName_whenValidate_thenHasConstraintViolations(){
        validateName("", true);
    }
    @Test
    public void givenBlankName_whenValidate_thenHasConstraintViolations(){
        validateName("\n\n\n   \t\t \r", true);
    }
    @Test
    public void givenTooLongName_whenValidate_thenHasConstraintViolations(){
        validateName("r".repeat(76), true);
    }
    @Test
    public void givenCorrectZone_whenValidate_thenHasNotConstraintViolations(){
        whenValidate_thenHasNotConstraintViolations();
    }
    private void validateName(String name, boolean exists){
        getEntity().setName(name);
        whenValidate_thenHasConstraintViolation("name", exists);
    }
}
