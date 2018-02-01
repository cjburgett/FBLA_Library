package sample.staffScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.dbUtil.dbConnection;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class staffScreenController implements Initializable {
    @FXML
    JFXButton backButton;
    @FXML
    private TableView<TeacherData> teachertable;
    @FXML
    private TableColumn<TeacherData, String> idcolumn;
    @FXML
    private TableColumn<TeacherData, String> firstnamecolumn;
    @FXML
    private TableColumn<TeacherData, String> lastnamecolumn;
    @FXML
    private TableColumn<TeacherData, String> daysallowedcolumn;
    @FXML
    private TableColumn<TeacherData, String> booksallowedcolumn;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField teacherDaysAllowedField;
    @FXML
    private TextField TeacherNumberOfBooksAllowedField;
    @FXML

    private JFXTextField firstNameEditField;
    @FXML
    private JFXTextField lastNameEditField;
    @FXML
    private JFXTextField teacherDaysAllowedEditField;
    @FXML
    private JFXTextField teacherNumberOfBooksAllowedEditField;
    @FXML
    private Label teacherIDEdit;
    @FXML
    public JFXButton editTeacherButton;
    @FXML
    private Label errorMessageAdd;
    @FXML
    private JFXTextField firstNameDeleteField;
    @FXML
    private JFXTextField lastNameDeleteField;
    @FXML
    private JFXTextField teacherDaysAllowedDeleteField;
    @FXML
    private JFXTextField teacherNumberOfBooksAllowedDeleteField;
    @FXML
    private Label teacherIDDelete;
    @FXML
    public JFXButton deleteTeacherButton;
    @FXML
    private Label errorMessageDelete;


    private ObservableList<TeacherData> data;
    private dbConnection dc;




    public void initialize(URL url, ResourceBundle rb)
    {
        this.dc = new dbConnection();
        try
        {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM teachers");
            while (rs.next()) {
                this.data.add(new TeacherData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
        this.daysallowedcolumn.setCellValueFactory(new PropertyValueFactory("booksAllowed"));
        this.booksallowedcolumn.setCellValueFactory(new PropertyValueFactory("daysAllowed"));
        //this.booksallowedcolumn.setCellValueFactory();

        this.teachertable.setItems(null);
        this.teachertable.setItems(this.data);


        teachertable.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = teachertable.getSelectionModel().getSelectedCells();

        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                //TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                //Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                TeacherData selectedData = teachertable.getSelectionModel().getSelectedItem();
                //System.out.println("Selected Value " + selectedData.getFirstName());
                try{
                    teacherIDEdit.setText(selectedData.getID());
                    firstNameEditField.setText(selectedData.getFirstName());
                    lastNameEditField.setText(selectedData.getLastName());
                    teacherDaysAllowedEditField.setText(selectedData.getDaysAllowed());
                    teacherNumberOfBooksAllowedEditField.setText(selectedData.getBooksAllowed());

                    teacherIDDelete.setText(selectedData.getID());
                    firstNameDeleteField.setText(selectedData.getFirstName());
                    lastNameDeleteField.setText(selectedData.getLastName());
                    teacherDaysAllowedDeleteField.setText(selectedData.getDaysAllowed());
                    teacherNumberOfBooksAllowedDeleteField.setText(selectedData.getBooksAllowed());
                } catch (Exception e)
                {
                    System.out.println("hello ");
                }

            }
        });


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


    @FXML
    private void loadTeacherData(ActionEvent event)
    {
        try
        {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM teachers");
            while (rs.next()) {
                this.data.add(new TeacherData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error " + e);
        }
        this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
        this.daysallowedcolumn.setCellValueFactory(new PropertyValueFactory("booksAllowed"));
        this.booksallowedcolumn.setCellValueFactory(new PropertyValueFactory("daysAllowed"));
        //this.booksallowedcolumn.setCellValueFactory();

        this.teachertable.setItems(null);
        this.teachertable.setItems(this.data);
    }


    @FXML
    private void addTeacher(ActionEvent event)
    {
        //String sql = "INSERT INTO `students`(studentF`, `studentL`, `bLimit`, `dLimit`) VALUES (? , ?, ?, ?)";
        String sql = "INSERT INTO `teachers`(`teacherF`, `teacherL`, `bLimit`, `dLimit`) VALUES (? , ?, ?, ?)";
        if(isInteger(this.TeacherNumberOfBooksAllowedField.getText()) && this.TeacherNumberOfBooksAllowedField.getText() != null && isInteger(this.teacherDaysAllowedField.getText()) && this.teacherDaysAllowedField.getText() != null) {
            try {

                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                //stmt.setString(1, this.id.getText());
                stmt.setString(1, this.firstNameField.getText());
                stmt.setString(2, this.lastNameField.getText());
                if (isInteger(this.TeacherNumberOfBooksAllowedField.getText()) && this.TeacherNumberOfBooksAllowedField.getText() != null) {
                    stmt.setString(3, this.TeacherNumberOfBooksAllowedField.getText());
                } else {
                    System.out.println("this is not a number");
                }

                stmt.setString(4, this.teacherDaysAllowedField.getText());

                stmt.execute();
                conn.close();


                // add data


            } catch (SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }

            try
            {
                Connection conn = dbConnection.getConnection();
                this.data = FXCollections.observableArrayList();

                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM teachers");
                while (rs.next()) {
                    this.data.add(new TeacherData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
            }
            catch (SQLException e)
            {
                System.err.println("Error " + e);
            }

            this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
            this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
            this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
            this.daysallowedcolumn.setCellValueFactory(new PropertyValueFactory("booksAllowed"));
            this.booksallowedcolumn.setCellValueFactory(new PropertyValueFactory("daysAllowed"));
            //this.booksallowedcolumn.setCellValueFactory();

            this.teachertable.setItems(null);
            this.teachertable.setItems(this.data);


        }else{
            //JOptionPane.showMessageDialog(null, "Please Enter Valid Data", "InfoBox: " + " Incorrect Data Types", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Bad data");
            errorMessageAdd.setText("Please Enter Valid Data Types");
        }

    }

    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);

            // s is a valid integer

            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }


    public void editTeacher(ActionEvent actionEvent) {
        String firstname = this.firstNameEditField.getText();
        String lastname = this.lastNameEditField.getText();
        String daysAllowed = this.teacherDaysAllowedEditField.getText();
        String numBooks = this.teacherNumberOfBooksAllowedEditField.getText();
        String IDnum = this.teacherIDEdit.getText();

        String sql = ("UPDATE teachers SET teacherF = '"+firstname+"', teacherL = '"+lastname+"', bLimit = '"+daysAllowed+"', dLimit = '"+numBooks+"' WHERE id = '"+IDnum+"'");
        if(isInteger(this.teacherDaysAllowedEditField.getText()) && this.teacherNumberOfBooksAllowedEditField.getText() != null && isInteger(this.teacherDaysAllowedEditField.getText()) && this.teacherDaysAllowedEditField.getText() != null) {
            try {
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.execute();
                conn.close();


            } catch (SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }
            System.out.println(1);
            try
            {
                Connection conn = dbConnection.getConnection();
                this.data = FXCollections.observableArrayList();

                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM teachers");
                while (rs.next()) {
                    this.data.add(new TeacherData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
            }
            catch (SQLException e)
            {
                System.err.println("Error " + e);
            }
            this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
            this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
            this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
            this.daysallowedcolumn.setCellValueFactory(new PropertyValueFactory("booksAllowed"));
            this.booksallowedcolumn.setCellValueFactory(new PropertyValueFactory("daysAllowed"));
            //this.booksallowedcolumn.setCellValueFactory();

            this.teachertable.setItems(null);
            this.teachertable.setItems(this.data);
            /*
            try
            {
                Connection conn = dbConnection.getConnection();
                this.data = FXCollections.observableArrayList();

                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM teachers");
                while (rs.next()) {
                    this.data.add(new TeacherData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
            }
        catch (SQLException e)
            {
                System.err.println("Error " + e);
            }
            this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
            this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
            this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
            this.daysallowedcolumn.setCellValueFactory(new PropertyValueFactory("booksAllowed"));
            this.booksallowedcolumn.setCellValueFactory(new PropertyValueFactory("daysAllowed"));
            //this.booksallowedcolumn.setCellValueFactory();

            this.teachertable.setItems(null);
            this.teachertable.setItems(this.data);


*/
            //teachertable.getSelectionModel().setCellSelectionEnabled(false);
            //teachertable.getSelectionModel().setCellSelectionEnabled(true);
        }else{
            //JOptionPane.showMessageDialog(null, "Please Enter Valid Data", "InfoBox: " + " Incorrect Data Types", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Bad data");
            errorMessageAdd.setText("Please Enter Valid Data Types");
        }
    }

    public void deleteTeacher(ActionEvent actionEvent) {
        String IDnum = this.teacherIDDelete.getText();
        //String sql = "INSERT INTO `students`(studentF`, `studentL`, `bLimit`, `dLimit`) VALUES (? , ?, ?, ?)";
        String sql = ("DELETE FROM teachers WHERE id = '"+IDnum+"'");
        if(isInteger(this.teacherNumberOfBooksAllowedDeleteField.getText()) && this.teacherNumberOfBooksAllowedDeleteField.getText() != null && isInteger(this.teacherDaysAllowedDeleteField.getText()) && this.teacherDaysAllowedDeleteField.getText() != null) {
            try {

                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                //stmt.setString(1, this.id.getText());
                stmt.execute();
                conn.close();


                // add data


            } catch (SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }

            try
            {
                Connection conn = dbConnection.getConnection();
                this.data = FXCollections.observableArrayList();

                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM teachers");
                while (rs.next()) {
                    this.data.add(new TeacherData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
            }
            catch (SQLException e)
            {
                System.err.println("Error " + e);
            }

            this.idcolumn.setCellValueFactory(new PropertyValueFactory("ID"));
            this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory("firstName"));
            this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory("lastName"));
            this.daysallowedcolumn.setCellValueFactory(new PropertyValueFactory("booksAllowed"));
            this.booksallowedcolumn.setCellValueFactory(new PropertyValueFactory("daysAllowed"));
            //this.booksallowedcolumn.setCellValueFactory();

            this.teachertable.setItems(null);
            this.teachertable.setItems(this.data);


        }else{
            //JOptionPane.showMessageDialog(null, "Please Enter Valid Data", "InfoBox: " + " Incorrect Data Types", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Bad data");
            errorMessageAdd.setText("Please Enter Valid Data Types");
        }
    }
}
