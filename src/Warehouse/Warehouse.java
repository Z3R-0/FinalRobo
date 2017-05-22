package Warehouse;

import java.util.ArrayList;

public class Warehouse {
    //VARIABLES
    private ArrayList<Product> product;

    //CONSTRUCTOR
    public Warehouse(ArrayList<Product> p){
        product = new ArrayList<Product>();
        product.addAll(p);

    }

    @Override
    public String toString() {
        return "\n{" +
                "Warehouse = " + product +
                '}';
    }

    //FUNCTIONS
    public ArrayList<Product> getProduct() {
        return product;
    }


}
