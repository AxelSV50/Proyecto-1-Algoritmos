/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.schedule;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ScheduleFXMLController implements Initializable {

    @FXML
    private AnchorPane ac;
    @FXML
    private Button btnBack;
    @FXML
    private Button asignSchedule;
    @FXML
    private Button display;
    @FXML
    private Text txtTitle;
    @FXML
    private TableView<?> tableAsignSchedule;
    @FXML
    private Text txtCourseSchedule;
    @FXML
    private ComboBox<String> cbDay1Schedule1;
    @FXML
    private ComboBox<String> cbDay2Schedule1;
    @FXML
    private ComboBox<String> cbHour1Schedule1;
    @FXML
    private ComboBox<String> cbHour2Schedule1;
    @FXML
    private ComboBox<String> cbDay1Schedule2;
    @FXML
    private ComboBox<String> cbDay2Schedule2;
    @FXML
    private ComboBox<String> cbHour1Schedule2;
    @FXML
    private ComboBox<String> cbHour2Schedule2;
    @FXML
    private Button btnSaveTimeTible;
    @FXML
    private Button btnCancelTimeTible;
    @FXML
    private Text txtError;
    @FXML
    private Text txtSuccess;
    @FXML
    private ComboBox<String> cbFinalHour1Schedule1;
    @FXML
    private ComboBox<String> cbFinalHour2Schedule1;
    @FXML
    private ComboBox<String> cbFinalHour1Schedule2;
    @FXML
    private ComboBox<String> cbFinalHour2Schedule2;
    @FXML
    private Button eliminatechedule;
    @FXML
    private Pane paneAddSchedule;
    @FXML
    private Pane paneDisplayAll;
    @FXML
    private TableView<?> tableCoursesEnrollment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        initComobox(cbDay1Schedule1);
        initComobox(cbDay2Schedule1);
        initComobox(cbDay1Schedule2);
        initComobox(cbDay2Schedule2);
        initComobox(cbHour1Schedule1);
        initComobox(cbHour2Schedule1);
        initComobox(cbHour1Schedule2);
        initComobox(cbHour2Schedule2);

    }

    private void cleanAll() {

        paneAddSchedule.setVisible(false);
        paneDisplayAll.setVisible(false);

        txtCourseSchedule.setText("");
        cbDay1Schedule1.setValue(null);
        cbDay2Schedule1.setValue(null);
        cbDay1Schedule2.setValue(null);
        cbDay2Schedule2.setValue(null);
        cbHour1Schedule1.setValue(null);
        cbHour2Schedule1.setValue(null);
        cbHour1Schedule2.setValue(null);
        cbHour2Schedule2.setValue(null);
        cbFinalHour1Schedule1.setValue(null);
        cbFinalHour2Schedule1.setValue(null);
        cbFinalHour2Schedule2.setValue(null);
        cbFinalHour1Schedule2.setValue(null);

        cbFinalHour1Schedule1.setDisable(true);
        cbFinalHour2Schedule1.setDisable(true);
        cbFinalHour2Schedule2.setDisable(true);
        cbFinalHour1Schedule2.setDisable(true);

    }

    private void initComobox(ComboBox<String> cb) {

        ObservableList<String> initHours = FXCollections.observableArrayList();
        initHours.addAll("8:00", "9:00", "10:00",
                "11:00", "13:00", "14:00",
                "15:00", "16:00", "17:00",
                "18:00", "19:00", "20:00");
        ObservableList<String> finalHours = FXCollections.observableArrayList();
        finalHours.addAll("8:50", "9:50", "10:50",
                "11:50", "13:50", "14:50",
                "15:50", "16:50", "17:50",
                "18:50", "19:50", "20:50");
        ObservableList<String> days = FXCollections.observableArrayList();
        days.addAll("L", "K", "M",
                "J", "V", "S");

        if (cb == cbDay1Schedule1 || cb == cbDay2Schedule1
                || cb == cbDay1Schedule2 || cb == cbDay2Schedule2) {

            cb.setItems(days);
        }
        if (cb == cbHour1Schedule1 || cb == cbHour1Schedule2
                || cb == cbHour2Schedule1 || cb == cbHour2Schedule2) {

            cb.setItems(initHours);
        }

        int value = 0;

        try {
            if (cb == cbFinalHour1Schedule1) {

                String array[] = cbHour1Schedule1.getValue().split(":");
                value = Integer.parseInt(array[0]);

            }
            if (cb == cbFinalHour2Schedule1) {

                String array[] = cbHour2Schedule1.getValue().split(":");
                value = Integer.parseInt(array[0]);

            }
            if (cb == cbFinalHour1Schedule2) {

                String array[] = cbHour1Schedule2.getValue().split(":");
                value = Integer.parseInt(array[0]);

            }
            if (cb == cbFinalHour2Schedule2) {

                String array[] = cbHour2Schedule2.getValue().split(":");
                value = Integer.parseInt(array[0]);

            }
        } catch (Exception e) {
        }

        if (cb == cbFinalHour1Schedule1 || cb == cbFinalHour2Schedule1
                || cb == cbFinalHour1Schedule2 || cb == cbFinalHour2Schedule2) {

            int value2;

            for (int i = 0; i < finalHours.size(); i++) {

                String array[] = finalHours.get(i).split(":");
                value2 = Integer.parseInt(array[0]);

                if (value == value2) {

                    ObservableList<String> aux = FXCollections.observableArrayList();

                    for (int j = i; j < finalHours.size(); j++) {

                        String array2[] = finalHours.get(j).split(":");
                        value2 = Integer.parseInt(array2[0]);

                        if ((value < 12 && value2 < 12) || value >= 13) {

                            aux.add(finalHours.get(j));

                        } else {
                            j = finalHours.size();
                        }
                    }
                    i = finalHours.size();
                    cb.setItems(aux);
                }
            }
        }

        cb.setVisibleRowCount(3);

    }

    @FXML
    private void btnBack(ActionEvent event) {

    }

    @FXML
    private void asignSchedule(ActionEvent event) {

        cleanAll();
        paneAddSchedule.setVisible(true);
    }

    @FXML
    private void display(ActionEvent event) {

        cleanAll();
        paneDisplayAll.setVisible(true);
    }

    @FXML
    private void btnSaveTimeTible(ActionEvent event) {
    }

    @FXML
    private void btnCancelTimeTible(ActionEvent event) {
    }

    @FXML
    private void cbHour1Schedule1(ActionEvent event) {

        if (cbHour1Schedule1 != null) {

            initComobox(cbFinalHour1Schedule1);
            cbFinalHour1Schedule1.setDisable(false);
        }
    }

    @FXML
    private void cbHour2Schedule1(ActionEvent event) {

        if (cbHour2Schedule1 != null) {

            initComobox(cbFinalHour2Schedule1);
            cbFinalHour2Schedule1.setDisable(false);
        }
    }

    @FXML
    private void cbHour1Schedule2(ActionEvent event) {

        if (cbHour1Schedule2 != null) {

            initComobox(cbFinalHour1Schedule2);
            cbFinalHour1Schedule2.setDisable(false);

        }
    }

    @FXML
    private void cbHour2Schedule2(ActionEvent event) {

        if (cbHour2Schedule2 != null) {

            initComobox(cbFinalHour2Schedule2);
            cbFinalHour2Schedule2.setDisable(false);
        }
    }

    @FXML
    private void eliminatechedule(ActionEvent event) {
    }

}
