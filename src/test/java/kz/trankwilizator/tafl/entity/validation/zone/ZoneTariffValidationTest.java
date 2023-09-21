package kz.trankwilizator.tafl.entity.validation.zone;

import kz.trankwilizator.tafl.entity.validation.ValidationResult;
import kz.trankwilizator.tafl.entity.validation.ValidationTest;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.MoneyArgumentsProvider;
import kz.trankwilizator.tafl.entity.validation.arguments.provider.TariffArgumentsProvider;
import kz.trankwilizator.tafl.entity.zone.tariff.Tariff;
import kz.trankwilizator.tafl.entity.zone.tariff.ZoneTariff;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.math.BigDecimal;

public class ZoneTariffValidationTest extends ValidationTest<ZoneTariff> {
    @Override
    protected ZoneTariff createInstance() {
        return ZoneTariff.builder()
                .price(new BigDecimal(415))
                .tariff(new Tariff())
                .build();
    }
    @ParameterizedTest(name = "test {index}: price={0}, result={1}")
    @ArgumentsSource(MoneyArgumentsProvider.class)
    public void givenPrice_whenValidate_thenHasConstraintViolations(BigDecimal price, ValidationResult result){
        getEntity().setPrice(price);
        whenValidate_thenHasConstraintViolation("price", result);
    }

    @ParameterizedTest(name = "test {index}: price={0}, result={1}")
    @ArgumentsSource(TariffArgumentsProvider.class)
    public void givenTariff_whenValidate_thenHasConstraintViolations(Tariff tariff, ValidationResult result){
        getEntity().setTariff(tariff);
        whenValidate_thenHasConstraintViolation("tariff", result);
    }

}
