package com.sadapay;

import com.sadapay.utils.Interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;


/**
 * Entrypoint into the system
 */
public class Main {

    private static Interpreter cli;

//    private final Interpreter cli;
    /**
     * Starting Function. Depending on args, it decides the control flow of the system; interactive mode or file mode.
     * @param args Command Line Args
     * @throws IllegalArgumentException
     */
    public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException, ParseException, NumberFormatException{

        File inventoryFile = null;
        File commandFile = null;

//        Checking the count of args. This particular system needs 1 or 2 args.
        if(! (args.length < 3 && args.length > 0) ){
            throw new IllegalArgumentException("Received "  + args.length + " args. Correct Args Format: <inventory.csv> <command.txt>?");
        }

//        Initializes the interpreter we will use
        cli = Interpreter.getInstance();

//        Checks for the validity of inventory file and its presence
        inventoryFile = loadFile(args[0], ".csv");

//        Checks the avialability of command file and its validity
        if (args.length == 2 && args[1] != null){
            commandFile = loadFile(args[1], ".txt");

//            Goes to file mode, when both files are present
            file_mode(inventoryFile, commandFile);
            return;
        }

//        initiates the interactive cli
        interactive_mode(inventoryFile);
        return;

    }

    /**
     * Prepares a file to be read. Otherwise, throws and error
     * @param filePath relative file path
     * @param extension extension of file, .csv or .txt
     * @return File
     * @throws FileNotFoundException
     */
    public static File loadFile(String filePath, String extension) throws FileNotFoundException {

        File file = new File(filePath);

//        check if file exists and is readable and has the expected extension
        if (! (file.exists() && file.isFile() && file.canRead() && file.toString().endsWith(extension)) ){
            throw new FileNotFoundException("File " + filePath + " not found relative to the working directory." +
                    " Argument must be a " + extension + " file.");
        }

        return file;
    }

    /**
     * Function for Initializing Inventory at startup
     * @param file File Object for inventory.csv
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws NumberFormatException
     */
    public static void initializeInventory(File file) throws FileNotFoundException, ParseException, NumberFormatException{

        Scanner read = new Scanner(file);

        while(read.hasNext()){
            List<String> contents = new ArrayList<>();
            String input = read.nextLine();

//            Remove any blank/extra lines in file
            if(input.isBlank()){
                continue;
            }

            for(String s : input.split(",")){
                contents.add(s.trim());
            }

//            Check if format of .csv file is right
            if(contents.size() != 3) {
                throw new ParseException("Got " + contents.size() + " columns. Required 3 columns.", contents.size());
            }

//            Loads items, throws error if conversion of data isn't possible
            try {
                cli.loadItemIntoInventory(contents.get(0),
                        Double.valueOf(contents.get(1)),
                        Integer.valueOf(contents.get(2)));
            }catch (NumberFormatException e){
                throw new NumberFormatException(e.getMessage()+" in "+file.toString());
            }
        }

        read.close();
    }

    /**
     * Function for Interactive Mode
     * @param inventoryFile
     */
    public static void interactive_mode(File inventoryFile) throws FileNotFoundException, ParseException, NumberFormatException {

        initializeInventory(inventoryFile);

        Scanner console = new Scanner(System.in);

//        console input loop
        while(true){

            System.out.print(">> ");
            String input = console.nextLine();

            if(Objects.equals(input, "exit")){
                break;
            }

//            execution of command
            System.out.println(cli.command(input));
        }

        console.close();

    }

    /**
     * Function for File Mode
     * @param inventoryFile
     * @param commandFile
     */
    public static void file_mode(File inventoryFile, File commandFile) throws FileNotFoundException, ParseException, NumberFormatException{

        initializeInventory(inventoryFile);

        Scanner read = new Scanner(commandFile);

        while(read.hasNext()){

            List<String> contents = new ArrayList<>();
            String input = read.nextLine();

//            skip blank lines in command file
            if(input.isBlank()){
                continue;
            }

//            prep the command by removing any unnecessary whitespaces
//            and execute
            System.out.println(cli.command(input.replaceAll("\\s+", " ").trim()));

        }

        read.close();
    }
}
