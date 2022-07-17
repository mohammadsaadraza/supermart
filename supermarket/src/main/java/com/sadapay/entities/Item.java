package com.sadapay.entities;

public class Item {
    private final String name;
    private final Double amount;
    private Integer quantity;


    public Item(String name, Double amount, Integer quantity) {
        this.name = name;

        if(amount <= 0.0 || quantity < 0){
            throw new IllegalArgumentException("Number cannot be zero or negative");
        }

        this.amount = amount;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static Item copy(Item i){
        return new Item(i.getName(), i.getAmount(), i.getQuantity());
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!getName().equals(item.getName())) return false;
        if (!getAmount().equals(item.getAmount())) return false;
        return getQuantity().equals(item.getQuantity());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAmount().hashCode();
        result = 31 * result + getQuantity().hashCode();
        return result;
    }
}
