
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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * Generates member ids.
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 *
 */
public class IdServer implements Serializable {
    private int productIdCounter;
    private int membertIdCounter;
    private static IdServer server;

    /*
     * Private constructor for singleton pattern
     * 
     */
    private IdServer() {
        membertIdCounter = 1;
        productIdCounter = 1;
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static IdServer instance() {
        if (server == null) {
            return (server = new IdServer());
        } else {
            return server;
        }
    }

    /**
     * Getter for id
     * 
     * @return id of the member
     */
    public int getProductId() {
        return productIdCounter++;
    }

    /**
     * Getter for id
     *
     * @return id of the member
     */
    public int getMemberId() {
        return membertIdCounter++;
    }
    /**
     * String form of the collection
     * 
     */
    @Override
    public String toString() {
        return String.format("Product Id: %d, Member Id: %d", productIdCounter, membertIdCounter);
    }

    /**
     * Retrieves the server object
     * 
     * @param input
     *            inputstream for deserialization
     */
    public static void retrieve(ObjectInputStream input) {
        try {
            server = (IdServer) input.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}