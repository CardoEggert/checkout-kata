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
        long countOfA = cart.stream().filter(item -> item.item().equals("A")).count();
        sumOfItems -= ((countOfA / 3) * 20);
        long countOfB = cart.stream().filter(item -> item.item().equals("B")).count();
        sumOfItems -= ((countOfB / 2) * 15);
        return sumOfItems;
    }
}
