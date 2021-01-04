package sk.microservise.client.client.controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.microservise.client.service.AdminService;

@Component
public class addDestinationControler {

    @Autowired
    private AdminService service;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField destName;

    @FXML
    private Button addButton;

    @FXML
    void addDestination(ActionEvent event) {
        String nname = destName.getText();
        if(nname.isBlank())
            return;

        if(!service.addDestination(nname))
            errorDialog();
        else {
            destName.clear();
        }

    }

    @FXML
    void initialize() {
        assert destName != null : "fx:id=\"destName\" was not injected: check your FXML file 'addDestination.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addDestination.fxml'.";

    }

    private void errorDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Problem adding entity");
        alert.setHeaderText("There was a problem while adding new destination");
        alert.showAndWait();
    }
}
