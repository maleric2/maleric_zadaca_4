/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.application;

import java.util.logging.Level;
import java.util.logging.Logger;
import maleric_zadaca_4.data.Car;
import maleric_zadaca_4.data.Parking;
import maleric_zadaca_4.mvc.CarController;
import maleric_zadaca_4.mvc.ParkingController;

/**
 *
 * @author Marko
 */
public class Application {

    private static Application instance;

    private Application() {
        System.out.println("New Application..");
    }

    public static Application getInstance() {
        if (instance == null) {
            synchronized (Application.class) {
                if (instance == null) {
                    instance = new Application();
                }
            }
        }
        return instance;
    }

    public void start(String[] args) {
        //validator
        ArgumentsValidator validator = new ArgumentsValidator();
        if (!validator.validate(args)) {
            System.out.println(validator.getErrorMsg());
        } else {
            
            int randomValue1=1;
            int randomValue2=1;
            int randomValue3=1;
            
            int intervalUlaskaAuta = (validator.getVremenskaJedinica() / validator.getIntervalDolaska()) * randomValue1;
            int intervalVlasnikaDoAuta = (validator.getVremenskaJedinica() / validator.getIntervalOdlaska()) * randomValue3;
            
            System.out.println("TImeUnit: " + validator.getVremenskaJedinica());
            System.out.println("Interval dolaska: " + validator.getIntervalDolaska());
            
            //Generira se parkiralište sa brojemZona i sa kapacitetZone i sa maxParkiranjem i vremenskomJedinicom
            //Generiraju se automobili (brojAutomobila) sa svojim identifikatorom
            //Autom. ulazi u parkiralište sa vremenskim_razmakom = (vremenskaJedinica/IntervalDolaska)*generiranaVrijednost
            //Autom. odabire zonu zeljenaZona = (brojZona * generiranaVrijednost2) - tako da sve zone imaju istu vjerojatnost odabira ??
            
            //PARKIRANJE
            //Automobil ulazi u slobodnu odabranu zonu i plaća parkiranje po VremenskaJedinica, cijena = ((brojZona+1-brojZone)*cijenaJedinice;
            //Nema slobodnog mjesta izlazi iz parkirališta i ne plaća parkiranje
            
            
            //MOŽDA NEKI CONTROLLER ILI NEŠTO
            Parking p = new Parking(validator.getBrojZona(), validator.getKapacitetZone(), validator.getMaksParkiranje(), 
                    validator.getVremenskaJedinica(), validator.getIntervalDolaska(), validator.getCijenaJedinice(), validator.getIntervalKontrole(), validator.getMaksParkiranje(), validator.getKaznaParkiranja());
            ParkingController pController = new ParkingController(p, validator.getIntervalDolaska());
            
            CarController cController = new CarController(validator.getBrojAutomobila(), p, validator.getIntervalDolaska(), validator.getIntervalOdlaska());
            pController.setcController(cController);
            
            pController.startParking();
            
            
            
            
            String choice;            
            System.out.println(MainMenu.getInstance().getMainMenu());
            do {
                choice = MainMenu.getInstance().getChoice();
                
               // p.parkCar(new Car(carBr++));
                //pController.parkCar();
                System.out.println("--------------------------------------------");
                System.out.println("Odabrana opcija: " + choice);
                switch(choice){
                    case "1": {
                        System.out.println("\n-ZATVARAM PARKING-\n");
                        pController.closeParking();
                        break;
                    }
                    case "2": {
                        System.out.println("\n-OTVARAM PARKING-\n");
                        pController.startParking();
                        break;
                    }
                    case "3": {
                        System.out.println("\n-ZARADA PARKIRALISTA-\n");
                        pController.getParkingProfit();
                        break;
                    }
                    case "4": {
                        System.out.println("\n-KAZNE U PARKIRALISTU-\n");
                        pController.getParkingPenalty();
                        //pController.getParkingProfit2();
                        //cController.getUnparkedCar();
                        break;
                    }
                    case "5": {
                        System.out.println("\n-AUTOMOBILI KOJI NISU NAŠLI PARKING-\n");
                        cController.viewUnparkedCars();
                        break;
                    }
                    case "6": {
                        System.out.println("\n-AUTOMOBILI NA DEPONIJU-\n");
                        cController.viewDeponij();
                        break;
                    }
                     case "7": {
                        System.out.println("\n-TOP 5 AUTOMOBILA SA PARKIRANJIMA-\n");
                        cController.viewTopCars(5);
                        
                        break;
                    }
                    case "8": {
                        System.out.println("\n-PARKIRNA MJESTA PO ZONAMA-\n");
                        pController.getParkingInfo();
                        break;
                    }
                    case "9": {
                        System.out.println("\n-POVIJEST PARKIRANJA-\n");
                        pController.getParkingHistory();
                        break;
                    }
                }
                System.out.println("--------------------------------------------");
            }while(!choice.equals("Q"));
            pController.stop();
            System.out.println("Cekam dretve..");
        }

    }
    
    
    
}
