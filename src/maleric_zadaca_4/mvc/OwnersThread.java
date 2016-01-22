/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.mvc;

import static java.lang.Thread.sleep;
import maleric_zadaca_4.data.Car;
import maleric_zadaca_4.data.Owner;

/**
 *
 * @author Marko
 */
public class OwnersThread implements Runnable {

    private Thread t;
    private float interval;
    CarController cc;
    ParkingController pc;

    OwnersThread(ParkingController pc) {
        this.interval = 1;
        this.cc = pc.getcController();
        this.pc =pc;
    }

    @Override
    public void run() {
        do {
            Car c = cc.getParkedCar();
            if(c!=null)
                interval = c.getIntervalOdlaska();
            try {
                sleep((long) (interval * 1000));
            } catch (InterruptedException ex) {
            }

            if(c!=null) c.getOwner().doAction(cc);
            
        } while (pc.isAppRunning());
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
