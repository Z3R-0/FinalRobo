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
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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
                System.out.println(read.getOrder().getProducts());
                Main.producten = read.getOrder().getProducts();
                System.out.println("Producten in warehouse: " + Main.producten);
                fxTextAreaStatusRetrieve.appendText("Added products to ArrayList\n");

                //CREATE GREEDY ALGORITHM FOR TESTING
                Greedy greedy = new Greedy();
                fxTextAreaStatusRetrieve.appendText("Created Greedy algorithm\n");

                //CREATE ROBOT WITH START LOCATION
                Location locaRobot = new Location(0, 1);
                Robot robot = new Robot(locaRobot);
                fxTextAreaStatusRetrieve.appendText("Created robot with start position\n");

                //CALCULATE PATH FOR ROBOT TO TAKE
                int result = robot.moveRobot(greedy.CalculatePath(Main.producten));
                fxTextAreaStatusRetrieve.appendText("Calculated Path\n");
                if(result == 1){
                    StringBuilder tos = new StringBuilder("");
                    fxTextAreaStatusRetrieve.appendText("Order uitgevoerd!\n");
                    for(Product pro : Main.producten){
                        fxTextAreaStatusRetrieve.appendText("Hiiii");
                        System.out.println(pro.toString());
                        tos.append(pro.toString());
                    }
                    fxTextAreaStatusRetrieve.appendText(tos.toString());
                } else {
                    fxTextAreaStatusRetrieve.appendText("Order mislukt!\n");
                }
            } else {
                fxTextAreaStatusRetrieve.appendText("****************************************\n Selecteer een order om uit te voeren\n****************************************\n");
            }
        });

        fxButtonPacking.setOnAction((event) -> {
            Parent root;
            try {
                root = FXMLLoader.load(Controller2.class.getResource("mainLayoutPacking.fxml"), resources);
                Stage stage = new Stage();
                stage.setTitle("Magazijnrobot");
                stage.setScene(new Scene(root, 1820, 980));
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
            fxTextFieldFile.setText(file.getAbsolutePath());
            something = fxTextFieldFile.getText();
        });
    }
}
