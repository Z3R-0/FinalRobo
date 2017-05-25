package Algorithms;

import OrderInfo.Order;
import RobotClasses.Box;
import Warehouse.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class BestFit extends Algorithm {

    //Run functie om het algoritme te starten.
    public ArrayList<Box> Run(ArrayList<Product> p) {
        Box.setIdCounter();                                                                                             //Idcounter zetten.
        ArrayList<Product> tempOrder = new ArrayList<Product>();
        for (Product product : p) {
            tempOrder.add(product.cloneProduct());
        }                                                                                                               //Producten in een array zetten.

        ArrayList<Box> boxes = new ArrayList<Box>();
        boxes.add(new Box());
        //doe iets wanneer de tijdelijke ordergrootte groter is dan 0.
        do {
            int bestBox = -1;
            int gap = hoogte + 1;
            Product product = tempOrder.get(0);
            //Loop door de dozen.
            for (int i = 0; i < boxes.size(); i++) {
                Box box = boxes.get(i);
                int remain = box.getRemain();
                //Als het product past, dan de Box instellen die het beste past.
                if (remain >= product.getHeight()) {
                    if (remain <= gap) {
                        gap = remain;
                        bestBox = i;
                    }
                }
            }
            //als er een bestbox is dan product er aan toevoegen. Anders een nieuwe box maken.
            if (bestBox == -1) {
                Box newBox = new Box();
                newBox.addProduct(product);
                boxes.add(newBox);
            } else {
                boxes.get(bestBox).addProduct(product);
            }
            tempOrder.remove(product);
        } while (tempOrder.size() != 0);
        return boxes;
    }
    //XMLwrite aanroepen.
    public void writeXML(ArrayList<Box> b) {
        //new WriteXML(b);
    }

    @Override
    protected ArrayList<Product> BerekenRoute(ArrayList<Product> order) {
        return null;
    }

}