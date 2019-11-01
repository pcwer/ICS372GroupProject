
/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * This class implements the user interface for the grocery store project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 *
 */
public class UserInterface {
    private static UserInterface userInterface;
    private static GroceryStore groceryStore;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final File FILE = new File("GroceryData");
    private static final int EXIT = 0;
    private static final int ENROLL_A_MEMBER = 1;
    private static final int REMOVE_A_MEMBER = 2;
    private static final int RETRIEVE_MEMBER_INFO = 3;
    private static final int ADD_PRODUCT = 4;
    private static final int CHECK_OUT_A_MEMBER_CART = 5;
    private static final int RETRIEVE_PRODUCT_INFO = 6;
    private static final int PROCESS_SHIPMENT = 7;
    private static final int CHANGE_PRICE = 8;
    private static final int PRINT_TRANSACTION = 9;
    private static final int LIST_ALL_MEMBERS = 10;
    private static final int LIST_ALL_PRODUCTS = 11;
    private static final int SAVE = 12;
    private static final int HELP = 13;

    /**
     * Made private for singleton pattern. Conditionally asks for
     * a test bed to be created.Otherwise, it gets a singleton grocery store object.
     */
    private UserInterface() {
        if (yesOrNo("Do you want to generate a test bed and invoke"
                +" the functionality using asserts?")) {
            retrieve();
        } else {
            groceryStore = GroceryStore.getInstance();
        }
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static UserInterface instance() {
        if (userInterface == null) {
            return userInterface = new UserInterface();
        } else {
            return userInterface;
        }
    }

    /**
     * Gets a token after prompting
     * 
     * @param prompt
     *            - whatever the user wants as prompt
     * @return - the token from the keyboard
     * 
     */
    public String getToken(String prompt) {
        do {
            try {
                System.out.println(prompt);
                String line = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
                if (tokenizer.hasMoreTokens()) {
                    return tokenizer.nextToken();
                }
            } catch (IOException ioe) {
                System.exit(0);
            }
        } while (true);
    }

    /**
     * Queries for a yes or no and returns true for yes and false for no
     * 
     * @param prompt
     *            The string to be prepended to the yes/no prompt
     * @return true for yes and false for no
     * 
     */
    private boolean yesOrNo(String prompt) {
        String more = getToken(prompt + " (Y|y)[es] or anything else for no");
        if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
            return false;
        }
        return true;
    }

    /**
     * Converts the string to a number
     *
     * @param prompt
     *            the string for prompting
     * @return the integer corresponding to the string
     *
     */
    private int getNumber(String prompt) {
        do {
            try {
                String item = getToken(prompt);
                Integer number = Integer.valueOf(item);
                return number;
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }
        } while (true);
    }

    /**
     * Converts the string to a double
     *
     * @param prompt
     *            the string for prompting
     * @return the integer corresponding to the string
     *
     */
    private double getDouble(String prompt) {
        do {
            try {
                String item = getToken(prompt);
                return Double.parseDouble(item);
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a number ");
            }
        } while (true);
    }

    /**
     * Prompts for a date and gets a date object
     *
     * @param prompt
     *            the prompt
     * @return the data as a Calendar object
     */
    public Calendar getDate(String prompt) {
        do {
            try {
                Calendar date = new GregorianCalendar();
                String item = getToken(prompt);
                DateFormat dateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
                date.setTime(dateFormat.parse(item));
                return date;
            } catch (Exception fe) {
                System.out.println("Please input a date as mm/dd/yy");
            }
        } while (true);
    }

