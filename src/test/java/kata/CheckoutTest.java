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

        scanItemMultipleTimes(item, 1);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(item.unitPrice());
    }

    @Test
    void checkout_two_items() {
        final Item item = new Item("A", 50L);
        final Item item2 = new Item("B", 30L);

        scanItemMultipleTimes(item, 1);
        scanItemMultipleTimes(item2, 1);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(item.unitPrice() + item2.unitPrice());
    }

    @Test
    void checkout_special_price_item_A_combo() {
        final Long specialPrice = 130L;
        final Item item1 = new Item("A", 50L);

        scanItemMultipleTimes(item1, 3);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(specialPrice);
    }

    @Test
    void checkout_special_price_item_A_combo_twice() {
        final Long specialPrice = 130L;
        final Item item = new Item("A", 50L);

        scanItemMultipleTimes(item, 6);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(specialPrice * 2);
    }

    @Test
    void checkout_special_price_item_B_combo() {
        final Long specialPrice = 45L;
        final Item item = new Item("B", 30L);

        scanItemMultipleTimes(item, 2);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(specialPrice);
    }

    private void scanItemMultipleTimes(Item item, int howManyTimes) {
        for (int i = 0; i < howManyTimes; i++) {
            checkoutService.scanItem(item);
        }
    }
}
