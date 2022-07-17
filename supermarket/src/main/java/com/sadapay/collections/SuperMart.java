package com.sadapay.collections;

import com.sadapay.entities.Item;
import com.sadapay.entities.Offer;

import java.util.NoSuchElementException;

/**
 * Abstraction over Collection classes. This is a Singleton class, that allows only 1 instance.
 */
public class SuperMart {

    private static SuperMart instance = null;
    private final Inventory inventory;
    private final ShoppingCart cart;

    /**
     * Private Constructor for SuperMart. Initializes the instance for Inventory and ShoppingCart collections.
     */
    private SuperMart(){
        inventory = Inventory.getInstance();
        cart = ShoppingCart.getInstance();
    }

    /**
     * Loads the initial inventory for a SuperMarket
     * @param item Item
     */
    public void loadInventory(Item item){
        this.inventory.addItem(item.getName(), item.getAmount(), item.getQuantity());
    }

    /**
     * Clears the inventory of SuperMarket
     */
    public void resetInventory(){
        this.inventory.clear();
    }

    /**
     * Clears the shopping cart state
     */
    public void resetCart(){
        this.cart.clear();
    }

    /**
     * Adds Item available in Inventory to Cart. Handles error; NoSuchElementException from Inventory,
     * ArithmeticException from when quantity available exceeds the requested quantity.
     * @param name Name of Item
     * @param quantity Quantity of Item
     * @return String
     */
    public String add(String name, Integer quantity){

        try{
            Item item = this.inventory.getItem(name);
            this.inventory.consumeItem(name, quantity);
            this.cart.addToCart(name, item.getAmount(), quantity);
            return "added " + name + " " + quantity;
        }
        catch (NoSuchElementException e){
            return name + " doesn't exist in inventory";
        }
        catch (ArithmeticException e){
            return name + " quantity of " + quantity + " is more than available in inventory";
        }
        catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * Sets an Offer on Item in Cart. Handles errors; IllegalArgumentException for Offer not available,
     * NoSuchElementException for when Item isn't present in Cart.
     * @param token Offer Enum, in string form
     * @param name Name of Item
     * @return String
     */
    public String offer(String token, String name){

        try{
            this.cart.applyDiscount(Offer.valueOf(token), name);
            return "offer added";
        }
        catch (IllegalArgumentException e){
            return "offer " + token + " isn't available";
        }
        catch (NoSuchElementException e){
            return name + " doesn't exist in inventory";
        }
        catch(Exception e){
            return e.getMessage();
        }

    }

    /**
     * Remove Item from Cart and stocks back into Inventory. Handles error; NoSuchElementException from Inventory,
     * ArithmeticException from when remove quantity exceeds the quantity in Cart.
     * @param name Name of Item
     * @param quantity Quantity of Item
     * @return String
     */
    public String remove(String name, Integer quantity){

        try{
            Item item = this.inventory.getItem(name);
            this.cart.removeFromCart(name, quantity);
            this.inventory.stockItem(name, quantity);
            return "removed " + name + " " + quantity;
        }
        catch (NoSuchElementException e){
            return name + " doesn't exist in inventory";
        }
        catch (ArithmeticException e){
            return name + " quantity of " + quantity + " is more than present in cart";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    /**
     * Calculates invoice for Items in Cart
     * @return String
     */
    public String bill(){
        return this.cart.invoice();
    }

    /**
     * Ends the Cart state and starts fresh
     * @return String
     */
    public String checkout(){
        if(this.cart.size() == 0){
            return "empty cart";
        }
        this.resetCart();
        return "done";
    }

    /**
     * Lists the Items of Collections in a tabular format
     * @param collection inventory or collection, to list
     * @return String
     */
    public String list(String collection){
        switch (collection){
            case "inventory":
                return inventory.listItems();
            case "cart":
                return cart.listCartItems();
            default:
                return "wrong command";
        }
    }

    /**
     * Static Method for SuperMart class that returns the instance. If no instance exists, it returns a new one.
     * @return One and only 1 instance of SuperMart Class
     */
    public static SuperMart getInstance(){
        if (instance == null){
            instance = new SuperMart();
        }
        return instance;
    }

    /**
     * @return String representation of SuperMart; Inventory and Cart
     */
    @Override
    public String toString() {
        return "SuperMart{" +
                "inventory=" + inventory +
                ", cart=" + cart +
                '}';
    }
}
