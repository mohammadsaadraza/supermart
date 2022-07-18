package com.sadapay;

import com.sadapay.collections.Inventory;
import com.sadapay.collections.ShoppingCart;
import com.sadapay.collections.SuperMart;
import com.sadapay.entities.Item;
import com.sadapay.entities.Offer;
import com.sadapay.utils.Interpreter;


public class Main {
    public static void main(String[] args) {
//        ShoppingCart s = ShoppingCart.getInstance();
//
//        s.addToCart("bread", 10.5, 11);
//        s.addToCart("soap", 10.0, 4);
//
//        System.out.println(s.invoice());
//
//        s.applyDiscount(Offer.buy_1_get_half_off, "bread");
//        s.applyDiscount(Offer.buy_1_get_half_off, "soap");
//
//        s.removeFromCart("soap", 2);
//
//        System.out.println(s.invoice());
//        System.out.println(s);
//        System.out.println(s.listCartItems());

//        Inventory i = Inventory.getInstance();
//
//        i.addItem("bread", 10.0, 20);
//        i.addItem("soap", 20.0, 30);
//
//        System.out.println(i.listItems());
//
//        i.consumeItem("bread", 10);
//        i.consumeItem("soap", 28);
//
//        System.out.println(i.listItems());
//
//        try{
//            i.consumeItem("soap", 5);
//        }catch (ArithmeticException e){
//            System.out.println(e.getMessage());
//        }
//
//        i.stockItem("bread", 10);
//        i.stockItem("soap", 30);
//
//        i.removeItem("soap");
//
//        System.out.println(i);

//        SuperMart mart = SuperMart.getInstance();
//
//        mart.loadInventory(new Item("bread", 2.50, 10));
//        mart.loadInventory(new Item("soap", 10.00, 100));
//
//        System.out.println(mart.checkout());
//        System.out.println(mart.add("soap", 5));
//        System.out.println(mart.add("bread", 1));
//        System.out.println(mart.bill());
//
//        System.out.println(mart.offer(Offer.buy_2_get_1_free.name(), "soap"));
//        System.out.println(mart.bill());
//
//        System.out.println(mart.add("soap", 1));
//        System.out.println(mart.bill());
//
//        System.out.println(mart.offer(Offer.buy_1_get_half_off.name(), "bread"));
//        System.out.println(mart.add("bread", 1));
//
//        System.out.println(mart.bill());
//        System.out.println(mart.checkout());
//
//        System.out.println(mart.checkout());
//
//        System.out.println(mart.toString());

        Interpreter ci = Interpreter.getInstance();

        ci.mart.loadInventory(new Item("bread", 2.50, 10));
        ci.mart.loadInventory(new Item("soap", 10.00, 100));

        System.out.println(ci.command("checkout"));
        System.out.println(ci.command("add soap 5"));
        System.out.println(ci.command("added bread 1"));
        System.out.println(ci.command("bill"));

        System.out.println(ci.command("offer buy_2_get_1jhj_free soap"));
        System.out.println(ci.command("bill"));

        System.out.println(ci.command("add soap 1"));
        System.out.println(ci.command("bill"));

        System.out.println(ci.command("offer buy_1_get_half_off bread"));
        System.out.println(ci.command("add bread 1"));

        System.out.println(ci.command("list carthg"));

        System.out.println(ci.command("remove soap 6hbh"));
        System.out.println(ci.command("remove bread 2"));
        System.out.println(ci.command("bill"));
        System.out.println(ci.command("checkout"));

        System.out.println(ci.mart.toString());

    }
}
