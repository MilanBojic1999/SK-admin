package sk.microservise.client.client.controlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.microservise.client.model.Plane;
import sk.microservise.client.service.AdminService;

@Component
public class planeTableControler {

    private boolean withUsed;
    private int pageCnt;

    @Autowired
    private AdminService service;

    @Autowired
    private addFlightControler flightControler;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Plane> planeTable;

    @FXML
    private TableColumn<Plane, Long> idColumn;

    @FXML
    private TableColumn<Plane, String> nameColumn;

    @FXML
    private TableColumn<Plane, Integer> capColumn;

    @FXML
    private TableColumn<Plane, Boolean> usedColumn;

    @FXML
    private Button bButton;

    @FXML
    private Button fButton;

    @FXML
    private Button removeButt;

    @FXML
    private CheckBox chButton;

    @FXML
    void goBeckward(ActionEvent event) {
        if(pageCnt==0)
            return;
        pageCnt--;
    }

    @FXML
    void goForward(ActionEvent event) {
        pageCnt++;
    }

    @FXML
    void removeSelected(ActionEvent event) {
        Plane plane = planeTable.getSelectionModel().getSelectedItem();

        service.deletePlanes(plane.getChassis());
    }

    @FXML
    void selectPlane(MouseEvent event) {

    }

    @FXML
    void selectionUsed(ActionEvent event) {
        withUsed = chButton.isSelected();
        populate();
    }

    @FXML
    void initialize() {
        assert planeTable != null : "fx:id=\"planeTable\" was not injected: check your FXML file 'planeWindow.fxml'.";
        assert idColumn != null : "fx:id=\"idColumn\" was not injected: check your FXML file 'planeWindow.fxml'.";
        assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'planeWindow.fxml'.";
        assert capColumn != null : "fx:id=\"capColumn\" was not injected: check your FXML file 'planeWindow.fxml'.";
        assert usedColumn != null : "fx:id=\"usedColumn\" was not injected: check your FXML file 'planeWindow.fxml'.";
        assert bButton != null : "fx:id=\"bButton\" was not injected: check your FXML file 'planeWindow.fxml'.";
        assert fButton != null : "fx:id=\"fButton\" was not injected: check your FXML file 'planeWindow.fxml'.";
        assert removeButt != null : "fx:id=\"removeButt\" was not injected: check your FXML file 'planeWindow.fxml'.";
        assert chButton != null : "fx:id=\"chButton\" was not injected: check your FXML file 'planeWindow.fxml'.";

        initMate();
    }

    void populate(){
        planeTable.getItems().clear();

        List<Plane> list;
        if(withUsed){
            list = service.getAllPlanes();
        }else{
            list = service.getUnusedPlane();
        }

        planeTable.getItems().addAll(list);
    }

    void initMate(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("chassis"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        capColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        usedColumn.setCellValueFactory(new PropertyValueFactory<>("used"));

        chButton.setSelected(true);

        this.pageCnt = 0;
        populate();
    }
}
