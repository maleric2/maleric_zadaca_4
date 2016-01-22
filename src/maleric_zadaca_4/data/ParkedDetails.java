/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.data;

import java.util.Date;
import maleric_zadaca_4.data.pooling.ParkingSpot;

/**
 * Used inside zone
 *
 * @author Marko
 */
public class ParkedDetails {

    public boolean parked;
    Date dateParked;
    Car carParked;
    ParkingSpot spot;
    Date dateUnparked;

    public ParkedDetails(Car carParked, ParkingSpot spot) {
        this.carParked = carParked;
        this.spot = spot;
        dateParked = new Date();
        parked = true;
    }
    public void unpark(){
        dateUnparked = new Date();
        parked = false;
    }

    public String getDetialsWritter(String f) {
        String date = Zone.getTime(dateParked);
        String details = date + f + carParked.getId() + f + spot.getId();
        return details;
    }

    public String getDetailsHeader(String f) {
        String header = "Date" + f+"\t" + "Car" + f + "Spot\n";
        header+=        "----" + f+"\t" + "---" + f + "----";
        return header;
    }
    
    //TODO mozda visitor
    public String getDetialsWritterAdv(String f) {
        String date1 = Zone.getTime(dateParked);
        String date2 = Zone.getTime(dateUnparked);
        String totalParkTime = "     ";
        if(dateParked!=null && dateUnparked==null)
            totalParkTime = getParkingTime(new Date())/1000+"s";
        else if(dateUnparked!=null)
            totalParkTime = getTotalParkingTime()/1000+"s";
                
        String details =  date1 + f + date2 +f +carParked.getId() + f + spot.getId() + f +spot.getZone().getId() + f + totalParkTime;
        return details;
    }

    public String getDetailsHeaderAdv(String f) {
        String header = "Parked" + f+"\t" + "Unparked"+ f+"" + "Car" + f + "Spot" + f + "Zone" + f + "Time\n";
        header+=        "------" + f+"\t" + "--------"+ f+"" + "---" + f + "----" + f + "----"+ f + "----";
        return header;
    }
    
    public long getTotalParkingTime(){
        return dateUnparked.getTime() - dateParked.getTime();
    }
    public long getParkingTime(Date d){
        return d.getTime() - dateParked.getTime();
    }
    public Car getCarParked() {
        return carParked;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public Date getDateUnparked() {
        return dateUnparked;
    }

}
