package sample.homeScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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

public class homeScreenController implements Initializable {
    public JFXButton studentButton;
    public JFXButton staffButton;
    public JFXButton booksButton;
    public JFXButton checkedOutButton;
    public JFXButton finesButton;
    public JFXButton reportButton;
    public JFXButton checkAndReturnScreenButton;

    public JFXTextArea title;

    @FXML
    public void studentScreenButton(ActionEvent event) throws IOException {
        //This function listens to an action from the login/sign in button and changes the scene
        Scene oldScene = studentButton.getScene();
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/studentScreen/studentScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent, oldScene.getWidth(), oldScene.getHeight());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();



    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void reportScreenButton(ActionEvent event) throws IOException{
        //This function listens to an action from the login/sign in button and changes the scene
        Scene oldScene = reportButton.getScene();
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/reportScreen/reportScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent, oldScene.getWidth(), oldScene.getHeight());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }


    public void checkedOutScreenButton(ActionEvent event) throws IOException{
        //This function listens to an action from the login/sign in button and changes the scene
        Scene oldScene = checkedOutButton.getScene();
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/checkedOutScreen/checkedOutScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent, oldScene.getWidth(), oldScene.getHeight());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void staffScreenButton(ActionEvent event) throws IOException{
        //This function listens to an action from the login/sign in button and changes the scene
        Scene oldScene = staffButton.getScene();
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/staffScreen/staffScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent, oldScene.getWidth(), oldScene.getHeight());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void booksScreenButton(ActionEvent event) throws IOException{
        //This function listens to an action from the login/sign in button and changes the scene
        Scene oldScene = booksButton.getScene();
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/bookScreen/bookScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent, oldScene.getWidth(), oldScene.getHeight());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void checkButton(ActionEvent event) throws IOException{
        //This function listens to an action from the login/sign in button and changes the scene
        Scene oldScene = checkAndReturnScreenButton.getScene();
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/checkAndReturnScreen/checkAndReturnScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent, oldScene.getWidth(), oldScene.getHeight());
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }


}