    /**
     * Prompts for a command from the keyboard
     * 
     * @return a valid command
     * 
     */
    public int getCommand() {
        do {
            try {
                int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
                if (value >= EXIT && value <= HELP) {
                    return value;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number");
            }
        } while (true);
    }

    /**
     * Displays the help screen
     * 
     */
    public void help() {
        System.out.println("Enter a number between 0 and 13 as explained below:");
        System.out.println(EXIT + "  to Exit\n");
        System.out.println(ENROLL_A_MEMBER + "  to enroll a member");
        System.out.println(REMOVE_A_MEMBER + "  to remove a member");
        System.out.println(RETRIEVE_MEMBER_INFO + "  to retrieve member's information");
        System.out.println(ADD_PRODUCT + "  to add a product ");
        System.out.println(CHECK_OUT_A_MEMBER_CART + "  to checkout a members cart");
        System.out.println(RETRIEVE_PRODUCT_INFO + "  to retrieve products info");
        System.out.println(PROCESS_SHIPMENT + "  to process  a shipment");
        System.out.println(CHANGE_PRICE + "  to change price of a product");
        System.out.println(PRINT_TRANSACTION + "  to print a transaction");
        System.out.println(LIST_ALL_MEMBERS + " to list all members");
        System.out.println(LIST_ALL_PRODUCTS + " to list all products");
        System.out.println(SAVE + " to save data");
        System.out.println(HELP + " for help");
    }

    /**
     * Method to be called for adding a member. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for adding the
     * member.
     *
     */
    protected void addMember() {
        String name = getToken("Enter a name:");
        String address = getToken("Enter address:");
        int phoneNumber = getNumber("Enter a phone number:");
        double feePaid = getDouble("Fee Paid:");
        groceryStore.enrollMember(name, address, phoneNumber, feePaid);
    }

    /**
     * Method to be called for removing a member. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for removing the
     * member.
     *
     */
    protected void removeAMember() {
        String memberID = getToken("Enter a member ID:");
        groceryStore.removeMember(memberID);
    }

    /**
     * Method to be called for retrieving member information. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for retrieving
     * member information.
     * 
     */
    protected void retrieveMemberInfo() {
        String memberName = getToken("Enter a member name:");
        Iterator members = groceryStore.retrieveMembers(memberName);

        while (members.hasNext()) {
            Member member = (Member) members.next();
            System.out.println(member.toString());
        }
    }

    /**
     * Method to be called for adding a product. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for adding the
     * a product.
     *
     */

    public void addProduct() {
        String name = getToken("Name of the product:");
        int quantity = getNumber("Stock in hand:");
        int minimumLevel = getNumber("Minimum reorder level:");
        double price = getDouble("Current price:");

        groceryStore.addProduct(name, quantity, minimumLevel, price);
    }


    /**
     * Method to be called for checking out member's  product. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for checking out
     * member's product.
     * 
     */
    public void checkOut() {
        Product product;

        boolean notFinished = true;
        int quantity;
        String productName;
        String memberId = getToken("Enter the member's ID:");

        // Since member IDs are unique, it will only find one
        if (!groceryStore.retrieveMembers(memberId).hasNext()) {
            System.out.println("No Such member.");
            return;
        }
        // Process checkout
        do {
            // Find product
            productName = getToken("Enter the product name:");
            product = groceryStore.retrieveProduct(productName);

            if (product == null) {
                System.out.println("Product not found.");
            } else {
                quantity = getNumber("Number of items:");

            }

            notFinished = yesOrNo("More items?");
        } while (notFinished);

        groceryStore.checkout(memberId);

    }

    /**
     * Method to be called for retrieving product information. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for retrieving
     * product info.
     * 
     */
    public void retrieveProductInfo() {
        String productName = getToken("Enter a product name:");
        Product product = groceryStore.retrieveProduct(productName);
        System.out.println(product.toString());
    }

    /**
     * Method to be called for processing a shipment. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for processing
     * a shipment.
     * 
     */
    public void processShipment() {
        // Loop to manipulate inventory
        System.out.println("processShipment: to be implemented");
    }

    /**
     * Method to be called for changing a price of a product. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for changing
     * a product's price.
     * 
     */
    public void changePrice() {
        String productId = getToken("Enter the product ID");
        double price = getDouble("Enter a new price for the product:");
        Product product = groceryStore.changePrice(productId, price);

        System.out.println();
    }

    /**
     * Method to be called for printing transactions. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for printing
     * a transaction.
     *
     * 
     */
    public void getTransaction() {
        String memberID = getToken("Enter member id:");
        Calendar startDate = getDate("Please enter the start date as mm/dd/yy:");
        Calendar endDate = getDate("Please enter the end date as mm/dd/yy:");
        Iterator result = groceryStore.getTransactions(memberID, startDate, endDate);
        if (result == null) {
            System.out.println("Invalid Member ID");
        } else {
            while (result.hasNext()) {
                Transaction transaction = (Transaction) result.next();
//                System.out.println(transaction.getType() + "   " + transaction.getTitle() + "\n");
            }
            System.out.println("\n  There are no more transactions \n");
        };
    }

    /**
     * Method to be called for listing all members. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for listing
     * all members.
     * 
     */
    public void listAllMembers() {
        System.out.println("listAllMembers: to be implemented");
    }

    /**
     * Method to be called for listing all products. Prompts the user for the
     * appropriate values and uses the appropriate grocery store method for listing
     * all products.
     * 
     */
    public void listAllProducts() {
        System.out.println("listAllProducts: to be implemented");
    }

    /**
     * Method to be called for saving the grocery store object. Uses the appropriate
     * grocery store method for saving.
     * 
     */
    private void save() {
        if (groceryStore.save()) {
            System.out.println(String.format("The grocery has been successfully saved in the file %s \n", FILE.toString()));
        } else {
            System.out.println(" There has been an error in saving \n");
        }
    }

    /**
     * Method to be called for retrieving saved data. Uses the appropriate
     * grocery store method for retrieval.
     * 
     */
    private void retrieve() {
        try {
            if (groceryStore == null) {
                groceryStore = GroceryStore.retrieve();
                if (groceryStore != null) {
                    System.out.println(" The library has been successfully retrieved from the file LibraryData \n");
                } else {
                    System.out.println("File doesnt exist; creating new library");
                    groceryStore = GroceryStore.getInstance();
                }
            }
        } catch (Exception cnfe) {
            cnfe.printStackTrace();
        }
    }

    /**
     * Orchestrates the whole process. Calls the appropriate method for the
     * different functionalities.
     * 
     */
    public void process() {
        int command;
        help();
        while ((command = getCommand()) != EXIT) {
            switch (command) {
            case ENROLL_A_MEMBER:
                addMember();
                break;
            case REMOVE_A_MEMBER:
                removeAMember();
                break;
            case RETRIEVE_MEMBER_INFO:
                retrieveMemberInfo();
                break;
            case ADD_PRODUCT:
                addProduct();
                break;
            case RETRIEVE_PRODUCT_INFO:
                retrieveProductInfo();
                break;
            case CHECK_OUT_A_MEMBER_CART:
                checkOut();
                break;
            case PROCESS_SHIPMENT:
                processShipment();
                break;
            case CHANGE_PRICE:
                changePrice();
                break;
            case PRINT_TRANSACTION:
                getTransaction();
                break;
            case LIST_ALL_MEMBERS:
                listAllMembers();
                break;
            case LIST_ALL_PRODUCTS:
                listAllProducts();
                break;
            case SAVE:
                save();
                break;
            case HELP:
                help();
                break;
            }
        }
    }

    /**
     * The method to start the application. Simply calls process().
     * 
     * @param args
     *            not used
     */
    public static void main(String[] args) {
        UserInterface.instance().process();
    }
}