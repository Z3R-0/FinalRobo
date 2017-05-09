package RobotClasses;

import Warehouse.Location;
import Warehouse.Product;

public class Robot {
    private Location locationRobot;

    public Robot(Location locationRobot) {
        this.locationRobot = locationRobot;
    }

    public Location getLocationRobot() {
        return locationRobot;
    }

    public void moveRobot(Product[] ps){
        for(Product p : ps){
            while(getLocationRobot() != p.getLocation()){
                //execute algorithm
            }
            if(getLocationRobot() == p.getLocation()){
                retrieveProduct(p);
            }
        }
    }

    public void retrieveProduct(Product p){
        if (p.getLocation().getX() == getLocationRobot().getX() && p.getLocation().getY() == getLocationRobot().getY()){
            //execute arduino code for moving robot arm
        }
    }
}
