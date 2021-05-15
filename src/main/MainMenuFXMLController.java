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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class MainMenuFXMLController implements Initializable {

    @FXML
    private ImageView opcCarrer;
    @FXML
    private ImageView opcStudents;
    @FXML
    private ImageView opcSchedule;
    @FXML
    private ImageView opcReports;
    @FXML
    private ImageView opcCourse;
    @FXML
    private ImageView opcEnrollment;
    @FXML
    private Button btnLogout;
    @FXML
    private AnchorPane anchorPaneMainMenu;
    @FXML
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void opcCarrer(MouseEvent event) {
    }

    @FXML
    private void opcStudents(MouseEvent event) {
    }

    @FXML
    private void opcSchedule(MouseEvent event) {
    }

    @FXML
    private void opcReports(MouseEvent event) {
    }

    @FXML
    private void opcCourse(MouseEvent event) {
    }

    @FXML
    private void opcEnrollment(MouseEvent event) {
    }

    @FXML
    private void btnLogout(ActionEvent event) {
        
        bp.setVisible(false);
    }
    
}
