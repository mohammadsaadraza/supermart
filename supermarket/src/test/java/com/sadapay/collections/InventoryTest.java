package com.sadapay.collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    Inventory i;

    @BeforeEach
    void setUp(){
        i = Inventory.getInstance();
        i.clear();
        i.addItem("bread", 10.00, 20);
        i.addItem("soap", 6.00, 30);
    }

    @Test
    @DisplayName("Test Singleton Class")
    void testSingleton(){
        Inventory testInventory = Inventory.getInstance();

        assertEquals(i, testInventory);
    }

    @Test
    @DisplayName("Test Item Fetching")
    void testItemFetch(){
        assertAll(
                () -> assertThrows(NoSuchElementException.class, () -> i.getItem("glass")),
                () -> assertFalse(i.containsItem("glass")),
                () -> assertDoesNotThrow(() -> i.getItem("bread"))
        );
    }

    @Test
    @DisplayName("Test Item Addition")
    void testItemAdd(){
        i.addItem("glass", 30.00, 5);

        assertAll(
                () -> assertDoesNotThrow(() -> i.getItem("glass")),
                () -> assertTrue(i.containsItem("glass")),
                () -> assertEquals(30.0, i.getItem("glass").getAmount()),
                () -> assertEquals(5, i.getItem("glass").getQuantity())
        );
    }

    @Test
    @DisplayName("Test Item Removal")
    void testItemRemove(){

        assertAll(
                () -> assertThrows(NoSuchElementException.class, () -> i.removeItem("glass")),
                () -> assertFalse(i.containsItem("glass")),
                () -> assertDoesNotThrow(() -> i.addItem("glass", 30.00, 5)),
                () -> assertDoesNotThrow(() -> i.removeItem("glass")),
                () -> assertFalse(i.containsItem("glass"))
        );
    }

    @Test
    @DisplayName("Test Item Consumption")
    void testItemConsume(){

        assertAll(
                () -> assertThrows(NoSuchElementException.class, () -> i.consumeItem("glass", 5)),
                ()-> assertDoesNotThrow(() -> i.consumeItem("bread", 20)),
                () -> assertThrows(ArithmeticException.class, () -> i.consumeItem("bread", 1)),
                () -> assertEquals(0, i.getItem("bread").getQuantity())
        );
    }

    @Test
    @DisplayName("Test Item Stocking")
    void testItemStock(){

        assertAll(
                () -> assertThrows(NoSuchElementException.class, () -> i.stockItem("glass", 5)),
                ()-> assertDoesNotThrow(() -> i.stockItem("bread", 20)),
                () -> assertEquals(40, i.getItem("bread").getQuantity())
        );
    }

}