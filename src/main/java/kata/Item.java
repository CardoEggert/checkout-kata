package kata;

import java.util.Objects;

public final class Item {

    private final String item;
    private final Long unitPrice;

    public Item(String item, Long unitPrice) {
        this.item = item;
        this.unitPrice = unitPrice;
    }

    public String item() {
        return item;
    }

    public Long unitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        var that = (Item) obj;
        return Objects.equals(this.item, that.item) &&
                Objects.equals(this.unitPrice, that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, unitPrice);
    }

    @Override
    public String toString() {
        return "Item[" +
                "item=" + item + ", " +
                "unitPrice=" + unitPrice + ']';
    }

}