package kz.trankwilizator.tafl.entity.validation.arguments.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class PriceArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(new BigDecimal(15), false),
                Arguments.of(new BigDecimal(-15), true),
                Arguments.of(new BigDecimal("5.555"), false),
                Arguments.of(null, true)
        );
    }
}