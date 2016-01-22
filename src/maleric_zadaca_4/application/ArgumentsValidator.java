/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maleric_zadaca_4.application;

import java.io.File;

/**
 *
 * @author Marko
 */
public class ArgumentsValidator {

    private String errorMsg;
    private String[] args;

    public boolean validate(String[] args) {
        this.args = args;
        //brojAutomobila brojZona kapacitetZone maksParkiranje vremenskaJedinica intervalDolaska intervalOdlaska cijenaJedinice intervalKontrole kaznaParkiranja

        if (args.length < 10) {
            //System.out.println("ERROR: Parameters do not match");
            errorMsg = "ERROR: Number of parameters doesn't match";
            return false;
        } else {
            for (int i = 0; i < 5; i++) {
                if (args[i] == null) {
                    //System.out.println("ERROR: Every parameter must have value");
                    errorMsg = "ERROR: Every parameter must have value";
                    return false;
                }
            }
            int brojAutomobila, brojZona, kapacitetZone, maksParkiranje, vremenskaJedinica;
            int intervalDolaska, intervalOdlaska, cijenaJedinice, intervalKontrole, kaznaParkiranja;
            brojAutomobila = Integer.parseInt(args[0]);
            brojZona = Integer.parseInt(args[1]);
            kapacitetZone = Integer.parseInt(args[2]);
            maksParkiranje = Integer.parseInt(args[3]);
            vremenskaJedinica = Integer.parseInt(args[4]);

            intervalDolaska = Integer.parseInt(args[5]);
            intervalOdlaska = Integer.parseInt(args[6]);

            cijenaJedinice = Integer.parseInt(args[7]);
            intervalKontrole = Integer.parseInt(args[8]);
            kaznaParkiranja = Integer.parseInt(args[9]);

            //String podjelaOkvira = args[2];
            if (brojAutomobila < 10 || brojAutomobila > 100) {
                //System.out.println("ERROR: 'brojRedaka' must be 24-60");
                errorMsg = "ERROR: 'brojAutomobila' must be 10-100";
                return false;
            }
            if (brojZona < 1 || brojZona > 4) {
                //System.out.println("ERROR: 'brojStupaca' must be 80-160");
                errorMsg = "ERROR: 'brojZona' must be 1-4";
                return false;
            }
            if (kapacitetZone < 1 || kapacitetZone > 100) {
                //System.out.println("ERROR: 'brSekundi' must be 1-120");
                errorMsg = "ERROR: 'kapacitetZone' must be 1-100";
                return false;
            }
            if (maksParkiranje < 1 || maksParkiranje > 10) {
                //System.out.println("ERROR: 'brSekundi' must be 1-120");
                errorMsg = "ERROR: 'maksParkiranje' must be 1-10";
                return false;
            }
            if (vremenskaJedinica < 1 || vremenskaJedinica > 10) {
                //System.out.println("ERROR: 'brSekundi' must be 1-120");
                errorMsg = "ERROR: 'vremenskaJedinica' must be 1-10";
                return false;
            }
            if (intervalDolaska < 1 || intervalDolaska > 10) {
                //System.out.println("ERROR: 'brSekundi' must be 1-120");
                errorMsg = "ERROR: 'intervalDolaska' must be 1-10";
                return false;
            }
            if (intervalOdlaska < 1 || intervalOdlaska > 10) {
                //System.out.println("ERROR: 'brSekundi' must be 1-120");
                errorMsg = "ERROR: 'intervalOdlaska' must be 1-10";
                return false;
            }
            if (cijenaJedinice < 1 || cijenaJedinice > 10) {
                //System.out.println("ERROR: 'brSekundi' must be 1-120");
                errorMsg = "ERROR: 'cijenaJedinice' must be 1-10";
                return false;
            }
            if (intervalKontrole < 1 || intervalKontrole > 10) {
                //System.out.println("ERROR: 'brSekundi' must be 1-120");
                errorMsg = "ERROR: 'intervalKontrole' must be 1-10";
                return false;
            }
            if (kaznaParkiranja < 1 || kaznaParkiranja > 100) {
                //System.out.println("ERROR: 'brSekundi' must be 1-120");
                errorMsg = "ERROR: 'kaznaParkiranja' must be 1-100";
                return false;
            }
            return true;
        }
    }
//TODO: možda ovi getteri i setteri da se ubace preko nekog design patterna u neki poseban objekt gdje je samo to (kao tip)
    public String getErrorMsg() {
        return errorMsg;
    }

    public int getBrojAutomobila() {
        return Integer.parseInt(args[0]);
    }

    public int getBrojZona() {
        return Integer.parseInt(args[1]);
    }

    public int getKapacitetZone() {
        return Integer.parseInt(args[2]);

    }

    public int getMaksParkiranje() {
        return Integer.parseInt(args[3]);
    }

    public int getVremenskaJedinica() {
        return Integer.parseInt(args[4]);
    }

    public int getIntervalDolaska() {
        return Integer.parseInt(args[5]);
    }

    public int getIntervalOdlaska() {
        return Integer.parseInt(args[6]);
    }
    public int getCijenaJedinice(){
        return Integer.parseInt(args[7]);
    }
    public int getIntervalKontrole(){
        return Integer.parseInt(args[8]);
    }
    public int getKaznaParkiranja(){
        return Integer.parseInt(args[9]);
    }
}
