package kata;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private List<Item> cart = new ArrayList<>();

    public void scanItem(Item item) {
        cart.add(item);
    }

    public long checkout() {
        long sumOfItems = cart.stream().map(Item::unitPrice).reduce(0L, Long::sum);
        sumOfItems -= getDiscount("A", 3, 20);
        sumOfItems -= getDiscount("B", 2, 15);
        return sumOfItems;
    }

    private long getDiscount(String discountItem, int countOfItemsForDiscount, int discountPrice) {
        long countOfDiscountItems = cart.stream().filter(item -> item.item().equals(discountItem)).count();
        return (countOfDiscountItems / countOfItemsForDiscount) * discountPrice;
    }
}
