package org.example.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Medicine implements Comparable<Medicine> {
    private final long id;
    private final String name;
    private final String sku;
    private final MedicineCategory category;
    private BigDecimal price;
    private int inStock;

    public Medicine(long id, String name, String sku, MedicineCategory category,
                    BigDecimal price, int inStock) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.category = category;
        this.price = price;
        this.inStock = inStock;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public MedicineCategory getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    @Override
    public int compareTo(Medicine other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(sku, medicine.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return "Medicine [" + name + " | SKU: " + sku +
                " | Category: " + category.getDescription() +
                " | Price: " + price + " | inStock: " + inStock + "]";
    }
}
