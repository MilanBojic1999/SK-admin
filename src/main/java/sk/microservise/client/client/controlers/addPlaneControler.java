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
public class addPlaneControler {

    @Autowired
    private AdminService service;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField chessisTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField capacityTF;

    @FXML
    private Button addButton;

    @FXML
    void addPlane(ActionEvent event) {
        long chss;
        int cap;
        try{
            chss = Long.parseLong(chessisTF.getText());
            cap = Integer.parseInt(capacityTF.getText());
        }catch (NumberFormatException e){
            e.printStackTrace();
            return;
        }

        String name = nameTF.getText();
        boolean res = service.addPlane(chss,name,cap);
        if(res){
            nameTF.clear();
            chessisTF.clear();
            capacityTF.clear();
        }else {
            errorDialog();
        }
    }



    @FXML
    void initialize() {
        assert chessisTF != null : "fx:id=\"chessisTF\" was not injected: check your FXML file 'addPlanePlane.fxml'.";
        assert nameTF != null : "fx:id=\"nameTF\" was not injected: check your FXML file 'addPlanePlane.fxml'.";
        assert capacityTF != null : "fx:id=\"capacityTF\" was not injected: check your FXML file 'addPlanePlane.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addPlanePlane.fxml'.";

    }

    private void errorDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Problem adding entity");
        alert.setHeaderText("There was a problem while adding new plane");
        alert.showAndWait();
    }
}
