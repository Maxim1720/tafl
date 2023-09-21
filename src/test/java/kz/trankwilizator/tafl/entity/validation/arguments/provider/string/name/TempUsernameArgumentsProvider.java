package kz.trankwilizator.tafl.entity.validation.arguments.provider.string.name;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.StringArgumentProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

public class TempUsernameArgumentsProvider extends StringArgumentProvider {
    @Override
    protected List<Arguments> arguments() {
        return List.of(
                Arguments.of("21321321", ValidationResult.ERROR),
                Arguments.of("123456123456", ValidationResult.OK),
                Arguments.of("dwadawdaw", ValidationResult.ERROR),
                Arguments.of("123123d12312", ValidationResult.ERROR)
        );
    }
}
