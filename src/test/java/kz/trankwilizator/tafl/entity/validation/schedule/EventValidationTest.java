package kz.trankwilizator.tafl.entity.validation.schedule;

import kz.trankwilizator.tafl.entity.schedule.Event;
import kz.trankwilizator.tafl.entity.schedule.Schedule;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.DescriptionArgumentsProvider;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.NameArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

public class EventValidationTest extends ValidationTest<Event> {
    @Override
    protected Event createInstance() {
        return Event.builder()
                .description("trestsrtst")
                .name("event name")
                .schedule(new Schedule())
                .build();
    }

    @ParameterizedTest(name = "test {index}: name={0}, exists={1}")
    @ArgumentsSource(NameArgumentsProvider.class)
    public void givenName_whenValidate_thenHasConstraintViolations(String name, boolean exists){
        getEntity().setName(name);
        whenValidate_thenHasConstraintViolation("name", exists);
    }

    @ParameterizedTest(name = "test {index}: discription={0}, exists={1}")
    @ArgumentsSource(DescriptionArgumentsProvider.class)
    public void givenDescription_whenValidate_thenHasConstraintViolations(String disc, boolean exists){
        getEntity().setDescription(disc);
        whenValidate_thenHasConstraintViolation("description", exists);
    }
}
