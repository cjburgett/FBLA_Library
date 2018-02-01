package sample.bookScreen;

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
import sample.staffScreen.*;
import sample.bookScreen.*;
import sample.studentScreen.StudentData;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class bookScreenController implements Initializable {


    //BOOK TABLE
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
    private TableColumn<BookData, String> whoCheckedcolumn;
    @FXML
    private TextField ISBNField;
    @FXML
    private TextField aLastField;
    @FXML
    private TextField aFirstField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField ISBNEditField;
    @FXML
    private TextField aLastEditField;
    @FXML
    private TextField aFirstEditField;
    @FXML
    private TextField titleEditField;
    @FXML
    private TextField whoCheckedField;
    @FXML
    private TextField checkedOutColumn;
    @FXML
    JFXButton backButton;
    @FXML
    private Label idBookField;
    @FXML
    private Label idBookEditField;

    @FXML
    private Label bookIDDelete;
    @FXML
    private TextField ISBNDeleteField;
    @FXML
    private TextField aLastDeleteField;
    @FXML
    private TextField aFirstDeleteField;
    @FXML
    private TextField titleDeleteField;

    private ObservableList<BookData> data;
    private dbConnection dc;




    public void initialize(URL url, ResourceBundle rb)
    {
        this.dc = new dbConnection();


        load();



        booktable.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = booktable.getSelectionModel().getSelectedCells();
        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                //TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                //Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                BookData selectedData = booktable.getSelectionModel().getSelectedItem();
                //System.out.println("Selected Value " + selectedData.getFirstName());
                try{
                    idBookEditField.setText(selectedData.getID());
                    titleEditField.setText(selectedData.getTitle());
                    aLastEditField.setText(selectedData.getaLast());
                    aFirstEditField.setText(selectedData.getaFirst());
                    ISBNEditField.setText(selectedData.getISBN());

                    bookIDDelete.setText(selectedData.getID());
                    titleDeleteField.setText(selectedData.getTitle());
                    aLastDeleteField.setText(selectedData.getaLast());
                    aFirstDeleteField.setText(selectedData.getaFirst());
                    ISBNDeleteField.setText(selectedData.getISBN());



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
    private void loadBookData(ActionEvent event)
    {
        load();
    }



    @FXML
    private void addBook(ActionEvent event)
    {

        //String sql = "INSERT INTO `books`(ISBN`, `aLast`, `aFirst`, `title`) VALUES (? , ?, ?, ?)";
        String sql = "INSERT INTO `books`(`ISBN`, `aLast`, `aFirst`, `title`) VALUES (? , ?, ?, ?)";
            try {

                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, this.ISBNField.getText());
                stmt.setString(2, this.aLastField.getText());
                stmt.setString(3, this.aFirstField.getText());
                stmt.setString(4, this.titleField.getText());

                stmt.execute();
                conn.close();


                // add data


            } catch (SQLException e) {
                System.err.println("Got an exception!");
                System.err.println(e.getMessage());
            }

            load();



    }

    public void load(){
        try
        {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books");
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
        //this.checkedOutColumn.setCellValueFactory(new PropertyValueFactory("title"));
        //this.whoCheckedcolumn.setCellValueFactory(new PropertyValueFactory("whoChecked"));

        //this.booksallowedcolumn.setCellValueFactory();

        this.booktable.setItems(null);
        this.booktable.setItems(this.data);
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


    public void editBook(ActionEvent actionEvent) {

        String ISBNvar = this.ISBNEditField.getText();
        String authorLast = this.aLastEditField.getText();
        String authorFirst = this.aFirstEditField.getText();
        String titlevar = this.titleEditField.getText();
        String IDnum = this.idBookEditField.getText();

        String sql = ("UPDATE books SET ISBN = '"+ISBNvar+"', aLast = '"+authorLast+"', aFirst = '"+authorFirst+"', title = '"+titlevar+"' WHERE id = '"+IDnum+"'");
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.execute();
            conn.close();


        } catch (SQLException e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        load();

    }

public void deleteBook(ActionEvent actionEvent) {
        String IDnum = this.bookIDDelete.getText();
        String sql = ("DELETE FROM books WHERE id = '"+IDnum+"'");
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

    load();
    }

}

