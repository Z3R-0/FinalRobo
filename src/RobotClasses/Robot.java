package RobotClasses;

import RobotoAppMain.Controller;
import Warehouse.*;

import java.util.ArrayList;

public class Robot {
    private Location locationRobot;
    private Controller controller;
    public Robot(Location locationRobot,Controller cont) {
        this.locationRobot = locationRobot;
        this.controller = cont;
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
            Controller.ard.serialWrite("X:" + String.valueOf(p.getLocation().getX()));
            Controller.ard.serialWrite("Y:" + String.valueOf(p.getLocation().getY()));
            while(!(Controller.ard.serialRead().equals("X:"+p.getLocation().getX()+"Y:"+p.getLocation().getY()))){
                //wachten totdat hij print
            }
            controller.drawProductLocations(controller.gc,p,true);
        }
        return psh;
    }

    public void retrieveProduct(Product p){
        if (p.getLocation().getX() == getLocationRobot().getX() && p.getLocation().getY() == getLocationRobot().getY()){
            //execute arduino code for moving robot arm
        }
    }
}
