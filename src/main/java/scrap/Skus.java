package scrap;

public class Skus {
    private String name;
    private double currentPrice;
    private double oldPrice;
    private boolean available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
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
