package Warehouse;

public class Location {
	// VARIABLES
	private int x;
	private int y;

	// CONSTRUCTOR
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// FUNCTIONS
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getLengte(Location location1) {
		return Location.getLengte(location1,this);
	}

	public static double getLengte(Location Locatie1, Location Locatie2) {
		double xAfstand = ((double)Locatie2.getX() - (double)Locatie1.getX())/2;
		double yAfstand = (double)Locatie2.getY() - (double)Locatie1.getY();
		return Math.sqrt(Math.pow(xAfstand, 2) + Math.pow(yAfstand, 2));
	}

	public String toString() {
		return "[" + getX() + ", " + getY() + "]";
	}
}
