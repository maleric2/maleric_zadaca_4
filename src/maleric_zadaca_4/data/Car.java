/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.data;

import java.util.Random;

/**
 *
 * @author Marko
 */
public class Car implements Comparable {
    int id;
    Owner owner;
    int spent = 0;
    int numParking=0;
    int numExtends=0;
    int kazne = 0;
    
    public double randomValue;
    private float intervalOdlaska;
    private float intervalDolaska;
       
    public Car(int id, float intervalDolaska, float intervalOdlaska, double randomValue) {
        this.id = id;
        owner = new Owner(this);
        
        this.randomValue = randomValue;
        this.intervalDolaska=intervalDolaska;
        this.intervalOdlaska=intervalOdlaska;        
    }

    public int getId() {
        return id;
    }

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }

    public float getIntervalOdlaska() {
        return intervalOdlaska;
    }

    public void setIntervalOdlaska(float intervalOdlaska) {
        this.intervalOdlaska = intervalOdlaska;
    }

    public float getIntervalDolaska() {
        return intervalDolaska;
    }

    public void setIntervalDolaska(float intervalDolaska) {
        this.intervalDolaska = intervalDolaska;
    }

    public Owner getOwner() {
        return owner;
    }

    public int getNumExtends() {
        return numExtends;
    }

    public void setNumExtends(int numExtends) {
        this.numExtends = numExtends;
    }

    public int getKazne() {
        return kazne;
    }

    public void addKazne(int kazne) {
        this.kazne = this.kazne + kazne;
    }

    @Override
    public int compareTo(Object o) {
        Car c = (Car)o;
        int compareage=c.getNumParking();
        return compareage-this.getNumParking();
    }

    public int getNumParking() {
        return numParking+numExtends;
    }

    public void setNumParking(int numParking) {
        this.numParking = numParking;
    }
    public void addNumParking( ) {
        this.numParking++;
    }
}
