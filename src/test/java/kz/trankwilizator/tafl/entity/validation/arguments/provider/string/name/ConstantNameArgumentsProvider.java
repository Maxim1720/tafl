package kz.trankwilizator.tafl.entity.validation.arguments.provider.string.name;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.StringArgumentProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

public class ConstantNameArgumentsProvider extends StringArgumentProvider {

    @Override
    protected List<Arguments> arguments() {
        return List.of(
                Arguments.of("R", ValidationResult.OK),
                Arguments.of("R".repeat(76), ValidationResult.ERROR),
                Arguments.of("TEST_TEST_TEST", ValidationResult.OK),
                Arguments.of("TEST__TEST", ValidationResult.ERROR),
                Arguments.of("_TEST__", ValidationResult.ERROR),
                Arguments.of("TEST_PERMANENT_USER", ValidationResult.OK),
                Arguments.of("r_", ValidationResult.ERROR),
                Arguments.of("R_", ValidationResult.ERROR)
        );
    }
}
