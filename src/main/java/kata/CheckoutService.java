package kata;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private List<Item> cart = new ArrayList<>();
    private List<Discount> discounts = List.of(
            new Discount("A", 3, 20),
            new Discount("B", 2, 15));

    public void scanItem(Item item) {
        cart.add(item);
    }

    public long checkout() {
        long sumOfItems = cart.stream().map(Item::unitPrice).reduce(0L, Long::sum);
        for (Discount discount : discounts) {
            sumOfItems -= getDiscount(discount);
        }
        return sumOfItems;
    }

    private long getDiscount(Discount discount) {
        long countOfDiscountItems = cart.stream().filter(item -> item.item().equals(discount.discountItem())).count();
        return (countOfDiscountItems / discount.countOfItemsForDiscount()) * discount.discountPrice();
    }
}
