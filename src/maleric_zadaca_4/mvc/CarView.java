/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.mvc;

import java.util.List;
import maleric_zadaca_4.data.Car;

/**
 *
 * @author Marko
 */
public class CarView {
    
    public void drawDeponij(List<Car> models){
        StringBuilder sb = new StringBuilder();
        sb.append("AUTO"+"\t"+"KAZNA"+"\t"+"TROSAK PARKINGA\n");
        for(Car c :models){
            sb.append(c.getId()).append("\t").append(c.getKazne()).append("\t").append(c.getSpent()).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    public void drawCarList(List<Car> models){
        StringBuilder sb = new StringBuilder();
        sb.append("AUTO"+"\t"+"KAZNE\t"+"TROSAK PARKINGA\n");
        models.stream().forEach((c) -> {
            sb.append(c.getId()).append("\t").append(c.getKazne()).append("\t").append(c.getSpent()).append("\n");
        });
        System.out.println(sb.toString());
    }
    public void drawCarList(List<Car> models, int limit){
        StringBuilder sb = new StringBuilder();
        sb.append("AUTO"+"\t"+"KAZNE\t"+"TROSAK PARKINGA\tBr.Parkiranja\n");
        int i=0;
        for(Car c :models){
            sb.append(c.getId()).append("\t").append(c.getKazne()).append("\t").append(c.getSpent()).append("\t\t").append(c.getNumParking()).append("\n");
            if(i++>=limit) break;
        }
        System.out.println(sb.toString());
    }
}
