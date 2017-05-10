package OrderInfo;

public class Customer {
	private static int count = 0;
	private int customerId;
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
		this.customerId = count++;
	}

	public String toString() {
		String text;
		text = "Voornaam: " + surName + "\n" + "Achternaam: " + lastName + "\n" + "Adres: " + adress + "\n"
				+ "Postcode: " + zipCode + "\n" + "Woonplaats: " + city;

		return text;
	}

}
