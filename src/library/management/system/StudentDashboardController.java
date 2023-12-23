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

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {

    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private TableColumn<Book, String> colid;
    @FXML
    private TableColumn<Book, String> coltitle;
    @FXML
    private TableColumn<Book, String> colauthor;
    @FXML
    private TableColumn<Book, String> colyear;
    @FXML
    private TableColumn<Book, String> colpage;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAvailableBook;
    @FXML
    private Button Borrow;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfauthor;
    @FXML
    private TextField tfyear;
    @FXML
    private TextField tfpage;

    private ObservableList<Book> bookList;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Check if TableColumn variables are properly injected
        if (colid == null || coltitle == null || colauthor == null || colyear == null || colpage == null) {
            System.err.println("FXML elements not injected properly");
            return;
        }

   
    colid.setCellValueFactory(cellData -> cellData.getValue().idProperty());
    coltitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    colauthor.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
    colyear.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
    colpage.setCellValueFactory(cellData -> cellData.getValue().pageProperty());

    bookList = FXCollections.observableArrayList();

    // Load available books
    showavailableBooks();
}


    private void showavailableBooks() {
        String selectData = "SELECT * FROM booktableview";
        connect = database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            while (result.next()) {
                Book book = new Book(
                        result.getString("id"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getString("year"),
                        result.getString("page")
                );
                bookList.add(book);
            }

            bookTableView.setItems(bookList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleHomeButtonClick(ActionEvent event) {
        // Add your logic here
    }

    @FXML
    private void handleAvailableBookButtonClick(ActionEvent event) {
        // Add your logic here
    }

    @FXML
    private void handleBorrowButtonClick(ActionEvent event) {
        // Add your logic here
    }

    @FXML
    private void addbutton(ActionEvent event) {
        Book newBook = new Book(tfid.getText(), tftitle.getText(), tfauthor.getText(), tfyear.getText(), tfpage.getText());
        bookList.add(newBook);
        clearTextFields();
    }
/**
 * 
 /**/
    private void clearTextFields() {
        tfid.clear();
        tftitle.clear();
        tfauthor.clear();
        tfyear.clear();
        tfpage.clear();
    }
}
