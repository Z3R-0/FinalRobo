package RobotoAppMain;

import Database.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import Warehouse.*;
import java.sql.*;

import java.io.File;
import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<Product> producten1;
    public static ArrayList<Product> producten2;


	@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainLayoutRetrieve.fxml"));
        primaryStage.setTitle("Magazijnrobot");
        primaryStage.setScene(new Scene(root, 1366, 768));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
	    DBConnection connection = new DBConnection();
	    connection.getData();
        try {
            connection.terminateConnection();
            System.out.println("Connection terminated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        producten1 = new ArrayList<Product>();
        
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

       
        Main.producten1.add(p1);
        Main.producten1.add(p2);
        Main.producten1.add(p3);
        Main.producten1.add(p4);
        Main.producten1.add(p5);
        Main.producten1.add(p6);
        
        Warehouse warehouse = new Warehouse(producten1);

        launch(args);
    }
}