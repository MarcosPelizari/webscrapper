package scrap;

public class Skus {
    private String name;
    private Double currentPrice;
    private Double oldPrice;
    private boolean available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
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
