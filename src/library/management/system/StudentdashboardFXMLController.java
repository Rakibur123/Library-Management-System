package library.management.system;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
 
public class StudentdashboardFXMLController implements Initializable {

    @FXML
    private TableColumn<?, ?> colid;
    @FXML
    private TableColumn<?, ?> coltittle;
    @FXML
    private TableColumn<?, ?> colaurther;
    @FXML
    private TableColumn<?, ?> colyear;
    @FXML
    private TableColumn<?, ?> colpage;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tftitle;
    @FXML
    private TextField tfaurther;
    @FXML
    private TextField tfyear;
    @FXML
    private TextField tfpage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code for Student Dashboard
    }
}
