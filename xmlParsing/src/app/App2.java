package app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class App2 {
    public static void main(String[] args) throws Exception {

        Document doc = DomUtil.parse("ejemplo.xml", false);
        Element elemento = doc.getDocumentElement(); 
        recorrer(elemento);       

    }

    public static void recorrer(Element elemento) {

        NodeList nodos = elemento.getChildNodes();

        for(int i = 0; i < nodos.getLength(); i++){

            if(nodos.item(i).getNodeType() == 1) {

                recorrer((Element)nodos.item(i));

            }

        }

        System.out.println("Encontrado nodo: " + elemento.getAttribute("id"));

    }

}