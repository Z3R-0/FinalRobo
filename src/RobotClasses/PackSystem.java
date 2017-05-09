package RobotClasses;

import Warehouse.Product;

public class PackSystem {
    private Box box1;
    private Box box2;

    public PackSystem(Box box1, Box box2) {
        this.box1 = box1;
        this.box2 = box2;
    }

    public Box getBox1() {
        return box1;
    }

    public void setBox1(Box box1) {
        this.box1 = box1;
    }

    public Box getBox2() {
        return box2;
    }

    public void setBox2(Box box2) {
        this.box2 = box2;
    }

    public void fillBox(Product p){
        //execute algorithm to steer arduino
    }
}
