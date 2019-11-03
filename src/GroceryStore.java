import java.io.*;
import java.util.*;

public class GroceryStore implements Serializable {
	private static GroceryStore groceryStore;
	private MemberList memberList;
	private Inventory inventory;
	private TransactionList transactionList;
	private UserInterface userInterface;
    public static final int OPERATION_COMPLETED = -1;
    public static final int MEMBER_NOT_FOUND = 100;
    public static final int BAD_TRANSACTION = 500;

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

    /**
     * A Checkout starts the transaction process where products are added and tracked
     * to the transaction.
     * @return a result
     */
    public int checkout() {
        Member member;
        Transaction transaction;
        String memberID;
        boolean successful;

        // find member to start a transaction
        memberID = userInterface.getToken("Enter a member ID:");

        member = memberList.findMember(memberID);
        if (member == null) {
            return MEMBER_NOT_FOUND;
        }

        transaction = new Transaction(member);

        addProductsToTransaction(transaction);
        reorderProducts(transaction);
        sendReciept(transaction);

        successful = transactionList.addTransaction(transaction);

        return successful? OPERATION_COMPLETED: BAD_TRANSACTION;
    }

    /**
     * Sends a receipt of the products purchased in the transaction.
     * @param transaction
     */
    private void sendReciept(Transaction transaction) {
        userInterface.notify(transaction.toString());
    }

    /**
     * Finds all the items in the transaction that causes the products
     * to reach their minimum reorder level.
     * @param transaction the transaction with the products to be ordered
     */
    private void reorderProducts(Transaction transaction) {
        Iterator minimumProducts;
        Product product;

        minimumProducts = transaction.getMinimumProducts();

        userInterface.notify("The following products will be reordered:");
        while (minimumProducts.hasNext()) {
            product = (Product) minimumProducts.next();
            userInterface.notify(String.format("%s", product.getName()));
        }
    }

    /**
     * Generates line items for the transaction
     * @param transaction
     */
    private void addProductsToTransaction(Transaction transaction) {
        String productName;
        Product product;
        int quantity;
        boolean moreProducts;

        do {
            productName = userInterface.getToken("Enter a product name:");
            product = inventory.findProduct(productName);

            if (product == null) {
                userInterface.notify("Product not found.");
            } else {
                quantity = userInterface.getNumber("Enter the quantity:");
                transaction.addLineItem(product, quantity);
            }

            moreProducts = userInterface.yesOrNo("Are there more items?");
        } while(moreProducts);
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
			output.writeObject(IdServer.getInstance());
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
