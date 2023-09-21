package kz.trankwilizator.tafl.entity.validation.arguments.provider.string;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class StringArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        ArrayList<Arguments> arguments = new ArrayList<>(stringArgs().toList());
        arguments.addAll(arguments());
        return arguments.stream();
    }

    private Stream<? extends Arguments> stringArgs(){
        return Stream.of(
                Arguments.of("", ValidationResult.ERROR),
                Arguments.of("\n\n\n  \t", ValidationResult.ERROR),
                Arguments.of("", ValidationResult.ERROR),
                Arguments.of(null, ValidationResult.ERROR)

        );
    }

    protected abstract List<Arguments> arguments();
}
