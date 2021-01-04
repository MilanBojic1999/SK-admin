package sk.microservise.client.client.controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.microservise.client.ClientApplication;
import sk.microservise.client.client.ViewManager;

@Component
public class adminWindowControler {

    @Autowired
    private ViewManager viewManager;

    @Autowired
    ClientApplication app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button showPlanes;

    @FXML
    private Button showFlights;

    @FXML
    private Button addDestination;

    @FXML
    private Button exitButton;

    @FXML
    private Button addPlane;

    @FXML
    private Button remPlane;

    @FXML
    private Button addFlight;

    @FXML
    private Button remFlight;

    @FXML
    void addDestination(ActionEvent event) {
        Pane pane = viewManager.getSomePane("/fxml/addDestination.fxml");
        mainPane.setBottom(pane);
    }

    @FXML
    void addFlight(ActionEvent event) {
        Pane pane = viewManager.getSomePane("/fxml/addFlightPane.fxml");
        mainPane.setBottom(pane);
    }

    @FXML
    void addPlane(ActionEvent event) {
        Pane pane = viewManager.getSomePane("/fxml/addPlanePlane.fxml");
        mainPane.setBottom(pane);
    }

    @FXML
    void exitApp(ActionEvent event) {
        Stage stage = (Stage) mainPane.getScene().getWindow();

        stage.hide();
        stage.setTitle("Admin log in");
        stage.setScene(viewManager.createLogInScene());
        stage.show();
    }

    @FXML
    void remFlight(ActionEvent event) {
        Scene scene = viewManager.createCustomScene("/fxml/flightTable.fxml");
        Stage stage = new Stage();
        stage.setTitle("Flight Table");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void remPlane(ActionEvent event) {
        Scene scene = viewManager.createCustomScene("/fxml/planeWindow.fxml");
        Stage stage = new Stage();
        stage.setTitle("Plane Table");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showFlight(ActionEvent event) {
        Scene scene = viewManager.createCustomScene("/fxml/flightTable.fxml");
        Stage stage = new Stage();
        stage.setTitle("Flight Table");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showPlane(ActionEvent event) {
        Scene scene = viewManager.createCustomScene("/fxml/planeWindow.fxml");
        Stage stage = new Stage();
        stage.setTitle("Plane Table");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        assert mainPane != null : "fx:id=\"mainPane\" was not injected: check your FXML file 'adminWindow.fxml'.";
        assert showPlanes != null : "fx:id=\"showPlanes\" was not injected: check your FXML file 'adminWindow.fxml'.";
        assert showFlights != null : "fx:id=\"showFlights\" was not injected: check your FXML file 'adminWindow.fxml'.";
        assert addDestination != null : "fx:id=\"addDestination\" was not injected: check your FXML file 'adminWindow.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'adminWindow.fxml'.";
        assert addPlane != null : "fx:id=\"addPlane\" was not injected: check your FXML file 'adminWindow.fxml'.";
        assert remPlane != null : "fx:id=\"remPlane\" was not injected: check your FXML file 'adminWindow.fxml'.";
        assert addFlight != null : "fx:id=\"addFlight\" was not injected: check your FXML file 'adminWindow.fxml'.";
        assert remFlight != null : "fx:id=\"remFlight\" was not injected: check your FXML file 'adminWindow.fxml'.";

    }
}
