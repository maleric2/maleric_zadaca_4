/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import maleric_zadaca_4.data.cache.ZoneCache;
import maleric_zadaca_4.data.pooling.ParkingSpot;

/**
 *
 * @author Marko
 */
public class Parking {

    int numZones;
    int capacatyPerZone;
    boolean open = true;
    int maxParkTime;

    int timeUnit;
    int intervalDolaska;
    int intervalKontrole;

    int parkingPrice;
    int kaznaParkiranja;

    //Zona->kapacitet = brojZone * kapacitetZone
    //Zona->vrijeme_parkiranja = brojZone * maxParkiranje * vremenskaJedinica
    public Parking(int numZones, int capacityPerZone, int maxParkTime, int timeUnit, int intervalDolaska, int parkingPrice, int intervalKontrole, int maxProduzivanje, int kaznaParkiranja) {
        this.numZones = numZones;
        this.capacatyPerZone = capacityPerZone;
        this.kaznaParkiranja = kaznaParkiranja;
        this.maxParkTime = maxParkTime;
        this.timeUnit = timeUnit;
        this.intervalDolaska = intervalDolaska;
        this.parkingPrice = parkingPrice;
        this.intervalKontrole = intervalKontrole;

        cache = new ZoneCache();
        for (int i = 0; i < numZones; i++) {
            //TODO: izračun kapaciteta zone
            cache.addZone(new Zone(i, Zone.getZoneCapacity(i + 1, capacityPerZone), Zone.getZoneParkingTime(i + 1, maxParkTime, timeUnit), Zone.getZoneParkingPrice(i, numZones, parkingPrice), maxProduzivanje));
        }
    }

    private static ZoneCache cache;

    public static Zone getZone(int zoneNum) {
        Zone zone = cache.acquire(zoneNum);
        if (zone != null) {
            return zone;
        }
        return null;
    }

    public boolean parkCar(Car c, Zone z) {
        //TODO: Iterator
        if (open && z != null && c != null) {
            ParkedDetails parkedSpot = z.park(c);
            if (parkedSpot != null) {
                System.out.println("- " + Zone.getTime(parkedSpot.dateParked) + " - Car " + c.getId() + "\t- PARKED - Zone:" + z.getId() + " (SPOT: " + parkedSpot.spot.getId() + " - PRICE: " + parkedSpot.spot.getZone().parkingPrice + ")");
                return true;
            } else {
                System.out.println("- " + Zone.getTimeNow() + " - Car " + c.getId() + "\t-  FULL  - Zone:" + z.getId() + " (capacity: " + z.getCapacity() + ")");
                return false;
            }
        }
        return false;
    }

    public void parkCar(Car c) {
        //TODO: Iterator
        if (open) {
            for (int i = 0; i < numZones; i++) {
                Zone current = getZone(i);
                ParkedDetails parkedSpot = current.park(c);
                if (parkedSpot != null) {
                    System.out.println("- park car - Car " + c.getId() + " is parked on " + parkedSpot.spot.getId() + " spot in zone " + current.getId());
                    return;
                } else {
                    System.out.println("- park car - Zone " + current.getId() + " is full (capacity: " + current.getCapacity() + ")");
                }
            }
        }
    }

    public void unparkCar(Car c) {
        Zone cZone = carZone(c);
        if (cZone != null) {
            ParkedDetails pDetails = cZone.unpark(c);
            System.out.println("- " + Zone.getTimeNow() + " - Car " + c.getId() + "\t-UNPARKING- Zone:" + cZone.getId() + " (SPOT: " + pDetails.getSpot().getId() + " - TOTAL TIME: " + pDetails.getTotalParkingTime() / 1000 + ")");

        }
    }

    public void extend(Car c) {
        Zone cZone = carZone(c);
        if (cZone != null) {
            ParkedDetails pDetails = cZone.extend(c);
            if (pDetails != null) {
                System.out.println("- " + Zone.getTimeNow() + " - Car " + c.getId() + "\t-EXTENDED- Zone:" + cZone.getId() + " (SPOT: " + pDetails.getSpot().getId() + " - PRICE: " + pDetails.spot.getZone().parkingPrice + ")");
            } else {
                System.out.println("- " + Zone.getTimeNow() + " - Car " + c.getId() + "\t-EXT. FAILED- Zone:" + cZone.getId());
            }

        }
    }

