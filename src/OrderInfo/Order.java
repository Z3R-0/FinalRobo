package OrderInfo;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

public class Order {
	private int ordernumber;
	private Date date;
	private Customer customer;
	ArrayList<Integer> products = new ArrayList<Integer>();

	// CONSTRUCTOR
	public Order(int ordernumber, Date date, Customer customer) {
		this.ordernumber = ordernumber;
		this.date = date;
		this.customer = customer;
	}

	// SETTERS
	public void setOrdernummer(int ordernumber) {
		this.ordernumber = ordernumber;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void addProducts(int product) {
		products.add(product);
	}

}
