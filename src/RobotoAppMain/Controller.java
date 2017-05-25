package RobotoAppMain;

import java.io.File;

import Algorithms.BestFit;
import Algorithms.Greedy;
import Algorithms.ZAlgorithm;
import Algorithms.ZNav;
import OrderInfo.ReadXML;
import RobotClasses.Box;
import RobotClasses.Robot;
import Warehouse.*;
import arduino.Arduino;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
    public BorderPane mainPaneRetrieve;
    @FXML
    public TextArea fxTextAreaStatusRetrieve;
    @FXML
    private Button fxButtonExecute;
    @FXML
    private TextField fxTextFieldFile;
    @FXML
    private Button fxButtonSelectFile;
    @FXML
    private Canvas fxCanvasRetrieve;

    private static String something;
    public static Arduino TSPard = new Arduino("COM4",9600);
    public static Arduino BPPard = new Arduino("COM5",9600);
    public GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TSPard.openConnection();
        BPPard.openConnection();

        gc = fxCanvasRetrieve.getGraphicsContext2D();
        drawGrid(gc);

        fxButtonExecute.setOnAction((event) -> {
            if(!(fxTextFieldFile.getText().trim().isEmpty() || fxTextFieldFile == null)){
                //CREATE ALGORITHM FOR TESTING
                ZNav znav = new ZNav();
                fxTextAreaStatusRetrieve.appendText("Created algorithm\n");

                //CREATE ROBOT WITH START LOCATION
                Location locaRobot = new Location(0, 1);
                Robot robot = new Robot(locaRobot,this);
                fxTextAreaStatusRetrieve.appendText("Created robot with start position\n");

                //CALCULATE PATH FOR ROBOT TO TAKE
                ArrayList<Product> orderArray = new ArrayList<Product>();

                ArrayList<Product> correctRoute = znav.BerekenStart(Main.orderList);
                ArrayList<Box> boxes = new ArrayList<Box>();
                BestFit bf = new BestFit();
                boxes = bf.Run(Main.orderList);
                //bereken BPP.

                orderArray = robot.moveRobot(correctRoute,boxes);

                ArrayList<Location> correctPath = new ArrayList<Location>();
                for(Product pro : orderArray){
                    correctPath.add(pro.getLocation());
                }
                fxTextAreaStatusRetrieve.appendText("Calculated path\n");
                drawLines(gc, correctPath);
                fxTextAreaStatusRetrieve.appendText("Drew path");
                int counter = 1;
                for(Product p : orderArray){
                    fxTextAreaStatusRetrieve.appendText(p.toString(counter));
                    counter++;
                }
                fxTextAreaStatusRetrieve.appendText("\n****************************************************************\n\n");

            } else {
                fxTextAreaStatusRetrieve.appendText("****************************************\n Select an order to execute\n****************************************\n");
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

                //CREATE XML READER
                ReadXML read = new ReadXML();
                fxTextAreaStatusRetrieve.appendText("ReadXML created\n");

                //READ SELECTED XML FILE AND ADD PRODUCTS TO ORDER
                read.readXmlFile(fxTextFieldFile.getText());
                fxTextAreaStatusRetrieve.appendText("Received file URL\n");
                Main.orderList = read.getOrder().getProducts();
            } catch (NullPointerException npe){
                System.out.println("No file chosen");
            }
            /*
            System.out.println("------------WAREHOUSE------------" + warehouse.toString());
            System.out.println(" ------------ORDER------------\n" + Main.orderList.toString());
            */
            gc.clearRect(0,0, 490, 490);
            drawGrid(gc);
            try {
                for (Product p : Main.orderList) {
                    drawProductLocations(gc, p, false);
                }
            } catch (NullPointerException npe){
                System.out.println("No file selected");
            }
        });
    }

    private void drawGrid(GraphicsContext gc){
        //GRID----------------------------------------
        //Square
        gc.strokeLine(1, 1, 490, 1);
        gc.strokeLine(490, 1, 490, 410);
        gc.strokeLine(490, 410, 1, 410);
        gc.strokeLine(1, 410, 1, 1);

        //Grid lines
        gc.strokeLine(0, 82, 490, 82);
        gc.strokeLine(0, 164, 490, 164);
        gc.strokeLine(0, 246, 490, 246);
        gc.strokeLine(0, 328, 490, 328);
        gc.strokeLine(82, 0, 82, 410);
        gc.strokeLine(164, 0, 164, 410);
        gc.strokeLine(246, 0, 246, 410);
        gc.strokeLine(328, 0, 328, 410);
        gc.strokeLine(410, 0, 410, 410);
        //----------------------------------------------
    }

    public void drawProductLocations(GraphicsContext gc, Product pro, boolean isGet) {
        Location changedLoc = locationIdentifier(pro.getLocation());
        if(!isGet) {
            gc.setFill(Color.RED);
        } else {
            gc.setFill(Color.GREEN);
        }
        gc.fillRect(changedLoc.getX(), changedLoc.getY(), 61, 61);
    }

    private void drawLines(GraphicsContext gc, ArrayList<Location> locations) {
        ArrayList<Location> correctedLocations = new ArrayList<Location>();

        for(Location loc : locations){
            Location newLoc = locationIdentifier(loc);
            correctedLocations.add(newLoc);
        }
        int j = 1;
        for(int i = 0; i < correctedLocations.size() - 1; i++) {
            int y = correctedLocations.get(j).getY();
            int x = correctedLocations.get(j).getX();
            gc.strokeLine(correctedLocations.get(i).getX() + 31, correctedLocations.get(i).getY() + 31,x + 31, y + 31);
            j++;
            }
    }

    private Location locationIdentifier(Location loc){
        //ints to add values to
        int xActual = 0;
        int yActual = 0;

        //x Actuals
        if(loc.getX() == 0){
            xActual = 11;
        } else if(loc.getX() == 1){
            xActual = 93;
        } else if(loc.getX() == 2){
            xActual = 174;
        } else if(loc.getX() == 3){
            xActual = 256;
        } else if(loc.getX() == 4){
            xActual = 338;
        } else if(loc.getX() == 5){
            xActual = 420;
        }

        //y Actuals
        if(loc.getY() == 0){
            yActual = 11;
        } else if(loc.getY() == 1){
            yActual = 93;
        } else if(loc.getY() == 2){
            yActual = 174;
        } else if(loc.getY() == 3){
            yActual = 256;
        } else if(loc.getY() == 4){
            yActual = 338;
        }
        Location locFinal = new Location(xActual, yActual);

        return locFinal;
    }
}
