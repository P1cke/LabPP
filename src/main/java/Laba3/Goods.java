package Laba3;

import java.util.Arrays;
import java.util.Objects;

// Класс "Товар"
class Goods implements Product {
    private String[] products;
    private String name;
    private int price;

    public Goods(String[] products, String name, int price) throws InvalidPriceException {
        if (price <= 0) {
            throw new InvalidPriceException("Цена должна быть больше нуля");
        }
        this.products = products;
        this.name = name;
        this.price = price;
    }

    @Override
    public String[] getProducts() {
        return products;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int calculateTotalPrice() {
        return price * products.length;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "products=" + Arrays.toString(products) +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Goods goods = (Goods) obj;
        return price == goods.price &&
                Arrays.equals(products, goods.products) &&
                Objects.equals(name, goods.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, price);
        result = 31 * result + Arrays.hashCode(products);
        return result;
    }
}