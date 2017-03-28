package avagian.gwtApp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import avagian.gwtApp.shared.BusStopInfo;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface GwtAppServiceAsync {


    void gwtAppCall(String data, AsyncCallback<List<BusStopInfo>> async);



    void addNewBus(BusStopInfo busStopInfo, AsyncCallback<Void> async);

    void deleteBus(String bus, AsyncCallback<Void> async);

    void sortByBusN(List<BusStopInfo> listOfBuses, AsyncCallback<List<BusStopInfo>> async); //throws IOException, SAXException, ParserConfigurationException;

    void sortByFstop(List<BusStopInfo> listOfBuses, AsyncCallback<List<BusStopInfo>> async); //throws IOException, SAXException, ParserConfigurationException;

    void sortByLstop(List<BusStopInfo> listOfBuses, AsyncCallback<List<BusStopInfo>> async) ;//throws IOException, SAXException, ParserConfigurationException;

    void sortByTime(List<BusStopInfo> listOfBuses, AsyncCallback<List<BusStopInfo>> async);// throws IOException, SAXException, ParserConfigurationException;
}
