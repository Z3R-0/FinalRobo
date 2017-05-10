package OrderInfo;

public class Customer {
	private int customerId = 1;
	private String surName;
	private String lastName;
	private String adress;
	private String zipCode;
	private String city;

	// CONSTRUCTOR
	public Customer(String surName, String lastName, String adress, String zipCode, String city) {
		this.surName = surName;
		this.lastName = lastName;
		this.adress = adress;
		this.zipCode = zipCode;
		this.city = city;
	}

}
