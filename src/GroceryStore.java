import java.io.Serializable;

public class GroceryStore implements Serializable{
	
	public static GroceryStore groceryStore;
	private MemberList memberList;
	private Inventory inventory;
	private TransactionList transactionList;
	private UserInterface userInterface;
	
	private GroceryStore() {
		//TODO
	}
	
	public getInstance() {
		//TODO
	}
	
	public boolean enrollMember(String name, String address, int phoneNumber, double feePaid) {
		Member member = new Member(name, address, phoneNumber, feePaid);
		//TODO
	}
	
	public boolean removeMember(String memberId) {
		//TODO
	}
	
	public boolean retrieveMember(String memberId) {
		//TODO
	}
	
	public boolean addProduct(String name, int quantity, double price, int minimumLevel) {
		Product product = new Product(name, quantity, price, minimumLevel);
		//TODO
	}
	
	public Transaction checkout(String memberId) {
		//TODO
	}
	
	public Product retrieveProduct(String productId) {
		//TODO
	}
	
	public boolean processShipment(String productId, int quantity) {
		//TODO
	}
	
	public boolean changePrice(String productId, double price) {
		//TODO
	}
	
	public Iterator printTransactions(String memberId, GregorianCalendar startDate, GregorianCalendar endDate) {
		//TODO
	}
	
	public Iterator listProducts() {
		//TODO
	}
	
	public Iterator listMembers() {
		//TODO
	}
	
	public boolean save() {
		//TODO
	}
	
	public boolean retrieve() {
		//TODO
	}
	
	public boolean exit() {
		//TODO
	}
	
}
