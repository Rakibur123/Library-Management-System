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
import javafx.scene.Node;
import javafx.stage.Stage;
import library.management.system.Book;

public class StudentDashboardController implements Initializable {

    @FXML
    private Button btnHome;
    @FXML
    private Button btnAvailableBook;
    @FXML
    private AnchorPane Homeform;
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
    @FXML
    private Button addButton;
    @FXML
    private TextField id;
    @FXML
    private TextField year;
    @FXML
    private TextField page;
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private Button Delete;
    @FXML
    private Button Logout;
    @FXML
    private Button Save;

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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydatabase", "root", "");

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

    }

    @FXML
    private void handleAvailableBookButtonClick(ActionEvent event) {
        Homeform.setVisible(false);
        AvailableBookform.setVisible(true);

    }

    @FXML
    private void addbtn(ActionEvent event) {
        try {
            // Get the values from the text fields
            String bookId = id.getText();
            String bookTitle = title.getText();
            String bookAuthor = author.getText();
            String bookYear = year.getText();
            String bookPage = page.getText();

            // Create a new Book object with the entered values
            Book newBook = new Book(bookId, bookTitle, bookAuthor, bookYear, bookPage);

            // Add the new book to the table
            bookList.add(newBook);

            // Save the new book to the database
            saveBookToDatabase(newBook);

            // Clear the text fields after adding the book
            clearTextFields();
        } catch (Exception e) {
            e.printStackTrace();
            // Add more specific error handling if needed
        }
    }

    @FXML
    private void Deletebtn(ActionEvent event) {
        // Get the selected book from the table
        Book selectedBook = booktableview1.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Remove the selected book from the UI
            bookList.remove(selectedBook);

            // Remove the selected book from the database
            deleteBookFromDatabase(selectedBook);
        } else {
            // Display a message to the user that no book is selected for deletion
            // You can show an alert or update a status label, for example
            System.out.println("No book selected for deletion");
        }
    }

    private void deleteBookFromDatabase(Book book) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydatabase", "root", "");

            // Prepare the SQL query for deleting a book by ID
            String deleteQuery = "DELETE FROM booktableview WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, book.getId());

            // Execute the delete query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Savebtn(ActionEvent event) {
        try {
            // Get the values from the text fields
            String bookId = id.getText();
            String bookTitle = title.getText();
            String bookAuthor = author.getText();
            String bookYear = year.getText();
            String bookPage = page.getText();

            // Print values for debugging
            System.out.println("ID: " + bookId);
            System.out.println("Title: " + bookTitle);
            System.out.println("Author: " + bookAuthor);
            System.out.println("Year: " + bookYear);
            System.out.println("Page: " + bookPage);

            // Create a new Book object with the entered values
            Book newBook = new Book(bookId, bookTitle, bookAuthor, bookYear, bookPage);

            // Add the new book to the table
            bookList.add(newBook);

            // Save the new book to the database
            saveBookToDatabase(newBook);

            // Clear the text fields after adding the book
            clearTextFields();
        } catch (Exception e) {
            e.printStackTrace();
            // Add more specific error handling if needed
        }
    }

    public static void saveBookToDatabase(Book book) {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/librarydatabase", "root", "");

            // Prepare the SQL query for inserting a new book
            String insertQuery = "INSERT INTO booktableview (id, title, author, year, page) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, book.getId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getYear());
            preparedStatement.setString(5, book.getPage());

            // Execute the insert query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearTextFields() {
        id.clear();
        title.clear();
        author.clear();
        year.clear();
        page.clear();
    }

    @FXML
    private void Logoutbtn(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close(); // Close the current window

    }
}
