/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author valer
 */
public class ScheduleFXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Pane navPane;
    @FXML
    private Button btnBack;
    @FXML
    private Pane paneTable;
    @FXML
    private TableView<?> tableSchedule;
    @FXML
    private TableColumn<?, ?> colHorario1;
    @FXML
    private TableColumn<?, ?> colHorario2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnBack(ActionEvent event) {
    }
    
}
