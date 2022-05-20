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
        if (cart.stream().filter(item -> item.item().equals("A")).count() >= 3) {
            sumOfItems -= 20L;
        }
        return sumOfItems;
    }
}
