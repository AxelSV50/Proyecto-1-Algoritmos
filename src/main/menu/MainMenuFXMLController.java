/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.menu;

import main.*;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main.security.SecurityFXMLController;

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
    private BorderPane bp;
    @FXML
    private AnchorPane anchorPaneMainMenu;
    @FXML
    private Button btnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        opcCarrer.setImage(new Image("/Images/book.png"));
        opcStudents.setImage(new Image("/Images/students.png"));
        opcReports.setImage(new Image("/Images/Report.png"));
        opcEnrollment.setImage(new Image("/Images/pen.png"));
        opcCourse.setImage(new Image("/Images/conocimiento.png"));
        opcSchedule.setImage(new Image("/Images/calendar.png"));
    }

    @FXML
    private void btnLogout(ActionEvent event) {

        bp.setVisible(false);
    }

    @FXML
    private void opcCareer(MouseEvent event) {
        
        loadPage("/main/career/CareerFXML");
    }

    @FXML
    private void opcStudents(MouseEvent event) {
        
        loadPage("/main/student/StudentFXML");
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

    private void loadPage(String page) {

        Parent root = null;

        try {

            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));

        } catch (IOException ex) {
            Logger.getLogger(SecurityFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bp.setRight(root);
     }
   
}
