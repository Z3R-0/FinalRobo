package RobotoAppMain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    @FXML
    private BorderPane mainPanePacking;
    @FXML
    private TextArea fxTextAreaStatusPacking;
    @FXML
    private Button fxButtonAlgorithm1;
    @FXML
    private Button fxButtonRetrieve;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxButtonAlgorithm1.setOnAction((event) -> {
            fxTextAreaStatusPacking.appendText("Algoritme1 uitgevoerd!\n");
        });

        fxButtonRetrieve.setOnAction((event) -> {
            Parent root;
            try {
                root = FXMLLoader.load(Controller.class.getResource("mainLayoutRetrieve.fxml"), resources);
                Stage stage = new Stage();
                stage.setTitle("Magazijnrobot");
                stage.setScene(new Scene(root, 1366, 768));
                stage.show();
                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
