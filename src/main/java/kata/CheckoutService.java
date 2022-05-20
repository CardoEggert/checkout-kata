package kata;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private List<Item> cart = new ArrayList<>();

    public void scanItem(Item item) {
        cart.add(item);
    }

    public long checkout() {
        long sumOfItems = cart.stream().map(Item::unitPrice).reduce(0L, (subPrice, price) -> subPrice + price);
        final long discount = getDiscount("A", 3, 20);
        sumOfItems -= discount;
        long countOfB = cart.stream().filter(item -> item.item().equals("B")).count();
        sumOfItems -= ((countOfB / 2) * 15);
        return sumOfItems;
    }

    private long getDiscount(String discountItem, int countOfItemsForDiscount, int discountPrice) {
        long countOfA = cart.stream().filter(item -> item.item().equals(discountItem)).count();
        final long discount = (countOfA / countOfItemsForDiscount) * discountPrice;
        return discount;
    }
}
