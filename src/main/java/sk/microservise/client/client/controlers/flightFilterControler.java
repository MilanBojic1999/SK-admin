package sk.microservise.client.client.controlers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.microservise.client.model.FlightCriteria;

@Component
public class flightFilterControler {

    private List<String> list4Numbers;
    private List<String> list4String;

    @Autowired
    private flightTableControler flightTableControler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField capacityTF;

    @FXML
    private ComboBox<String> capacityCB;

    @FXML
    private TextField priceTF;

    @FXML
    private ComboBox<String> priceCB;

    @FXML
    private TextField distanceTF;

    @FXML
    private ComboBox<String> distCB;

    @FXML
    private TextField planeTF;

    @FXML
    private ComboBox<String> planeCB;

    @FXML
    private TextField originTF;

    @FXML
    private ComboBox<String> orgCB;

    @FXML
    private TextField destinatiionTF;

    @FXML
    private ComboBox<String> destCB;

    @FXML
    private Button filterButton;

    @FXML
    void filter(ActionEvent event) {

        List<FlightCriteria> list = new ArrayList<>();
        if(!capacityTF.getText().isBlank()){
            int cap = 0;
            try{
                cap = Integer.parseInt(capacityTF.getText());
            }catch (NumberFormatException e){
                return;
            }
            list.add(new FlightCriteria("capacity",capacityCB.getSelectionModel().getSelectedItem(),cap));
        }
        if(!priceTF.getText().isBlank()){
            int cap = 0;
            try{
                cap = Integer.parseInt(priceTF.getText());
            }catch (NumberFormatException e){
                return;
            }
            list.add(new FlightCriteria("price",priceCB.getSelectionModel().getSelectedItem(),cap));
        }
        if(!distanceTF.getText().isBlank()){
            int cap = 0;
            try{
                cap = Integer.parseInt(distanceTF.getText());
            }catch (NumberFormatException e){
                return;
            }
            list.add(new FlightCriteria("distance",distCB.getSelectionModel().getSelectedItem(),cap));
        }
        if(!planeTF.getText().isBlank()){
            list.add(new FlightCriteria("plane",planeCB.getSelectionModel().getSelectedItem(),planeTF.getText()));
        }
        if(!originTF.getText().isBlank()){
            list.add(new FlightCriteria("originId",orgCB.getSelectionModel().getSelectedItem(),originTF.getText()));
        }
        if(!destinatiionTF.getText().isBlank()){
            list.add(new FlightCriteria("destinationId",destCB.getSelectionModel().getSelectedItem(),destinatiionTF.getText()));
        }

        flightTableControler.setFlightCriteria(list);
        flightTableControler.filter(event);
    }

    @FXML
    void initialize() {
        assert capacityTF != null : "fx:id=\"capacityTF\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert capacityCB != null : "fx:id=\"capacityCB\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert priceTF != null : "fx:id=\"priceTF\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert priceCB != null : "fx:id=\"priceCB\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert distanceTF != null : "fx:id=\"distanceTF\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert distCB != null : "fx:id=\"distCB\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert planeTF != null : "fx:id=\"planeTF\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert planeCB != null : "fx:id=\"planeCB\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert originTF != null : "fx:id=\"originTF\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert orgCB != null : "fx:id=\"orgCB\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert destinatiionTF != null : "fx:id=\"destinatiionTF\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert destCB != null : "fx:id=\"destCB\" was not injected: check your FXML file 'filterWindow.fxml'.";
        assert filterButton != null : "fx:id=\"filterButton\" was not injected: check your FXML file 'filterWindow.fxml'.";
        initMate();
    }

    void initMate(){
        list4Numbers = Arrays.asList("=","<",">");
        list4String = Arrays.asList("=","~");

        capacityCB.getItems().addAll(list4Numbers);
        priceCB.getItems().addAll(list4Numbers);
        distCB.getItems().addAll(list4Numbers);
        planeCB.getItems().addAll(list4String);
        orgCB.getItems().addAll(list4String);
        destCB.getItems().addAll(list4String);
    }
}
