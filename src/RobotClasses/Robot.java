package RobotClasses;

import Warehouse.*;

import java.util.ArrayList;

public class Robot {
    private Location locationRobot;

    public Robot(Location locationRobot) {
        this.locationRobot = locationRobot;
    }

    public Location getLocationRobot() {
        return locationRobot;
    }

    public void setLocationRobot(Location locationRobot) {
        this.locationRobot = locationRobot;
    }

    public ArrayList<Product> moveRobot(ArrayList<Product> ps){
        ArrayList<Product> psh = new ArrayList<Product>();
        psh = ps;
        for(Product p : ps){
            while(getLocationRobot() != p.getLocation()){
                setLocationRobot(p.getLocation());
            }
            if(getLocationRobot() == p.getLocation()){
                retrieveProduct(p);
            }
        }
        return psh;
    }

    public void retrieveProduct(Product p){
        if (p.getLocation().getX() == getLocationRobot().getX() && p.getLocation().getY() == getLocationRobot().getY()){
            //execute arduino code for moving robot arm
        }
    }
}
