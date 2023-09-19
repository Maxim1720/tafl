package kz.trankwilizator.tafl.entity.validation.tariff;

import kz.trankwilizator.tafl.entity.schedule.Schedule;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.zone.tariff.Tariff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

public class TariffValidationTest extends ValidationTest<Tariff> {
    @Override
    protected Tariff createInstance() {
        return Tariff.builder()
                .name("tariff")
                .schedule(new Schedule())
                .build();
    }



    @ParameterizedTest(name = "tariff name {index} = {0}")
    @ValueSource(strings = {"\n\n\n   \n"})
    @NullAndEmptySource
    public void givenName_whenValidate_thenHasConstraintViolations(String name){
        validateName(name, true);
    }


    @Test
    public void givenTESTName_whenValidate_thenHasNotConstraintViolations(){
        validateName("TEST", false);
    }


    @ParameterizedTest(name = "schedule {index} = {0} and constraint violations exists = {1}")
    @ArgumentsSource(value = SheduleArgumentsProvider.class)
    public void givenSchedule_whenValidate_thenHasConstraintViolations(Schedule s, boolean exists){
        getEntity().setSchedule(s);
        whenValidate_thenHasConstraintViolation("schedule", exists);
    }


    private void validateName(String name, boolean exists){
        getEntity().setName(name);
        whenValidate_thenHasConstraintViolation("name", exists);
    }

    private static class SheduleArgumentsProvider implements ArgumentsProvider{

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Arguments.of(null, true),
                    Arguments.of(new Schedule(), false)
            );
        }
    }
}
