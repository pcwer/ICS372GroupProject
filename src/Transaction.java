import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

public class Transaction {
	private Calendar saleDate;
	private double total;
	private Member member;
	private LinkedList<LineItem> lineItemList = new LinkedList<>();
	private LinkedList<Product> minimumProducts = new LinkedList<>();
	
	public Transaction(Member member) {
		this.member = member;
		this.saleDate = new GregorianCalendar();
	}
	
	public boolean addLineItem(Product product, int quantity) {
		int productQuantity = product.getQuantity();
		int minimumLevel = product.getMinimumLevel();

		if (productQuantity - quantity <= minimumLevel) {
			minimumProducts.add(product);
		}

		LineItem lineItem = new LineItem(product, quantity);
		return lineItemList.add(lineItem);
	}
	
	public double getTotal() {
		double runningTotal = 0.0;

		for (LineItem lineItem: lineItemList) {
			runningTotal += lineItem.getTotalPrice();
		}

		total = runningTotal;

		return total;
	}

	public Iterator getMinimumProducts(){
		return minimumProducts.iterator();
	}

	public Member getMember() {
		return member;
	}

	public Calendar getSaleDate() {
		return saleDate;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		String string = String.format("Total: %1.2f %n", getTotal());

		for (LineItem lineItem: lineItemList) {
			stringBuilder.append(lineItem.toString());
		}
		stringBuilder.append(String.format("----------%n"));
		stringBuilder.append(string);

		return stringBuilder.toString();
	}
}
