package kz.trankwilizator.tafl.entity.validation.arguments.provider.schedule;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalTime;
import java.util.stream.Stream;

public class StartLocalTimeArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(LocalTime.now(), ValidationResult.OK),
                Arguments.of(null, ValidationResult.ERROR),
                Arguments.of(LocalTime.now().plusMinutes(1), ValidationResult.ERROR),
                Arguments.of(LocalTime.now().minusMinutes(1), ValidationResult.OK)
        );
    }
}
