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
        primaryStage.setScene(new Scene(root, 1820, 980));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
        producten = new ArrayList<Product>();
        
        Location l1 = new Location(1,2);
        Product p1 = new Product("a;sldlfdkjgdh", l1 , 5);
        Location l2 = new Location(1,3);
        Product p2 = new Product("a;lkjkgaefdkjgdh", l2 , 5);
        Location l3 = new Location(8,2);
        Product p3 = new Product("a;sldlfdk;kasvdjbjgdh", l3 , 5);
        Location l4 = new Location(3,4);
        Product p4 = new Product("a;sldlliklfdkjgdh", l4 , 5);
        Location l5 = new Location(3,5);
        Product p5 = new Product("a;sdvkkjsldlfdkjgdh", l5 , 5);
        Location l6 = new Location(6,2);
        Product p6 = new Product("a;sldliewqrwfdkjgdh", l6 , 5);
       
        producten.add(p1);
        producten.add(p2);
        producten.add(p3);
        producten.add(p4);
        producten.add(p5);
        producten.add(p6);
        
        Warehouse warehouse = new Warehouse(producten);
        
    }
}