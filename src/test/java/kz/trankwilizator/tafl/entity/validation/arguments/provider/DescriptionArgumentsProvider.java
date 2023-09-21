package kz.trankwilizator.tafl.entity.validation.arguments.provider;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.StringArgumentProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

public class DescriptionArgumentsProvider extends StringArgumentProvider {

    @Override
    protected List<Arguments> arguments() {
        return List.of(
                Arguments.of("somedexccwadwada", ValidationResult.OK),
                Arguments.of("r".repeat(256), ValidationResult.ERROR)
        );
    }
}
