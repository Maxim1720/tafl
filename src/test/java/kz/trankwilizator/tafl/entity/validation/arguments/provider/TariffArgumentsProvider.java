package kz.trankwilizator.tafl.entity.validation.arguments.provider;

import kz.trankwilizator.tafl.entity.zone.tariff.Tariff;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class TariffArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(null, true),
                Arguments.of(new Tariff(), false)
        );
    }
}
