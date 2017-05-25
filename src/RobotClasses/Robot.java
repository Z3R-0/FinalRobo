package RobotClasses;

import RobotoAppMain.Controller;
import Warehouse.*;
import arduino.Arduino;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Robot {
    private Location locationRobot;
    private Controller controller;
    private int plek1 = 0;
    private int plek2 = 1;
    public Robot(Location locationRobot,Controller cont) {
        this.locationRobot = locationRobot;
        this.controller = cont;
    }

    public Location getLocationRobot() {
        return locationRobot;
    }

    public void setLocationRobot(Location locationRobot) {
        this.locationRobot = locationRobot;
    }

    public ArrayList<Product> moveRobot(ArrayList<Product> ProductsRoute,ArrayList<Box> ProductsBoxes){

//        int i = 0;
//        //for(Product p : ps){
//        for(int ivan = 0;ivan<ps.size();ivan++){
//            Product p = ps.get(ivan);
//            i++;
//            String str = p.getLocation().getX() +"," + p.getLocation().getY();
//
//            String input;
//            byte[] b;
//
//            System.out.println(str);
//            SendLocation(p.getLocation().getX(),p.getLocation().getY());
//            //Controller.TSPard.serialWrite("ping");
//            do{
//                input = Controller.TSPard.serialRead(1);
//                b = input.getBytes();
//                controller.fxTextAreaStatusRetrieve.appendText("tests");
//                System.out.println(Arrays.toString(b));
//            }while(!(b.length!= 0 && b[0] == 116 && b[1] == 101 && b[2] == 115 && b[3] == 116));
//
//            System.out.println(input);
//            controller.drawProductLocations(controller.gc,p,true);
//        }


        Controller.TSPard.serialWrite("ping");
        if(!WaitFor("TSP",Controller.TSPard,false)){
            System.out.println("no TSP");
            return ProductsRoute;
        }
        Controller.BPPard.serialWrite("ping");
        if(! WaitFor("BPP",Controller.BPPard,false)){
            System.out.println("no BPP");
            return ProductsRoute;
        }

        Product product = ProductsRoute.get(0);
        SendLocation(product);
        int boxnumber = FindBox(product,ProductsBoxes);
        SendBox(boxnumber);
        WaitFor("next",Controller.TSPard,true);

        if(ProductsRoute.size() >1){
            for(int i = 1; i<ProductsRoute.size();i++){
                Product product2 = ProductsRoute.get(i);
                SendLocation(product2);
                WaitFor("next",Controller.BPPard,true);
                int boxnumber2 = FindBox(product,ProductsBoxes);
                SendBox(boxnumber2);
                WaitFor("next",Controller.TSPard,true);
            }
        }



        return ProductsRoute;

    }

    public void SendLocation(Product product){
        System.out.println(product.getLocation().getX()+","+product.getLocation().getY());
        String str = product.getLocation().getX()+","+product.getLocation().getY();
        Controller.TSPard.serialWrite(str);
        WaitFor("pos",Controller.TSPard,true);
    }

    public boolean WaitFor(String tekst, Arduino ard, boolean inf){
        byte[] findWord = tekst.getBytes();
        ArrayList<Byte> readText = new ArrayList<>();
        boolean match = true;
        int counter = 0;
        do{
            counter++;
            match = true;
            String input = ard.serialRead(1);
            byte[] b = input.getBytes();
            System.out.println("--");
            System.out.println(input);

            if(b.length >=2){
                for(int i = 0; i<b.length-1;i++){
                   readText.add(b[i]);
                }
                if(createWord(findWord,readText)){
                    match=true;
                }
            }else{
                match = false;
            }


            if(!inf) {
                if (counter >= 20) {
                    return false;
                }
            }
        }while(!match);
        return true;
    }

    public boolean createWord(byte[] woord,ArrayList<Byte> tekst){

        for(int i=0;i<tekst.size();i++){
            if(i+woord.length-1 < tekst.size()) {
                boolean komtOvereen = true;
                for (int j = 0; j < woord.length; j++) {
                    if (woord[j] != tekst.get(j + i)) {
                        komtOvereen = false;
                    }
                }
                if (komtOvereen) {
                    return true;
                }
            }
        }
        return false;
    }

    public void SendBox(int box) {
        //6.3 - 6.6
        if (box == plek1) {
            Controller.BPPard.serialWrite(String.valueOf(1));
            System.out.println(1);
            Controller.TSPard.serialWrite("baan");
        } else if (box == plek2) {
            Controller.BPPard.serialWrite(String.valueOf(2));
            System.out.println(2);
            Controller.TSPard.serialWrite("baan");
        } else {

            Random random = new Random();
            int plek = random.nextInt(2 - 1 + 1) + 1;
            System.out.println("Plaats doos " + box + " op plek " + plek + ".");
            int response = JOptionPane.showConfirmDialog(new Frame(), "is de doos op plek "+plek+" geplaatst?", "Melding", JOptionPane.DEFAULT_OPTION);
            System.out.println("hey");
            if (response == JOptionPane.OK_OPTION) {
                System.out.println(plek);
                if(plek == 1){
                    plek1 = plek;
                }else {
                    plek2 = plek;
                }
                Controller.BPPard.serialWrite(String.valueOf(plek));
                System.out.println(plek);
                Controller.TSPard.serialWrite("baan");
            }
        }
    }

    public int FindBox(Product p, ArrayList<Box> dozen){
        int i = 0;
        for(Box b : dozen){
            for(Product pr : b.getProducten()){
                if(p.getId() == pr.getId()){
                    return i;
                }
            }
            i++;
        }
        return -1;
    }
        /*

        5.1 Stuur locatie naar TSP.
        5.2 Controleer of doos er is.
        5.2.1 Als de doos er niet is, stuur fout naar gebruiker.
        5.3 Stuur boxnummer naar BPP.
        5.5 Stuur baan vrij naar TSP.
        5.5 Wacht totdat TSP robot next zegt.

        6.1 Stuur locatie naar TSP
        6.2 Wacht to BPP baanvrij zegt
        6.3 Controleer of doos er is.
        6.4 Als doos er niet is, stuur fout naar gebruiker.
        6.5 Stuur boxnummer naar BPP.
        6.6 Stuur baan vrij naar TSP.
        6.7 Wacht totdat TSP robot next zegt.
        6.8 repeat.

        :SendLocation(int x,int y);
        :WaitFor("next",);
        :SendBox(int boxNumber);
        :WaitFor("next")
         */


    public void retrieveProduct(Product p){
        if (p.getLocation().getX() == getLocationRobot().getX() && p.getLocation().getY() == getLocationRobot().getY()){
            //execute arduino code for moving robot arm
        }
    }
}
