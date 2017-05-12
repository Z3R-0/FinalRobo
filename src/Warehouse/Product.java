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

    public void setLocation(Location Location) {
        this.location = location;
    }

    public void inStock(boolean inStock) {
        this.inStock = inStock;
    }
    
    public String toString(){
    	String string = "Artikel Code: " + articleCode +
    			"Location: "  + location +
    			"Formaat: " + height +
    			"Voorradig: ";
    	if (inStock){
    		string = string + "ja";
    	} else {
    		string =  string + "nee";
    	}
    	
    	return string;
    }
   
    
}

