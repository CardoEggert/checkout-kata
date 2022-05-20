package kata;

import java.util.List;
import java.util.Map;

import com.github.larseckart.tcr.CommitOnGreenExtension;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CommitOnGreenExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class CheckoutTest {

    private final List<Discount> discounts = List.of(
            new Discount("A", 3, 20),
            new Discount("B", 2, 15));
    private final CheckoutService checkoutService = new CheckoutService(Map.of(
            "A", new Item("A", 50L),
            "B", new Item("B", 30L),
            "C", new Item("C", 20L),
            "D", new Item("D", 15L)
    ), discounts);

    @Test
    void checkout_one_item() {
        final Item item = new Item("A", 50L);

        scanItemMultipleTimes(item.item(), 1);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(item.unitPrice());
    }

    @Test
    void checkout_two_items() {
        final Item item = new Item("A", 50L);
        final Item item2 = new Item("B", 30L);

        scanItemMultipleTimes(item.item(), 1);
        scanItemMultipleTimes(item2.item(), 1);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(item.unitPrice() + item2.unitPrice());
    }

    @Test
    void checkout_special_price_item_A_combo() {
        final Item item1 = new Item("A", 50L);

        scanItemMultipleTimes(item1.item(), 3);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(item1.unitPrice() + item1.unitPrice() + item1.unitPrice() - discounts.get(0).getDiscountPrice());
    }

    @Test
    void checkout_special_price_item_A_combo_twice() {
        final Item item = new Item("A", 50L);

        scanItemMultipleTimes(item.item(), 6);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo((item.unitPrice() + item.unitPrice() + item.unitPrice() - discounts.get(0).getDiscountPrice()) * 2);
    }

    @Test
    void checkout_special_price_item_B_combo() {
        final Item item = new Item("B", 30L);

        scanItemMultipleTimes(item.item(), 2);

        Assertions.assertThat(checkoutService.checkout()).isEqualTo(item.unitPrice() + item.unitPrice() - discounts.get(1).getDiscountPrice());
    }

    private void scanItemMultipleTimes(String itemName, int howManyTimes) {
        for (int i = 0; i < howManyTimes; i++) {
            checkoutService.scanItem(itemName);
        }
    }
}
