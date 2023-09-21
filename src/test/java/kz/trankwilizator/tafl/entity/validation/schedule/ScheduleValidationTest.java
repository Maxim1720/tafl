package kz.trankwilizator.tafl.entity.validation.schedule;

import kz.trankwilizator.tafl.entity.schedule.Schedule;
import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.schedule.EndLocalTimeArgumentsProvider;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.schedule.StartLocalTimeArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.time.LocalTime;


public class ScheduleValidationTest extends ValidationTest<Schedule> {
    @Override
    protected Schedule createInstance() {
        return Schedule.builder()
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(5))
                .build();
    }

    @ParameterizedTest(name = "test {index}: start time={0}, result={1}")
    @ArgumentsSource(StartLocalTimeArgumentsProvider.class)
    public void givenStartTime_whenValidate_thenHasViolations(LocalTime start, ValidationResult result){
        getEntity().setStartTime(start);
        whenValidate_thenHasConstraintViolation("startTime", result);
    }

    @ParameterizedTest(name = "test {index}: end time = {0}, result = {1}")
    @ArgumentsSource(EndLocalTimeArgumentsProvider.class)
    public void givenEndTime_whenValidate_thenHasViolations(LocalTime end, ValidationResult result){
        getEntity().setEndTime(end);
        whenValidate_thenHasConstraintViolation("endTime", result);
    }
}
