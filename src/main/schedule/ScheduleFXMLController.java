/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.schedule;

import main.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Valeria
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
    private TableView<?> tableCourse;
    @FXML
    private TableColumn<?, ?> colIDCourse;
    @FXML
    private TableColumn<?, ?> colPeriodCourse;
    @FXML
    private TableColumn<?, ?> colScheduleCourse1;
    @FXML
    private TableColumn<?, ?> colScheduleCourse2;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtFieldDaySchedule1;
    @FXML
    private TextField txtFieldHourSchedule1;
    @FXML
    private TextField txtFieldDaySchedule2;
    @FXML
    private TextField txtFieldHourSchedule2;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtFieldPeriodCourse;

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

    @FXML
    private void btnSave(ActionEvent event) {
    }

    @FXML
    private void btnAdd(ActionEvent event) {
    }
    
}
