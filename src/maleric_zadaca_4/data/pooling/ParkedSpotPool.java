/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.data.pooling;

import java.util.Enumeration;
import maleric_zadaca_4.data.Car;
import maleric_zadaca_4.data.Zone;

/**
 *
 * @author Marko
 */
public class ParkedSpotPool extends ObjectPool<ParkingSpot>{
    Zone poolZone;
    
    //TODO ovo nije baš ovako dobro za parkiranje, možda nekako drugačije da mu prosljedimo argumente
    public ParkedSpotPool(Zone zone){
        super(3000); //vrijeme isteka za ovu zonu na parkiranje
        
        this.poolZone = zone;
                
    }
    public ParkingSpot park(Car c){
        System.out.println("Parkiravam auto.. " + c.getId());
        
        ParkingSpot t = new ParkingSpot(1,poolZone);
        locked.put(t, System.currentTimeMillis());
        return (t);
        
    }
    @Override
    protected ParkingSpot create() {
        //return new ParkingSpot();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validate(ParkingSpot o) {
        return o.IsEmpty();
    }

    @Override
    public void expire(ParkingSpot o) {
        //Kad istekne onda treba izać auto iz parkirališta
        System.out.println("Car must unpark, limit is over for " + o.unpark().getId());
    }
    
    //TODO: malo izmjenit da nije checkOut, nego getSpot(...);
    @Override
    public synchronized ParkingSpot checkOut() {
        long now = System.currentTimeMillis();
        ParkingSpot t;
        if (unlocked.size() > 0) {
            Enumeration<ParkingSpot> e = unlocked.keys();
            while (e.hasMoreElements()) {
                t = e.nextElement();
                if ((now - unlocked.get(t)) > expirationTime) {
                    // object has expired
                    unlocked.remove(t);
                    expire(t);
                    t = null;
                } else {
                    if (validate(t)) {
                        unlocked.remove(t);
                        locked.put(t, now);
                        return (t);
                    } else {
                        // object failed validation
                        unlocked.remove(t);
                        expire(t);
                        t = null;
                    }
                }
            }
        }
        // no objects available, create a new one
       /* Ovo je sad park
        t = create();
        locked.put(t, now);
        return (t);
        */
        
        return null;
    }
}
