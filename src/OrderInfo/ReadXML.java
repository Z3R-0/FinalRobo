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

// eElement.getElementsByTagName("firstname").item(0).getTextContent()

/*
 * public class ReadXML {
 * 
 * public static void main(String args[]) { System.out.println("test1"); try {
 * 
 * System.out.println("test2");
 * 
 * DocumentBuilderFactory docBuilderFactory =
 * DocumentBuilderFactory.newInstance(); DocumentBuilder docBuilder =
 * docBuilderFactory.newDocumentBuilder(); Document doc = docBuilder.parse(new
 * File("C:/Users/Erwin/Documents/FinalRobo/src/XMLtest.xml"));
 * 
 * // normalize text representation doc.getDocumentElement().normalize();
 * 
 * NodeList bestelling = doc.getElementsByTagName("bestelling"); for (int s = 0;
 * s < bestelling.getLength(); s++) {
 * 
 * System.out.println("test3");
 * 
 * Node ordernummer = bestelling.item(s); System.out.println("test4"); if
 * (ordernummer.getNodeType() == Node.ELEMENT_NODE) {
 * 
 * 
 * 
 * NodeList textFNList = ordernummer.getChildNodes();
 * System.out.println("test4");
 * System.out.println(textFNList.item(0).getNodeValue().trim());
 * 
 * System.out.println("test5");
 * 
 * Order order1 = new
 * Order(Integer.parseInt(textFNList.item(0).getNodeValue().trim()));
 * System.out.println(order1); } }
 * 
 * } catch (SAXParseException err) {
 * System.out.println("** Er is een syntax fout opgetreden op lijn " +
 * err.getLineNumber() + "."); System.out.println(" " + err.getMessage());
 * 
 * } catch (SAXException e) { Exception x = e.getException(); ((x == null) ? e :
 * x).printStackTrace();
 * 
 * } catch (Throwable t) { t.printStackTrace(); } } }
 */
