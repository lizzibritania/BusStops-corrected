package avagian.gwtApp.server;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import avagian.gwtApp.shared.BusStopInfo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Lizzi on 21.03.2017.
 */
public class BusService {


    public List <BusStopInfo> getBusStopInfo() throws ParserConfigurationException, IOException, SAXException {
        List <BusStopInfo> result = new ArrayList<BusStopInfo>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(this.getClass().getResourceAsStream("/busstops.xml"));
        document.getDocumentElement().normalize();
        String bnum,fStop,lStop,xTime;
        NodeList nodeList = document.getElementsByTagName("BusStopInfo");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node busStop = nodeList.item(i);
                Element element = (Element) busStop;
                bnum = (element.getElementsByTagName("busNumber").item(0).getTextContent());
                fStop = element.getElementsByTagName("firstStop").item(0).getTextContent();
                lStop = element .getElementsByTagName("lastStop").item(0) .getTextContent();
                xTime = element.getElementsByTagName("xTime").item(0) .getTextContent();
                BusStopInfo busStopInfo=new BusStopInfo();
                busStopInfo.setBusNumber(bnum);
                busStopInfo.setFirstStop(fStop);
                busStopInfo.setLastStop(lStop);
                busStopInfo.setxTime(xTime);
                result.add(busStopInfo);}
        return result;
    }

    public void setBusStopInfo(BusStopInfo busStopInfo) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        String bnum=busStopInfo.getBusNumber();
        String fStop=busStopInfo.getFirstStop();
        String  lStop=busStopInfo.getLastStop();
        String  xTim=busStopInfo.getxTime();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(this.getClass().getResourceAsStream("/busstops.xml"));

        Element nList = doc.getDocumentElement();


        Element newBusInfo = doc.createElement("BusStopInfo");

           Element busNumber = doc.createElement("busNumber");
        busNumber.appendChild(doc.createTextNode(bnum));
        newBusInfo.appendChild(busNumber);

        Element firstStop = doc.createElement("firstStop");
        firstStop.appendChild(doc.createTextNode(fStop));
        newBusInfo.appendChild(firstStop);

        Element lastStop = doc.createElement("lastStop");
        lastStop.appendChild(doc.createTextNode(lStop));
        newBusInfo.appendChild(lastStop);

        Element xTime = doc.createElement("xTime");
        xTime.appendChild(doc.createTextNode(xTim));
        newBusInfo.appendChild(xTime);

        nList.appendChild(newBusInfo);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        //initialize StreamResult with File object to save to file
        StreamResult result = new StreamResult(new File("C:/GWT - UIBinder/src/main/resources/busstops.xml"));
        DOMSource source = new DOMSource(doc);
        try {
            transformer.transform(source, result);
            System.out.println("DONE");
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        BusService busService=new BusService();
        busService.setBusStopInfo(new BusStopInfo("d","d","s","j"));
    }

    public void DeleteBus(String busNumber) throws ParserConfigurationException, IOException, SAXException, TransformerConfigurationException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(this.getClass().getResourceAsStream("/busstops.xml"));

        Node categories = doc.getElementsByTagName("BusStopInfo").item(0);
        NodeList busList = categories.getChildNodes();

        while (busList.getLength() > 0) {
            Node node = busList.item(0);
            node.getParentNode().removeChild(node);
        }
        for(int i=1;i<20;i++){

            Element busStopInfo= doc.createElement("BusStopInfo");
            categories.appendChild(busStopInfo);}
}


}
