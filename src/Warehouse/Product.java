package Warehouse;

public class Product {
    //VARIABLES
    private static int idCounter = 0;
    private int id;
    private int articleCode;
    private Location location;
    private int height;
    private boolean inStock;

    //CONSTRUCTOR
    public Product(int articleCode, Location location, int height) {
        idCounter++;
        this.id = idCounter;
        this.articleCode = articleCode;
        this.location = location;
        this.height = height;
        inStock = true;
    }

    private Product(int id, Location locatie, int hoogte,int articleCode) {
        this.id = id;
        this.location = locatie;
        this.height = hoogte;
        this.articleCode = articleCode;
    }

    //FUNCTIONS
    public int getId() {
        return id;
    }

    public int getArticleCode() {
        return articleCode;
    }

    public Location getLocation() {
        return location;
    }

    public int getHeight() {
        return height;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public void setLocation(Location Location) {
        this.location = location;
    }

    public void inStock(boolean inStock) {
        this.inStock = inStock;
    }
    
/*
    public String toString(){
    	String string = "\n{Article Code: " + articleCode +
    			" Location: "  + location +
    			" Height: " + height +
    			" In stock: ";
    	if (inStock){
    		string = string + "yes}";
    	} else {
    		string =  string + "no}";
    	}

    	return string;
    }
*/

    public String toString(int counter) {
        return "\nProduct " + counter +
                ": location = " + location +
                ", height = " + height +
                '}';
    }

    public Product cloneProduct(){
        return new Product(id,location,height,articleCode);
    }
}

