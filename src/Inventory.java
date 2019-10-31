import java.util.Iterator;
import java.util.LinkedList;

public class Inventory {
	private LinkedList<Product> productList = new LinkedList<Product>();
	private static Inventory inventory;

	/**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
	public static Inventory getInstance() {
		if(inventory == null) {
			inventory = new Inventory();
		}

		return inventory;
	}
	
	/**
     * Checks whether a product with a given product name exists.
     * 
     * @param name
     *            the name of the product
     * @return true iff the product exists
     * 
     */
	public Product findProduct(String name) {
		for (Iterator iterator = productList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if (product.getName().equals(name)) {
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
	public boolean removeProduct(String productId) {
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
	public boolean addProduct(Product product) {
		productList.add(product);
		return true;
	}
	
	/**
     * Returns an iterator to all product
     * 
     * @return iterator to the collection
     */
	public Iterator<Product> getProducts() {
		return productList.iterator();
	}
}
