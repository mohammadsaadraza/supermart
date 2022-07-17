package com.sadapay.collections;

import com.sadapay.entities.Item;
import java.util.*;

public class Inventory {

    private static Inventory instance = null;
    private final Map<String, Item> items = new HashMap<>();

    private Inventory () {
    }

    public Integer size(){
        return items.size();
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public void clear(){
        this.items.clear();
    }

    public boolean containsItem(String name){
        return items.containsKey(name);
    }

    public String[] getItemList(){
        return items.keySet().toArray(new String[0]);
    }

    public Item getItem(String name) throws NoSuchElementException{
        if (!this.containsItem(name)){
            throw new NoSuchElementException("Item doesn't exist");
        }
        return items.get(name);
    }

    private Item add(Item i){
        items.put(i.getName(), i);
        return items.get(i.getName());
    }

    public Item addItem(String name, Double amount, Integer quantity){
        return this.add(new Item(name, amount, quantity));
    }

    public Item removeItem(String name) {
        this.getItem(name);
        return items.remove(name);
    }

    public Item consumeItem(String name, Integer consume) throws ArithmeticException {

        Item i = this.getItem(name);

        if (i.getQuantity() <= 0 || consume > i.getQuantity()){
            throw new ArithmeticException("Not Enough Quantity");
        }

        i.setQuantity(i.getQuantity() - consume);
        return this.add(i);
    }

    public Item stockItem(String name, Integer stock) {

        Item i = this.getItem(name);

        i.setQuantity(i.getQuantity() + stock);
        return this.add(i);
    }

    public static Inventory getInstance(){
        if(instance == null){
            instance = new Inventory();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "items=" + items +
                '}';
    }
}
