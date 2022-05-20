package kata;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private List<Long> prices = new ArrayList<>();

    public void scanItem(String item, Long unitPrice) {
        prices.add(unitPrice);
    }

    public long checkout() {
        return prices.stream().reduce(0L, (subPrice, price) -> subPrice + price);
    }
}
