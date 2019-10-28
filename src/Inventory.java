import java.io.Serializable;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * 
 * 
 * The following code is based on code from Brahma Dathan 
 * lecture notes
 *
 * The collection class for Product Objects
 * 
 *
 */
public class Inventory{
	private LinkedList productList = new LinkedList();
	private static Inventory inventory;	
	
	/*
     * Private constructor for singleton pattern
     * 
     */
	private Inventory() {
	}
	
	/**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
	public static Inventory getInstance() {
		if(inventory == null) {
			return (inventory = new Inventory());
		}
	}
	
	/**
     * Checks whether a product with a given product id exists.
     * 
     * @param productId
     *            the id of the product
     * @return true iff the product exists
     * 
     */
	private Product findProduct(String productId){
		for (Iterator iterator = productList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if (product.getId().equals(productId)) {
				return product;
			}
		}
		return null;
	}
	
	/**
     * Removes a product from the inventory
     * 
     * @param productId
     *            product id
     * @return true iff product could be removed
     */
	private boolean removeProduct(String productId) {
		Product product = findProduct(productId);
		if (product == null) {
			return false;
		} else {
			return productList.remove(product);
		}
	}
	
	 /**
     * Add a product into the collection
     * 
     * @param product
     *            the product to be added
     * @return true iff the product could be added. Currently always true
     */
	private boolean addProduct(Product product) {
		productList.add(product);
		return true;
	}
	
	/**
     * Returns an iterator to all product
     * 
     * @return iterator to the collection
     */
	private Iterator getProducts() {
		return productList.iterator();
	}
}
