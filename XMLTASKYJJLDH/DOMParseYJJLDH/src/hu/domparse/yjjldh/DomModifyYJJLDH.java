package hu.domparse.yjjldh;

import java.io.*;
import java.text.ParseException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.w3c.dom.traversal.*;
import org.xml.sax.*;

public class DomModifyYJJLDH {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException, DOMException, ParseException, TransformerException {
        // XML fájl betárolása
        File xml = new File("C:/xml/beadando/XMLYJJLDH.xml");

        // DOM doc készítése az xml ből
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xml);

        // a DOM doc adatainak módosítása
        DomModifier.modifyDom(document);

        // DOM Traversal készítése a DOM Documentből
        DocumentTraversal traversal = (DocumentTraversal) document;

        // DOM TreeWalker inicializálása        
        TreeWalker walker = traversal.createTreeWalker(document.getDocumentElement(),
                NodeFilter.SHOW_ELEMENT | NodeFilter.SHOW_TEXT, null, true);

        // rekurzív DOM bejárás 
        DomTraverser.traverseLevel(walker, "");

        //modositott XML létrehozása XMLyjjldh.updated.xml fájlként
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("XMLyjjldh.updated.xml"));
        transformer.transform(source, result);
    }

    private static class DomModifier {
        public static void modifyDom(Document document) throws XPathExpressionException, DOMException, ParseException {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            // 1.)Az egyik Volkswagen Passatot tanulókocsit lecserélték Golfra
            // XPath segítségével lekérdezzük a megfelelő elemet/csomópontot a DOM
            // documentból
            Node fajta = (Node) xpath.evaluate("//jarmu[./gyarto='Volkswagen']/tipus",
                    document, XPathConstants.NODE);

            fajta.setTextContent("Golf");

            // 2.) Mivel az egyik tanulo megbukott a kreszvizsgan igy ujra be kell fizetnie a vizsgadijat
            //500.000-ret futott szánok teherbírását 20% -kal csökkenteni kell.
            NodeList kreszvizsga = (NodeList) xpath.evaluate("//kreszvizsga[./eredmeny='Megbukott']/vizsgadij", document,
                    XPathConstants.NODESET);

            for (int i = 0; i < kreszvizsga.getLength(); i++) {
                Node dij = kreszvizsga.item(i);


                String vizsgadij = (dij.getTextContent());
                int vizsgadij1 = Integer.parseInt(vizsgadij);
                dij.setTextContent(Double.toString(vizsgadij1 + 4600));
            }
        }
    }

    private static class DomTraverser {
        public static void traverseLevel(TreeWalker walker, String indent) {
            // aktuális csomópont
            Node node = walker.getCurrentNode();

            // kiíratás
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                printElementNode(node, indent);
            } else {
                printTextNode(node, indent);
            }

            // rekurzívan meghívjuk a bejárást a DOM fa eggyel mélyebben lévő csomópontjára,
            // majd azok testvér csomópontjaira
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseLevel(walker, indent + "    ");
            }

            walker.setCurrentNode(node);
        }

        private static void printElementNode(Node node, String indent) {
            System.out.print(indent + node.getNodeName());

            printElementAttributes(node.getAttributes());
        }

        private static void printElementAttributes(NamedNodeMap attributes) {
            int length = attributes.getLength();

            if (length > 0) {
                System.out.print(" [ ");

                for (int i = 0; i < length; i++) {
                    Node attribute = attributes.item(i);

                    System.out.printf("%s=%s%s", attribute.getNodeName(), attribute.getNodeValue(),
                            i != length - 1 ? ", " : "");
                }

                System.out.println(" ]");
            } else {
                System.out.println();
            }
        }

        private static void printTextNode(Node node, String indent) {
            String content_trimmed = node.getTextContent().trim();

            if (content_trimmed.length() > 0) {
                System.out.print(indent);
                System.out.printf("{ %s }%n", content_trimmed);
            }
        }
    }
   
}