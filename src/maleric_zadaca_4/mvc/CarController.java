/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import maleric_zadaca_4.data.Car;
import maleric_zadaca_4.data.Owner;
import maleric_zadaca_4.data.Parking;

/**
 *
 * @author Marko
 */
public class CarController {

    private int numerOfCars;
    private Parking parking;
    List<Car> model;
    CarView view;
    //iterator
    List<Car> unparked;
    List<Car> parked;
    List<Car> deponij;
    List<Car> oldParked;

    int intervalDolaska;
    int intervalOdlaska;

    public CarController(int numerOfCars, Parking p, int intervalDolaska, int intervalOdlaska) {
        numerOfCars = numerOfCars;
        this.parking = p;
        unparked = new ArrayList<>();
        parked = new ArrayList<>();
        deponij = new ArrayList<>();
        oldParked = new ArrayList<>();
        this.intervalDolaska = intervalDolaska;
        this.intervalOdlaska = intervalOdlaska;
        model = generateCars(numerOfCars);

        this.view = new CarView();
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public void setParkedUnparkedCars() {
        if (parking == null) {
            return;
        }
        List<Car> parked1 = new ArrayList<>();
        List<Car> unparked1 = new ArrayList<>();
        model.stream().forEach((c) -> {
            if (parking.isCarParked(c)) {
                parked1.add(c);
            } else {
                unparked1.add(c);
            }
        });
        parked = parked1;
        unparked = unparked1;
    }

    private List<Car> generateCars(int num) {
        List<Car> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            double rand = Owner.getRandomValue();
            float intD = Owner.getInterval(parking.getTimeUnit(), this.intervalDolaska, rand);
            float intO = Owner.getInterval(parking.getTimeUnit(), this.intervalOdlaska, rand);
            Car c = new Car(i, intD, intO, rand);
            list.add(c);
        }
        return list;
    }

    public boolean isThereUnparkedCars() {
        setParkedUnparkedCars();
        if (unparked != null && unparked.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isThereParkedCars() {
        setParkedUnparkedCars();
        if (parked != null && parked.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Car getUnparkedCar() {
        setParkedUnparkedCars();
        if (unparked != null && unparked.size() > 0) {
            Car c = unparked.get(0);
            unparked.remove(c);
            return c;
        }
        return null;
    }

    public void getParkedStatus() {
        setParkedUnparkedCars();
        System.out.println("unparked: " + unparked.size());
        System.out.println("parked: " + parked.size());
    }

    public Car getParkedCar() {
        setParkedUnparkedCars();
        if (parked != null && parked.size() > 0) {
            Car c = parked.get(0);
            parked.remove(c);
            return c;
        }
        return null;
    }

    /**
     * Postavlja auto koje se nije uspješno parkiralo na početak liste (tj. da
     * se zadnje parkira
     *
     * @param c
     */
    public void setNotSuccessufullParked(Car c) {
        for (int i = 0; i <= model.size(); i++) {
            if (c.getId() == model.get(i).getId()) {
                Car c1 = model.get(i);
                model.remove(i);
                model.add(0, c1);
                return;
            }
        }
    }

    public void extendParking(Car c) {
        if (parking != null) {
            parking.extend(c);
        }
    }

    public void doNothing(Car c) {
        if (parking != null) {
            parking.doNothing(c);
        }
    }

    public void unpark(Car c) {
        if (parking != null) {
            parking.unparkCar(c);
        }
    }

    public synchronized void odveziAutoNaDeponij(Car c) {
        if (model != null) {

            List<Car> copy = new ArrayList<>(model);

            for (int i = 0; i < copy.size(); i++) {
                if (copy.get(i).getId() == c.getId()) {
                    deponij.add(copy.get(i));
                    copy.remove(copy.get(i));
                }
            }
            model = copy;
        }
    }

    public synchronized void addOldParkedCar(Car c) {
        if (model != null) {
            List<Car> copy = new ArrayList<>(model);

            for (int i = 0; i < copy.size(); i++) {
                if (copy.get(i).getId() == c.getId()) {
                    oldParked.add(copy.get(i));
                    copy.remove(copy.get(i));
                }
            }
            model = copy;
        }
    }

    public List<Car> getDeponij() {
        return deponij;
    }

    public void viewDeponij() {
        this.view.drawDeponij(deponij);
    }
    public void viewUnparkedCars() {
        this.view.drawCarList(unparked);
    }
    public void viewTopCars(int num){
        List<Car> top = new ArrayList<>();
        for(Car c: model){
            top.add(c);
        }
        for(Car c: deponij){
            top.add(c);
        }
        Collections.sort(top);
        
        this.view.drawCarList(top,num-1);
    }

    public static float getIntervalDoAuta(int timeUnit, int intervalOdlaska) {
        Random ran = new Random();
        float x = (ran.nextInt(1) + 10) / 10;
        return (timeUnit / intervalOdlaska) * x;
    }

}
