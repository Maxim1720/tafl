package kz.trankwilizator.tafl.entity.validation.arguments.provider;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class MoneyArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(new BigDecimal(15), ValidationResult.OK),
                Arguments.of(new BigDecimal(-15), ValidationResult.ERROR),
                Arguments.of(new BigDecimal("5.555"), ValidationResult.OK),
                Arguments.of(null, ValidationResult.ERROR)
        );
    }
}