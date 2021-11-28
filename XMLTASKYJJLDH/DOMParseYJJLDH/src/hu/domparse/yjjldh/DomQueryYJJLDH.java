package hu.domparse.yjjldh;

import java.io.File;

import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class DomQueryYJJLDH {
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		 
			 
		      try {  
		    	// XML fájl beolvasása
		         File inputFile = new File("C:/xml/beadando/XMLYJJLDH.xml");
		         
		      // DOM doc készítése az xml ből
		         DocumentBuilderFactory dbFactory 
		            = DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		         Document doc = dBuilder.parse(inputFile);
		         //gyökér elem
		         doc.getDocumentElement().normalize();
		         System.out.println("Root element :"
		            + doc.getDocumentElement().getNodeName());
		         //melyik vizsgatípus adatait szeretné lekérni 
		         System.out.println("Melyik vizsgát szeretné lekerni? forgalmivizsga vagy kreszvizsga");
		         Scanner sc = new Scanner(System.in);
		         String vizsga = sc.nextLine();
		         if( vizsga.equals("forgalmivizsga")) {
		        	 NodeList nList = doc.getElementsByTagName("forgalmivizsga");
		        	 for (int temp = 0; temp < nList.getLength(); temp++) {
				            Node nNode = nList.item(temp);
				            System.out.println("\nCurrent Element :"
				               + nNode.getNodeName());
				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				               Element eElement = (Element) nNode;
				               System.out.println("Forgalmivizsga ID: "
				                  + eElement.getAttribute("id"));
				               System.out.println("Időpont : "
				                  + eElement
				                  .getElementsByTagName("idopont")
				                  .item(0)
				                  .getTextContent());
				               System.out.println("Eredmény : "
				               + eElement
				                  .getElementsByTagName("eredmeny")
				                  .item(0)
				                  .getTextContent());
				               System.out.println("Vizsgadíj : "
				               + eElement
				                  .getElementsByTagName("vizsgadij")
				                  .item(0)
				                  .getTextContent());
				               System.out.println("Vizsgáztató : "
						               + eElement
						                  .getElementsByTagName("vizsgaztato")
						                  .item(0)
						                  .getTextContent());
				            }
				         }
		         }
		         else {
		        	 NodeList nList = doc.getElementsByTagName("kreszvizsga");
		        	 for (int temp = 0; temp < nList.getLength(); temp++) {
				            Node nNode = nList.item(temp);
				            System.out.println("\nCurrent Element :"
				               + nNode.getNodeName());
				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				               Element eElement = (Element) nNode;
				               System.out.println("Kreszvizsga ID: "
				                  + eElement.getAttribute("id"));
				               System.out.println("Időpont : "
				                  + eElement
				                  .getElementsByTagName("idopont")
				                  .item(0)
				                  .getTextContent());
				               System.out.println("Eredmény : "
				               + eElement
				                  .getElementsByTagName("eredmeny")
				                  .item(0)
				                  .getTextContent());
				               System.out.println("Vizsgadíj : "
				               + eElement
				                  .getElementsByTagName("vizsgadij")
				                  .item(0)
				                  .getTextContent());
				            }
				         }
		         }		           	 
		         
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
			
		   }
}
