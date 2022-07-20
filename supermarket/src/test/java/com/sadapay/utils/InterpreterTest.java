package com.sadapay.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {

    Interpreter cli;

    @BeforeEach
    void setUp(){
        cli = Interpreter.getInstance();
    }

    @Test
    @DisplayName("Test Singleton Class")
    void testSingleton(){
        Interpreter test = Interpreter.getInstance();

        assertEquals(cli, test);
    }

    @Test
    @DisplayName("Test Interpreter Commands")
    void testCommands(){

        assertAll(
                () -> assertEquals("wrong command", cli.command("add")),
                () -> assertEquals("wrong command", cli.command("add soap")),
                () -> assertEquals("wrong command", cli.command("add soap 5 arb_arg")),
                () -> assertEquals("5arb is not a valid quantity for soap", cli.command("add soap 5arb")),
                () -> assertEquals("wrong command", cli.command("remove")),
                () -> assertEquals("wrong command", cli.command("remove soap")),
                () -> assertEquals("wrong command", cli.command("remove soap 5 arb_arg")),
                () -> assertEquals("5arb is not a valid quantity for soap", cli.command("remove soap 5arb")),
                () -> assertEquals("wrong command", cli.command("offer")),
                () -> assertEquals("wrong command", cli.command("offer offer_name")),
                () -> assertEquals("wrong command", cli.command("offer offer_name soap arb_arg")),
                () -> assertEquals("wrong command", cli.command("bill arb_arg")),
                () -> assertEquals("wrong command", cli.command("checkout arb_arg")),
                () -> assertEquals("wrong command", cli.command("list")),
                () -> assertEquals("wrong command", cli.command("list arb_arg")),
                () -> assertEquals("wrong command", cli.command("list cart arb_arg")),
                () -> assertEquals("wrong command", cli.command("arb_command"))
        );
    }
}