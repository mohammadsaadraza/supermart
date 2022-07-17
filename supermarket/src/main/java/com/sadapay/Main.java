package com.sadapay;

import com.sadapay.collections.Inventory;
import com.sadapay.collections.ShoppingCart;
import com.sadapay.entities.Offer;


public class Main {
    public static void main(String[] args) {
        ShoppingCart s = ShoppingCart.getInstance();

        s.addToCart("bread", 10.5, 11);
        s.addToCart("soap", 10.0, 4);

        System.out.println(s.invoice());

        s.applyDiscount(Offer.buy_1_get_half_off, "bread");
        s.applyDiscount(Offer.buy_1_get_half_off, "soap");

        s.removeFromCart("soap", 2);

        System.out.println(s.invoice());
        System.out.println(s);

//        Inventory i = Inventory.getInstance();
//
//        i.addItem("bread", 10.0, 20);
//        i.addItem("soap", 20.0, 30);
//
//        i.consumeItem("bread", 10);
//        i.consumeItem("soap", 28);
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





    }
}
