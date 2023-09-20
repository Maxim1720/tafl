package kz.trankwilizator.tafl.entity.validation.product;

import kz.trankwilizator.tafl.entity.product.Category;
import kz.trankwilizator.tafl.entity.product.Product;
import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.string.name.NameArgumentsProvider;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.MoneyArgumentsProvider;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class ProductValidationTest extends ValidationTest<Product> {
    @Override
    protected Product createInstance() {
        return Product.builder()
                .name("product name")
                .category(new Category())
                .price(new BigDecimal(15))
                .build();
    }

    @ParameterizedTest(name = "test {index}, price={0} has violations={1}")
    @ArgumentsSource(MoneyArgumentsProvider.class)
    public void givenPrice_whenValidate_thenHasConstraintViolations(BigDecimal price, ValidationResult hasCV){
        getEntity().setPrice(price);
        whenValidate_thenHasConstraintViolation("price", hasCV);
    }

    @ParameterizedTest(name = "test {index}: name={0}, result={1}")
    @ArgumentsSource(NameArgumentsProvider.class)
    public void givenName_whenValidate_thenHasConstraintViolations(String name, ValidationResult result){
        getEntity().setName(name);
        whenValidate_thenHasConstraintViolation("name", result);
    }

    @ParameterizedTest
    @ArgumentsSource(CategoryArgumentsProvider.class)
    public void givenCategory_whenValidate_thenHasConstraintViolations(Category category, ValidationResult result){
        getEntity().setCategory(category);
        whenValidate_thenHasConstraintViolation("category", result);
    }

    private static class CategoryArgumentsProvider implements ArgumentsProvider{

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            return Stream.of(
                    Arguments.of(null, ValidationResult.ERROR),
                    Arguments.of(new Category(), ValidationResult.OK)
            );
        }
    }
}
