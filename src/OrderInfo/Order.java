package OrderInfo;

import java.util.ArrayList;
import Warehouse.*;
import RobotoAppMain.Main;
import java.util.Date;

public class Order {
	private int ordernumber;
	private Date date;
	private Customer customer;
	ArrayList<Product> products = new ArrayList<Product>();

	// CONSTRUCTOR
	public Order(int ordernumber, Date date, Customer customer) {
		this.ordernumber = ordernumber;
		this.date = date;
		this.customer = customer;
	}

	// Add products to list
	public void addProducts(int p) {
		for (int i=0; i < Main.warehouse.getProduct().size(); i++){
			if(Main.warehouse.getProduct().get(i).getId() == p){
				products.add(Main.warehouse.getProduct().get(i));
			}
		}
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	
	

}
