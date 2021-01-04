package sk.microservise.client.client.controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.microservise.client.client.ViewManager;
import sk.microservise.client.service.AdminService;

@Component
public class logInControler {

    @Autowired
    private ViewManager viewManager;

    @Autowired
    private AdminService service;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Button loginButton;

    @FXML
    void logInAction(ActionEvent event) {
        System.out.println(username.getText()+" sa "+password.getText());
        boolean result = service.adminLogIn(username.getText(),password.getText());
        if(result) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.hide();
            stage.setScene(viewManager.createAdminScene());
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Log in problem");
            alert.setHeaderText("We couldn't login onto service");
            alert.showAndWait();
            System.err.println("We coulnd login");
        }
    }

    @FXML
    void initialize() {
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'Untitled'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'Untitled'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'Untitled'.";

    }

    private void errorDialog(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Problem with log in");
        alert.setHeaderText("There was a problem while adding new destination");
        alert.showAndWait();
    }
}
