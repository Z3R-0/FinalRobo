package Database;

import Warehouse.Location;
import Warehouse.Product;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/WarehouseDB", "root", "usbw");
            st = con.createStatement();

        }catch(Exception ex){
            System.out.println("Error"+ex);
        }
    }

    public ArrayList<Product> getData(){
        ArrayList<Product> producten = new ArrayList<Product>();
        try{
            String query = "select * from product";
            rs = st.executeQuery(query);
            //System.out.println("Records from Database");
            while(rs.next()){
                String article = rs.getString("articleCode");
                String locationX = rs.getString("locationX");
                String locationY = rs.getString("locationY");
                String inStock = rs.getString("inStock");
                String height = rs.getString("height");
                int articleC = Integer.parseInt(article);
                int heightP = Integer.parseInt(height);
                int LocaX = Integer.parseInt(locationX);
                int LocaY = Integer.parseInt(locationY);

                Location productLocatie = new Location(LocaX, LocaY);

                Product p = new Product(articleC, productLocatie, heightP);

                if(inStock.equals("0")){
                    p.setInStock(false);
                }

                producten.add(p);
                /*
                System.out.print("article: " + article+ " location (" +locationX+", "+locationY + ") in stock: ");
                if(inStock.equals("1")){
                    System.out.print(" yes\n");
                } else {
                    System.out.print(" no\n");
                }
                */
            }

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return producten;
    }

    public void terminateConnection() throws SQLException {
        con.close();
    }
}
