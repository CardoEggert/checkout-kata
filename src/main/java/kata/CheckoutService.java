package kata;

public class CheckoutService {

    private Long price;

    public void scanItem(String item, Long unitPrice) {
        price = unitPrice;
    }

    public long checkout() {
        return price;
    }
}
