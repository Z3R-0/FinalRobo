package Algorithms;

import java.util.ArrayList;

import RobotClasses.Box;
import Warehouse.Product;

import java.util.ArrayList;

/**
 * Created by Ivan on 04/05/2017.
 */
public class Greedy extends Algorithm {

    protected ArrayList<Product> BerekenRoute(ArrayList<Product> order) {
        System.out.println(order.size());
        ArrayList<Product> route = new ArrayList<Product>();

        route.add(order.get(0));
        order.remove(0);
        while(order.size()>=1){
            if(order.size() == 1){
                route.add(order.get(0));
                order.remove(0);
            }else {
                int bestProduct = 0;
                double bestDistance = order.get(0).getLocation().getLengte(route.get(route.size()-1).getLocation());
                for (int i = 1; i<order.size();i++) {
                    double newDistance = order.get(i).getLocation().getLengte(route.get(route.size()-1).getLocation());
                    if(newDistance<=bestDistance){
                        bestProduct = i;
                        bestDistance = newDistance;
                    }
                }
                route.add(order.get(bestProduct));
                order.remove(bestProduct);
            }
        }
        return route;
    }

    @Override
    protected ArrayList<Box> Run(ArrayList<Product> p) {
        return null;
    }
}
