package sample.studentScreen;

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

public class studentScreenController implements Initializable {
    @FXML
    JFXButton backButton;
    @FXML
    private TableView<StudentData> studenttable;
    @FXML
    private TableColumn<StudentData, String> idcolumn;
    @FXML
    private TableColumn<StudentData, String> firstnamecolumn;
    @FXML
    private TableColumn<StudentData, String> lastnamecolumn;
    @FXML
    private TableColumn<StudentData, String> daysallowedcolumn;
    @FXML
    private TableColumn<StudentData, String> booksallowedcolumn;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField studentDaysAllowedField;
    @FXML
    private TextField StudentNumberOfBooksAllowedField;
    @FXML

    private JFXTextField firstNameEditField;
    @FXML
    private JFXTextField lastNameEditField;
    @FXML
    private JFXTextField studentDaysAllowedEditField;
    @FXML
    private JFXTextField studentNumberOfBooksAllowedEditField;
    @FXML
    private Label studentIDEdit;
    @FXML
    public JFXButton editStudentButton;
    @FXML
    private Label errorMessageAdd;
    @FXML
    private JFXTextField firstNameDeleteField;
    @FXML
    private JFXTextField lastNameDeleteField;
    @FXML
    private JFXTextField studentDaysAllowedDeleteField;
    @FXML
    private JFXTextField studentNumberOfBooksAllowedDeleteField;
    @FXML
    private Label studentIDDelete;
    @FXML
    public JFXButton deleteStudentButton;
    @FXML
    private Label errorMessageDelete;


    private ObservableList<StudentData> data;
    private dbConnection dc;




