package com.sadapay.collections;

import com.sadapay.entities.Item;
import com.sadapay.entities.Offer;

import java.util.*;

public class ShoppingCart {

    private static ShoppingCart instance = null;
    private final Map<String, Item> items = new HashMap<>();
    private final Map<String, Offer> offers = new HashMap<>();

    private ShoppingCart() {

    }

    public Integer size(){
        return items.size();
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void clear(){
        this.items.clear();
        this.offers.clear();
    }

    public boolean containsItem(String name){
        return items.containsKey(name);
    }

    public String[] getCartItems(){
        return items.keySet().toArray(new String[0]);
    }

    public String getCartOffers(){
        return offers.toString();
    }

    public Item getFromCart(String name) throws NoSuchElementException{
        if (!this.containsItem(name)){
            throw new NoSuchElementException("Item doesn't exist");
        }
        return items.get(name);
    }

    private Item add(Item i){
        items.put(i.getName(), i);
        return items.get(i.getName());
    }

    public Item addToCart(String name, Double amount, Integer quantity){
        if(!this.containsItem(name)){
            return this.add(new Item(name, amount, quantity));
        }

        Item i = this.getFromCart(name);
        i.setQuantity(i.getQuantity() + quantity);

        return this.add(i);
    }

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

    public String invoice(){
        Double totalPrice = 0.0; Double discounts = 0.0;

        for(Item i : items.values()){
            totalPrice += i.getAmount() * i.getQuantity();
            discounts += this.getDiscountPrice(i);
        }

        return "subtotal:" + totalPrice + ", discount:" + discounts + ", total:" + (totalPrice - discounts);
    }

    public Double getDiscountPrice(Item item){
        switch (this.offers.getOrDefault(item.getName(), Offer.None)){
            case buy_1_get_half_off:
                return Math.floor((float)item.getQuantity() / 2) * item.getAmount() * 0.5;
            case buy_2_get_1_free:
                return Math.floor((float)item.getQuantity() / 3) * item.getAmount();
            default:
                return 0.0;
        }
    }

    public void applyDiscount(Offer token, String name) {
        this.getFromCart(name);
        this.offers.put(name, token);
    }

    public static ShoppingCart getInstance() {
        if (instance == null){
            instance = new ShoppingCart();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }
}
