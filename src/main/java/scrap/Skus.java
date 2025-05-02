package scrap;

public class Skus {
    private String name;
    private Float currentPrice;
    private Float oldPrice;
    private boolean available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Skus{" +
                "name='" + name + '\'' +
                ", currentPrice=" + currentPrice +
                ", oldPrice=" + oldPrice +
                ", available=" + available +
                '}';
    }
}
