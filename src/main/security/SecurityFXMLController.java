/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.security;

import domain.list.CircularLinkedList;
import domain.Security;
import data.FileManagementUsers;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class SecurityFXMLController implements Initializable {

    @FXML
    private Button btnAdmin;
    @FXML
    private Button btnStudent;
    @FXML
    private Pane panelLoginStudent;
    @FXML
    private TextField tfStudentID;
    @FXML
    private PasswordField tfPasswordStudent;
    @FXML
    private Pane panelLoginAdmin;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSeePasswordStudent;
    @FXML
    private TextField tfShowPasswordStudent;
    @FXML
    private TextField tfShowPasswordAdmin;
    @FXML
    private Button btnSeePasswordAdmin;
    @FXML
    private TextField tfUserAdmin;
    @FXML
    private PasswordField tfPasswordAdmin;
    @FXML
    private Text txtErrorLogin;
    @FXML
    private BorderPane bp;

    private CircularLinkedList infoUsers = new CircularLinkedList();
    private boolean showPassword = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnAdmin(ActionEvent event) {

        cleanAll();
        panelLoginAdmin.setVisible(true);

    }

    @FXML
    private void btnStudent(ActionEvent event) {

        cleanAll();
        panelLoginStudent.setVisible(true);
    }

    @FXML
    private void btnLogin(ActionEvent event) {

        if (panelLoginAdmin.isVisible()) {

            if (!(tfUserAdmin.textProperty().getValue().equals("")
                    || tfPasswordAdmin.textProperty().getValue().equals("")) && tfPasswordAdmin.isVisible()) {

                infoUsers = FileManagementUsers.getDataLogin(FileManagementUsers.getNameFileAdmin());

                Security s = new Security(tfUserAdmin.textProperty().getValue(), tfPasswordAdmin.textProperty().getValue());

                if (s.getAccess(null, infoUsers).equals("ADMIN")) {

                    //Aquí se carga el menu principal con acceso total
                    tfUserAdmin.setText("");
                    tfPasswordAdmin.setText("");
                    tfShowPasswordAdmin.setText("");
                    tfStudentID.setText("");
                    tfPasswordStudent.setText("");
                    tfShowPasswordStudent.setText("");
                    txtErrorLogin.setVisible(false);
                    loadPage("/main/menu/MainMenuFXML");
                } else {

                    txtErrorLogin.setVisible(true);
                    txtErrorLogin.setText("Usuario y/o contraseña incorrectos");
                }
            } else if (!(tfUserAdmin.textProperty().getValue().equals("")
                    || tfShowPasswordAdmin.textProperty().getValue().equals("")) && tfShowPasswordAdmin.isVisible()) {

                infoUsers = FileManagementUsers.getDataLogin(FileManagementUsers.getNameFileAdmin());
                Security s = new Security(tfUserAdmin.textProperty().getValue(), tfShowPasswordAdmin.textProperty().getValue());

                if (s.getAccess(null, infoUsers).equals("ADMIN")) {

                    //Aquí se carga el menu principal con acceso total
                    tfUserAdmin.setText("");
                    tfPasswordAdmin.setText("");
                    tfShowPasswordAdmin.setText("");
                    tfStudentID.setText("");
                    tfPasswordStudent.setText("");
                    tfShowPasswordStudent.setText("");
                    txtErrorLogin.setVisible(false);
                    loadPage("/main/menu/MainMenuFXML");
                } else {

                    txtErrorLogin.setVisible(true);
                    txtErrorLogin.setText("Usuario y/o contraseña incorrectos");
                }
            } else {

                txtErrorLogin.setVisible(true);
                txtErrorLogin.setText("Debe rellenar todos los recuadros");
            }

        } else {

            if (!(tfStudentID.textProperty().getValue().equals("")
                    || tfPasswordStudent.textProperty().getValue().equals("")) && tfPasswordStudent.isVisible()) {

                infoUsers = FileManagementUsers.getDataLogin(FileManagementUsers.getNameFileStudents());
                Security s = new Security(tfStudentID.textProperty().getValue(), tfPasswordStudent.textProperty().getValue());

                if (s.getAccess(infoUsers, null).equals("STUDENT")) {

                    tfUserAdmin.setText("");
                    tfPasswordAdmin.setText("");
                    tfShowPasswordAdmin.setText("");
                    tfStudentID.setText("");
                    tfPasswordStudent.setText("");
                    tfShowPasswordStudent.setText("");
                    txtErrorLogin.setVisible(false);
                    loadPage("/main/reports/ReportsFXML");

                } else {

                    txtErrorLogin.setVisible(true);
                    txtErrorLogin.setText("Usuario y/o contraseña incorrectos");
                }

            } else if (!(tfStudentID.textProperty().getValue().equals("")
                    || tfShowPasswordStudent.textProperty().getValue().equals("")) && tfShowPasswordStudent.isVisible()) {

                infoUsers = FileManagementUsers.getDataLogin(FileManagementUsers.getNameFileStudents());
                Security s = new Security(tfStudentID.textProperty().getValue(), tfShowPasswordStudent.textProperty().getValue());

                if (s.getAccess(infoUsers, null).equals("STUDENT")) {
                    tfUserAdmin.setText("");
                    tfPasswordAdmin.setText("");
                    tfShowPasswordAdmin.setText("");
                    tfStudentID.setText("");
                    tfPasswordStudent.setText("");
                    tfShowPasswordStudent.setText("");
                    txtErrorLogin.setVisible(false);
                    loadPage("/main/reports/ReportsFXML");

                } else {

                    txtErrorLogin.setVisible(true);
                    txtErrorLogin.setText("Usuario y/o contraseña incorrectos");
                }
            } else {

                txtErrorLogin.setVisible(true);
                txtErrorLogin.setText("Debe rellenar todos los recuadros");
            }
        }
    }

    @FXML
    private void btnSeePasswordStudent(ActionEvent event) {

        if (showPassword) {

            tfPasswordStudent.setVisible(false);
            tfShowPasswordStudent.setVisible(true);
            tfShowPasswordStudent.setText(tfPasswordStudent.getText());
            showPassword = false;
        } else {

            tfPasswordStudent.setVisible(true);
            tfPasswordStudent.setText(tfShowPasswordStudent.getText());
            tfShowPasswordStudent.setVisible(false);
            showPassword = true;
        }

    }

    @FXML
    private void btnSeePasswordAdmin(ActionEvent event) {

        if (showPassword) {

            tfPasswordAdmin.setVisible(false);
            tfShowPasswordAdmin.setVisible(true);
            tfShowPasswordAdmin.setText(tfPasswordAdmin.getText());
            showPassword = false;
        } else {

            tfPasswordAdmin.setVisible(true);
            tfShowPasswordAdmin.setVisible(false);
            tfPasswordAdmin.setText(tfShowPasswordAdmin.getText());
            showPassword = true;
        }
    }

    private void cleanAll() {

        tfUserAdmin.setText("");
        tfPasswordAdmin.setText("");
        tfShowPasswordAdmin.setText("");
        tfStudentID.setText("");
        tfPasswordStudent.setText("");
        tfShowPasswordStudent.setText("");
        showPassword = true;
        tfShowPasswordStudent.setVisible(false);
        tfShowPasswordAdmin.setVisible(false);
        tfPasswordStudent.setVisible(true);
        tfPasswordAdmin.setVisible(true);
        panelLoginAdmin.setVisible(false);
        panelLoginStudent.setVisible(false);
        txtErrorLogin.setVisible(false);
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
