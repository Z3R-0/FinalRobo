package RobotoAppMain;

import java.io.File;

import OrderInfo.ReadXML;
import RobotClasses.Robot;
import Warehouse.*;
import Algorithms.Greedy;
import javafx.application.Platform;
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
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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



        fxButtonExecute.setOnAction((event) -> {
            if(!(fxTextFieldFile.getText().trim().isEmpty() || fxTextFieldFile == null)){
                //CREATE XML READER
                ReadXML read = new ReadXML();
                fxTextAreaStatusRetrieve.appendText("ReadXML created\n");

                //READ SELECTED XML FILE AND ADD PRODUCTS TO ORDER
                read.readXmlFile(fxTextFieldFile.getText());
                fxTextAreaStatusRetrieve.appendText("Received file URL\n");
                Main.producten2 = read.getOrder().getProducts();
                System.out.println(Main.producten1  + "\n ------------WAREHOUSE------------");
                System.out.println("Producten in warehouse: " + Main.producten2 + "\n ------------ORDER------------");
                fxTextAreaStatusRetrieve.appendText("Added products to ArrayList\n");

                //CREATE GREEDY ALGORITHM FOR TESTING
                Greedy greedy = new Greedy();
                fxTextAreaStatusRetrieve.appendText("Created Greedy algorithm\n");

                //CREATE ROBOT WITH START LOCATION
                Location locaRobot = new Location(0, 1);
                Robot robot = new Robot(locaRobot);
                fxTextAreaStatusRetrieve.appendText("Created robot with start position\n");

                //CALCULATE PATH FOR ROBOT TO TAKE
                System.out.println("\n--------------IMP1--------------\n" + Main.producten2 + "\n--------------IMP1-------------\n");
                ArrayList<Product> orderArray = new ArrayList<Product>();
                orderArray = robot.moveRobot(greedy.CalculatePath(Main.producten2));
                System.out.println("\n--------------IMP2--------------\n" + Main.producten2 + orderArray + "\n--------------IMP2-------------\n");
                fxTextAreaStatusRetrieve.appendText("Calculated Path\n");

                for(Product p : orderArray){
                    fxTextAreaStatusRetrieve.appendText(p.toString());
                }

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
        });
    }
}
