package RobotoAppMain;

import java.io.File;
import java.util.ArrayList;

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
    private Button fxButtonAdd;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxButtonExecute.setOnAction((event) -> {
            Location p1Loc = new Location(3,5);
            Product p1 = new Product("Kaas", p1Loc, 4);
            ArrayList<Product> p = new ArrayList<Product>();
            p.add(p1);
            Greedy greedy = new Greedy();
            Location locaRobot = new Location(0, 1);
            Robot robot = new Robot(locaRobot);
            int result = robot.moveRobot(greedy.CalculatePath(p));
            if(result == 1){
                fxTextAreaStatusRetrieve.appendText("Order uitgevoerd!\n");
            } else {
                fxTextAreaStatusRetrieve.appendText("Order mislukt!\n");
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
        });

        fxButtonAdd.setOnAction((event) -> {
            fxTextAreaStatusRetrieve.appendText("Bestand toegevoegd!\n" + fxTextFieldFile.getText() +"\n");
        });
    }
}
