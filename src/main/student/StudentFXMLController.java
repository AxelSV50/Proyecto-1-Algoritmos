/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.FXMain;

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
    private Button btnDeleteStudent;
    @FXML
    private Pane modifyPanel;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField txtCarnet;
    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtLastName;
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
    private Pane panelDeleteCareer;
    @FXML
    private TextField tfDeleteStudent;
    @FXML
    private Text txtTitle;
    @FXML
    private TextField tfSearchStudentUpdate;
    @FXML
    private Text txtCareerSearchUpdate;
    @FXML
    private Button btnSearchStudentUpdate;
    @FXML
    private TextField tfIdModify;
    @FXML
    private Button btnCancelModify;
    @FXML
    private TextField tfStudentIdModify;
    @FXML
    private TextField tfNameModify;
    @FXML
    private TextField tfLastNameModify;
    @FXML
    private TextField tfPhoneModify;
    @FXML
    private TextField tfEmailModify;
    @FXML
    private TextField tfAdrressModify;
    @FXML
    private DatePicker datePickerBirthdayModify;
    @FXML
    private ComboBox<?> comboBoxStudentModify;
    @FXML
    private BorderPane bp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnShowStudent(ActionEvent event) {
        
        Stage stage = new Stage();
        loadTable(stage);
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
        
        bp.setVisible(false);
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
    private void tfRemoveId(KeyEvent event) {
    }

    @FXML
    private void tfSearchCarrerUpdate(KeyEvent event) {
    }

    @FXML
    private void btnSearchStudentUpdate(ActionEvent event) {
    }

    @FXML
    private void btnCancelModify(ActionEvent event) {
        
    }
    private void loadTable(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("StudentsTable.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Estudiantes registrados");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
