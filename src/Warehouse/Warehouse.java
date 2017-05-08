package Warehouse;

import java.util.ArrayList;

public class Warehouse {
    //VARIABLES
    private Product product;
    private int[] size;
    private int[][] productLocations;

    //CONSTRUCTOR
    public Warehouse(Product p, int[] s, int[][] pL){
        product = p;
        size = s;
        productLocations = pL;
    }

    //FUNCTIONS
    public int[][] getProductLocations() {
        return productLocations;
    }

    public Product getProduct() {
        return product;
    }

    public int[] getSize() {
        return size;
    }

    public void restock(ArrayList<Warehouse> alist){
        for(Warehouse ware : alist){
            
        }
    }
}
