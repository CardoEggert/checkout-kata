package kata;

import com.github.larseckart.tcr.CommitOnGreenExtension;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CommitOnGreenExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CheckoutTest {

    private final CheckoutService checkoutService = new CheckoutService();

    @Test
    void checkout_one_item() {
        final String item = "A";
        final Long unitPrice = 50L;

        checkoutService.scanItem(item, unitPrice);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(unitPrice);
    }
}
