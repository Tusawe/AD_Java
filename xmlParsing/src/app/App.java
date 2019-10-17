package app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class App {
    public static void main(String[] args) throws Exception {

        Document doc = DomUtil.parse("mondial.xml", false);
        Element raiz = doc.getDocumentElement();
        NodeList paises = raiz.getElementsByTagName("country");
        String nombre;

        for(int i = 0; i < paises.getLength(); i++){

            nombre = ((Element) paises.item(i)).getElementsByTagName("name").item(0).getTextContent();
            System.out.println(nombre);

        }

    }

}