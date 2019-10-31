import java.util.GregorianCalendar;

/**
 * Tests the GroceryImplTester class using assert statements.
 * 
 */
public class GorceryStoreImplTester {

	public static void main(String[] args) {
		
		GroceryStore groceryStore;
		groceryStore = groceryStore.getInstance();

		assert (groceryStore.enrollMember(memberId));
		assert (groceryStore.removeMember(memberId);

		assert (groceryStore.addProduct(name, quantity, price, minimumLevel));
		assert (groceryStore.removeProduct(productId);
		
		assert (groceryStore.processShipment(productId, quantity));
		assert (groceryStore.changePrice(productId));
	
		assert (groceryStore.retrieve());
		assert (groceryStore.save());
	}
}
