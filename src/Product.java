
public class Product {
	private String productId;
	private String name;
	private int quantity;
	private int minimumLevel;
	private double price;
	
	public Product(String name, int quantity, int minimumLevel, double price) {
		this.productId = String.format("P%d", IdServer.getInstance().getProductId());
		this.name = name;
		this.quantity = quantity;
		this.minimumLevel = minimumLevel;
		this.price = price;
	}

	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMinimumLevel() {
		return minimumLevel;
	}

	public void setMinimumLevel(int minimumLevel) {
		this.minimumLevel = minimumLevel;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
