/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.mvc;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import maleric_zadaca_4.data.Car;
import maleric_zadaca_4.data.Owner;

/**
 *
 * @author Marko
 */
public class ParkingThread implements Runnable {

    private Thread t;
    private float interval;
    ParkingController pc;
    List<OwnerThread> owthreads;
    OwnersThread owthread;
    private ControlerThread cthread;

    ParkingThread(ParkingController pc) {
        this.pc = pc;
        owthreads = new ArrayList<>();
        this.interval = pc.getModel().getIntervalUlaskaAuta();
    }

    @Override
    public void run() {
        owthread = new OwnersThread(pc);
        owthread.start();
        cthread = new ControlerThread(pc.getModel().intervalKontrole(), pc);
        cthread.start();
        
        Car c = null;
        c= pc.getcController().getUnparkedCar();
        do {
            //
            if (pc.getcController().isThereUnparkedCars()) {
                //System.out.println("Interval:: " + interval);

                if (!pc.getModel().parkCar(c, pc.getModel().getRandomZone())) {
                    pc.getcController().setNotSuccessufullParked(c);
                } else {
                    /*OwnerThread owT = new OwnerThread(Owner.getIntervalOdlaskaAuta(pc.getModel().getTimeUnit(), pc.intervalDolaska), pc.getcController(), c);
                    owthreads.add(owT);
                    owT.start();*/
                }
                c = pc.getcController().getUnparkedCar();
                if(c!=null)setParkingInterval(c.getIntervalDolaska()); //postavlja novi interval za novo auto
            }
            try {
                sleep((long) (interval * 1000));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }

        } while (pc.isParkingOpen());
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
