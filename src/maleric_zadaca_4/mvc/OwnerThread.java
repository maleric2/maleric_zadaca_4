/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.mvc;

import static java.lang.Thread.sleep;
import maleric_zadaca_4.data.Car;

/**
 *
 * @author Marko
 */
public class OwnerThread implements Runnable {

    private Thread t;
    private float interval;
    CarController cc;
    Car car;

    OwnerThread(float interval, CarController cc, Car car) {
        this.interval = interval;
        this.cc = cc;
        this.car = car;
    }

    @Override
    public void run() {

        try {
            sleep((long) (interval * 1000));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        cc.unpark(car);

    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        } else {
            t = null;
            t = new Thread(this);
            t.start();
        }
    }

    public void setParkingInterval(float interval) {
        this.interval = interval;
    }
}
