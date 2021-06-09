/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.schedule;

import data.FileManagementTimeTable;
import domain.Career;
import domain.list.CircularDoublyLinkedList;
import domain.Course;
import domain.list.DoublyLinkedList;
import domain.list.ListException;
import domain.list.SinglyLinkedList;
import domain.TimeTable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import main.career.CareerFXMLController;

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
    private TableView<List<String>> tableAsignSchedule;
    @FXML
    private TableColumn<List<String>, String> courseIDAsignCol;
    @FXML
    private TableColumn<List<String>, String> courseDescripAsignCol;
    @FXML
    private TableColumn<List<String>, String> courseCreditsAsignCol;
    @FXML
    private TableColumn<List<String>, String> courseCareerAsign;
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
    private BorderPane bp;
    @FXML
    private ComboBox<String> cbPeriod;
    @FXML
    private TableView<List<String>> tableTime;
    @FXML
    private TableColumn<List<String>, String> timeTableIdCourseCol;
    @FXML
    private TableColumn<List<String>, String> timeTablePeriodCol;
    @FXML
    private TableColumn<List<String>, String> timeTableSchedule1Col;
    @FXML
    private TableColumn<List<String>, String> timeTableSchedule2Col;
    @FXML
    private TableColumn<List<String>, String> timeTableCareerCol;
    @FXML
    private Pane panelDeleteTimeTable;
    @FXML
    private TextField tfRemoveId;
    @FXML
    private Button btnDelete;
    @FXML
    private Text txtErrorDelete;
    @FXML
    private Text txtSuccessDelete;
    //Una vez que se precione una fila de la tabla de asignar horarios 
    //la información llega a esta variable, aquí está la información referente a ese curso seleccionado
    private List<String> selectedCourseData;

    private ObservableList<List<String>> coursesData;
    private DoublyLinkedList careersList = util.Utility.getCareersList();
    private CircularDoublyLinkedList courseList = util.Utility.getCoursesList();
    private SinglyLinkedList timeTablelList = util.Utility.getTimeTableList();

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
        cbPeriod.getItems().add("I-2021");
        cbPeriod.getItems().add("II-2021");
        initCourseTable();
        initTimeTable();
    }

    private void cleanAll() {

        paneAddSchedule.setVisible(false);
        paneDisplayAll.setVisible(false);
        panelDeleteTimeTable.setVisible(false);
        tfRemoveId.setText("");
        txtCourseSchedule.setText("");
        txtError.setText("");
        txtSuccess.setText("");
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
        cbPeriod.setValue(null);
        cbFinalHour1Schedule1.setDisable(true);
        cbFinalHour2Schedule1.setDisable(true);
        cbFinalHour2Schedule2.setDisable(true);
        cbFinalHour1Schedule2.setDisable(true);

    }

    private void initTimeTable() {
        timeTableIdCourseCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        timeTablePeriodCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        timeTableSchedule1Col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        timeTableSchedule2Col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        timeTableCareerCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(4)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        ObservableList<List<String>> tableContent = FXCollections.observableArrayList();

        if (!timeTablelList.isEmpty()) {

            try {
                for (int i = 1; i <= timeTablelList.size(); i++) {

                    List<String> arrayList = new ArrayList<>();

                    TimeTable t = (TimeTable) timeTablelList.getNode(i).data;

                    int c = courseList.indexOf(new Course(t.getCourseID(), "", 0, 0));
                    Course c1 = (Course) courseList.getNode(c).data;
                    int c2 = careersList.indexOf(new Career(c1.getCareerID(), ""));
                    Career c3 = (Career) careersList.getNode(c2).data;

                    //Agregamos todos los datos al arrayList
                    arrayList.add(t.getCourseID());
                    arrayList.add(t.getPeriod());
                    arrayList.add(t.getSchedule1());
                    arrayList.add(t.getSchedule2());
                    arrayList.add(c3.getDescription());
                    tableContent.add(arrayList);
                }
            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableTime.setItems(tableContent);
    }

    private void initCourseTable() {

        courseCareerAsign.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        courseCreditsAsignCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        courseDescripAsignCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        courseIDAsignCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });

        final ObservableList<List<String>> tableAsignScheduleCell = tableAsignSchedule.getSelectionModel().getSelectedItems();
        tableAsignScheduleCell.addListener(selectTableCourses);

        coursesData = FXCollections.observableArrayList();
        //Actualizo la lista para estar seguro de mostrar sólo cursos que no tengan horario
        timeTablelList = util.Utility.getTimeTableList();

        if (!courseList.isEmpty()) {

            try {
                for (int i = 1; i <= courseList.size(); i++) {

                    Course a = (Course) courseList.getNode(i).data;

                    //Si el curso no tiene horario lo agrega a la tabla
                    if (timeTablelList.isEmpty() || !timeTablelList.contains(new TimeTable(a.getId(), "", "", ""))) {
                        List<String> arrayList = new ArrayList<>();

                        int index = careersList.indexOf(new Career(a.getCareerID(), ""));
                        Career c = (Career) careersList.getNode(index).data;

                        //Agregamos todos los datos al arrayList
                        arrayList.add(a.getId());
                        arrayList.add(a.getName());
                        arrayList.add(a.getCredits() + "");
                        arrayList.add(c.getDescription());

                        coursesData.add(arrayList);
                    }

                }
            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableAsignSchedule.setItems(coursesData);
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

        bp.setVisible(false);
    }

    @FXML
    private void asignSchedule(ActionEvent event) {

        cleanAll();
        initCourseTable();
        txtTitle.setText("Mostar horarios");
        paneAddSchedule.setVisible(true);
    }

    @FXML
    private void display(ActionEvent event) {

        cleanAll();
        initTimeTable();
        txtTitle.setText("Lista de horarios");
        paneDisplayAll.setVisible(true);
    }

    @FXML
    private void btnSaveTimeTible(ActionEvent event) throws ListException {

        initCourseTable();

        if (selectedCourseData != null) {

            if (!(cbDay1Schedule1.getValue() == null || cbDay2Schedule1.getValue() == null
                    || cbDay1Schedule2.getValue() == null || cbDay2Schedule2.getValue() == null
                    || cbHour1Schedule1.getValue() == null || cbHour1Schedule2.getValue() == null
                    || cbHour2Schedule2.getValue() == null || cbHour2Schedule1.getValue() == null
                    || cbFinalHour1Schedule1.getValue() == null || cbFinalHour1Schedule2.getValue() == null
                    || cbFinalHour2Schedule1.getValue() == null || cbFinalHour2Schedule2.getValue() == null || cbPeriod.getValue() == null)) {

                boolean validSchedules = true;

                //Comparación de horas día 1 y 2 horario 1
                if (cbDay1Schedule1.getValue().equals(cbDay2Schedule1.getValue())) {

                    String[] array1 = cbHour1Schedule1.getValue().split(":");
                    String[] array2 = cbFinalHour1Schedule1.getValue().split(":");
                    String[] array3 = cbHour2Schedule1.getValue().split(":");
                    String[] array4 = cbFinalHour2Schedule1.getValue().split(":");

                    int init1 = Integer.parseInt(array1[0]);
                    int final1 = Integer.parseInt(array2[0]);
                    int init2 = Integer.parseInt(array3[0]);
                    int final2 = Integer.parseInt(array4[0]);

                    if (init1 >= init2 && init1 <= final2) {

                        validSchedules = false;
                    } else if (init2 >= init1 && init2 <= final1) {
                        validSchedules = false;
                    } else if (final1 >= init2 && final1 <= final2) {
                        validSchedules = false;
                    } else if (final2 >= init1 && final2 <= final1) {
                        validSchedules = false;
                    }

                }
                //Comparación de horas día 1 y 2 horario 2
                if (cbDay2Schedule2.getValue().equals(cbDay1Schedule2.getValue())) {

                    String[] array1 = cbHour1Schedule2.getValue().split(":");
                    String[] array2 = cbFinalHour1Schedule2.getValue().split(":");
                    String[] array3 = cbHour2Schedule2.getValue().split(":");
                    String[] array4 = cbFinalHour2Schedule2.getValue().split(":");

                    int init1 = Integer.parseInt(array1[0]);
                    int final1 = Integer.parseInt(array2[0]);
                    int init2 = Integer.parseInt(array3[0]);
                    int final2 = Integer.parseInt(array4[0]);

                    if (init1 >= init2 && init1 <= final2) {
                        validSchedules = false;
                    } else if (init2 >= init1 && init2 <= final1) {
                        validSchedules = false;
                    } else if (final1 >= init2 && final1 <= final2) {
                        validSchedules = false;
                    } else if (final2 >= init1 && final2 <= final1) {
                        validSchedules = false;
                    }
                }
                //Comparación de horas día 1 horario 1 y día 1 horario 2

                if (cbDay1Schedule1.getValue().equals(cbDay1Schedule2.getValue())) {

                    String[] array1 = cbHour1Schedule2.getValue().split(":");
                    String[] array2 = cbFinalHour1Schedule2.getValue().split(":");
                    String[] array3 = cbHour1Schedule1.getValue().split(":");
                    String[] array4 = cbFinalHour1Schedule1.getValue().split(":");

                    int init1 = Integer.parseInt(array1[0]);
                    int final1 = Integer.parseInt(array2[0]);
                    int init2 = Integer.parseInt(array3[0]);
                    int final2 = Integer.parseInt(array4[0]);

                    if (init1 >= init2 && init1 <= final2) {
                        validSchedules = false;
                    } else if (init2 >= init1 && init2 <= final1) {
                        validSchedules = false;
                    } else if (final1 >= init2 && final1 <= final2) {
                        validSchedules = false;
                    } else if (final2 >= init1 && final2 <= final1) {
                        validSchedules = false;
                    }
                }
                //Comparación de horas día 1 horario 1 y día 2 horario 2
                if (cbDay1Schedule1.getValue().equals(cbDay2Schedule2.getValue())) {

                    String[] array1 = cbHour2Schedule2.getValue().split(":");
                    String[] array2 = cbFinalHour2Schedule2.getValue().split(":");
                    String[] array3 = cbHour1Schedule1.getValue().split(":");
                    String[] array4 = cbFinalHour1Schedule1.getValue().split(":");

                    int init1 = Integer.parseInt(array1[0]);
                    int final1 = Integer.parseInt(array2[0]);
                    int init2 = Integer.parseInt(array3[0]);
                    int final2 = Integer.parseInt(array4[0]);

                    if (init1 >= init2 && init1 <= final2) {
                        validSchedules = false;
                    } else if (init2 >= init1 && init2 <= final1) {
                        validSchedules = false;
                    } else if (final1 >= init2 && final1 <= final2) {
                        validSchedules = false;
                    } else if (final2 >= init1 && final2 <= final1) {
                        validSchedules = false;
                    }
                }
                //Comparación de horas día 2 horario 1 y día 1 horario 2
                 if (cbDay2Schedule1.getValue().equals(cbDay1Schedule2.getValue())) {

                    String[] array1 = cbHour1Schedule2.getValue().split(":");
                    String[] array2 = cbFinalHour1Schedule2.getValue().split(":");
                    String[] array3 = cbHour2Schedule1.getValue().split(":");
                    String[] array4 = cbFinalHour2Schedule1.getValue().split(":");

                    int init1 = Integer.parseInt(array1[0]);
                    int final1 = Integer.parseInt(array2[0]);
                    int init2 = Integer.parseInt(array3[0]);
                    int final2 = Integer.parseInt(array4[0]);

                    if (init1 >= init2 && init1 <= final2) {
                        validSchedules = false;
                    } else if (init2 >= init1 && init2 <= final1) {
                        validSchedules = false;
                    } else if (final1 >= init2 && final1 <= final2) {
                        validSchedules = false;
                    } else if (final2 >= init1 && final2 <= final1) {
                        validSchedules = false;
                    }
                }
                //Comparación de horas día 2 horario 1 y día 2 horario 2
               if (cbDay2Schedule1.getValue().equals(cbDay2Schedule2.getValue())) {

                    String[] array1 = cbHour2Schedule2.getValue().split(":");
                    String[] array2 = cbFinalHour2Schedule2.getValue().split(":");
                    String[] array3 = cbHour2Schedule1.getValue().split(":");
                    String[] array4 = cbFinalHour2Schedule1.getValue().split(":");

                    int init1 = Integer.parseInt(array1[0]);
                    int final1 = Integer.parseInt(array2[0]);
                    int init2 = Integer.parseInt(array3[0]);
                    int final2 = Integer.parseInt(array4[0]);

                    if (init1 >= init2 && init1 <= final2) {
                        validSchedules = false;
                    } else if (init2 >= init1 && init2 <= final1) {
                        validSchedules = false;
                    } else if (final1 >= init2 && final1 <= final2) {
                        validSchedules = false;
                    } else if (final2 >= init1 && final2 <= final1) {
                        validSchedules = false;
                    }
                }
                if (validSchedules) {

                    //Aquí se crean los objetos TimeTable que se van a agregar a la lista y archivos
                    if (timeTablelList.isEmpty() || !timeTablelList.contains(new TimeTable(txtCourseSchedule.textProperty().getValue(), "", "", ""))) {

                        //Si el usuario no selecciona nada, el combo devuelve null
                        txtError.setText("");
                        //Agrega el horario al archivo 
                        FileManagementTimeTable.add(txtCourseSchedule.textProperty().getValue(), (String) cbPeriod.getValue(), (String) cbDay1Schedule1.getValue() + "-" + (String) cbHour1Schedule1.getValue() + "-" + (String) cbFinalHour1Schedule1.getValue() + "-" + (String) cbDay2Schedule1.getValue() + "-" + (String) cbHour2Schedule1.getValue() + "-" + (String) cbFinalHour2Schedule1.getValue(),
                                (String) cbDay1Schedule2.getValue() + "-" + (String) cbHour1Schedule2.getValue() + "-" + (String) cbFinalHour1Schedule2.getValue() + "-" + (String) cbDay2Schedule2.getValue() + "-" + (String) cbHour2Schedule2.getValue() + "-" + (String) cbFinalHour2Schedule2.getValue(), FileManagementTimeTable.getNameFileTime());
                        //Actualiza la lista para que salga la carrera nueva
                        timeTablelList = util.Utility.getTimeTableList();

                    }

                    //Esto debe ir de último
                    selectedCourseData = null;
                    cleanAll();
                    initCourseTable();
                    paneAddSchedule.setVisible(true);
                    txtError.setText("");
                    txtSuccess.setText("Guardado con éxito");
                } else {

                    txtError.setText("Error: Existe un choque de horarios");
                    txtSuccess.setText("");
                }

            } else {

                txtError.setText("Debe rellenar todo lo que se le solicita");
                txtSuccess.setText("");
            }
        } else {
            txtError.setText("Primero debe seleccionar un curso");
            txtSuccess.setText("");
        }

    }

    @FXML
    private void btnCancelTimeTible(ActionEvent event
    ) {
        cleanAll();
        initCourseTable();
        paneAddSchedule.setVisible(true);
        txtError.setText("");
    }

    @FXML
    private void cbHour1Schedule1(ActionEvent event
    ) {

        if (cbHour1Schedule1 != null) {

            initComobox(cbFinalHour1Schedule1);
            cbFinalHour1Schedule1.setDisable(false);
        }
    }

    @FXML
    private void cbHour2Schedule1(ActionEvent event
    ) {

        if (cbHour2Schedule1 != null) {

            initComobox(cbFinalHour2Schedule1);
            cbFinalHour2Schedule1.setDisable(false);
        }
    }

    @FXML
    private void cbHour1Schedule2(ActionEvent event
    ) {

        if (cbHour1Schedule2 != null) {

            initComobox(cbFinalHour1Schedule2);
            cbFinalHour1Schedule2.setDisable(false);

        }
    }

    @FXML
    private void cbHour2Schedule2(ActionEvent event
    ) {

        if (cbHour2Schedule2 != null) {

            initComobox(cbFinalHour2Schedule2);
            cbFinalHour2Schedule2.setDisable(false);
        }
    }

    @FXML
    private void eliminatechedule(ActionEvent event) {
        cleanAll();
        panelDeleteTimeTable.setVisible(true);
        txtTitle.setText("Elimina horario");
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        if (!tfRemoveId.textProperty().getValue().equals("")) {

            try {

                //Busca si el elemento está
                Object element = new TimeTable(tfRemoveId.textProperty().getValue(), "", "", "");

                if (timeTablelList.contains(element)) {

                    timeTablelList.remove(element);
                    //Reescribe el archivo
                    FileManagementTimeTable.overwriteTimetableFile(timeTablelList);
                    tfRemoveId.setText("");
                    txtErrorDelete.setText("");
                    txtSuccessDelete.setText("Horario eliminado correctamente");

                } else {

                    txtErrorDelete.setText("Horario no encontrado");
                    txtSuccessDelete.setText("");
                }

            } catch (ListException ex) {

                txtErrorDelete.setText("No hay horarios agregados");
                txtSuccessDelete.setText("");
                tfRemoveId.setText("");
            }

        } else {

            txtErrorDelete.setText("Debe rellenar todos los recuadros");
            txtSuccessDelete.setText("");
        }
    }
    private final ListChangeListener<List<String>> selectTableCourses
            = new ListChangeListener<List<String>>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends List<String>> c) {
            setSelectedCourse();
        }
    };

    public List<String> getSelectedStudentsTable() {
        if (courseList != null) {
            List<List<String>> tabla = tableAsignSchedule.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final List<String> competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    private void setSelectedCourse() {
        final List<String> course = getSelectedStudentsTable();

        if (course != null) {

            selectedCourseData = course;
            txtCourseSchedule.setText(selectedCourseData.get(0));
        }

    }

    @FXML
    private void tfRemoveId(KeyEvent event) {
        
        txtSuccess.setText("");
        txtSuccessDelete.setText("");
    }

}
