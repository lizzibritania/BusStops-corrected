package avagian.gwtApp.shared;

import java.io.Serializable;

/**
 * Created by Lizzi on 21.03.2017.
 */
public class BusStopInfo implements Serializable {
    @Override
    public String toString() {
        return "BusStopInfo{" +
                "busNumber='" + busNumber + '\'' +
                ", firstStop='" + firstStop + '\'' +
                ", lastStop='" + lastStop + '\'' +
                ", xTime='" + xTime + '\'' +
                '}';
    }

    private String busNumber;
    private String firstStop;
    private String lastStop;
    private String xTime;

    public String getFirstStop() {
        return firstStop;
    }

    public void setFirstStop(String firstStop) {
        this.firstStop = firstStop;
    }

    public String getLastStop() {
        return lastStop;
    }

    public void setLastStop(String lastStop) {
        this.lastStop = lastStop;
    }

    public String getxTime() {
        return xTime;
    }

    public void setxTime(String xTime) {
        this.xTime = xTime;
    }

    public BusStopInfo(String busNumber, String firstStop, String lastStop, String xTime) {
        this.busNumber = busNumber;
        this.firstStop = firstStop;
        this.lastStop = lastStop;
        this.xTime = xTime;
    }

        public BusStopInfo(String busNumber) {
            this.busNumber = busNumber;

        }

    public BusStopInfo() {
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }



}
