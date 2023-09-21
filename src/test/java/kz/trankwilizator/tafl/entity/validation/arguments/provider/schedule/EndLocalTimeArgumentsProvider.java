package kz.trankwilizator.tafl.entity.validation.arguments.provider.schedule;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalTime;
import java.util.stream.Stream;

public class EndLocalTimeArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(LocalTime.now().plusHours(150), ValidationResult.OK),
                Arguments.of(null, ValidationResult.ERROR),
                Arguments.of(LocalTime.now().minusHours(150),  ValidationResult.ERROR)
        );
    }
}
