package com.sadapay.collections;

import com.sadapay.entities.Offer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    ShoppingCart s;

    @BeforeEach
    void setUp(){
        s = ShoppingCart.getInstance();
        s.clear();
        s.addToCart("bread", 2.50, 2);
        s.addToCart("soap", 10.00, 6);
    }

    @Test
    @DisplayName("Test Singleton Class")
    void testSingleton(){
        ShoppingCart test = ShoppingCart.getInstance();

        assertEquals(s, test);
    }

    @Test
    @DisplayName("Test Item Fetching")
    void testItemFetch(){
        assertAll(
                () -> assertThrows(NoSuchElementException.class, () -> s.getFromCart("glass")),
                () -> assertFalse(s.containsItem("glass")),
                () -> assertDoesNotThrow(() -> s.getFromCart("bread"))
        );
    }

    @Test
    @DisplayName("Test Item Addition")
    void testItemAdd(){
        s.addToCart("glass", 30.00, 5);

        assertAll(
                () -> assertDoesNotThrow(() -> s.getFromCart("glass")),
                () -> assertTrue(s.containsItem("glass")),
                () -> assertEquals(30.0, s.getFromCart("glass").getAmount()),
                () -> assertEquals(5, s.getFromCart("glass").getQuantity())
        );
    }

    @Test
    @DisplayName("Test Item Removal")
    void testItemRemove(){

        assertAll(
                () -> assertThrows(NoSuchElementException.class, () -> s.removeFromCart("glass", 5)),
                () -> assertFalse(s.containsItem("glass")),
                () -> assertDoesNotThrow(() -> s.addToCart("glass", 30.00, 5)),
                () -> assertDoesNotThrow(() -> s.removeFromCart("glass", 2)),
                () -> assertTrue(s.containsItem("glass")),
                () -> assertEquals(3, s.getFromCart("glass").getQuantity()),
                () -> assertThrows(ArithmeticException.class, () -> s.removeFromCart("glass", 5)),
                () -> assertDoesNotThrow(() -> s.removeFromCart("glass", 3)),
                () -> assertFalse(s.containsItem("glass"))
        );
    }

    @Test
    @DisplayName("Test Item Offers")
    void testOffers(){

        s.applyDiscount(Offer.buy_2_get_1_free, "soap");
        s.applyDiscount(Offer.buy_1_get_half_off, "bread");

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> s.applyDiscount(Offer.valueOf("arb_offer"), "soap")),
                () -> assertThrows(NoSuchElementException.class, () -> s.applyDiscount(Offer.buy_1_get_half_off, "glass")),
                () -> assertEquals("subtotal:65.00, discount:21.25, total:43.75", s.invoice())
        );
    }

}