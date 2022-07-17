package com.sadapay.entities;

/**
 * Entity representation of an Item in the Supermarket System
 * Properties Name and Amount are const. Quantity can be reassigned.
 */
public class Item {
    private final String name;
    private final Double amount;
    private Integer quantity;


    /**
     * Constructor for an Item.
     *
     * Checks the constraints; quantity >= 0 and amount > 0; to be true. Otherwise, throws and IllegalArgumentException
     * @param name Name of Item
     * @param amount Price of Item
     * @param quantity Quantity of Item
     */
    public Item(String name, Double amount, Integer quantity) throws IllegalArgumentException {
        this.name = name;

        if(amount <= 0.0 || quantity < 0){
            throw new IllegalArgumentException("Number cannot be zero or negative");
        }

        this.amount = amount;
        this.quantity = quantity;
    }

    /**
     * @return Name of Item
     */
    public String getName() {
        return name;
    }

    /**
     * @return Price of Item
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @return Quantity of Item
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity Set Quantity of Item
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Function for creating a new instance of Item, with the same fields as of the item argument
     * @param item Object of Class Item
     * @return Item
     */
    public static Item copy(Item item){
        return new Item(item.getName(), item.getAmount(), item.getQuantity());
    }

    /**
     * @return String representation of the Item
     */
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", quantity=" + quantity +
                '}';
    }

    /**
     * Checks if properties of argument Object o are same as of this instance of Item.
     * @param o Upcasted object of Item
     * @return True or False
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!getName().equals(item.getName())) return false;
        if (!getAmount().equals(item.getAmount())) return false;
        return getQuantity().equals(item.getQuantity());
    }

    /**
     * Hash Code for the combined properties of Item; name, price, quantity
     * @return Int hash code
     */
    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAmount().hashCode();
        result = 31 * result + getQuantity().hashCode();
        return result;
    }
}
