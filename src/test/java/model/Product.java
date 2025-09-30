package model;

public class Product {

    String name;
    String price;
    String description;

    public Product(String name, String price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product() {

    }

    public String getTitle() {
        return name;
    }

    public Product withName(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public Product withPrice(String price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
