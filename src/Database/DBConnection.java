package Database;

import java.sql.*;

public class DBConnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/WarehouseDB", "root", "");
            st = con.createStatement();

        }catch(Exception ex){
            System.out.println("Error"+ex);
        }
    }

    public void getData(){
        try{
            String query = "select * from product";
            rs = st.executeQuery(query);
            System.out.println("Records from Database");
            while(rs.next()){
                String article = rs.getString("id");
                String locationX = rs.getString("locationX");
                String locationY = rs.getString("locationY");
                String inStock = rs.getString("inStock");
                System.out.print("article: " + article+ " location (" +locationX+", "+locationY + ") in stock: ");
                if(inStock.equals("1")){
                    System.out.print(" yes\n");
                } else {
                    System.out.print(" no\n");
                }
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void terminateConnection() throws SQLException {
        con.close();
    }
}