    public void doNothing(Car c) {
        Zone cZone = carZone(c);
        if (cZone != null) {
            ParkedDetails pDetails = cZone.getDetails(c);
            System.out.println("- " + Zone.getTimeNow() + " - Car " + c.getId() + "\t-DO NOTHING- Zone:" + cZone.getId() + " (SPOT: " + pDetails.getSpot().getId() + ")");

        }
    }

    public void naplatiKaznu(Car c, Zone cZone) {
        if (cZone != null) {
            cZone.naplatiKaznu(this.kaznaParkiranja);
            c.addKazne(this.kaznaParkiranja);
            cZone.unpark(c);
            System.out.println("- " + Zone.getTimeNow() + " - Car " + c.getId() + "\t- KAZNA - Zone:" + cZone.getId());

        }
    }

    public Zone carZone(Car c) {
        for (int i = 0; i < numZones; i++) {
            Zone current = getZone(i);
            boolean parked = current.isCarParked(c);
            if (parked) {
                //System.out.println("- is Parked - Car " + c.getId() + " is parked on " + current.getId());
                return current;
            }
        }
        return null;
    }

    public synchronized boolean isCarParked(Car c) {
        if (carZone(c) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void openParking(boolean open) {
        this.open = open;

        if (open) {
            System.out.println("Parking is open");
        } else {
            System.out.println("Parking is closed");
        }
    }

    public List<ParkingSpot> getAllParkingSpots() {
        List<ParkingSpot> list = new ArrayList<>();
        for (int i = 0; i < numZones; i++) {
            Zone current = getZone(i);
            current.getSpots().stream().forEach((p) -> {
                list.add(p);
            });
        }
        return list;
    }

    public boolean isParkingOpen() {
        return open;
    }

    public int getNumZones() {
        return numZones;
    }

    public int getMaxParkTime() {
        return maxParkTime;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public int getIntervalDolaska() {
        return intervalDolaska;
    }

    public int getIntervalKontrole() {
        return intervalKontrole;
    }

    public int getParkingPrice() {
        return parkingPrice;
    }

    public int getKaznaParkiranja() {
        return kaznaParkiranja;
    }

    public void setKaznaParkiranja(int kaznaParkiranja) {
        this.kaznaParkiranja = kaznaParkiranja;
    }

    public void getParkingPenalty() {
        System.out.println("ZONA\tKAZNE\tBr.Kazni");
        for (int i = 0; i < numZones; i++) {
            Zone current = getZone(i);
            System.out.println(current.getId() + "\t" + current.getKazne() + "\t" + current.getTotalNumKazni() + "");
        }
    }

    public void getParkingProfit() {
        System.out.println("ZONA\tPROFIT\tBr.Parkiranja");
        for (int i = 0; i < numZones; i++) {
            Zone current = getZone(i);
            System.out.println(current.getId() + "\t" + current.getZarada() + "\t" + current.getTotalNumParkings() + "");
        }
    }

    public void getParkingHistory() {
        boolean includeHeader = true;
        for (int i = 0; i < numZones; i++) {
            Zone current = getZone(i);
            if (current != null) {
                StringBuilder sb = current.getAllDetails(includeHeader);
                System.out.println(sb.toString());
                includeHeader=false;
            }
        }
    }

    public synchronized void getParkingZoneInfo() {
        for (int i = 0; i < numZones; i++) {
            Zone current = getZone(i);
            current.getCurrentParkingInfo();
        }
    }

    public static float getIntervalUlaskaAuta(int timeUnit, int intervalUlaska) {
        Random ran = new Random();
        double x = ran.nextDouble() * 1.0;
        return (float) ((timeUnit / intervalUlaska) * x);
    }

    public float getIntervalUlaskaAuta() {
        Random ran = new Random();
        double x = ran.nextDouble() * 1.0;
        //System.out.println("x = " +x);
        return (float) ((this.timeUnit / this.intervalDolaska) * x);
    }

    public float intervalKontrole() {
        return (float) ((this.timeUnit / this.intervalKontrole));
    }

    public Zone getRandomZone() {
        Random ran = new Random();
        double x = ran.nextDouble() * 1.0;
        //System.out.println("RandZone " + (int)(numZones * x));
        return getZone((int) (numZones * x));
    }
}
