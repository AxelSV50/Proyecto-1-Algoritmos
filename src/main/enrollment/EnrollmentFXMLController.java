/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.enrollment;

import domain.*;
import domain.list.*;
import domain.Course;
import domain.TimeTable;
import java.io.File;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Alert;
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
    private TableView<List<String>> studentsTable;
    @FXML
    private TableColumn<List<String>, String> idStudentCol;
    @FXML
    private TableColumn<List<String>, String> idCarneStudentCol;
    @FXML
    private TableColumn<List<String>, String> nameStudentCol;
    @FXML
    private TableColumn<List<String>, String> lastnameStudentCol;
    @FXML
    private TableColumn<List<String>, String> careerStudentCol;

    @FXML
    private Button enrollment;
    @FXML
    private Button derollment;
    @FXML
    private Button btnInitErollment;
    @FXML
    private Pane paneEnrollCourses;
    @FXML
    private TableView<List<String>> tableCoursesEnrollment;
    @FXML
    private TableColumn<List<String>, String> courseIDEnrollmentCol;
    @FXML
    private TableColumn<List<String>, String> courseNameErollmentCol;
    @FXML
    private TableColumn<List<String>, String> courseCreditsEnrollmentCol;
    @FXML
    private TableColumn<List<String>, String> courseScheduleEnrollmentCol;
    @FXML
    private TableColumn<List<String>, String> courseEnrollemntSchedule2Col;
    @FXML
    private TableColumn<List<String>, String> coursePeriodEnrollmentCol;
    @FXML
    private TableView<List<String>> tableAddedCoursesEnrollment;
    @FXML
    private TableColumn<List<String>, String> courseIDAddedEnrollmentCol;
    @FXML
    private TableColumn<List<String>, String> nameCourseAddedEnrollementCol;
    @FXML
    private TableColumn<List<String>, String> creditsAddedEnrollmentCol;
    @FXML
    private TableColumn<List<String>, String> scheduleAddedEnrollmentCol;
    @FXML
    private TableColumn<List<String>, String> periodAddedEnrollmentCol;

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
    @FXML
    private Text txtIDcourseEnroll;
    @FXML
    private Text txtTitle2;

    private ObservableList<List<String>> studentsData;
    private ObservableList<List<String>> coursesEnrollmentData;
    private ObservableList<List<String>> coursesAddedData = FXCollections.observableArrayList();
    ;

    private SinglyLinkedList studentList = util.Utility.getStudentsList();
    private DoublyLinkedList careersList = util.Utility.getCareersList();
    private SinglyLinkedList timeTableList = util.Utility.getTimeTableList();
    private CircularDoublyLinkedList courseList = util.Utility.getCoursesList();
    private CircularDoublyLinkedList enrollmentList = util.Utility.getEnrollmentList();

    private List<String> studentEnrollment;
    private List<String> courseToEnroll;
    private int courseEnrollmentPosition;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        initStudentsTable();
        txtTitle.setVisible(true);

    }

    private void initComboBox(ComboBox<String> cb) {

        ObservableList opc = FXCollections.observableArrayList();
        opc.addAll("Horario 1", "Horario 2");

        cb.setItems(opc);
        cb.setValue("Horario 1");
    }

    private void initCoursesEnrollmentTable() {

        courseIDEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        courseNameErollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        courseCreditsEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        courseScheduleEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        courseEnrollemntSchedule2Col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(4)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        coursePeriodEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(5)); //To change body of generated methods, choose Tools | Templates.
            }
        });

        final ObservableList<List<String>> coursesEnrollmentCell = tableCoursesEnrollment.getSelectionModel().getSelectedItems();
        coursesEnrollmentCell.addListener(selectTableCoursesEnrollment);

        coursesEnrollmentData = FXCollections.observableArrayList();

        if (!timeTableList.isEmpty()) {

            try {
                for (int i = 1; i <= timeTableList.size(); i++) {

                    //Obtenemos los datos del curso
                    TimeTable t = (TimeTable) timeTableList.getNode(i).data;

                    int indexCourse = courseList.indexOf(new Course(t.getCourseID(), "", 0, 0));
                    Course c1 = (Course) courseList.getNode(indexCourse).data;
                    int idCareer = Integer.parseInt(studentEnrollment.get(5));

                    if (c1.getCareerID() == idCareer) {

                        List<String> list = new ArrayList<>();

                        list.add(t.getCourseID());
                        list.add(c1.getName());
                        list.add(c1.getCredits() + "");
                        list.add(t.getSchedule1());
                        list.add(t.getSchedule2());
                        list.add(t.getPeriod());

                        coursesEnrollmentData.add(list);
                    }

                }
            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableCoursesEnrollment.setItems(coursesEnrollmentData);
    }

    private void initStudentsTable() {

        idStudentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        idCarneStudentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        nameStudentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        lastnameStudentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        careerStudentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(4)); //To change body of generated methods, choose Tools | Templates.
            }
        });

        final ObservableList<List<String>> studentsTableCell = studentsTable.getSelectionModel().getSelectedItems();
        studentsTableCell.addListener(selectTableStudents);

        studentsData = FXCollections.observableArrayList();

        if (!studentList.isEmpty()) {

            try {
                for (int i = 1; i <= studentList.size(); i++) {

                    Student a = (Student) studentList.getNode(i).data;
                    Career c = (Career) careersList.getNode(careersList.indexOf(new Career(a.getCareerID(), ""))).data;
                    List<String> list = new ArrayList<>();

                    list.add(a.getId() + "");
                    list.add(a.getStudentID());
                    list.add(a.getFirstname());
                    list.add(a.getLastname());
                    list.add(c.getDescription());
                    list.add(a.getCareerID() + "");

                    studentsData.add(list);
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

    private final ListChangeListener<List<String>> selectTableStudents
            = new ListChangeListener<List<String>>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends List<String>> c) {
            setSelectedStudent();
        }
    };
    private final ListChangeListener<List<String>> selectTableCoursesEnrollment
            = new ListChangeListener<List<String>>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends List<String>> c) {
            setSelectedCourseEnrollment();
        }
    };

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public List<String> getSelectedStudentsTable() {
        if (studentsTable != null) {
            List<List<String>> tabla = studentsTable.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final List<String> competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    public List<String> getSelectedCoursesEnrollmentTable() {
        if (tableCoursesEnrollment != null) {
            List<List<String>> table = tableCoursesEnrollment.getSelectionModel().getSelectedItems();
            if (table.size() == 1) {
                final List<String> competicionSeleccionada = table.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void setSelectedStudent() {
        final List<String> student = getSelectedStudentsTable();

        if (student != null) {

            studentEnrollment = student;
        }

    }

    private void setSelectedCourseEnrollment() {
        final List<String> course = getSelectedCoursesEnrollmentTable();
        courseEnrollmentPosition = coursesEnrollmentData.indexOf(course);

        if (course != null) {

            courseToEnroll = course;
            txtIDcourseEnroll.setText(courseToEnroll.get(0));

        }

    }

    @FXML
    private void enrollment(ActionEvent event) {

        cleanAll();
        paneSelectStudent.setVisible(true);
        studentEnrollment = null;
        courseToEnroll = null;
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

        courseToEnroll = null;
        for (int i = 0; i < coursesAddedData.size(); i++) {
            coursesAddedData.remove(i);
        }
        if (!coursesAddedData.isEmpty()) {
            coursesAddedData.remove(0);
        }

    }

    @FXML
    private void btnInitErollment(ActionEvent event) {

        if (studentEnrollment != null) {

            cleanAll();
            txtTitle.setVisible(true);
            paneSelectStudent.setVisible(true);
            paneEnrollCourses.setVisible(true);

            Career c = null;
            boolean canEroll = false;
            try {
                for (int i = 1; i <= careersList.size(); i++) {

                    c = (Career) careersList.getNode(i).data;

                    int idCareer = Integer.parseInt(studentEnrollment.get(5));
                    if (idCareer == c.getId()) {

                        for (int j = 1; j <= timeTableList.size(); j++) {

                            TimeTable t = (TimeTable) timeTableList.getNode(j).data;
                            int indexCourse = courseList.indexOf(new Course(t.getCourseID(), "", 0, 0));
                            Course c1 = (Course) courseList.getNode(indexCourse).data;

                            if (c.getId() == c1.getCareerID()) {
                                canEroll = true;
                                j = timeTableList.size() + 1;
                            }
                        }
                        i = careersList.size() + 1;
                    }
                }

            } catch (ListException ex) {

            }

            if (canEroll) {

                txtIDcourseEnroll.setText("");
                initComboBox(cbScheduleCourse);
                initCoursesEnrollmentTable();
                paneEnrollCourses.setVisible(true);
                careerStudentEnroll.setText(c.getDescription());
                txtNameStudentEnroll.setText(studentEnrollment.get(2) + " " + studentEnrollment.get(3));
                idStudetEnroll.setText(studentEnrollment.get(0) + "");
                carneStudentEnroll.setText(studentEnrollment.get(1) + "");

            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Matrícula");
                alert.setHeaderText("El estudiante no puede realizar la matrícula \n"
                        + "debido a que la carrera " + c.getDescription() + " no tiene \n"
                        + "cursos con horarios asignados.");
                alert.showAndWait();
            }

        }
    }

    private static int nextID;

    @FXML
    private void btnSaveEnrollemnt(ActionEvent event) {

        if (!coursesAddedData.isEmpty()) {

            enrollmentList = util.Utility.getEnrollmentList();
            
            for (int i = 0; i < coursesAddedData.size(); i++) {

                int id = 1;
                if(!enrollmentList.isEmpty()){
                    
                    try {
                        Enrollment aux = (Enrollment) enrollmentList.getLast();
                        id = aux.getId()+1;
                    } catch (ListException ex) {
                    }
                }
                Enrollment e = new Enrollment(new Date(), studentEnrollment.get(1), coursesAddedData.get(i).get(0), coursesAddedData.get(i).get(4), id);
                enrollmentList.add(e);
                data.FileManagementEnrollement.add(e);
            }

            paneEnrollCourses.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Matrícula");
            alert.setHeaderText("La matrícula se ha realizado satisfatoriamente  ✓");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Matrícula");
            alert.setHeaderText("Debe agregar al menos un curso para guardar.");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnCancelEnrollemnt(ActionEvent event) {

        paneEnrollCourses.setVisible(false);
        courseToEnroll = null;
        for (int i = 0; i < coursesAddedData.size(); i++) {
            coursesAddedData.remove(i);
        }
        if (!coursesAddedData.isEmpty()) {
            coursesAddedData.remove(0);
        }

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

    @FXML
    private void btnAddCourse(ActionEvent event) {

        if (courseToEnroll != null) {

            List<String> aux = new ArrayList<>();

            aux.add(courseToEnroll.get(0));
            aux.add(courseToEnroll.get(1));
            aux.add(courseToEnroll.get(2));
            aux.add(courseToEnroll.get(5));

            if (cbScheduleCourse.getValue().equals("Horario 1")) {

                aux.add(courseToEnroll.get(3));
            } else {

                aux.add(courseToEnroll.get(4));
            }
            coursesAddedData.add(aux);
            initTableAddedCourses();

            if (courseEnrollmentPosition >= 0) {
                coursesEnrollmentData.remove(courseEnrollmentPosition);

                if (courseEnrollmentPosition < 0) {
                    txtIDcourseEnroll.setText("");
                    courseToEnroll = null;
                }
            }

        } else if (!coursesEnrollmentData.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Matrícula");
            alert.setHeaderText("Debe Selecionar un curso para agregar.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Matrícula");
            alert.setHeaderText("No hay cursos para agregar");
            alert.showAndWait();
        }
    }

    private void initTableAddedCourses() {

        courseIDAddedEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        nameCourseAddedEnrollementCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        creditsAddedEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        scheduleAddedEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(4)); //To change body of generated methods, choose Tools | Templates.
            }
        });

        periodAddedEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });

        tableAddedCoursesEnrollment.setItems(coursesAddedData);
    }

}
