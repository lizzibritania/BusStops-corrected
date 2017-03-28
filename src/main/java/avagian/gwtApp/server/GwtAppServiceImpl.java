package avagian.gwtApp.server;

import avagian.gwtApp.client.GwtAppService;
import avagian.gwtApp.shared.BusStopInfo;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class GwtAppServiceImpl extends RemoteServiceServlet implements GwtAppService {
    public BusService busService = new BusService();

    public List<BusStopInfo> gwtAppCall(String data) {
        BusService service = new BusService();
        try {
            return service.getBusStopInfo();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addNewBus(BusStopInfo busStopInfo) {
        BusService service = new BusService();
        try {
            service.setBusStopInfo(busStopInfo);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public void deleteBus(String bus) {
        BusService service = new BusService();

        try {
            service.DeleteBus(bus);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }


    }

    public List<BusStopInfo> sortByBusN(List<BusStopInfo> listOfBuses){
        List<BusStopInfo> busList = null;
        try {
            busList = busService.getBusStopInfo();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        List<BusStopInfo> sortedList;
        Collections.sort(busList,  NumberComparator);
        return busList;
    }

    public List<BusStopInfo> sortByFstop(List<BusStopInfo> listOfBuses) {
        List<BusStopInfo> busList = null;
        try {
            busList = busService.getBusStopInfo();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        List<BusStopInfo> sortedList;
        Collections.sort(busList,  FirstSComparator);
        return busList;
    }

    public List<BusStopInfo> sortByLstop(List<BusStopInfo> listOfBuses) {
        List<BusStopInfo> busList = null;
        try {
            busList = busService.getBusStopInfo();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        List<BusStopInfo> sortedList;
        Collections.sort(busList,  LastSComparator);
        return busList;
    }

    public List<BusStopInfo> sortByTime(List<BusStopInfo> listOfBuses) {

        List<BusStopInfo> busList = null;
        try {
            busList = busService.getBusStopInfo();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        List<BusStopInfo> sortedList;
        Collections.sort(busList,  TimeComparator);
        return busList;
    }
    public static final Comparator<BusStopInfo> TimeComparator= new Comparator<BusStopInfo>() {
        @Override
        public int compare(BusStopInfo busStopInfo, BusStopInfo t1) {
            return busStopInfo.getxTime().compareTo(t1.getxTime());
        }
    };
    public static final Comparator<BusStopInfo> NumberComparator= new Comparator<BusStopInfo>() {
        @Override
        public int compare(BusStopInfo busStopInfo, BusStopInfo t1) {
            return busStopInfo.getBusNumber().compareTo(t1.getBusNumber());
        }
    };
    public static final Comparator<BusStopInfo> FirstSComparator= new Comparator<BusStopInfo>() {
        @Override
        public int compare(BusStopInfo busStopInfo, BusStopInfo t1) {
            return busStopInfo.getFirstStop().compareTo(t1.getFirstStop());
        }
    };
    public static final Comparator<BusStopInfo> LastSComparator= new Comparator<BusStopInfo>() {

        public int compare(BusStopInfo busStopInfo, BusStopInfo t1) {
            return busStopInfo.getLastStop().compareTo(t1.getLastStop());
        }
    };


}