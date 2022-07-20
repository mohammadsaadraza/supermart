package com.sadapay.collections;

import com.sadapay.entities.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperMartTest {

    SuperMart mart;

    @BeforeEach
    void setUp(){
        mart = SuperMart.getInstance();
        mart.resetCart();
        mart.resetInventory();

        mart.loadInventory(new Item("bread", 2.50, 10));
        mart.loadInventory(new Item("soap", 10.00, 100));
    }

    @Test
    @DisplayName("Test Singleton Class")
    void testSingleton(){
        SuperMart test = SuperMart.getInstance();

        assertEquals(mart,test);
    }

    @Test
    @DisplayName("Test Mart Add")
    void testAdd(){

        assertAll(
                () -> assertEquals("glass doesn't exist in inventory", mart.add("glass", 5)),
                () -> assertEquals("bread quantity of 12 is more than available in inventory", mart.add("bread", 12)),
                () -> assertEquals("added soap 5", mart.add("soap", 5))
        );
    }

    @Test
    @DisplayName("Test Mart Remove")
    void testRemove(){

        mart.add("soap", 5);

        assertAll(
                () -> assertEquals("glass doesn't exist in inventory/cart", mart.remove("glass", 5)),
                () -> assertEquals("soap quantity of 12 is more than present in cart", mart.remove("soap", 12)),
                () -> assertEquals("removed soap 5", mart.remove("soap", 5)),
                () -> assertEquals("soap doesn't exist in inventory/cart", mart.remove("soap", 5))
        );
    }

    @Test
    @DisplayName("Test Mart Offer")
    void testOffer(){

        assertAll(
                () -> assertEquals("soap doesn't exist in cart", mart.offer("buy_2_get_1_free", "soap")),
                () -> assertEquals("offer arb_offer isn't available", mart.offer("arb_offer", "soap")),
                () -> assertEquals("soap doesn't exist in cart", mart.offer("buy_2_get_1_free", "soap")),
                () -> assertDoesNotThrow(() -> mart.add("soap", 5)),
                () -> assertEquals("offer added", mart.offer("buy_2_get_1_free", "soap"))
        );
    }

    @Test
    @DisplayName("Test Mart List")
    void testList(){

        assertAll(
                () -> assertEquals("wrong command", mart.list("arb_coll")),
                () -> assertDoesNotThrow(() -> mart.list("inventory")),
                () -> assertDoesNotThrow(() -> mart.list("cart"))
        );
    }

    @Test
    @DisplayName("Test Mart Checkout")
    void testCheckout(){

        assertAll(
                () -> assertEquals("empty cart", mart.checkout()),
                () -> assertDoesNotThrow(() -> mart.add("soap", 5)),
                () -> assertEquals("done", mart.checkout())
        );
    }

}