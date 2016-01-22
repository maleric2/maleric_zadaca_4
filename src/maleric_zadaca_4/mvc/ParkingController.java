/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.mvc;

import java.util.List;
import maleric_zadaca_4.data.Car;
import maleric_zadaca_4.data.Owner;
import maleric_zadaca_4.data.Parking;
import maleric_zadaca_4.data.pooling.ParkingSpot;

/**
 *
 * @author Marko
 */
public class ParkingController {
    private Parking model;
    private ParkingView view;
    
    private CarController cController;
    private int parkingInterval;
    
    private ParkingThread pthread;
    private ControlerThread cthread;
    public int intervalDolaska;
    
    private boolean appRuning=true;

    public ParkingController(Parking model, int intervalDolaska) {
        this.model = model;
        this.intervalDolaska = intervalDolaska;
        pthread = new ParkingThread(this);
    }
    
    public void startParking(){
        model.openParking(true);
        pthread.start();
    }
    public void start(){
        cthread.start();
    }
    
    /**
     * Used by thread for parking
     */
    public void stop(){
        closeParking();
        appRuning=false;
    }
    public boolean isAppRunning(){
        return appRuning;
    }
    
    //možda ovo sve u view
    public void getParkingProfit(){
       model. getParkingProfit();
    }
    public void getParkingPenalty(){
        model.getParkingPenalty();
    }
    
     public void getParkingHistory(){
       model.getParkingHistory();
    } 
     public void getParkingInfo(){
       model.getParkingZoneInfo();
    }
    public boolean isParkingOpen(){
        return model.isParkingOpen();
    }
    public void closeParking() {
        model.openParking(false);
    }

    public void setcController(CarController cController) {
        this.cController = cController;
    }

    public CarController getcController() {
        return cController;
    }

    public Parking getModel() {
        return model;
    }
    public List<ParkingSpot> getAllParkingSpots(){
        return model.getAllParkingSpots();
    }
    
}
