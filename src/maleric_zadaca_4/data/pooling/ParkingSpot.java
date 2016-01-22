/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.data.pooling;

import java.util.Date;
import maleric_zadaca_4.data.Car;
import maleric_zadaca_4.data.Zone;

/**
 *
 * @author Marko
 */
public class ParkingSpot {

    Date parked;
    int id;
    Zone zone;
    Car car;
    boolean isParked;

    ParkedSpotPool pool; //>MOZDA PREKO POOL-a dohvaćat trenutno parkirno mjesto i provjeravat dali je isteklo i sl. negdje drugo
    //
    public ParkingSpot(int num, Zone zone) {
        this.id = num;
        this.zone = zone;
        this.car = null;
        isParked = false;
        
        //pool = new ParkedSpotPool(zone);
    }
    public ParkingSpot(int num, Zone zone, Car car) {
        this.id = num;
        this.zone = zone;
        this.car = car;
        isParked = true;
        
        pool = new ParkedSpotPool(zone);
    }
    
    public boolean isVrijemeIsteklo(float time){
        long difference = new Date().getTime() - parked.getTime(); 
        if(difference > time*1000){
            //System.out.println(difference +" > "+time*1000);
            return true;
        }
        return false;
    }
    public Zone getZone() {
        return zone;
    }

    public Car getCar() {
        return car;
    }

    public boolean park(Car c) {
        if (IsEmpty()) {
            parked = new Date();
            this.car = c;
            isParked = true;
            return true;
        }
        return false;
    }
    public boolean extend(){
        parked = new Date();
        return true;
    }

    public Car unpark() {
        Car tmp = car;
        car = null;
        isParked = false;
        return tmp;
    }

    public boolean IsEmpty() {
        if (this.car == null) {
            return true;
        } else {
            return false;
        }
    }

    public int getId() {
        return id;
    }
    
    public String print(String f) {
        String date = Zone.getTime(parked);
        String carId = " ";
        if(car!=null)carId = car.getId()+"";
        
        String details = date + f + carId + f + this.getId();
        return details;
    }
}
