package Algorithms;

import java.util.ArrayList;
import Warehouse.Product;

public class Greedy extends Algorithm{
    public ArrayList<Product> BerekenRoute(ArrayList<Product> order) {
        ArrayList<Product> route = new ArrayList<Product>();
        System.out.println("Started creating Greedy path");
        route.add(order.get(0));
        order.remove(0);
        while(order.size()>=1){
            if(order.size() == 1){
                route.add(order.get(0));
                order.remove(0);
            }else {
                int bestProduct = 0;
                double bestDistance = order.get(0).getLocation().getLengte(order.get(1).getLocation(),route.get(bestProduct).getLocation());
                for (int i = 1; i < order.size()-1;i++) {
                    double newDistance = order.get(i).getLocation().getLengte(order.get(i).getLocation(),route.get(bestProduct).getLocation());
                    if(newDistance<=bestDistance){
                        bestProduct = i;
                        bestDistance = newDistance;
                    }
                }
                route.add(order.get(bestProduct));
                order.remove(bestProduct);
            }
        }
        System.out.println("Created Greedy path");
        return route;
    }
}
