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
        final Item item = new Item("A", 50L);

        checkoutService.scanItem(item);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(item.unitPrice());
    }

    @Test
    void checkout_two_items() {
        final Item item = new Item("A", 50L);
        final Item item2 = new Item("B", 30L);

        checkoutService.scanItem(item);
        checkoutService.scanItem(item2);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(item.unitPrice() + item2.unitPrice());
    }

    @Test
    void checkout_special_price_item_combo() {
        final Long specialPrice = 130L;
        final Item item1 = new Item("A", 50L);

        checkoutService.scanItem(item1);
        checkoutService.scanItem(item1);
        checkoutService.scanItem(item1);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(specialPrice);
    }
}
