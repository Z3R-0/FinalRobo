package Algorithms;

import Warehouse.Product;

import java.util.ArrayList;


public class ZAlgorithm extends Algorithm{
    private ArrayList<Product> GetXMostProducts(ArrayList<Product> order) {
        ArrayList<Product> route = new ArrayList<Product>();
        route.add(order.get(0));
        if(order.size()>=1){
            for(Product product:order){
                if(product.getId()!=order.get(0).getId()) {
                    if (route.get(0).getLocation().getX() <= product.getLocation().getX()) {
                        if (route.get(0).getLocation().getX() < product.getLocation().getX()) {
                            route = new ArrayList<Product>();
                            route.add(product);
                        } else {
                            route.add(product);
                        }
                    }
                }
            }
        }
        return route;
    }
    private ArrayList<Product> SortProductOnX(ArrayList<Product> order){
        ArrayList<Product> route = new ArrayList<Product>();
        do{
            ArrayList<Product> newProducts = GetXMostProducts(order);
            for(Product product:newProducts){
                route.add(product);
                order.remove(product);
            }
        }while(order.size()!=0);
        return route;
    }



    public ArrayList<Product>  GetYMostProducts(ArrayList<Product> order){
        ArrayList<Product> route = new ArrayList<Product>();
        route.add(order.get(0));
        if(order.size()>=1){
            for(Product product:order){
                if(product.getId()!=order.get(0).getId()){
                    if (route.get(0).getLocation().getY() <= product.getLocation().getY()) {
                        if (route.get(0).getLocation().getY() < product.getLocation().getY()) {
                            route = new ArrayList<Product>();
                            route.add(product);
                        } else {
                            route.add(product);
                        }
                    }
                }
            }
        }
        return SortProductOnX(route);
    }




    public ArrayList<Product> BerekenRoute(ArrayList<Product> order) {
        ArrayList<Product> route = new ArrayList<Product>();
        do{
            ArrayList<Product> newProducts = GetYMostProducts(order);
            for(Product product:newProducts){
                route.add(product);
                order.remove(product);
            }
        }while(order.size()!=0);
        return route;
    }
}
