/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.mvc;

import static java.lang.Thread.sleep;
import java.util.List;
import maleric_zadaca_4.data.Car;
import maleric_zadaca_4.data.pooling.ParkingSpot;

/**
 *
 * @author Marko
 */
public class ControlerThread implements Runnable {

    private Thread t;
    private float interval;
    ParkingController pc;

    ControlerThread(float interval, ParkingController pc) {
        this.interval = interval;
        this.pc = pc;
    }

    @Override
    public void run() {
        do {
            try {
                sleep((long) (interval * 1000));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
            List<ParkingSpot> parkingSpots = pc.getAllParkingSpots();
            if (parkingSpots != null) {
                for (ParkingSpot p : parkingSpots) {
                    if (p.getCar() != null) {
                        if (p.isVrijemeIsteklo(pc.getModel().getMaxParkTime())) {
                            int kazna = pc.getModel().getKaznaParkiranja();
                            Car c = p.getCar();
                            pc.getModel().naplatiKaznu(c, p.getZone());
                            pc.getcController().odveziAutoNaDeponij(c);
                        }
                    }
                }
            }

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
