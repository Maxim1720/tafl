package kz.trankwilizator.tafl.entity.validation.arguments.provider.string.name;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.StringArgumentProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

public class NameArgumentsProvider extends StringArgumentProvider {

    @Override
    protected List<Arguments> arguments() {
        return List.of(
                Arguments.of("test category lol", ValidationResult.OK),
                Arguments.of("c".repeat(76), ValidationResult.ERROR)
        );
    }
}

