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

    //public static ArrayList<Product> producten1;
    public static ArrayList<Product> orderList;
    public static Warehouse warehouse;


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
	    ArrayList<Product> databaseWarehouse = connection.getData();
        try {
            connection.terminateConnection();
            System.out.println("Connection terminated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        warehouse = new Warehouse(databaseWarehouse);

        launch(args);
    }
}