/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.course;

import data.FileManagementCourses;
import domain.Career;
import domain.list.CircularDoublyLinkedList;
import domain.Course;
import domain.list.DoublyLinkedList;
import domain.list.ListException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import main.career.CareerFXMLController;
import main.student.StudentFXMLController;

/**
 * FXML Controller class
 *
 * @author carlo
 */
public class CourseFXMLController implements Initializable {

    @FXML
    private Button btnBack;
    @FXML
    private Button btnAddCourse;
    @FXML
    private Button btnDeleteCourse;
    @FXML
    private Button btnModifyCourse;
    @FXML
    private Button btnShowCourse;
    @FXML
    private Pane panelAddCourse;
    @FXML
    private TextField tfAddCourseId;
    @FXML
    private TextField tfAddNameCourse;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField tfAddCreditsCourse;
    @FXML
    private Text txtTitle;
    @FXML
    private ComboBox<String> comboBoxCourseAdd;
    @FXML
    private Text txtAdded;
    @FXML
    private Text txtError;
    @FXML
    private Pane panelDeleteCourse;
    @FXML
    private TextField tfRemoveId;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<List<String>> courseTable;
    @FXML
    private TableColumn<List<String>, String> colIdCourse;
    @FXML
    private TableColumn<List<String>, String> colNameCourse;
    @FXML
    private TableColumn<List<String>, String> colCreditsCourse;
    @FXML
    private TableColumn<List<String>, String> colDescriptionCarreer;
    @FXML
    private AnchorPane ac;
    @FXML
    private Pane bp;
    @FXML
    private Pane panelModifyCourse;
    @FXML
    private TextField tfSiglaModify;
    @FXML
    private TextField tfNameCourseModify;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfModifyCreditsCourse;
    @FXML
    private ComboBox<String> comboBoxCourseModify;
    @FXML
    private Button btnCancelModify;
    @FXML
    private TextField tfSearchCourserUpdate;
    @FXML
    private Button btnSearchCourseUpdate;

    private DoublyLinkedList careersList = util.Utility.getCareersList();
    private CircularDoublyLinkedList courseList = util.Utility.getCoursesList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        courseList = util.Utility.getCoursesList();
        initComboBox(comboBoxCourseAdd);
        initComboBox(comboBoxCourseModify);

