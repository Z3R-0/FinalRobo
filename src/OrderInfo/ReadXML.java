package OrderInfo;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReadXML {

    public static void main(String args[]) {
    	System.out.println("test1");
        try {
        	
        	System.out.println("test2");

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("C:/Users/Erwin/Documents/FinalRobo/src/XMLtest.xml"));

            // normalize text representation
            doc.getDocumentElement().normalize();

            NodeList bestelling = doc.getElementsByTagName("bestelling");
            for (int s = 0; s < bestelling.getLength(); s++) {
            	
            	System.out.println("test3");

                Node ordernummer = bestelling.item(s);
                System.out.println("test4");
               if (ordernummer.getNodeType() == Node.ELEMENT_NODE) {
            	   
            	   
            	   
                    NodeList textFNList = ordernummer.getChildNodes();
                    System.out.println("test4");
                    System.out.println(textFNList.item(0).getNodeValue().trim());
                    
                    System.out.println("test5");
                    
                    Order order1 = new Order(Integer.parseInt(textFNList.item(0).getNodeValue().trim()));
                    System.out.println(order1);
                }
            }

        } catch (SAXParseException err) {
            System.out.println("** Er is een syntax fout opgetreden op lijn " + err.getLineNumber() + ".");
            System.out.println(" " + err.getMessage());

        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

