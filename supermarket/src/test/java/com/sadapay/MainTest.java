package com.sadapay;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("Test Main Run Method")
    void main() {

        String[] validArgs = new String[] { "./../static/inventory.csv", "./../static/commands.txt"};

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> Main.main(new String[0])),
                () -> assertThrows(IllegalArgumentException.class, () -> Main.main(new String[] { "arg_arg", "arg_arg","arg_arg"})),
                () -> assertDoesNotThrow(() -> Main.main(validArgs))
        );
    }

    @Test
    @DisplayName("Test File Loading")
    void loadFile() {

        String correctFile = "./../static/inventory.csv";

        assertAll(
                () -> assertThrows(FileNotFoundException.class, () -> Main.loadFile("wrong_file_path.csv", ".csv")),
                () -> assertThrows(FileNotFoundException.class, () -> Main.loadFile(correctFile, ".xlsx")),
                () -> assertDoesNotThrow(() -> Main.loadFile(correctFile, ".csv"))
        );
    }


}