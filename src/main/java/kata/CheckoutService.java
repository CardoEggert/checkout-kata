package kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutService {

    private List<Item> cart;
    private Map<String, Item> warehouse;
    private List<Discount> discounts;

    public CheckoutService(Map<String, Item> warehouse, List<Discount> discounts) {
        cart = new ArrayList<>();
        this.warehouse = warehouse;
        this.discounts = discounts;
    }

    public void scanItem(String itemName) {
        cart.add(warehouse.get(itemName));
    }

    public long checkout() {
        long sumOfItems = cart.stream().map(Item::unitPrice).reduce(0L, Long::sum);
        for (Discount discount : discounts) {
            sumOfItems -= discount.getDiscount(cart);
        }
        return sumOfItems;
    }

}
