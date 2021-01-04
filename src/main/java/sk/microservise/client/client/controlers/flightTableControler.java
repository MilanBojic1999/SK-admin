package sk.microservise.client.client.controlers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.microservise.client.client.ViewManager;
import sk.microservise.client.model.Flight;
import sk.microservise.client.model.FlightCriteria;
import sk.microservise.client.model.Plane;
import sk.microservise.client.service.AdminService;

@Component
public class flightTableControler {


    private int pageInd;

    @Autowired
    private AdminService service;

    @Autowired
    private ViewManager viewManager;

    private List<FlightCriteria> flightCriteria;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane mainPane;

    @FXML
    private TableView<Flight> flightTable;

    @FXML
    private TableColumn<Flight, Long> idColumn;

    @FXML
    private TableColumn<Flight, String> planeColumn;

    @FXML
    private TableColumn<Flight, String > originColumn;

    @FXML
    private TableColumn<Flight, String> destColumn;

    @FXML
    private TableColumn<Flight, Integer> distanceColumn;

    @FXML
    private TableColumn<Flight, Integer> priceColumn;

    @FXML
    private TableColumn<Flight, Integer> capacityColumn;

    @FXML
    private Button backwardButt;

    @FXML
    private Button forwardsButt;

    @FXML
    private Button removeFlightButton;

    @FXML
    private Button filterButton;

    @FXML
    void filter(ActionEvent event) {
        if(mainPane.getRight()==null){
            Pane pane = viewManager.getSomePane("/fxml/filterWindow.fxml");
            mainPane.setRight(pane);
        }else{
            mainPane.setRight(null);
        }
    }

    @FXML
    void nextPage(ActionEvent event) {
        pageInd++;
        populate();
    }

    @FXML
    void prevPage(ActionEvent event) {
        pageInd--;
        if(pageInd<0)
            pageInd=0;
        populate();
    }

    @FXML
    void removeFlight(ActionEvent event) {
        Flight f = flightTable.getSelectionModel().getSelectedItem();
        if(f==null)
            return;

        boolean succ = service.cancelFlights(f.getId());
        if(succ){
            System.out.println("We did it");
            populate();
        }else{
            System.out.println("No nooo");
            errorDialog("We couldn't remove flight");
        }
    }

    void populate(){


        flightTable.getItems().clear();

        List<Flight> list = service.getFlights(flightCriteria,pageInd);

        flightTable.getItems().addAll(list);
    }

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert flightTable != null : "fx:id=\"flightTable\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert planeColumn != null : "fx:id=\"planeColumn\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert originColumn != null : "fx:id=\"originColumn\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert destColumn != null : "fx:id=\"destColumn\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert distanceColumn != null : "fx:id=\"distanceColumn\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert priceColumn != null : "fx:id=\"priceColumn\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert capacityColumn != null : "fx:id=\"capacityColumn\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert backwardButt != null : "fx:id=\"backwardButt\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert forwardsButt != null : "fx:id=\"forwardsButt\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert removeFlightButton != null : "fx:id=\"removeFlightButton\" was not injected: check your FXML file 'flightTable.fxml'.";
        assert filterButton != null : "fx:id=\"filterButton\" was not injected: check your FXML file 'flightTable.fxml'.";

        initMate();
    }

    void initMate(){
        this.pageInd = 0;
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        planeColumn.setCellValueFactory(new PropertyValueFactory<>("plane"));
        originColumn.setCellValueFactory(new PropertyValueFactory<>("originName"));
        destColumn.setCellValueFactory(new PropertyValueFactory<>("destinationName"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));


        flightCriteria = new ArrayList<>();

        populate();
    }

    public void setFlightCriteria(List<FlightCriteria> flightCriteria) {
        if(flightCriteria==null)
            return;
        this.flightCriteria = flightCriteria;
        populate();
    }

    private void errorDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Problem adding entity");
        alert.setHeaderText(msg);
        alert.showAndWait();
    }
}
