package sample.checkAndReturnScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.bookScreen.BookData;
import sample.dbUtil.dbConnection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class checkAndReturnScreenController implements Initializable {

    @FXML
    private TableView<BookData> booktable;
    @FXML
    private TableColumn<BookData, String> idBookcolumn;
    @FXML
    private TableColumn<BookData, String> ISBNColumn;
    @FXML
    private TableColumn<BookData, String> aLastcolumn;
    @FXML
    private TableColumn<BookData, String> aFirstcolumn;
    @FXML
    private TableColumn<BookData, String> titlecolumn;
    @FXML
    private ComboBox comboP;
    @FXML
    private JFXTextField keyword;
    @FXML
    private JFXButton refreshBooks;
    @FXML
    private JFXButton checkOutButton;
    @FXML
    private JFXTextField bookField;
    @FXML
    private JFXTextField userField;
    @FXML
    private ComboBox roleCombo;
    @FXML
    private JFXButton backButton;


    @FXML
    private TableView<BookData> checktable;
    @FXML
    private TableColumn<BookData, String> idBookCheckcolumn;
    @FXML
    private TableColumn<BookData, String> ISBNCheckColumn;
    @FXML
    private TableColumn<BookData, String> aLastCheckcolumn;
    @FXML
    private TableColumn<BookData, String> aFirstCheckcolumn;
    @FXML
    private TableColumn<BookData, String> titleCheckcolumn;
    @FXML
    private ComboBox comboCheckP;
    @FXML
    private JFXTextField userCheckField;
    @FXML
    private JFXButton searchUsers;
    @FXML
    private JFXButton checkInButton;
    @FXML
    private JFXTextField bookCheckField;
    @FXML
    private JFXTextField userIDCheck;
    @FXML
    private ComboBox roleCheckCombo;


    private ObservableList<BookData> data;
    private dbConnection dc;



    public void initialize(URL url, ResourceBundle rb)
    {
        this.dc = new dbConnection();


        load();


    }


    public void load(){
        try
        {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books WHERE whoChecked IS NULL");
            while (rs.next()) {
                this.data.add(new BookData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.idBookcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
        this.ISBNColumn.setCellValueFactory(new PropertyValueFactory("ISBN"));
        this.aLastcolumn.setCellValueFactory(new PropertyValueFactory("aLast"));
        this.aFirstcolumn.setCellValueFactory(new PropertyValueFactory("aFirst"));
        this.titlecolumn.setCellValueFactory(new PropertyValueFactory("title"));

        this.booktable.setItems(null);
        this.booktable.setItems(this.data);
    }


    public void refreshBookList(ActionEvent actionEvent) {
        try
        {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            String searchWord = this.keyword.getText();
            String parameter = this.comboP.getValue().toString();


            if(parameter.equals("Book ID")){
                System.out.println(searchWord);
                String sql = "SELECT * FROM books WHERE whoChecked IS NULL AND id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, searchWord);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    this.data.add(new BookData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                }
            }else if(parameter.equals("ISBN")){
                String sql = "SELECT * FROM books WHERE whoChecked IS NULL AND ISBN = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, searchWord);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    this.data.add(new BookData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                }
            }else if(parameter.equals("Author Last Name")){
                String sql = "SELECT * FROM books WHERE whoChecked IS NULL AND aLast = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, searchWord);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    this.data.add(new BookData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                }
            }else if(parameter.equals("Title")){
                String sql = "SELECT * FROM books WHERE whoChecked IS NULL AND title = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, searchWord);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    this.data.add(new BookData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                }

            }



        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.idBookcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
        this.ISBNColumn.setCellValueFactory(new PropertyValueFactory("ISBN"));
        this.aLastcolumn.setCellValueFactory(new PropertyValueFactory("aLast"));
        this.aFirstcolumn.setCellValueFactory(new PropertyValueFactory("aFirst"));
        this.titlecolumn.setCellValueFactory(new PropertyValueFactory("title"));

        this.booktable.setItems(null);
        this.booktable.setItems(this.data);
    }

    public void checkOut(ActionEvent actionEvent) {
        String book = this.bookField.getText();
        String user = this.userField.getText();
        String role = this.roleCombo.getValue().toString();


        if(role.equals("Student")){
            String sql = ("UPDATE books SET whoChecked = '"+user+"' WHERE id = '"+book+"'" );
            try {
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.execute();
                conn.close();

            } catch (SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
        }else if(role.equals("Teacher")){
            String sql = ("UPDATE books SET whoCheckedT = '"+user+"' WHERE id = '"+book+"'");
            try {
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.execute();
                conn.close();



            } catch (SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }

        }



        load();


    }

    public void checkIn(ActionEvent actionEvent) {
        String book = this.bookCheckField.getText();
        String user = this.userCheckField.getText();
        String role = this.roleCheckCombo.getValue().toString();


        if(role.equals("Student")){
            String sql = ("UPDATE books SET whoChecked = NULL WHERE id = '"+book+"'" );
            try {
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.execute();
                conn.close();

            } catch (SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
        }else if(role.equals("Teacher")){
            String sql = ("UPDATE books SET whoCheckedT = NULL WHERE id = '"+book+"'");
            try {
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.execute();
                conn.close();



            } catch (SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }

        }



        reloadCheck();

    }

    public void refreshBookCheckList(ActionEvent actionEvent) {
        reloadCheck();
    }


    public void reloadCheck(){
        try
        {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            String userCheck = this.userIDCheck.getText();
            String roleCheck = this.comboCheckP.getValue().toString();

            System.out.println(roleCheck);
            System.out.println(userCheck);
            if(roleCheck.equals("Student")){
                System.out.println(userCheck);
                String sql = "SELECT * FROM books WHERE whoChecked = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, userCheck);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    this.data.add(new BookData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
                }
            }

        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.idBookCheckcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
        this.ISBNCheckColumn.setCellValueFactory(new PropertyValueFactory("ISBN"));
        this.aLastCheckcolumn.setCellValueFactory(new PropertyValueFactory("aLast"));
        this.aFirstCheckcolumn.setCellValueFactory(new PropertyValueFactory("aFirst"));
        this.titleCheckcolumn.setCellValueFactory(new PropertyValueFactory("title"));

        this.checktable.setItems(null);
        this.checktable.setItems(this.data);
    }

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
