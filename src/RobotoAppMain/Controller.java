package RobotoAppMain;

import java.io.File;

import OrderInfo.ReadXML;
import RobotClasses.Robot;
import Warehouse.*;
import Algorithms.Greedy;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static RobotoAppMain.Main.warehouse;

public class Controller implements Initializable {
    //VARIABLES
    @FXML
    private BorderPane mainPaneRetrieve;
    @FXML
    private TextArea fxTextAreaStatusRetrieve;
    @FXML
    private Button fxButtonExecute;
    @FXML
    private Button fxButtonPacking;
    @FXML
    private TextField fxTextFieldFile;
    @FXML
    private Button fxButtonSelectFile;
    @FXML
    private Canvas fxCanvasRetrieve;

    private static String something;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        GraphicsContext gc = fxCanvasRetrieve.getGraphicsContext2D();
        drawGrid(gc);

        fxButtonExecute.setOnAction((event) -> {
            if(!(fxTextFieldFile.getText().trim().isEmpty() || fxTextFieldFile == null)){
                //CREATE GREEDY ALGORITHM FOR TESTING
                Greedy greedy = new Greedy();
                fxTextAreaStatusRetrieve.appendText("Created Greedy algorithm\n");

                //CREATE ROBOT WITH START LOCATION
                Location locaRobot = new Location(0, 1);
                Robot robot = new Robot(locaRobot);
                fxTextAreaStatusRetrieve.appendText("Created robot with start position\n");

                //CALCULATE PATH FOR ROBOT TO TAKE
                ArrayList<Product> orderArray = new ArrayList<Product>();
                orderArray = robot.moveRobot(greedy.CalculatePath(Main.orderList));
                fxTextAreaStatusRetrieve.appendText("Calculated Path\n");

                for(Product p : orderArray){
                    fxTextAreaStatusRetrieve.appendText(p.toString());
                }
                fxTextAreaStatusRetrieve.appendText("\n****************************************************************\n\n");

            } else {
                fxTextAreaStatusRetrieve.appendText("****************************************\n Select an order to execute\n****************************************\n");
            }
        });

        fxButtonPacking.setOnAction((event) -> {
            Parent root;
            try {
                root = FXMLLoader.load(Controller2.class.getResource("mainLayoutPacking.fxml"), resources);
                Stage stage = new Stage();
                stage.setTitle("Magazijnrobot");
                stage.setScene(new Scene(root, 1366, 768));
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        fxButtonSelectFile.setOnAction((event) -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(mainPaneRetrieve.getScene().getWindow());
            try {
                fxTextFieldFile.setText(file.getAbsolutePath());
                something = fxTextFieldFile.getText();
            } catch (NullPointerException npe){
                System.out.println("No file chosen");
            }
            //CREATE XML READER
            ReadXML read = new ReadXML();
            fxTextAreaStatusRetrieve.appendText("ReadXML created\n");

            //READ SELECTED XML FILE AND ADD PRODUCTS TO ORDER
            read.readXmlFile(fxTextFieldFile.getText());
            fxTextAreaStatusRetrieve.appendText("Received file URL\n");
            Main.orderList = read.getOrder().getProducts();
            System.out.println("------------WAREHOUSE------------" + warehouse);
            System.out.println(" ------------ORDER------------\n" + Main.orderList);

            gc.clearRect(0,0, 700, 700);
            drawGrid(gc);
            for(Product p: Main.orderList) {
                drawProductLocations(gc, p);
            }
        });
    }

    public void drawGrid(GraphicsContext gc){
        //GRID----------------------------------------
        //Square
        gc.strokeLine(1, 1, 700, 1);
        gc.strokeLine(700, 1, 700, 700);
        gc.strokeLine(700, 700, 1, 700);
        gc.strokeLine(1, 700, 1, 0);

        //Grid lines
        gc.strokeLine(0, 140, 700, 140);
        gc.strokeLine(0, 280, 700, 280);
        gc.strokeLine(0, 420, 700, 420);
        gc.strokeLine(0, 560, 700, 560);
        gc.strokeLine(117, 0, 117, 700);
        gc.strokeLine(234, 0, 234, 700);
        gc.strokeLine(351, 0, 351, 700);
        gc.strokeLine(468, 0, 468, 700);
        gc.strokeLine(585, 0, 585, 700);
        //----------------------------------------------
    }

    public void drawProductLocations(GraphicsContext gc, Product pro) {
        Location changedLoc = locationIdentifier(pro.getLocation());

        //------------------------------------------------------------------------------
        //ADD CODE M8
        gc.fillRect(changedLoc.getX(), changedLoc.getY(), 80, 80);
        //------------------------------------------------------------------------------
    }

    public void drawLines(GraphicsContext gc, ArrayList<Location> locations) {
        locations = new ArrayList<Location>();

        for(int i = 0; i < locations.size(); i++) {
            int y = locations.get(i+1).getY();
            int x = locations.get(i+1).getX();
            for (Location xy : locations) {
                gc.strokeLine(x, y, xy.getX(), xy.getY());
            }
        }
    }

    private Location locationIdentifier(Location loc){
        //ints to add values to
        int xActual = 0;
        int yActual = 20;

        //x Actuals
        if(loc.getX() == 1){
            xActual = 20;
        } else if(loc.getX() == 2){
            xActual = 127;
        } else if(loc.getX() == 3){
            xActual = 252;
        } else if(loc.getX() == 4){
            xActual = 369;
        } else if(loc.getX() == 5){
            xActual = 486;
        } else if(loc.getX() == 6){
            xActual = 603;
        }

        //y Actuals
        if(loc.getY() == 1){
            yActual = 30;
        } else if(loc.getY() == 2){
            yActual = 170;
        } else if(loc.getY() == 3){
            yActual = 310;
        } else if(loc.getY() == 4){
            yActual = 490;
        } else if(loc.getY() == 5){
            yActual = 630;
        }
        Location locFinal = new Location(xActual, yActual);

        return locFinal;
    }
}
