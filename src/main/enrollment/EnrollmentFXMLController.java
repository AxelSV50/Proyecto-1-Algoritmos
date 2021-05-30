/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.enrollment;

import domain.Career;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.career.CareerFXMLController;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class EnrollmentFXMLController implements Initializable {
    
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ac;
    @FXML
    private Button btnBack;
    @FXML
    private Text txtTitle;
    @FXML
    private Text txtError;
    @FXML
    private Text txtAdded;
    @FXML
    private Pane paneSelectStudent;
    @FXML
    private TableView<Student> studentsTable;
    
    private int positionStudent;
    @FXML
    private TableColumn<Student, Integer> idStudentCol;
    @FXML
    private TableColumn<Student, String> idCarneStudentCol;
    @FXML
    private TableColumn<Student, String> nameStudentCol;
    @FXML
    private TableColumn<Student, String> lastnameStudentCol;
    @FXML
    private Button enrollment;
    @FXML
    private Button derollment;
    @FXML
    private Button btnInitErollment;
    @FXML
    private Pane paneEnrollCourses;
    @FXML
    private TableView<?> tableCoursesEnrollment;
    @FXML
    private TableView<?> tableAddedCoursesEnrollment;
    @FXML
    private Text txtNameStudentEnroll;
    @FXML
    private Text idStudetEnroll;
    @FXML
    private Text carneStudentEnroll;
    @FXML
    private Text careerStudentEnroll;
    @FXML
    private ComboBox<String> cbScheduleCourse;
    @FXML
    private Button btnAddCourse;
    @FXML
    private Button btnSaveEnrollemnt;
    @FXML
    private Button btnCancelEnrollemnt;
    @FXML
    private Button btnRemoveSelectedEnroll;
    @FXML
    private Pane paneEnrolledCourses;
    @FXML
    private TableView<?> tableErolledCourses;
    @FXML
    private Text txtNameStudentEnrolled;
    @FXML
    private Text idStudetEnrolled;
    @FXML
    private Text carneStudentEnrolled;
    @FXML
    private Text careerStudentEnrolled;
    @FXML
    private Button btnBackEnrolledCourses;
    @FXML
    private Pane paneDenrolledCourses;
    @FXML
    private TableView<?> tableCoursesToDeroll;
    @FXML
    private Text txtNameStudentDeroll;
    @FXML
    private Text idStudetDeroll;
    @FXML
    private Text carneStudentDeroll;
    @FXML
    private Text careerStudentDeroll;
    @FXML
    private Button btnDerollSelected;
    @FXML
    private Button btnCancelDeroll;
    @FXML
    private Button btnSaveDeroll;
    @FXML
    private Pane panelSearchStudentDeroll;
    @FXML
    private TextField tfSearchStudentDeroll;
    @FXML
    private Text txtCareerSearchUpdate;
    @FXML
    private Button btnSearchStudentDeroll;
    
    private ObservableList<Student> studentsData;
    
    private SinglyLinkedList studentList = util.Utility.getStudentsList();
    private DoublyLinkedList careersList = util.Utility.getCareersList();
    
    private Student studentEnrollment;
    @FXML
    private Text txtIDcourseEnroll;
    @FXML
    private Text txtTitle2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        initStudentsTable();
        txtTitle.setVisible(true);
        
    }
    
    private void initStudentsTable() {
        
        idStudentCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        idCarneStudentCol.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
        nameStudentCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
        lastnameStudentCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
        
        final ObservableList<Student> studentsTableCell = studentsTable.getSelectionModel().getSelectedItems();
        studentsTableCell.addListener(selectTableStudents);
        
        studentsData = FXCollections.observableArrayList();
        
        if (!studentList.isEmpty()) {
            
            try {
                for (int i = 1; i <= studentList.size(); i++) {
                    
                    Student a = (Student) studentList.getNode(i).data;
                    studentsData.add(a);
                }
            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        studentsTable.setItems(studentsData);
        
    }
    
    @FXML
    private void btnBack(ActionEvent event) {
        
        bp.setVisible(false);
    }
    
    private final ListChangeListener<Student> selectTableStudents
            = new ListChangeListener<Student>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends Student> c) {
            setSelectedStudent();
        }
    };

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public Student getSelectedStudentsTable() {
        if (studentsTable != null) {
            List<Student> tabla = studentsTable.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Student competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void setSelectedStudent() {
        final Student student = getSelectedStudentsTable();
        positionStudent = studentsData.indexOf(student);
        
        if (student != null) {

            // Pongo los textFields con los datos correspondientes
            studentEnrollment = student;
        }
        
    }
    
    @FXML
    private void enrollment(ActionEvent event) {
        
        cleanAll();
        paneSelectStudent.setVisible(true);
        studentEnrollment = null;
        initStudentsTable();
        txtTitle.setVisible(true);
        
    }
    
    @FXML
    private void derollment(ActionEvent event) {
        
        cleanAll();
        panelSearchStudentDeroll.setVisible(true);
        txtTitle2.setVisible(true);
    }
    
    private void cleanAll() {
        paneSelectStudent.setVisible(false);
        panelSearchStudentDeroll.setVisible(false);
        paneEnrollCourses.setVisible(false);
        txtTitle.setVisible(false);
        txtTitle2.setVisible(false);
        
    }
    
    @FXML
    private void btnInitErollment(ActionEvent event) {
        
        if (studentEnrollment != null) {
            
            paneEnrollCourses.setVisible(true);
            
            txtNameStudentEnroll.setText(studentEnrollment.getFirstname() + " " + studentEnrollment.getLastname());
            idStudetEnroll.setText(studentEnrollment.getId() + "");
            carneStudentEnroll.setText(studentEnrollment.getStudentID() + "");
            
            try {
                for (int i = 1; i <= careersList.size(); i++) {
                    
                    Career a = (Career) careersList.getNode(i).data;
                    
                    if (studentEnrollment.getCareerID() == a.getId()) {
                        
                        careerStudentEnroll.setText(a.getDescription());
                        i = careersList.size() + 1;
                    }
                }
            } catch (ListException ex) {
                Logger.getLogger(EnrollmentFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void btnSaveEnrollemnt(ActionEvent event) {
    }
    
    @FXML
    private void btnCancelEnrollemnt(ActionEvent event) {
        
        paneEnrollCourses.setVisible(false);
        
    }
    
    @FXML
    private void btnBackEnrolledCourses(ActionEvent event) {
    }
    
    @FXML
    private void btnDerollSelected(ActionEvent event) {
    }
    
    @FXML
    private void btnCancelDeroll(ActionEvent event) {
    }
    
    @FXML
    private void btnSaveDeroll(ActionEvent event) {
    }
    
    @FXML
    private void tfSearchCarrerUpdate(KeyEvent event) {
    }
    
    @FXML
    private void btnSearchStudentDeroll(ActionEvent event) {
    }
}
