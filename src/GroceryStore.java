import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class GroceryStore implements Serializable {
	
	public static GroceryStore groceryStore;
	private MemberList memberList;
	private Inventory inventory;
	private TransactionList transactionList;
	private UserInterface userInterface;
	
	private GroceryStore() {
		//TODO
	}
	
	public static GroceryStore getInstance() {
		if (groceryStore == null) {
			groceryStore = new GroceryStore();
		}

		return groceryStore;
	}
	
	public boolean enrollMember(String name, String address, int phoneNumber, double feePaid) {
		Member member = new Member(name, address, phoneNumber, feePaid);
		//TODO
		return true;
	}
	
	public boolean removeMember(String memberId) {
		//TODO
		return true;
	}
	
	public boolean retrieveMember(String memberId) {
		//TODO
		return true;
	}
	
	public boolean addProduct(String name, int quantity, int minimumLevel, double price) {
		if (inventory.findProduct(name) == null) {
			return false;
		}

		Product product = new Product(name, quantity, minimumLevel, price);
		inventory.addProduct(product);
		return true;
	}
	
	public Transaction checkout(String memberId) {
		//TODO
		return null;
	}
	
	public Product retrieveProduct(String productId) {
		//TODO
		return null;
	}
	
	public boolean processShipment(String productId, int quantity) {
		//TODO
		return true;
	}
	
	public boolean changePrice(String productId, double price) {
		return true;
	}
	
	public Iterator printTransactions(String memberId, GregorianCalendar startDate, GregorianCalendar endDate) {
		return transactionList.getTransactions(memberId, startDate, endDate);
	}
	
	public Iterator listProducts() {
		return inventory.getProducts();
	}
	
	public Iterator listMembers() {
		return memberList.getMembers();
	}
	
	public boolean save() {
		//TODO
		return true;
	}
	
	public boolean retrieve() {
		//TODO
		return true;
	}
	
	public boolean exit() {
		//TODO
		return true;
	}
	
}
