package Algorithms;

import Warehouse.Product;

import java.util.ArrayList;

public abstract class Algorithm {

    protected boolean ControleerOrder(ArrayList<Product> order){
        return order.size()!=0;
    }

    private ArrayList<Product> CloneOrder(ArrayList<Product> order){
        ArrayList<Product> orderClone = new ArrayList<Product>();
        for (Product item : order) orderClone.add(item.clone());
        return orderClone;
    }

    public ArrayList<Product> BerekenStart(ArrayList<Product> order){
        if(ControleerOrder(order)){
            ArrayList<Product> orderClone = CloneOrder(order);
            return BerekenRoute(orderClone);
        }
        return new ArrayList<Product>();
    }

    abstract protected ArrayList<Product> BerekenRoute(ArrayList<Product> order);



}
