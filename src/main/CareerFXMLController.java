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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class CareerFXMLController implements Initializable {

    @FXML
    private Button btnAddCareer;
    @FXML
    private Button btnDeleteCareer;
    @FXML
    private Button btnModifyCareer;
    @FXML
    private Button btnShowCareer;
    @FXML
    private Button btnBack;
    @FXML
    private Pane panelAddCareer;
    @FXML
    private TextField tfCareerId;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button btnAdd;
    @FXML
    private Text txtTitle;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField tfIdCareer;
    @FXML
    private Pane panelModificar;
    @FXML
    private TextField tfIdCareerUpdate;
    @FXML
    private TextField tfDescriptionUpdate;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<?> tableCareer;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ac;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAddCareer(ActionEvent event) {
    }

    @FXML
    private void btnDeleteCareer(ActionEvent event) {
    }

    @FXML
    private void btnModifyCareer(ActionEvent event) {
    }

    @FXML
    private void btnShowCareer(ActionEvent event) {
    }

    @FXML
    private void btnBack(ActionEvent event) {
        
        bp.setVisible(false);
    }

    @FXML
    private void btnAdd(ActionEvent event) {
    }

    @FXML
    private void btnDelete(ActionEvent event) {
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
    }
    
}
