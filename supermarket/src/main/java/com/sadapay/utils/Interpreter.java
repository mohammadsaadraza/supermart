package com.sadapay.utils;

import com.sadapay.collections.SuperMart;

/**
 * Command Interpreter for SuperMart System. This is a Singleton class
 */
public class Interpreter {

    private static Interpreter instance = null;
    public final SuperMart mart;

    /**
     * Private Constructor. Initializes the SuperMart Class.
     */
    private Interpreter(){
        mart = SuperMart.getInstance();
    }

    /**
     * Function for Executing Commands
     * @param c Command String
     * @return String
     */
    public String command(String c){
        String[] tokens = c.split(" ");

        switch (tokens[0]){
            case "add":
                return this.addFunction(tokens);
            case "offer":
                return this.offerFunction(tokens);
            case "bill":
                return this.billFunction(tokens);
            case "checkout":
                return this.checkoutFunction(tokens);
            case "list":
                return this.listFunction(tokens);
            case "remove":
                return this.removeFunction(tokens);
            default:
                return "wrong command";
        }
    }

    /**
     * Add Function Execution
     * @param tokens split command tokens
     * @return String
     */
    public String addFunction(String[] tokens){
        if (tokens.length != 3){
            return "wrong command";
        }

        try{
            return mart.add(tokens[1], Integer.valueOf(tokens[2]));
        }
        catch(NumberFormatException e){
            return tokens[2] + " is not a valid quantity for " + tokens[1];
        }
        catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * Offer Function Execution
     * @param tokens split command tokens
     * @return String
     */
    public String offerFunction(String[] tokens){
        if (tokens.length != 3){
            return "wrong command";
        }
        return mart.offer(tokens[1], tokens[2]);
    }

    /**
     * Remove Function Execution
     * @param tokens split command tokens
     * @return String
     */
    public String removeFunction(String[] tokens){
        if (tokens.length != 3){
            return "wrong command";
        }

        try{
            return mart.remove(tokens[1], Integer.valueOf(tokens[2]));
        }
        catch(NumberFormatException e){
            return tokens[2] + " is not a valid quantity for " + tokens[1];
        }
        catch (Exception e){
            return e.getMessage();
        }

    }

    /**
     * Bill Function Execution
     * @param tokens split command tokens
     * @return String
     */
    public String billFunction(String[] tokens){
        if (tokens.length != 1){
            return "wrong command";
        }
        return mart.bill();
    }

    /**
     * Checkout Function Execution
     * @param tokens split command tokens
     * @return String
     */
    public String checkoutFunction(String[] tokens){
        if (tokens.length != 1){
            return "wrong command";
        }
        return mart.checkout();
    }

    /**
     * List Function Execution
     * @param tokens split command tokens
     * @return String
     */
    public String listFunction(String[] tokens){
        if (tokens.length != 2){
            return "wrong command";
        }
        return mart.list(tokens[1]);
    }

    /**
     * Static Method for Interpreter class that returns the instance. If no instance exists, it returns a new one.
     * @return One and only 1 instance of Interpreter Class
     */
    public static Interpreter getInstance(){
        if (instance == null){
            instance = new Interpreter();
        }
        return instance;
    }
}
