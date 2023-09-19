package kz.trankwilizator.tafl.entity.validation.runnable;

import kz.trankwilizator.tafl.entity.launchable.App;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntityType;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.NameArgumentsProvider;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class AppValidationTest extends ValidationTest<App> {
    @Override
    protected App createInstance() {
        return App.builder()
                .name("app name")
                .type(new RunnableEntityType())
                .build();
    }


    @ParameterizedTest(name = "test {index}: value={0}, exists={1}")
    @ArgumentsSource(NameArgumentsProvider.class)
    public void givenName_whenValidate_thenHasConstraintViolations(String name, boolean exists){
        getEntity().setName(name);
        whenValidate_thenHasConstraintViolation("name", exists);
    }
}
