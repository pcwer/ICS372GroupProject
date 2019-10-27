import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Transaction {
	private GregorianCalendar saleDate;
	private double total;
	private Member member;
	private LinkedList<LineItem> LineItemList = new LinkedList<LineItem>();
	
	public Transaction(Member member) {
		//TODO
	}
	
	public boolean addLineItem(LineItem lineItem) {
		//TODO
	}
	
	public double calculateTotal() {
		//TODO
	}
	
	public Member getMember() {
		//TODO
	}
}
