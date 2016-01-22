/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.data;

import java.util.Random;
import maleric_zadaca_4.mvc.CarController;

/**
 *
 * @author Marko
 */
public class Owner {

    Car car;

    public Owner(Car car) {
        this.car = car;
    }

    public void doAction(CarController cc) {
        int action = getRandomValue(1, 3);
        //System.out.println("Random action: " + action);
        switch (action) {
            case 1: {
                cc.doNothing(car);
                break;
            }
            case 2: {
                cc.unpark(car);
                //Ako se želi da se auto ne vrati na parkiralište
                //cc.addOldParkedCar(car);
                break;
            }
            case 3: {
                cc.extendParking(car);
                break;
            }

        }

    }

    public static float getInterval(int timeUnit, int intervalOdlaska, double random) {
        return (float) ((timeUnit / intervalOdlaska) * random);
    }

    public float getIntervalOdlaskaAuta(int timeUnit) {
        return car.getIntervalOdlaska();
    }

    public float getIntervalDolaska(int timeUnit) {
        return car.getIntervalDolaska();
    }

    public static double getRandomValue() {
        Random ran = new Random();
        double x = ran.nextDouble() * 1.0;
        return x;
    }

    public static int getRandomValue(int min, int max) {
        Random ran = new Random();
        int range = max - min + 1;
        int randomNum = ran.nextInt(range) + min;
        return randomNum;
    }
}
