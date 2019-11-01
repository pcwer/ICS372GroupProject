import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class GroceryStore implements Serializable {
	
	public static GroceryStore groceryStore;
	private MemberList memberList;
	private Inventory inventory;
	private TransactionList transactionList;
	private UserInterface userInterface;
	
	private GroceryStore() {}
	
	public static GroceryStore getInstance() {
		if (groceryStore == null) {
			groceryStore = new GroceryStore();
		}

		return groceryStore;
	}
	
	public boolean enrollMember(String name, String address, int phoneNumber, double feePaid) {
		Member member = new Member(name, address, phoneNumber, feePaid);
		memberList.addMember(member);

		return true;
	}
	
	public boolean removeMember(String memberId) {
		return memberList.removeMember(memberId);
	}
	
	public Iterator retrieveMembers(String memberId) {
		Iterator members = memberList.getMembers();
		List<Member> foundMembers = new LinkedList<>();

		while (members.hasNext()) {
			Member member = (Member) members.next();
			if (memberId.equals(member.getName())) {
				foundMembers.add(member);
			}
		}
		return foundMembers.iterator();
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
	
	public Product retrieveProduct(String productName) {
		return inventory.findProduct(productName);
	}
	
	public boolean processShipment(String productId, int quantity) {
		//TODO
		return true;
	}
	
	public Product changePrice(String productId, double price) {
		Product product = inventory.findProduct(productId);

		if (product != null) {
			product.setPrice(price);
		}

		return product;
	}
	
	public Iterator getTransactions(String memberId, Calendar startDate, Calendar endDate) {
		return transactionList.getTransactions(memberId, startDate, endDate);
	}
	
	public Iterator listProducts() {
		return inventory.getProducts();
	}
	
	public Iterator listMembers() {
		return memberList.getMembers();
	}
	
	public boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("GroceryData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(groceryStore);
			output.writeObject(IdServer.instance());
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
	
	public static GroceryStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("LibraryData");
			ObjectInputStream input = new ObjectInputStream(file);
			groceryStore = (GroceryStore) input.readObject();
			IdServer.retrieve(input);
			return groceryStore;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}
}
