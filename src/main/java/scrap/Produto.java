package scrap;

import java.util.Arrays;
import java.util.List;

public class Produto {
    private String title;
    private String brand;
    private String[] categories;
    private String description;
    private List<Skus> skus;
    private List<Properties> properties;
    private List<Reviews> reviews;
    private Float avgReview;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Skus> getSkus() {
        return skus;
    }

    public void setSkus(List<Skus> skus) {
        this.skus = skus;
    }

    public List<Properties> getProperties() {
        return properties;
    }

    public void setProperties(List<Properties> properties) {
        this.properties = properties;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public Float getAvgReview() {
        return avgReview;
    }

    public void setAvgReview(Float avgReview) {
        this.avgReview = avgReview;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "title='" + title + '\'' +
                ", brand='" + brand + '\'' +
                ", categories=" + Arrays.toString(categories) +
                ", description='" + description + '\'' +
                ", skus=" + skus +
                ", properties=" + properties +
                ", review=" + reviews +
                ", avgReview=" + avgReview +
                ", url='" + url + '\'' +
                '}';
    }
}
