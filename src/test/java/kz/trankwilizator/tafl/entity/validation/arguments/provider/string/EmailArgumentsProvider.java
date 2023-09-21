package kz.trankwilizator.tafl.entity.validation.arguments.provider.string;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;

public class EmailArgumentsProvider extends StringArgumentProvider {

    @Override
    protected List<Arguments> arguments() {
        return List.of(
                Arguments.of("test@test.ru", ValidationResult.OK),
                Arguments.of("rtedfwa@", ValidationResult.ERROR),
                Arguments.of("rawr@edwad", ValidationResult.ERROR),
                Arguments.of("dwadwa@dwadwad.", ValidationResult.ERROR)
        );
    }
}
