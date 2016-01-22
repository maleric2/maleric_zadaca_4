/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.application;

import java.util.Scanner;

/**
 *
 * @author Marko
 */
public class MainMenu {

    private static MainMenu instance;
    Scanner scanner = new Scanner(System.in);

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            synchronized (MainMenu.class) {
                if (instance == null) {
                    instance = new MainMenu();
                }
            }
        }
        return instance;
    }
    
    public String getMainMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("Izbornik:\n-------------------------------------------------\n");
        menu.append("-1 - zatvaranje parkirališta za nove ulaze automobila\n");
        menu.append("-2 - otvaranje parkirališta za nove ulaze automobila\n");
        menu.append("-3 - ispis zarada od parkiranja po zonama\n");
        menu.append("-4 - ispis zarada od kazni po zonama\n");
        menu.append("-5 - ispis broja automobila koji nisu mogli parkirati po zonama\n");
        menu.append("-6 - ispis broja automobila koji je pauk odveo na deponij po zonama\n");
        menu.append("-7 - ispis 5 automobila s najviše parkiranja\n");
        menu.append("-8 - stanje parkirnih mjesta po zonama (% zauzetih)\n");
        menu.append("-Q - prekid rada programa\n");
        menu.append("Odaberite: ");
        
        return menu.toString();
    }
    public String getChoice(){
        String choice= scanner.nextLine();
        return choice;
    }
    public String readString(){
        return scanner.next();
    }

}
