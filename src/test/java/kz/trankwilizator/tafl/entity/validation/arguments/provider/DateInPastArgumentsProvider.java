package kz.trankwilizator.tafl.entity.validation.arguments.provider;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Stream;

public class DateInPastArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(Date.from(new Date().toInstant().minus(1, ChronoUnit.MINUTES)), ValidationResult.ERROR),
                Arguments.of(Date.from(new Date().toInstant().plus(1, ChronoUnit.MINUTES)), ValidationResult.OK),
                Arguments.of(new Date(), ValidationResult.ERROR)
        );
    }
}
