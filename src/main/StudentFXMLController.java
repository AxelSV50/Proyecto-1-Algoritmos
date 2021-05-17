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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author carlo
 */
public class StudentFXMLController implements Initializable {

   @FXML
    private AnchorPane displayPanel;
    @FXML
    private Pane navPanel;
    @FXML
    private Button btnShowStudent;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnModifyStudent;
    @FXML
    private Button btnBack;
    @FXML
    private Pane addStudentPanel;
    @FXML
    private TextField txtDeleteStudent;
    @FXML
    private Button btnDeleteStudent;
    @FXML
    private Pane modifyPanel;
    @FXML
    private Pane dsly;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtCarnet;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtBirthDay;
    @FXML
    private TextField txtCellphone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAdrress;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<?> jcbCarreras;
    @FXML
    private TextField txtIdModify;
    @FXML
    private TextField txtStudentIdModify;
    @FXML
    private TextField txtNameModify;
    @FXML
    private TextField txtLastNameModify;
    @FXML
    private TextField txtBirthDayModify;
    @FXML
    private TextField txtPhoneModify;
    @FXML
    private TextField txtEmailModify;
    @FXML
    private TextField txtAdrressModify;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnShowStudent(ActionEvent event) {
    }

    @FXML
    private void btnDelete(ActionEvent event) {
    }

    @FXML
    private void btnAddStudent(ActionEvent event) {
    }

    @FXML
    private void btnModifyStudent(ActionEvent event) {
    }

    @FXML
    private void btnBack(ActionEvent event) {
    }

    @FXML
    private void btnDeleteStudent(ActionEvent event) {
    }

    @FXML
    private void btnAdd(ActionEvent event) {
    }

    @FXML
    private void jcbCarreras(ActionEvent event) {
    }

    @FXML
    private void btnCancel(ActionEvent event) {
    }
    
}
