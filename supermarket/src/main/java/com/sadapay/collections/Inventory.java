package com.sadapay.collections;

import com.sadapay.entities.Item;
import java.util.*;

/**
 * Collection Class for an Inventory. This is a Singleton Class that allows only 1 instance.
 */
public class Inventory {

    private static Inventory instance = null;
    private final Map<String, Item> items = new HashMap<>();

    /**
     * Private Constructor for Inventory
     */
    private Inventory () {
    }

    /**
     * Returns the number of Items in Inventory
     * @return Int
     */
    public Integer size(){
        return items.size();
    }

    /**
     * Checks if Items are present in Inventory
     * @return boolean
     */
    public boolean isEmpty(){
        return items.isEmpty();
    }

    /**
     * Empties out the Items in Inventory
     */
    public void clear(){
        this.items.clear();
    }

    /**
     * Checks if an Item is present in Inventory
     * @param name Name of Item
     * @return boolean
     */
    public boolean containsItem(String name){
        return items.containsKey(name);
    }

    /**
     * Returns names of Items in Inventory
     * @return String[]
     */
    public String[] getItemList(){
        return items.keySet().toArray(new String[0]);
    }

    /**
     * Gets an Item from Inventory. Otherwise, throws and error.
     * @param name Name of Item
     * @return Item
     * @throws NoSuchElementException
     */
    public Item getItem(String name) throws NoSuchElementException{
        if (!this.containsItem(name)){
            throw new NoSuchElementException("Item doesn't exist");
        }
        return items.get(name);
    }

    /**
     * Private Method for adding Item into Inventory
     * @param i Item instance
     * @return Item
     */
    private Item add(Item i){
        items.put(i.getName(), i);
        return items.get(i.getName());
    }

    /**
     * Add Item into Inventory
     * @param name Name of Item
     * @param amount Price of Item
     * @param quantity Quantity of Item
     * @return Item
     */
    public Item addItem(String name, Double amount, Integer quantity){
        return this.add(new Item(name, amount, quantity));
    }

    /**
     * Remove an Item from Inventory
     * @param name Name of Item
     * @return Item
     */
    public Item removeItem(String name) {
        this.getItem(name);
        return items.remove(name);
    }

    /**
     * Consume Item from Inventory.
     * If consume quantity is grater than the existing quantity, it throws an error.
     * @param name Name of Item
     * @param consume Quantity of Item
     * @return Item
     * @throws ArithmeticException
     */
    public Item consumeItem(String name, Integer consume) throws ArithmeticException {

        Item i = this.getItem(name);

        if (i.getQuantity() <= 0 || consume > i.getQuantity()){
            throw new ArithmeticException("Not Enough Quantity");
        }

        i.setQuantity(i.getQuantity() - consume);
        return this.add(i);
    }

    /**
     * Stock an Item in Inventory
     * @param name Name of Item
     * @param stock Quantity of Item
     * @return Item
     */
    public Item stockItem(String name, Integer stock) {

        Item i = this.getItem(name);

        i.setQuantity(i.getQuantity() + stock);
        return this.add(i);
    }

    /**
     * List items in the Inventory
     * @return String
     */
    public String listItems(){
        StringBuilder out = new StringBuilder();
        out.append("Name Price Quantity");

        for (Item i : items.values()){
            out.append("\n").append(i.getName()).append(" ").append(i.getAmount()).append(" ").append(i.getQuantity());
        }
        return out.toString();
    }

    /**
     * Static Method for Inventory class that returns the instance. If no instance exists, it returns a new one.
     * @return One and only 1 instance of Inventory Class
     */
    public static Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }
        return instance;
    }

    /**
     * @return String representation of Inventory and the Items inside.
     */
    @Override
    public String toString() {
        return "Inventory{" +
                "items=" + items +
                '}';
    }
}
