package OrderInfo;

public class Runfile {

	public static void main(String args[]) {
		new Runfile();
	}

	public Runfile() {
		ReadXML read1 = new ReadXML();
		read1.readXmlFile("XMLtest.xml");
		System.out.println(read1.getCustomer().toString());
	}

}
