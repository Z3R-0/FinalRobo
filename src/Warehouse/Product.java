package Warehouse;

public class Product {
    //VARIABLES
    private static int idCounter = 0;
    private int id;
    private String name;
    private Location location;
    private int height;
    private boolean inStock;

    //CONSTRUCTOR
    public Product(String name, Location location, int height) {
        idCounter++;
        this.id = idCounter;
        this.name = name;
        this.location = location;
        this.height = height;
        inStock = true;
    }

    //FUNCTIONS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getHeight() {
        return height;
    }

    public void setLocation(Location Location) {
        this.location = location;
    }

    public void inStock(boolean inStock) {
        this.inStock = inStock;
    }
   
    
}

