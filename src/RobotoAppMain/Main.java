package RobotoAppMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Warehouse.*;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<Product> producten;


	@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainLayoutRetrieve.fxml"));
        primaryStage.setTitle("Magazijnrobot");
        primaryStage.setScene(new Scene(root, 1366, 768));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {

        producten = new ArrayList<Product>();
        
        Location l1 = new Location(1,2);
        Product p1 = new Product(5, l1 , 5);
        Location l2 = new Location(1,3);
        Product p2 = new Product(5, l2 , 5);
        Location l3 = new Location(8,2);
        Product p3 = new Product(6, l3 , 5);
        Location l4 = new Location(3,4);
        Product p4 = new Product(3, l4 , 5);
        Location l5 = new Location(3,5);
        Product p5 = new Product(43, l5 , 5);
        Location l6 = new Location(6,2);
        Product p6 = new Product(23, l6 , 5);

       
        Main.producten.add(p1);
        Main.producten.add(p2);
        Main.producten.add(p3);
        Main.producten.add(p4);
        Main.producten.add(p5);
        Main.producten.add(p6);
        
        Warehouse warehouse = new Warehouse(producten);
        System.out.println("Warehouse: " + producten);

        launch(args);
    }
}