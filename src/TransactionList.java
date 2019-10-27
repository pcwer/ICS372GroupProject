import java.util.LinkedList;

public class TransactionList {
	private LinkedList<Transaction> transactions = new LinkedList<Transaction>();
	private static TransactionList transactionList;
	
	private TransactionList() {
		//TODO
	}
	
	public static TransactionList getInstance() {
		//TODO
	}
	
	public boolean addTransaction(Transaction transaction) {
		//TODO
	}
	
	public Iterator getTransactions(GregorianCalendar startDate, GregorianCalendar endDate) {
		//TODO
	}
}
