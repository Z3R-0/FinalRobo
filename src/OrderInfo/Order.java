package OrderInfo;

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

	// Add products to list
	public void addProducts(int product) {
		products.add(product);
	}

	public ArrayList<Integer> getProducts() {
		return products;
	}
	
	

}