        colIdCourse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colNameCourse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colCreditsCourse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colDescriptionCarreer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });

        cleanAll();
        courseTable.setVisible(true);
        txtTitle.setText("Lista de cursos");
        initTable();

    }

    private void initTable() {
        
        ObservableList<List<String>> tableContent = FXCollections.observableArrayList();

        if (!courseList.isEmpty()) {

            try {
                for (int i = 1; i <= courseList.size(); i++) {

                    List<String> arrayList = new ArrayList<>();

                    Course a = (Course) courseList.getNode(i).data;

                    int index = careersList.indexOf(new Career(a.getCareerID(), ""));
                    Career c = (Career) careersList.getNode(index).data;

                    //Agregamos todos los datos al arrayList
                    arrayList.add(a.getId());
                    arrayList.add(a.getName());
                    arrayList.add(a.getCredits() + "");
                    arrayList.add(c.getDescription());

                    tableContent.add(arrayList);
                }
            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        courseTable.setItems(tableContent);
    }

    private void initComboBox(ComboBox<String> comboBox) {
        
        ObservableList tableContent = FXCollections.observableArrayList();

        try {
            careersList.sort();
        } catch (ListException ex) {
            Logger.getLogger(StudentFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!careersList.isEmpty()) {

            try {
                for (int i = 1; i <= careersList.size(); i++) {

                    Career a = (Career) careersList.getNode(i).data;
                    tableContent.add(a.getId() + "-" + a.getDescription());
                }
            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        comboBox.setItems(tableContent);
        comboBox.setVisibleRowCount(3);
        if (comboBox == comboBoxCourseModify) {

        } else {
            comboBox.setPromptText("Seleccione");

        }

    }

    @FXML
    private void btnBack(ActionEvent event) {

        bp.setVisible(false);
    }

    @FXML
    private void btnAddCourse(ActionEvent event) {
        cleanAll();
        panelAddCourse.setVisible(true);
        txtTitle.setText("Agregar curso");
    }

    @FXML
    private void btnDeleteCourse(ActionEvent event) {
        cleanAll();
        panelDeleteCourse.setVisible(true);
        txtTitle.setText("Suprimir curso");
    }

    @FXML
    private void btnModifyCourse(ActionEvent event) {

        cleanAll();
        panelModifyCourse.setVisible(true);
        txtTitle.setText("Modificar curso");
    }

    @FXML
    private void btnAdd(ActionEvent event) {

        if (!(tfAddCourseId.textProperty().getValue().equals("") || tfAddNameCourse.textProperty().getValue().equals("") || tfAddCreditsCourse.textProperty().getValue().equals("") || comboBoxCourseAdd.getValue() == null)) {
            txtError.setText("");

            try {
                if (courseList.isEmpty() || !courseList.contains(new Course(tfAddCourseId.textProperty().getValue(), "", 0, 0))) {
                    String idCareer = comboBoxCourseAdd.getValue();
                    //Si el usuario no selecciona nada, el combo devuelve null
                    
                    String[] array = idCareer.split("-");
                    txtError.setText("");
                    //Agrega la carrera al archivo 
                    FileManagementCourses.add(tfAddCourseId.textProperty().getValue(), tfAddNameCourse.textProperty().getValue(), Integer.parseInt(tfAddCreditsCourse.textProperty().getValue()), Integer.parseInt(array[0]), FileManagementCourses.getNameFileCourses());
                    //Actualiza la lista para que salga la carrera nueva
                    careersList = util.Utility.getCareersList();
                    //Setter los textField
                    tfAddCourseId.setText("");
                    tfAddNameCourse.setText("");
                    tfAddCreditsCourse.setText("");
                    //Le dice al usuario que se agrego
                    txtAdded.setText("Agregado con éxito");
                } else {

                    //Si existe lanza este mensaje al usuario
                    txtError.setText("La sigla del curso ya está en uso");
                    txtAdded.setText("");

                }

            } catch (NumberFormatException e) {

                txtError.setText("Los créditos deben ser numéricos");
                txtAdded.setText("");

            } catch (ListException e) {

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

                //Busca si el elemento está
                Object element = new Course(tfRemoveId.textProperty().getValue(), "", 0, 0);

                if (courseList.contains(element)) {

                    courseList.remove(element);
                    //Reescribe el archivo
                    FileManagementCourses.overwriteCourseFile(courseList);
                    tfRemoveId.setText("");
                    txtError.setText("");
                    txtAdded.setText("Curso eliminado correctamente");

                } else {

                    txtError.setText("Curso no encontrado");
                    txtAdded.setText("");
                }

            } catch (ListException ex) {

                txtError.setText("No hay cursos agregados");
                txtAdded.setText("");
                tfRemoveId.setText("");
            }

        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");
        }

    }

    @FXML
    private void btnShowCourse(ActionEvent event) {
        courseList = util.Utility.getCoursesList();
        cleanAll();
        courseTable.setVisible(true);
        txtTitle.setText("Lista de cursos");
        initTable();
    }

    private void cleanAll() {
        courseTable.setVisible(false);
        panelAddCourse.setVisible(false);
        panelDeleteCourse.setVisible(false);
        panelModifyCourse.setVisible(false);
        tfAddCourseId.setText("");
        tfAddCreditsCourse.setText("");
        tfAddNameCourse.setText("");
        tfRemoveId.setText("");
        txtAdded.setText("");
        txtTitle.setText("");
        txtError.setText("");
    }

    @FXML
    private void tfAddCourseId(KeyEvent event) {

    }

    @FXML
    private void tfAddNameCourse(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {

                tfAddNameCourse.setEditable(false);

            } else {

                tfAddNameCourse.setEditable(true);
            }

        } catch (Exception e) {

            tfAddNameCourse.setEditable(true);

        }
    }

    @FXML
    private void tfAddCreditsCourse(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isDigit(c)) {

                tfAddCreditsCourse.setEditable(false);

            } else {

                tfAddCreditsCourse.setEditable(true);
            }

        } catch (Exception e) {

            tfAddCreditsCourse.setEditable(true);

        }
    }

    @FXML
    private void tfRemoveId(KeyEvent event) {

        txtAdded.setText("");
    }

    @FXML
    private void btnUpdate(ActionEvent event) {

        if (!tfSiglaModify.textProperty().getValue().equals("") && !tfNameCourseModify.textProperty().getValue().equals("")
                && !tfModifyCreditsCourse.textProperty().getValue().equals("") && comboBoxCourseModify.getValue() != null) {

            try {

                String array[] = comboBoxCourseModify.getValue().split("-");
                Object newElement = new Course(tfSiglaModify.getText(), tfNameCourseModify.getText(), Integer.parseInt(tfModifyCreditsCourse.getText()), Integer.parseInt(array[0]));

                Object oldElement = new Course(tfSiglaModify.getText(), "", 0, 0);

                courseList.modify(courseList.indexOf(oldElement), newElement);
                FileManagementCourses.overwriteCourseFile(courseList);
                txtError.setText("");
                tfSiglaModify.setText("");
                tfNameCourseModify.setText("");
                tfModifyCreditsCourse.setText("");
                comboBoxCourseModify.setValue("");
                tfSiglaModify.setDisable(true);
                tfNameCourseModify.setDisable(true);
                btnUpdate.setDisable(true);
                tfSearchCourserUpdate.setDisable(false);
                tfModifyCreditsCourse.setDisable(true);
                btnCancelModify.setDisable(true);
                btnSearchCourseUpdate.setDisable(false);
                comboBoxCourseModify.setDisable(true);
                txtAdded.setText("Curso modificado correctamente");

            } catch (ListException ex) {

                txtError.setText("No hay cursos agregados");
                tfSiglaModify.setText("");
                tfNameCourseModify.setText("");
                tfModifyCreditsCourse.setText("");
                comboBoxCourseModify.setValue("");
                tfSiglaModify.setDisable(true);
                tfNameCourseModify.setDisable(true);
                btnUpdate.setDisable(true);
                tfSearchCourserUpdate.setDisable(false);
                tfModifyCreditsCourse.setDisable(true);
                btnCancelModify.setDisable(true);
                btnSearchCourseUpdate.setDisable(false);
                comboBoxCourseModify.setDisable(true);
                txtAdded.setText("");

            } catch (NumberFormatException ex) {

                txtError.setText("Los créditos deben ser numéricos");
            }
        } else {

            txtError.setText("Debe rellenar todos los recuadros");
        }
    }

    @FXML
    private void btnCancelModify(ActionEvent event) {

        txtError.setText("");
        tfSearchCourserUpdate.setText("");
        tfSiglaModify.setText("");
        tfNameCourseModify.setText("");
        tfModifyCreditsCourse.setText("");
        comboBoxCourseModify.setValue("");
        txtAdded.setText("");
        tfSiglaModify.setDisable(true);
        tfModifyCreditsCourse.setDisable(true);
        tfNameCourseModify.setDisable(true);
        btnUpdate.setDisable(true);
        tfSearchCourserUpdate.setDisable(false);
        btnCancelModify.setDisable(true);
        btnSearchCourseUpdate.setDisable(false);
        comboBoxCourseModify.setDisable(true);
    }

    @FXML
    private void btnSearchCourseUpdate(ActionEvent event) {

        if (!tfSearchCourserUpdate.textProperty().getValue().equals("")) {

            try {

                Course element = new Course(tfSearchCourserUpdate.getText(), "", 0, 0);

                if (courseList.contains(element)) {

                    Course c = (Course) courseList.getNode(courseList.indexOf(element)).data;
                    int index = careersList.indexOf(new Career(c.getCareerID(), ""));
                    Career c2 = (Career) careersList.getNode(index).data;

                    tfSiglaModify.setText(c.getId());
                    tfNameCourseModify.setText(c.getName());
                    tfModifyCreditsCourse.setText(c.getCredits() + "");
                    comboBoxCourseModify.setValue(c.getCareerID() + "-" + c2.getDescription());
                    txtError.setText("");
                    txtAdded.setText("");
                    tfSiglaModify.setDisable(true);
                    tfNameCourseModify.setDisable(false);
                    btnUpdate.setDisable(false);
                    tfSearchCourserUpdate.setText("");
                    tfModifyCreditsCourse.setDisable(false);
                    btnCancelModify.setDisable(false);
                    btnSearchCourseUpdate.setDisable(true);
                    comboBoxCourseModify.setDisable(false);
                } else {

                    txtError.setText("Curso no encontrado");
                    tfSiglaModify.setText("");
                    tfNameCourseModify.setText("");
                    tfModifyCreditsCourse.setText("");
                    comboBoxCourseModify.setValue("");
                    txtAdded.setText("");
                    tfSiglaModify.setDisable(true);
                    tfNameCourseModify.setDisable(true);
                    btnUpdate.setDisable(true);
                    tfSearchCourserUpdate.setDisable(false);
                    tfModifyCreditsCourse.setDisable(true);
                    btnCancelModify.setDisable(true);
                    btnSearchCourseUpdate.setDisable(false);
                    comboBoxCourseModify.setDisable(true);
                }

            } catch (ListException ex) {

                txtError.setText("No hay cursos agregados");
                tfSiglaModify.setText("");
                tfNameCourseModify.setText("");
                tfModifyCreditsCourse.setText("");
                comboBoxCourseModify.setValue("");
                txtAdded.setText("");
                tfSiglaModify.setDisable(true);
                tfNameCourseModify.setDisable(true);
                btnUpdate.setDisable(true);
                tfSearchCourserUpdate.setDisable(false);
                tfModifyCreditsCourse.setDisable(true);
                btnCancelModify.setDisable(true);
                btnSearchCourseUpdate.setDisable(false);
                comboBoxCourseModify.setDisable(true);

            }

        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtAdded.setText("");

        }
    }

    @FXML
    private void tfNameCourseModify(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {

                tfNameCourseModify.setEditable(false);

            } else {

                tfNameCourseModify.setEditable(true);
            }

        } catch (Exception e) {

            tfNameCourseModify.setEditable(true);

        }
    }

    @FXML
    private void tfModifyCreditsCourse(KeyEvent event) {

        txtAdded.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isDigit(c)) {

                tfModifyCreditsCourse.setEditable(false);

            } else {

                tfModifyCreditsCourse.setEditable(true);
            }

        } catch (Exception e) {

            tfModifyCreditsCourse.setEditable(true);

        }
    }

    @FXML
    private void tfSearchCarrerUpdate(KeyEvent event) {

        txtAdded.setText("");
    }
}
