package sample.reportScreen;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class reportScreenController implements Initializable {
    @FXML
    JFXButton backButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void backButtonClicked(ActionEvent event) throws IOException {
        //This function listens to an action from the login/sign in button and changes the scene
        Scene oldScene = backButton.getScene();
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/homeScreen/homeScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent, oldScene.getWidth(), oldScene.getHeight());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }
}
