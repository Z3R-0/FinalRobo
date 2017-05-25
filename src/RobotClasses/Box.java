package RobotClasses;

import Warehouse.Product;

import java.util.ArrayList;

public class Box {
    //Lijst van producten wat in de doos zit
    private ArrayList<Product> producten = new ArrayList<Product>();
    //Overgebleven hoogte van de doos
    private int remain = 10;
    //id Counter om de dozen te onderscheiden.
    private static int idCounter = 0;
    private int id;

    //Constructor. Zet de ID van de doos.
    public Box() {
        idCounter++;
        this.id = idCounter;
    }

    //Tweede constructor. Voegt naast ID zetten ook een product toe.
    public Box(Product p) {
        idCounter++;
        this.id = idCounter;
        addProduct(p);
    }

    //Constructor voor het eigen algoritme. Maakt dozen op basis van bestaande dozen.
    public Box(Box box){
        this.producten = new ArrayList<>();
        for(Product product : box.getProducten()){
            this.producten.add(product);
        }
        remain = box.getRemain();
        idCounter++;
        this.id = idCounter;
    }

    //Getter voor de overgebleven ruimte.
    public int getRemain() {
        return remain;
    }

    //Overgebleven ruimte zetten.
    public void setRemain(int x) {
        this.remain -= x;
    }

    //Product toevoegen aan de doos.
    public void addProduct(Product x) {
        if (remain >= remain - x.getHeight() ){
            producten.add(x);
            remain = remain - x.getHeight();
        } else {
            System.out.println("Toevoegen geweigerd!");
        }
    }

    //Methode voor het toevoegen van producten. Wordt gebruikt bij het eigen algoritme. Zoals je ziet heeft Ivan de methode gemaakt.
    public boolean addProductIvan(Product x){
        producten.add(x);
        remain = remain - x.getHeight();
        return remain >= 0;
    }

    //Methode die de producten aanroept.
    public ArrayList<Product> getProducten() {
        return producten;
    }

    //Methode om ID aan te roepen.
    public int getID() {
        return id;
    }

    //IDcounter resetten.
    public static void setIdCounter() {
        idCounter = 0;
    }

    //Producten in een string zetten.
    @Override
    public String toString() {
        String tekst = "{";
        for(Product product:producten){
            tekst+=product.getHeight();
            tekst+=",";
        }
        tekst+="}";
        return tekst;
    }
}