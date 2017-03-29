package avagian.gwtApp.client;
import avagian.gwtApp.shared.BusStopInfo;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


@RemoteServiceRelativePath("gwtAppService")
public interface GwtAppService extends RemoteService {
  List<BusStopInfo> gwtAppCall(String data)  ;

 void addNewBus(BusStopInfo busStopInfo);

    void deleteBus(String bus);

    List<BusStopInfo>  sortByBusN(List<BusStopInfo> listOfBuses) ;

    List<BusStopInfo>  sortByFstop(List<BusStopInfo> listOfBuses) ;//throws IOException, SAXException, ParserConfigurationException;

    List<BusStopInfo> sortByLstop(List<BusStopInfo> listOfBuses) ;//throws IOException, SAXException, ParserConfigurationException;
    List<BusStopInfo>  sortByTime(List<BusStopInfo> listOfBuses) ;//throws IOException, SAXException, ParserConfigurationException;
    List<BusStopInfo> filtration( String lookup);
}
