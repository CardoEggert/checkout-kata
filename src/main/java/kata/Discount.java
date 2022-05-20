package kata;

import java.util.Objects;

public final class Discount {

    private final String discountItem;
    private final int countOfItemsForDiscount;
    private final int discountPrice;

    public Discount(String discountItem, int countOfItemsForDiscount, int discountPrice) {
        this.discountItem = discountItem;
        this.countOfItemsForDiscount = countOfItemsForDiscount;
        this.discountPrice = discountPrice;
    }

    public String discountItem() {
        return discountItem;
    }

    public int countOfItemsForDiscount() {
        return countOfItemsForDiscount;
    }

    public int discountPrice() {
        return discountPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        var that = (Discount) obj;
        return Objects.equals(this.discountItem, that.discountItem) &&
                this.countOfItemsForDiscount == that.countOfItemsForDiscount &&
                this.discountPrice == that.discountPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountItem, countOfItemsForDiscount, discountPrice);
    }

    @Override
    public String toString() {
        return "Discount[" +
                "discountItem=" + discountItem + ", " +
                "countOfItemsForDiscount=" + countOfItemsForDiscount + ", " +
                "discountPrice=" + discountPrice + ']';
    }

}