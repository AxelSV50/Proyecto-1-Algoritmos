/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.FileManagementCareers;
import domain.Career;
import domain.DoublyLinkedList;
import domain.ListException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
    private Button btnAdd;
    @FXML
    private Text txtTitle;
    @FXML
    private Button btnDelete;
    @FXML
    private Pane panelModificar;
    @FXML
    private TextField tfIdCareerUpdate;
    @FXML
    private TextField tfDescriptionUpdate;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<Career> tableCareer;
    @FXML
    private TableColumn<Career, String> colIdCareer;
    @FXML
    private TableColumn<Career, String> colDescriptionCareer;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ac;
    @FXML
    private Pane panelDeleteCareer;
    @FXML
    private Text txtError;
    @FXML
    private Text txtAdded;
    @FXML
    private TextField tfAddCareerId;
    @FXML
    private TextField tfAddDescription;
    @FXML
    private TextField tfRemoveId;
    @FXML
    private TextField tfSearchCarrerUpdate;
    @FXML
    private Button btnSearchCarrerUpdate;
    @FXML
    private Text txtCareerSearchUpdate;
    @FXML
    private Button btnUpdateCancel;

    private DoublyLinkedList careersList = new DoublyLinkedList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        careersList = FileManagementCareers.getCareers();
        colDescriptionCareer.setCellValueFactory(new PropertyValueFactory<Career, String>("description"));
        colIdCareer.setCellValueFactory(new PropertyValueFactory<Career, String>("id"));
    }

    @FXML
    private void btnAddCareer(ActionEvent event) {
        cleanAll();
        panelAddCareer.setVisible(true);
        txtTitle.setText("Agregar carrera");
    }

    @FXML
    private void btnDeleteCareer(ActionEvent event) {

        cleanAll();
        panelDeleteCareer.setVisible(true);
        txtTitle.setText("Suprimir carrera");
    }

    @FXML
    private void btnModifyCareer(ActionEvent event) {

        cleanAll();
        panelModificar.setVisible(true);
        tfSearchCarrerUpdate.setVisible(true);
        btnSearchCarrerUpdate.setVisible(true);
        txtCareerSearchUpdate.setVisible(true);
        txtTitle.setText("Modificar carrera");
    }

    @FXML
    private void btnShowCareer(ActionEvent event) {

        cleanAll();
        tableCareer.setVisible(true);
        txtTitle.setText("Lista de carreras");

        ObservableList<Career> tableContent = FXCollections.observableArrayList();

        if (!careersList.isEmpty()) {

            try {
                for (int i = 1; i <= careersList.size(); i++) {

                    Career a = (Career) careersList.getNode(i).data;
                    tableContent.add(a);
                }
            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableCareer.setItems(tableContent);
    }

    @FXML
    private void btnBack(ActionEvent event) {

        bp.setVisible(false);
    }

    @FXML
    private void btnAdd(ActionEvent event) {

        if (!(tfAddCareerId.textProperty().getValue().equals("") || tfAddDescription.textProperty().getValue().equals(""))) {

            try {

                try {
                    if (!careersList.contains(new Career(Integer.parseInt(tfAddCareerId.textProperty().getValue()), ""))) {

                        txtError.setText("");
                        FileManagementCareers.add(Integer.parseInt(tfAddCareerId.textProperty().getValue()),
                                tfAddDescription.textProperty().getValue());

                        careersList = FileManagementCareers.getCareers();
                        tfAddCareerId.setText("");
                        tfAddDescription.setText("");
                        txtAdded.setText("Agregado con éxito");
                    } else {

                        txtError.setText("El código de carrera ya está en uso");
                        txtAdded.setText("");

                    }
                } catch (ListException ex) {
                    txtError.setText("");
                    FileManagementCareers.add(Integer.parseInt(tfAddCareerId.textProperty().getValue()),
                            tfAddDescription.textProperty().getValue());

                    careersList = FileManagementCareers.getCareers();
                    tfAddCareerId.setText("");
                    tfAddDescription.setText("");
                    txtAdded.setText("Agregado con éxito");
                }

            } catch (NumberFormatException e) {

                txtError.setText("El código de carrera debe ser numérico");
                txtAdded.setText("");
            }

        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");
        }
    }

    @FXML
    private void btnDelete(ActionEvent event) {

        if (!tfRemoveId.textProperty().getValue().equals("")) {

            try {

                Object element = new Career(Integer.parseInt(tfRemoveId.textProperty().getValue()), "");

                if (careersList.contains(element)) {

                    careersList.remove(element);
                    FileManagementCareers.overwriteCareersFile(careersList);
                    tfRemoveId.setText("");
                    txtError.setText("");
                    txtAdded.setText("Carrera eliminada correctamente");

                } else {

                    txtError.setText("Carrera no encontrada");
                    txtAdded.setText("");
                }

            } catch (ListException ex) {

                txtError.setText("No hay carreras agregadas");
                txtAdded.setText("");
                tfRemoveId.setText("");
            } catch (NumberFormatException ex) {

                txtError.setText("El código de carrera debe ser numérico");
                tfRemoveId.setText("");
            }
        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");
        }
    }

    @FXML
    private void btnUpdate(ActionEvent event) {

        if (!tfIdCareerUpdate.textProperty().getValue().equals("") && !tfDescriptionUpdate.textProperty().getValue().equals("")) {

            try {

                Object newElelement = new Career(Integer.parseInt(tfIdCareerUpdate.textProperty().getValue()), tfDescriptionUpdate.textProperty().getValue());
                Object oldElement = new Career(Integer.parseInt(tfSearchCarrerUpdate.textProperty().getValue()), "");

                if (!careersList.contains(newElelement)) {

                    careersList.modify(careersList.indexOf(oldElement), newElelement);
                    FileManagementCareers.overwriteCareersFile(careersList);
                    txtError.setText("");
                    tfIdCareerUpdate.setText("");
                    tfSearchCarrerUpdate.setText("");
                    tfDescriptionUpdate.setText("");
                    tfIdCareerUpdate.setDisable(true);
                    tfDescriptionUpdate.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnUpdateCancel.setDisable(true);
                    tfSearchCarrerUpdate.setDisable(false);
                    txtAdded.setText("Carrera modificada correctamente");

                } else {

                    if (tfSearchCarrerUpdate.textProperty().getValue().equals(tfIdCareerUpdate.textProperty().getValue())) {

                        careersList.modify(careersList.indexOf(oldElement), newElelement);
                        FileManagementCareers.overwriteCareersFile(careersList);
                        txtError.setText("");
                        tfIdCareerUpdate.setText("");
                        tfSearchCarrerUpdate.setText("");
                        tfDescriptionUpdate.setText("");
                        tfIdCareerUpdate.setDisable(true);
                        tfDescriptionUpdate.setDisable(true);
                        btnUpdate.setDisable(true);
                        btnUpdateCancel.setDisable(true);
                        tfSearchCarrerUpdate.setDisable(false);
                        txtAdded.setText("Carrera modificada correctamente");

                    } else {

                        txtError.setText("EL código del curso ya está en uso");
                        txtAdded.setText("");
                    }

                }

            } catch (ListException ex) {

                txtError.setText("No hay carreras agregadas");
                txtAdded.setText("");
                tfIdCareerUpdate.setText("");
                tfSearchCarrerUpdate.setText("");
                tfDescriptionUpdate.setText("");
                tfIdCareerUpdate.setDisable(true);
                tfDescriptionUpdate.setDisable(true);
                btnUpdate.setDisable(true);
                btnUpdateCancel.setDisable(true);
                tfSearchCarrerUpdate.setDisable(false);

            } catch (NumberFormatException ex) {

                txtError.setText("El código de carrera debe ser numérico");
            }
        } else {

            txtError.setText("Debe rellenar todos los recuadros");
        }
    }

    private void cleanAll() {

        panelAddCareer.setVisible(false);
        panelModificar.setVisible(false);
        panelDeleteCareer.setVisible(false);
        tableCareer.setVisible(false);
        tfSearchCarrerUpdate.setVisible(false);
        btnSearchCarrerUpdate.setVisible(false);
        txtCareerSearchUpdate.setVisible(false);
        tfRemoveId.setText("");
        tfAddCareerId.setText("");
        tfAddDescription.setText("");
        tfIdCareerUpdate.setText("");
        tfDescriptionUpdate.setText("");
        tfIdCareerUpdate.setDisable(true);
        tfDescriptionUpdate.setDisable(true);
        btnUpdate.setDisable(true);
        tfSearchCarrerUpdate.setDisable(false);
        tfSearchCarrerUpdate.setText("");
        txtTitle.setText("");
        txtError.setText("");
        txtAdded.setText("");

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

    @FXML
    private void tfAddCareerId(KeyEvent event) {
        txtAdded.setText("");
        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfAddCareerId.setEditable(false);

            } else {

                tfAddCareerId.setEditable(true);
            }

        } catch (Exception e) {

            tfAddCareerId.setEditable(true);

        }
    }

    @FXML
    private void tfRemoveId(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfRemoveId.setEditable(false);

            } else {

                tfRemoveId.setEditable(true);
            }

        } catch (Exception e) {

            tfRemoveId.setEditable(true);

        }
    }

    @FXML
    private void btnSearchCarrerUpdate(ActionEvent event) {

        if (!tfSearchCarrerUpdate.textProperty().getValue().equals("")) {

            try {

                Object element = new Career(Integer.parseInt(tfSearchCarrerUpdate.textProperty().getValue()), "");

                if (careersList.contains(element)) {

                    Career c = (Career) careersList.getNode(careersList.indexOf(element)).data;

                    tfIdCareerUpdate.setText(c.getId() + "");
                    tfDescriptionUpdate.setText(c.getDescription() + "");
                    txtError.setText("");
                    txtAdded.setText("");
                    tfIdCareerUpdate.setDisable(false);
                    tfDescriptionUpdate.setDisable(false);
                    btnUpdate.setDisable(false);
                    tfSearchCarrerUpdate.setDisable(true);
                    btnUpdateCancel.setDisable(false);
                } else {

                    txtError.setText("Carrera no encontrada");
                    txtAdded.setText("");
                    tfIdCareerUpdate.setText("");
                    tfDescriptionUpdate.setText("");
                    tfIdCareerUpdate.setDisable(true);
                    tfDescriptionUpdate.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnUpdateCancel.setDisable(true);
                }

            } catch (ListException ex) {

                txtError.setText("No hay carreras agregadas");
                txtAdded.setText("");
                tfSearchCarrerUpdate.setText("");
                tfIdCareerUpdate.setText("");
                tfDescriptionUpdate.setText("");
                tfIdCareerUpdate.setDisable(true);
                tfDescriptionUpdate.setDisable(true);
                btnUpdate.setDisable(true);
                btnUpdateCancel.setDisable(true);

            } catch (NumberFormatException ex) {

                txtError.setText("El código de carrera debe ser numérico");
                tfSearchCarrerUpdate.setText("");
                tfIdCareerUpdate.setText("");
                tfDescriptionUpdate.setText("");
                tfIdCareerUpdate.setDisable(true);
                tfDescriptionUpdate.setDisable(true);
                btnUpdate.setDisable(true);
                btnUpdateCancel.setDisable(true);

            }
        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");

        }

    }

    @FXML
    private void tfSearchCarrerUpdate(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfSearchCarrerUpdate.setEditable(false);

            } else {

                tfSearchCarrerUpdate.setEditable(true);
            }

        } catch (Exception e) {

            tfSearchCarrerUpdate.setEditable(true);

        }
    }

    @FXML
    private void tfIdCareerUpdate(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfIdCareerUpdate.setEditable(false);

            } else {

                tfIdCareerUpdate.setEditable(true);
            }

        } catch (Exception e) {

            tfIdCareerUpdate.setEditable(true);

        }
    }

    @FXML
    private void btnUpdateCancel(ActionEvent event) {

        txtError.setText("");
        txtAdded.setText("");
        tfIdCareerUpdate.setText("");
        tfDescriptionUpdate.setText("");
        tfSearchCarrerUpdate.setText("");
        tfSearchCarrerUpdate.setDisable(false);
        tfIdCareerUpdate.setDisable(true);
        tfDescriptionUpdate.setDisable(true);
        btnUpdate.setDisable(true);
        btnUpdateCancel.setDisable(true);
    }

}
