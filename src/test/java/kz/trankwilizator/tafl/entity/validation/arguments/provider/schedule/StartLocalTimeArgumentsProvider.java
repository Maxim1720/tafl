package kz.trankwilizator.tafl.entity.validation.arguments.provider.schedule;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalTime;
import java.util.stream.Stream;

public class StartLocalTimeArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(LocalTime.now(), false),
                Arguments.of(null, true),
                Arguments.of(LocalTime.now().plusHours(1), true),
                Arguments.of(LocalTime.now().minusHours(15), false),
                Arguments.of(LocalTime.of(23, 0).plusHours(1), false)
        );
    }
}
