package kz.trankwilizator.tafl.entity.validation.arguments.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class NameArgumentsProvider  implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of("", true),
                Arguments.of("\n\n\n  \t", true),
                Arguments.of("test category lol", false),
                Arguments.of("", true),
                Arguments.of("c".repeat(76), true),
                Arguments.of(null, true)

        );
    }
}

