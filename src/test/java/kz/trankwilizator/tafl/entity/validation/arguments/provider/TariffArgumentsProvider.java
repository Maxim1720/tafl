package kz.trankwilizator.tafl.entity.validation.arguments.provider;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.zone.tariff.Tariff;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class TariffArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(null, ValidationResult.ERROR),
                Arguments.of(new Tariff(), ValidationResult.OK)
        );
    }
}