    public void initialize(URL url, ResourceBundle rb)
    {
        this.dc = new dbConnection();


        try
        {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()) {
                this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
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

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);

        studenttable.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = studenttable.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                //TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                //Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                StudentData selectedData = studenttable.getSelectionModel().getSelectedItem();
                //System.out.println("Selected Value " + selectedData.getFirstName());
                try{
                    studentIDEdit.setText(selectedData.getID());
                    firstNameEditField.setText(selectedData.getFirstName());
                    lastNameEditField.setText(selectedData.getLastName());
                    studentDaysAllowedEditField.setText(selectedData.getDaysAllowed());
                    studentNumberOfBooksAllowedEditField.setText(selectedData.getBooksAllowed());

                    studentIDDelete.setText(selectedData.getID());
                    //System.out.println(2);
                    firstNameDeleteField.setText(selectedData.getFirstName());
                    lastNameDeleteField.setText(selectedData.getLastName());
                    studentDaysAllowedDeleteField.setText(selectedData.getDaysAllowed());
                    studentNumberOfBooksAllowedDeleteField.setText(selectedData.getBooksAllowed());
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
    private void loadStudentData(ActionEvent event)
    {
        try
        {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
            while (rs.next()) {
                this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
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


        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);


    }


    @FXML
    private void addStudent(ActionEvent event)
    {
        //String sql = "INSERT INTO `students`(studentF`, `studentL`, `bLimit`, `dLimit`) VALUES (? , ?, ?, ?)";
        String sql = "INSERT INTO `students`(`studentF`, `studentL`, `bLimit`, `dLimit`) VALUES (? , ?, ?, ?)";
        if(isInteger(this.StudentNumberOfBooksAllowedField.getText()) && this.StudentNumberOfBooksAllowedField.getText() != null && isInteger(this.studentDaysAllowedField.getText()) && this.studentDaysAllowedField.getText() != null) {
            try {

                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                //stmt.setString(1, this.id.getText());
                stmt.setString(1, this.firstNameField.getText());
                stmt.setString(2, this.lastNameField.getText());
                if (isInteger(this.StudentNumberOfBooksAllowedField.getText()) && this.StudentNumberOfBooksAllowedField.getText() != null) {
                    stmt.setString(3, this.StudentNumberOfBooksAllowedField.getText());
                } else {
                    System.out.println("this is not a number");
                }

                stmt.setString(4, this.studentDaysAllowedField.getText());

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

                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
                while (rs.next()) {
                    this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
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

            this.studenttable.setItems(null);
            this.studenttable.setItems(this.data);


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


    public void editStudent(ActionEvent actionEvent) {
        String firstname = this.firstNameEditField.getText();
        String lastname = this.lastNameEditField.getText();
        String daysAllowed = this.studentDaysAllowedEditField.getText();
        String numBooks = this.studentNumberOfBooksAllowedEditField.getText();
        String IDnum = this.studentIDEdit.getText();

        //String sql = "INSERT INTO `students`(studentF`, `studentL`, `bLimit`, `dLimit`) VALUES (? , ?, ?, ?)";
        String sql = ("UPDATE students SET studentF = '"+firstname+"', studentL = '"+lastname+"', bLimit = '"+daysAllowed+"', dLimit = '"+numBooks+"' WHERE id = '"+IDnum+"'");
        //String sql = ("UPDATE students SET studentF = '"+this.firstNameEditField.getText()+"' WHERE id = '"+this.studentIDEdit.getText()+"'");
        //String sql = ("UPDATE students SET studentF = '"+firstname+"' WHERE id = 2");
        //UPDATE students SET studentF = 'testng' WHERE id = 1
        if(isInteger(this.studentDaysAllowedEditField.getText()) && this.studentNumberOfBooksAllowedEditField.getText() != null && isInteger(this.studentDaysAllowedEditField.getText()) && this.studentDaysAllowedEditField.getText() != null) {
            //System.out.println(2);
            try {
                //System.out.println(3);
                Connection conn = dbConnection.getConnection();
                //System.out.println(21);
                PreparedStatement stmt = conn.prepareStatement(sql);


                //System.out.println(conn.createStatement().executeQuery("SELECT * FROM students"));
                //stmt.setString(1, this.id.getText());
                /*stmt.setString(1, this.firstNameField.getText());
                stmt.setString(2, this.lastNameField.getText());
                if (isInteger(this.StudentNumberOfBooksAllowedField.getText()) && this.StudentNumberOfBooksAllowedField.getText() != null) {
                    stmt.setString(3, this.StudentNumberOfBooksAllowedField.getText());
                } else {
                    System.out.println("this is not a number");
                }

                stmt.setString(4, this.studentDaysAllowedField.getText());
*/
                //System.out.println(4);
                stmt.execute();
                //System.out.println(5);
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

                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
                while (rs.next()) {
                    this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
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

            this.studenttable.setItems(null);
            this.studenttable.setItems(this.data);
            /*
            try
            {
                Connection conn = dbConnection.getConnection();
                this.data = FXCollections.observableArrayList();

                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
                while (rs.next()) {
                    this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
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

            this.studenttable.setItems(null);
            this.studenttable.setItems(this.data);


*/
            //studenttable.getSelectionModel().setCellSelectionEnabled(false);
            //studenttable.getSelectionModel().setCellSelectionEnabled(true);
        }else{
            //JOptionPane.showMessageDialog(null, "Please Enter Valid Data", "InfoBox: " + " Incorrect Data Types", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Bad data");
            errorMessageAdd.setText("Please Enter Valid Data Types");
        }
    }

    public void deleteStudent(ActionEvent actionEvent) {
        String IDnum = this.studentIDDelete.getText();
        //String sql = "INSERT INTO `students`(studentF`, `studentL`, `bLimit`, `dLimit`) VALUES (? , ?, ?, ?)";
        String sql = ("DELETE FROM students WHERE id = '"+IDnum+"'");
        if(isInteger(this.studentNumberOfBooksAllowedDeleteField.getText()) && this.studentNumberOfBooksAllowedDeleteField.getText() != null && isInteger(this.studentDaysAllowedDeleteField.getText()) && this.studentDaysAllowedDeleteField.getText() != null) {
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

                ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM students");
                while (rs.next()) {
                    this.data.add(new StudentData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
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

            this.studenttable.setItems(null);
            this.studenttable.setItems(this.data);


        }else{
            //JOptionPane.showMessageDialog(null, "Please Enter Valid Data", "InfoBox: " + " Incorrect Data Types", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Bad data");
            errorMessageAdd.setText("Please Enter Valid Data Types");
        }
    }
}
