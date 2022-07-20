package com.sadapay.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("Test Item Creation")
    void testItem(){
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Item("bread", 0.00, 20)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Item("bread", 10.00, -1)),
                () -> assertDoesNotThrow(() -> new Item("bread", 12.0, 10))
        );
    }

    @Test
    @DisplayName("Test Item Copying")
    void testItemCopy(){
        Item i1 = new Item("bread", 10.00, 5);
        Item i2 = Item.copy(i1);
        assertAll(
                () -> assertFalse(i1 == i2),
                () -> assertTrue(i1 == i1)
        );
    }

    @Test
    @DisplayName("Test Quantity is Editable")
    void testItemQuantity(){
        Item item = new Item("bread", 10.00, 20);
        item.setQuantity(200);
        assertEquals(200, item.getQuantity());
    }

    @Test
    @DisplayName("Test Fields are Unchangeable")
    void testItemEncapsulation(){
        Item item = new Item("bread", 10.00, 20);

        assertAll(
                () -> assertFalse(item.getClass().getDeclaredField("name").canAccess(item)),
                () -> assertFalse(item.getClass().getDeclaredField("amount").canAccess(item))
        );
    }
}