package kz.trankwilizator.tafl.entity.validation.arguments.provider;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class DiscountArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(1.001, ValidationResult.ERROR),
                Arguments.of(-1.0, ValidationResult.ERROR),
                Arguments.of(null, ValidationResult.ERROR),
                Arguments.of(1.0, ValidationResult.OK),
                Arguments.of(0.0, ValidationResult.OK)
        );
    }
}
