package com.sadapay.collections;

import com.sadapay.entities.Item;
import com.sadapay.entities.Offer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Collection Class for a Shopping Cart. This is a Singleton Class that allows only 1 instance.
 */
public class ShoppingCart {

    private static ShoppingCart instance = null;
    private final Map<String, Item> items = new HashMap<>();
    private final Map<String, Offer> offers = new HashMap<>();

    /**
     * Private Constructor for ShoppingCart
     */
    private ShoppingCart() {

    }

    /**
     * Returns the number of Items in ShoppingCart
     * @return Int
     */
    public Integer size(){
        return items.size();
    }

    /**
     * Checks if Items are present in ShoppingCart
     * @return boolean
     */
    public boolean isEmpty(){
        return items.isEmpty();
    }

    /**
     * Empties out the Items in ShoppingCart
     */
    public void clear(){
        this.items.clear();
        this.offers.clear();
    }

    /**
     * Checks if an Item is present in ShoppingCart
     * @param name Name of Item
     * @return boolean
     */
    public boolean containsItem(String name){
        return items.containsKey(name);
    }


    /**
     * Return names of Items in ShoppingCart
     * @return String[]
     */
    public String[] getCartItems(){
        return items.keySet().toArray(new String[0]);
    }

    /**
     * Return offers on Items in ShoppingCart
     * @return String
     */
    public String getCartOffers(){
        return offers.toString();
    }

    /**
     * Gets Item from ShoppingCart. Otherwise, throws an error.
     * @param name Name of Item
     * @return Item
     * @throws NoSuchElementException
     */
    public Item getFromCart(String name) throws NoSuchElementException{
        if (!this.containsItem(name)){
            throw new NoSuchElementException("Item doesn't exist");
        }
        return items.get(name);
    }

    /**
     * Private Method for adding Item to ShoppingCart
     * @param i Item
     * @return Item
     */
    private Item add(Item i){
        items.put(i.getName(), i);
        return items.get(i.getName());
    }

    /**
     * Adds an Item to ShoppingCart. If the item already exists, it updates the quantity.
     * @param name Name of Item
     * @param amount Price of Item
     * @param quantity Quantity of Item
     * @return Item
     */
    public Item addToCart(String name, Double amount, Integer quantity){
        if(!this.containsItem(name)){
            return this.add(new Item(name, amount, quantity));
        }

        Item i = this.getFromCart(name);
        i.setQuantity(i.getQuantity() + quantity);

        return this.add(i);
    }

    /**
     * Removes Quantity of Item from ShoppingCart. If new quantity == 0, it removes the Item.
     * If remove quantity is grater than the existing quantity, it throws an error.
     * @param name Name of item
     * @param remove Quantity of Item to remove
     * @return Item
     * @throws ArithmeticException
     */
    public Item removeFromCart(String name, Integer remove) throws ArithmeticException{

        Item i = this.getFromCart(name);

        if(Objects.equals(remove, i.getQuantity())){
            return items.remove(name);
        }

        if ( remove > i.getQuantity()){
            throw new ArithmeticException("Remove Amount exceeds quantity in Cart");
        }

        i.setQuantity(i.getQuantity() - remove);
        return this.add(i);
    }

    /**
     * Calculates Invoice for the current state of ShoppingCart
     * @return String
     */
    public String invoice(){

        Double totalPrice = 0.0; Double discounts = 0.0;

        for(Item i : items.values()){
            totalPrice += i.getAmount() * i.getQuantity();
            discounts += this.getDiscountPrice(i);
        }

        Double net = totalPrice - discounts;

        totalPrice = new BigDecimal(totalPrice.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        discounts = new BigDecimal(discounts.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        net = new BigDecimal(net.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();

        Formatter formatter = new Formatter();
        formatter.format("subtotal:%.2f, discount:%.2f, total:%.2f", totalPrice, discounts, net);
        return formatter.toString();

    }

    /**
     * Calculate the discount on Item, depending on Offer on the Item
     * @param item Item
     * @return Double
     */
    private Double getDiscountPrice(Item item){
        switch (this.offers.getOrDefault(item.getName(), Offer.None)){
            case buy_1_get_half_off:
                return Math.floor((float)item.getQuantity() / 2) * item.getAmount() * 0.5;
            case buy_2_get_1_free:
                return Math.floor((float)item.getQuantity() / 3) * item.getAmount();
            default:
                return 0.0;
        }
    }

    /**
     * Applies an Offer on Item in ShoppingCart
     * @param token Offer Enum for an Item
     * @param name Name of Item
     */
    public void applyDiscount(Offer token, String name) {
        this.getFromCart(name);
        this.offers.put(name, token);
    }

    /**
     * List Items in ShoppingCart
     * @return String
     */
    public String listCartItems(){
        StringBuilder out = new StringBuilder();
        out.append("Name Price Quantity");

        for (Item i : items.values()){
            out.append("\n").append(i.getName()).append(" ").append(i.getAmount()).append(" ").append(i.getQuantity());
        }
        return out.toString();
    }

    /**
     * Static Method for ShoppingCart class that returns the instance. If no instance exists, it returns a new one.
     * @return One and only 1 instance of ShoppingCart Class
     */
    public static ShoppingCart getInstance() {
        if (instance == null){
            instance = new ShoppingCart();
        }
        return instance;
    }

    /**
     * @return String representation of ShoppingCart and the Items inside
     */
    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                ", offers=" + offers +
                '}';
    }
}
