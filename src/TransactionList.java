import java.io.Serializable;
import java.util.*;

/**
 * A collection class for Transactions.
 *
 * @author David Tran
 */
public class TransactionList implements Serializable {
	private List<Transaction> transactions = new LinkedList<>();
	private static TransactionList transactionList;

	/**
	 * Private constructor for a singleton list
	 *
	 */
	private TransactionList() {
	}

	/**
	 * Support for the singleton object
	 *
	 * @return a singleton object
	 */
	public static TransactionList getInstance() {
		if(transactionList == null) {
			transactionList = new TransactionList();
		}

		return transactionList;
	}

	/**
	 * Adds a Transaction to the collection
	 *
	 * @param transaction the transaction object
	 * @return true if added successfully
	 */
	public boolean addTransaction(Transaction transaction) {
		return transactions.add(transaction);
	}

	/**
	 * getTransactions searches for Transactions made by a specific member by their ID between a start
	 * date and end date, inclusive. The returned result of any Transactions found within the range,
	 * otherwise the iterator will be empty. The order is an implied date sorted order (ascending) due to
	 * the nature of Transactions occurring in order of time.
	 *
	 * This does not cover daylight savings adjusted times.
	 *
	 * @param memberId the member id
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return an iterator of Transactions
	 */
	public Iterator getTransactions(String memberId, Calendar startDate, Calendar endDate) {
		List transactionRange = new LinkedList<>();

		for (Transaction transaction: transactions) {
			if (!transaction.getMember().getMemberId().equals(memberId)) {
				break;
			}

			Calendar date = transaction.getSaleDate();
			if (startDate.get(Calendar.YEAR) > date.get(Calendar.YEAR)
					&& startDate.get(Calendar.MONTH) > date.get(Calendar.MONTH)
					&& startDate.get(Calendar.DATE) > date.get(Calendar.DATE)) {
				break;
			}

			if (endDate.get(Calendar.YEAR) < date.get(Calendar.YEAR)
					&& endDate.get(Calendar.MONTH) < date.get(Calendar.MONTH)
					&& endDate.get(Calendar.DATE) < date.get(Calendar.DATE)) {
				break;
			}

			transactionRange.add(transaction);
		}

		return transactionRange.iterator();
	}
}
