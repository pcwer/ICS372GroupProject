/**
 * Representation of a single product calculated in a transaction. The line item
 * includes the
 * @author David Tran
 */
public class LineItem {
	private Product product;
	private int quantity;
	private double totalPrice;
	
	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = calculateTotalPrice(product, quantity);

		adjustProductQuantity(product, quantity);
	}
	
	private double calculateTotalPrice(Product product, int quantity) {
		double totalPrice = product.getPrice() * quantity;

		return totalPrice;
	}

	private void adjustProductQuantity(Product product, int quantity) {
		int newQuantity = product.getQuantity() - quantity;

		product.setQuantity(newQuantity);
	}

	public Product getProduct() {
		return product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}

	@Override
	public String toString() {
		String name = product.getName();
		int quantity = product.getQuantity();
		double price = product.getPrice();

		return String.format("%s %d $%1.2f $%1.2f %n", name, quantity, price, totalPrice);
	}
}
