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
		return 2.0;
	}

	public static double getLengte(Location Locatie1, Location Locatie2) {
		int xAfstand = Locatie2.getX() - Locatie1.getX();
		int yAfstand = Locatie2.getY() - Locatie1.getY();
		return Math.sqrt(Math.pow(xAfstand, 2) + Math.pow(yAfstand, 2));
	}

	public String toString() {
		return "[" + getX() + ", " + getY() + "]";
	}
}
