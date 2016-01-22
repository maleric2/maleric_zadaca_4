/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import maleric_zadaca_4.data.pooling.ParkedSpotPool;
import maleric_zadaca_4.data.pooling.ParkingSpot;

/**
 * TODO: Composite Zone 1 ParkingSpot1 ParkingSpot2 Zone2 ParkingSpot1..
 * ParkingSpot4
 *
 *
 * @author Marko
 */
public class Zone {

    int id;
    int capacity;
    int parkingTime;
    int parkingPrice;
    int zarada;
    int kazne;
    int maxProduzivanje;

    int totalNumKazni = 0;
    int totalNumParkings = 0;
    int totalNumExtends = 0;

    private List<ParkingSpot> spots;
    private List<ParkedDetails> parkedDetails;

    public Zone(int id, int capacity, int parkingTime, int parkingPrice, int maxProduzivanje) {
        this.id = id;
        this.capacity = capacity;
        this.parkingTime = parkingTime;
        this.parkingPrice = parkingPrice;
        this.maxProduzivanje = maxProduzivanje;
        spots = new ArrayList<>();
        parkedDetails = new ArrayList<>();
        zarada = 0;
        initializeParkingSpots();
    }

    private void initializeParkingSpots() {
        for (int i = 0; i < capacity; i++) {
            spots.add(new ParkingSpot(i, this));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ParkedDetails park(Car c) {
        /*pool.park(c);
         pool.checkOut();*/
        //System.out.println("SPOTS: " + spots.size());
        for (int i = 0; i < spots.size(); i++) {
            if (spots.get(i).IsEmpty()) {
                if (spots.get(i).park(c)) {
                    //System.out.println("Spot" + spots.get(i).getId() + " have now car "+ spots.get(i).getCar().getId());
                    zarada += parkingPrice;
                    c.addNumParking();
                    c.setSpent(c.getSpent() + parkingPrice);
                    totalNumParkings++;
                    ParkedDetails det = new ParkedDetails(c, spots.get(i));
                    parkedDetails.add(det);
                    return det;
                }
            }
            //System.out.println("Spot " + spots.get(i).getId() + " is not empty");

        }
        return null;
    }

    public ParkedDetails unpark(Car c) {
        ParkingSpot cSpot = carSpot(c);
        if (cSpot != null) {
            cSpot.unpark();
            ParkedDetails cDetails = getDetails(c);
            cDetails.unpark();
            return cDetails;
        }
        return null;
    }

    public ParkedDetails extend(Car c) {
        ParkingSpot cSpot = carSpot(c);
        if (cSpot != null && c.getNumExtends() < this.maxProduzivanje) {
            cSpot.extend();
            zarada += parkingPrice;
            c.setSpent(c.getSpent() + parkingPrice);
            c.setNumExtends(c.getNumExtends() + 1);
            totalNumExtends++;
            return getDetails(c);
        }
        return null;
    }
    //TODO Iterator možda

    public ParkingSpot carSpot(Car c) {
        for (ParkingSpot spot : spots) {
            if (!spot.IsEmpty() && spot.getCar().getId() == c.getId()) {
                return spot;
            }
        }
        return null;
    }

    public ParkedDetails getDetails(Car c) {
        for (ParkedDetails p : parkedDetails) {
            if (p.getCarParked().equals(c)) {
                return p;
            }
        }
        return null;
    }

    public ParkedDetails getDetails(ParkingSpot s) {
        for (ParkedDetails p : parkedDetails) {
            if (p.getSpot().equals(s)) {
                return p;
            }
        }
        return null;
    }

    public void naplatiKaznu(int kazna) {
        kazne += kazna;
        totalNumKazni++;
    }

    public boolean isCarParked(Car c) {
        if (carSpot(c) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void getCurrentZoneParkingDetails() {
        if (parkedDetails != null && parkedDetails.size() > 0) {
            System.out.println(parkedDetails.get(0).getDetailsHeader("\t\t"));
            for (ParkedDetails p : parkedDetails) {
                if (p.parked) {
                    System.out.println(p.getDetialsWritter("\t\t"));
                }
            }

        }
    }

    public void getOldZoneParkingDetails() {
        if (parkedDetails != null && parkedDetails.size() > 0) {
            System.out.println(parkedDetails.get(0).getDetailsHeaderAdv("\t\t"));
            for (ParkedDetails p : parkedDetails) {
                if (!p.parked) {
                    System.out.println(p.getDetialsWritterAdv("\t\t"));
                }
            }
        }
    }

    public StringBuilder getAllDetails(boolean includeHeader) {

        StringBuilder sb = new StringBuilder();
        if (parkedDetails != null && parkedDetails.size() > 0) {
            if(includeHeader)sb.append(parkedDetails.get(0).getDetailsHeaderAdv("\t\t")).append("\n");
            for (ParkedDetails p : parkedDetails) {
                sb.append(p.getDetialsWritterAdv("\t\t")).append("\n");
            }
        }
        return sb;
    }

    public void getCurrentParkingInfo() {
        if (parkedDetails != null && parkedDetails.size() > 0) {
            System.out.println("_____________________________________________");
            System.out.println("ZONA: " + this.id);
            System.out.println(parkedDetails.get(0).getDetailsHeader("\t\t"));
            for (ParkingSpot s : spots) {
                if (s != null && s.getCar() != null) {
                    System.out.println(s.print("\t\t"));
                }
            }
            float percent = (countPopulatedSpots() * 100 / capacity);
            System.out.println("POPULATED: " + (percent) + " %");
            System.out.println("--------------------------------------------- \n");

        }
    }

    private int countPopulatedSpots() {
        int i = 0;
        for (ParkingSpot s : spots) {
            if (!s.IsEmpty()) {
                i++;
            }
        }
        return i;
    }

    public int getZarada() {
        return zarada;
    }

    public void setZarada(int zarada) {
        this.zarada = zarada;
    }

    public int getTotalNumParkings() {
        return totalNumParkings;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    //Zona->kapacitet = brojZone * kapacitetZone
    public static int getZoneCapacity(int zoneNum, int capacityNum) {
        return zoneNum * capacityNum;
    }

    //Zona->vrijeme_parkiranja = brojZone * maxParkiranje * vremenskaJedinica
    public static int getZoneParkingTime(int zoneNum, int maxParkTime, int timeUnit) {
        return zoneNum * maxParkTime * timeUnit;
    }

    public static int getZoneParkingPrice(int zoneNum, int totalZones, int price) {
        return (totalZones - 1 - zoneNum) * price;
    }

    public static String getTimeNow() {
        return getTime(new Date());
    }

    public static String getTime(Date date) {
        if (date == null) {
            return "            ";
        }
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss.SSS");
        String dateString = ft.format(date);
        return dateString;
    }

    public int getKazne() {
        return kazne;
    }

    public int getTotalNumExtends() {
        return totalNumExtends;
    }

    public int getTotalNumKazni() {
        return totalNumKazni;
    }

    public void setTotalNumKazni(int totalNumKazni) {
        this.totalNumKazni = totalNumKazni;
    }

}
