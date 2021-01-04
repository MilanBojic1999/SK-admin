package sk.microservise.client.client.controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.microservise.client.client.ViewManager;
import sk.microservise.client.model.Flight;
import sk.microservise.client.model.Plane;
import sk.microservise.client.service.AdminService;

import javax.print.attribute.standard.Destination;

@Component
public class addFlightControler {

    @Autowired
    private AdminService service;

    @Autowired
    private ViewManager viewManager;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField planeTF;

    @FXML
    private TextField originTF;

    @FXML
    private TextField destinationTF;

    @FXML
    private TextField milesTF;

    @FXML
    private TextField priceTF;

    @FXML
    private TextField capacityTF;

    @FXML
    private Button addButton;

    @FXML
    void addFlight(ActionEvent event) {
        long plane_id = 0;
        int miles = 0;
        int price = 0;
        int cap = 0;
        try{
            System.out.println(planeTF+"->"+planeTF.getText());
            plane_id = Long.parseLong(planeTF.getText());
            miles = Integer.parseInt(milesTF.getText());
            price = Integer.parseInt(priceTF.getText());
            cap = Integer.parseInt(capacityTF.getText());
        }catch (NumberFormatException e){
            return;
        }

        boolean ser = service.addFlight(plane_id,miles,price,cap,originTF.getText(),destinationTF.getText());
        if(ser){
            planeTF.clear();
            milesTF.clear();
            priceTF.clear();
            capacityTF.clear();
        }else {
            errorDialog();
        }
    }

    @FXML
    void openDestView(ActionEvent event) {

    }

    @FXML
    void openOrgView(ActionEvent event) {

    }

    @FXML
    void openPlaneView(ActionEvent event) {
        Scene scene = viewManager.createCustomScene("/fxml/planeWindow.fxml");
        Stage stage = new Stage();
        stage.setTitle("Plane Table");
        stage.setScene(scene);
        stage.show();
    }

    public void selectPlane(Plane plane){
        if(plane==null)
            return;
        planeTF.setText(plane.getChassis()+"");
    }

    public void selectOrigin(Destination destination){
        if(destination==null)
            return;
        originTF.setText(destination.getName());
    }

    public void selectDestination(Destination destination){
        if(destination==null)
            return;
        destinationTF.setText(destination.getName());
    }

    @FXML
    void initialize() {
        assert planeTF != null : "fx:id=\"planeTF\" was not injected: check your FXML file 'addFlightPane.fxml'.";
        assert originTF != null : "fx:id=\"originTF\" was not injected: check your FXML file 'addFlightPane.fxml'.";
        assert destinationTF != null : "fx:id=\"destinationTF\" was not injected: check your FXML file 'addFlightPane.fxml'.";
        assert milesTF != null : "fx:id=\"milesTF\" was not injected: check your FXML file 'addFlightPane.fxml'.";
        assert priceTF != null : "fx:id=\"priceTF\" was not injected: check your FXML file 'addFlightPane.fxml'.";
        assert capacityTF != null : "fx:id=\"capacityTF\" was not injected: check your FXML file 'addFlightPane.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addFlightPane.fxml'.";

    }

    private void errorDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Problem adding entity");
        alert.setHeaderText("There was a problem while adding new flight");
        alert.showAndWait();
    }
}
