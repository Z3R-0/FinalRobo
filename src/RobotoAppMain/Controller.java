package RobotoAppMain;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxButtonAdd.setOnAction((event) -> {
            fxTextAreaStatusRetrieve.appendText("Bestand toegevoegd!\n");
        });

        fxButtonExecute.setOnAction((event) -> {
            ArrayList<Product> p = new ArrayList<Product>();
            Greedy greedy = new Greedy();
            Location locaRobot = new Location(0, 1);
            Robot robot = new Robot(locaRobot);
            robot.moveRobot(greedy.CalculatePath(p));
            fxTextAreaStatusRetrieve.appendText("Order uitgevoerd!\n");
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
    }
}
