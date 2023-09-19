package kz.trankwilizator.tafl.entity.validation.product;

import kz.trankwilizator.tafl.entity.product.Category;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

public class CategoryValidationTest extends ValidationTest<Category> {
    @Override
    protected Category createInstance() {
        return Category.builder()
                .parentCategory(null)
                .name("category name")
                .build();
    }

    @ParameterizedTest(name = "test {index}: value = {0}, violations exists={1}")
    @ArgumentsSource(value = NameArgumentsProvider.class)
    public void givenName_whenValidate_thenHasConstraintViolations(String name, boolean exists){
        getEntity().setName(name);
        whenValidate_thenHasConstraintViolation("name", exists);
    }

    @ParameterizedTest(name = "test {index}: value = {0}, violations exists={1}")
    @ArgumentsSource(value = ParentCategoryProvider.class)
    public void givenParent_whenValidate_thenHasConstraintViolations(Category parent, boolean exists){
        getEntity().setParentCategory(parent);
        whenValidate_thenHasConstraintViolation("parentCategory", exists);
    }


    private static class NameArgumentsProvider implements ArgumentsProvider{

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
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

    private static class ParentCategoryProvider implements ArgumentsProvider{

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Arguments.of(null, false),
                    Arguments.of(new Category(), false)
            );
        }
    }
}
