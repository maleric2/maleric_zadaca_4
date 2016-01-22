/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.data.cache;

import java.util.ArrayList;
import java.util.List;
import maleric_zadaca_4.data.Zone;

/**
 *
 * @author Marko
 */
public class ZoneCache implements Cache<Zone>{

    private List<Zone> zones; 

    public ZoneCache() {
        zones = new ArrayList<Zone>();
    }
    
    

    public Zone getZone(int zoneNum) {
        for (Zone zone : zones) {
            if (zone.getId() == zoneNum) {
                //System.out.println("Returning cached zone with id " + zoneNum);
                return zone;
            }
        }
        return null;
    }
    
    public void addZone(Zone newZone){
        boolean exists = false;
        
        for(Zone zone: zones){
            if(zone.getId()==newZone.getId())
                exists=true;
        }
        if(!exists){
            zones.add(newZone);
        }
    }

    @Override
    public void release(Zone resource) {
        addZone(resource);
    }

    @Override
    public Zone acquire(int num) {
        return getZone(num);
    }
    
    
}
