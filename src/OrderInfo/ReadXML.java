package OrderInfo;

import java.io.File;
import java.text.SimpleDateFormat;

import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ReadXML {

	public static void main(String[] args) {

		try {
			File xmlFile = new File("XMLtest.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document orderList = dBuilder.parse(xmlFile);

			orderList.getDocumentElement().normalize();
			NodeList nodeList = orderList.getElementsByTagName("bestelling");

			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node nNode = nodeList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

					Customer customer1 = new Customer(
							eElement.getElementsByTagName("voornaam").item(0).getTextContent(),
							eElement.getElementsByTagName("achternaam").item(0).getTextContent(),
							eElement.getElementsByTagName("adres").item(0).getTextContent(),
							eElement.getElementsByTagName("postcode").item(0).getTextContent(),
							eElement.getElementsByTagName("plaats").item(0).getTextContent());

					Order order1 = new Order(
							Integer.parseInt(eElement.getElementsByTagName("ordernummer").item(0).getTextContent()),
							formatter.parse(eElement.getElementsByTagName("datum").item(0).getTextContent()),
							customer1);

					NodeList artikelnr = eElement.getElementsByTagName("artikelnr");
					for (int j = 0; j < artikelnr.getLength(); j++) {
						order1.addProducts(
								Integer.parseInt(eElement.getElementsByTagName("artikelnr").item(j).getTextContent()));
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}