import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class WeatherDOM {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        //iniciem el file
        File inputFile = new File("forecast.xml");
        //iniciem el DocumentBuilder  a partir d'un DocumentBuilderFactory
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        //preparem el document per a DOM
        Document doc = dBuilder.parse(inputFile);

        //obtenim el nodeList de "time"
        NodeList timeList = doc.getElementsByTagName("time");

        //Mostrem l'informació de l'apartat A
        for(int i = 0; i < timeList.getLength(); i++){
            //creem un Element temps per cada Node "time"
            Element temps = (Element) timeList.item(i);
            //obtenim l'informació que volem cada Element temps
            Element simbol = (Element) temps.getElementsByTagName("symbol").item(0);
            Element temperatura = (Element) temps.getElementsByTagName("temperature").item(0);
            Element windSpeed = (Element) temps.getElementsByTagName("windSpeed").item(0);

            //Obtenim el nom directament sobre el document ja que esta fora de "time" i l'imprimim
            System.out.println("Mostrant el temps de " + doc.getDocumentElement().getElementsByTagName("name").item(0).getTextContent());

            //mostrem la temperatura en graus Celsius
            System.out.println("\t Temperatura: " + temperatura.getAttribute("value") + "Cº");

            //mostrem el temps
            System.out.println("\t Tipus de temps: " + simbol.getAttribute("name"));

            //obtenim la velocitat del vent y li donem format
            String vel = String.format("%.2f",(Double.parseDouble(windSpeed.getAttribute("mps"))*3.6));
            //mostrem la velocitat del temps
            System.out.println("\t Velocitat del vent: " + vel + "Kph");

            //deixem un espai al final de cada node
            System.out.println("");
        }
    }
}
