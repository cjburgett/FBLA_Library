package sample.loginScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
    LoginModel loginModel = new LoginModel();
    public JFXButton button;
    public JFXTextArea usernameField;
    public JFXPasswordField passwordField;
    public Label dbstatus;


    public void initialize(URL url, ResourceBundle rb) {
        if (this.loginModel.isDatabaseConnected()) {
            this.dbstatus.setText("Database Status: Connected");
        } else {
            this.dbstatus.setText("Not Connected");
        }
    }





        @FXML




    public void loginButton(ActionEvent event) throws IOException {
    //This function listens to an action from the login/sign in button and changes the scene

        String username = "";
        String password = "";
        if((usernameField.getText().equals(username)) && (passwordField.getText().equals(password) )){
            Scene oldScene = button.getScene();
            Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("sample/homeScreen/homeScreen.fxml"));
            Scene home_page_scene = new Scene(home_page_parent, oldScene.getWidth(), oldScene.getHeight());
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Login");
            alert.setHeaderText(null);
            alert.setContentText("Inccorect password or username");

            alert.showAndWait();
            System.out.println("incorrect login entered");
        }




    }
}
