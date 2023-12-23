package library.management.system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import library.management.system.Book;

public class StudentDashboardController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnAvailableBook;
    @FXML
    private Button Borrow;
    @FXML
    private AnchorPane Homeform;
    @FXML
    private AnchorPane Borrowform;
    @FXML
    private AnchorPane AvailableBookform;
    @FXML
    private TableView<Book> booktableview1;

    @FXML
    private TableColumn<Book, String> ID;
    @FXML
    private TableColumn<Book, String> Title1;
    @FXML
    private TableColumn<Book, String> Author1;
    @FXML
    private TableColumn<Book, String> Year1;
    @FXML
    private TableColumn<Book, String> Page1;

    private ObservableList<Book> bookList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookList = FXCollections.observableArrayList();
        initializeBookTable();
        loadBooksFromDatabase();
    }

    private void initializeBookTable() {
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Title1.setCellValueFactory(new PropertyValueFactory<>("title"));
        Author1.setCellValueFactory(new PropertyValueFactory<>("author"));
        Year1.setCellValueFactory(new PropertyValueFactory<>("year"));
        Page1.setCellValueFactory(new PropertyValueFactory<>("page"));

        booktableview1.setItems(bookList);
    }

    private void loadBooksFromDatabase() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            
         Connection connection  = DriverManager.getConnection("jdbc:mysql://localhost/librarydatabase", "root", "");

            // Prepare the SQL query
            String selectQuery = "SELECT * FROM booktableview";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            // Execute the query and retrieve the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Populate the ObservableList with data from the result set
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getString("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("year"),
                        resultSet.getString("page")
                );
                bookList.add(book);
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHomeButtonClick(ActionEvent event) {
        Homeform.setVisible(true);
        AvailableBookform.setVisible(false);
        Borrowform.setVisible(false);
    }

    @FXML
    private void handleAvailableBookButtonClick(ActionEvent event) {
        Homeform.setVisible(false);
        AvailableBookform.setVisible(true);
        Borrowform.setVisible(false);
    }

    @FXML
    private void handleBorrowButtonClick(ActionEvent event) {
        Homeform.setVisible(false);
        AvailableBookform.setVisible(false);
        Borrowform.setVisible(true);
    }
}
