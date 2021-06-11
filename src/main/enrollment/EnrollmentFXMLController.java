/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.enrollment;

import data.FileManagementEnrollement;
import domain.*;
import domain.list.*;
import domain.Course;
import domain.TimeTable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javax.mail.MessagingException;
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
    private TableView<List<String>> tableEnrolledCourses;
    @FXML
    private TableColumn<List<String>, String> courseIDEnrolledCoursesCol;
    @FXML
    private TableColumn<List<String>, String> nameEnrolledCoursesCol;
    @FXML
    private TableColumn<List<String>, String> creditsEnrolledCoursesCol;
    @FXML
    private TableColumn<List<String>, String> scheduleEnrolledCoursesCol;
    @FXML
    private TableColumn<List<String>, String> periodEnrolledCoursesCol1;

    @FXML
    private Text txtidStudetEnrolled;
    @FXML
    private Text txtCarneStudentEnrolled;
    @FXML
    private Text txtCareerStudentEnrolled;
    @FXML
    private Text txtDateEnrollment;
    @FXML
    private Text txtTotalCoursesEnrollment;
    @FXML
    private Text txtCreditsCoursesEnrollment;
    @FXML
    private Pane paneDeEnnrollCourses;
    @FXML
    private Text txtIdStudetDeroll;
    @FXML
    private Text txtCarneStudentDeroll;
    @FXML
    private Text txtCareerStudentDeroll;

    @FXML
    private TableView<List<String>> tableCoursesToDeroll;
    @FXML
    private TableColumn<List<String>, String> nameCourseDeEnrollmentCol;
    @FXML
    private TableColumn<List<String>, String> creditsDeEnrollmentCol;
    @FXML
    private TableColumn<List<String>, String> courseIdDenrollmentCol;

    @FXML
    private TableColumn<List<String>, String> scheduleDeEnrollmentCol;
    @FXML
    private Text txtSelectedDeEnrollemnt;

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
    private Text txtNameStudentEnrolled;
    @FXML
    private Button btnBackEnrolledCourses;
    @FXML
    private Text txtNameStudentDeroll;
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
    @FXML
    private Button btnInitEnrollment;
    @FXML
    private TextArea taObservation;

    //Contenido de las tablas
    private ObservableList<List<String>> studentsData;
    private ObservableList<List<String>> coursesEnrollmentData;
    private ObservableList<List<String>> coursesAddedData = FXCollections.observableArrayList();
    private ObservableList<List<String>> deEnrollCoursesData = FXCollections.observableArrayList();

    //Listas con los datos de estudiantes, carreras, cursos, horarios, matrículas y retiro de cursos.
    private SinglyLinkedList studentList = util.Utility.getStudentsList();
    private DoublyLinkedList careersList = util.Utility.getCareersList();
    private SinglyLinkedList timeTableList = util.Utility.getTimeTableList();
    private CircularDoublyLinkedList courseList = util.Utility.getCoursesList();
    private CircularDoublyLinkedList enrollmentList = util.Utility.getEnrollmentList();
    private CircularDoublyLinkedList deEnrollmentList = util.Utility.getDeEnrollmentList();
    private CircularDoublyLinkedList deEnrollmentListAux = new CircularDoublyLinkedList();

    //Estas listas se utilizan para obtener los datos de un elemento específico en las tablas
    private List<String> studentEnrollment;
    private List<String> courseToEnroll;
    private List<String> courseToDeEnroll;
    //Posición de un elemento en la tabla de cursos a matricular
    private int courseEnrollmentPosition;
    private int courseDeEnrollmentPosition;

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
    private final ListChangeListener<List<String>> selectTableCoursesToDeEnroll
            = new ListChangeListener<List<String>>() {
        @Override
        public void onChanged(ListChangeListener.Change<? extends List<String>> c) {
            setSelectedCourseDeEnrollment();
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

    public List<String> getSelectedCoursesDeEnrollmentTable() {
        if (tableCoursesToDeroll != null) {
            List<List<String>> table = tableCoursesToDeroll.getSelectionModel().getSelectedItems();
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

    private void setSelectedCourseDeEnrollment() {
        final List<String> course = getSelectedCoursesDeEnrollmentTable();
        courseDeEnrollmentPosition = deEnrollCoursesData.indexOf(course);
        if (course != null) {

            courseToDeEnroll = course;
            txtSelectedDeEnrollemnt.setText(courseToDeEnroll.get(0));
            taObservation.setDisable(false);
        }

    }

    @FXML
    private void enrollment(ActionEvent event) {

        cleanAll();
        paneSelectStudent.setVisible(true);
        studentEnrollment = null;
        courseToEnroll = null;
        courseToDeEnroll = null;
        initStudentsTable();
        txtTitle.setVisible(true);

    }

    @FXML
    private void derollment(ActionEvent event) {

        cleanAll();
        panelSearchStudentDeroll.setVisible(true);
        courseToDeEnroll = null;
        txtSelectedDeEnrollemnt.setText("");
        txtTitle2.setVisible(true);
    }

    private void cleanAll() {
        paneSelectStudent.setVisible(false);
        panelSearchStudentDeroll.setVisible(false);
        paneEnrollCourses.setVisible(false);
        paneEnrolledCourses.setVisible(false);
        paneDeEnnrollCourses.setVisible(false);
        tfSearchStudentDeroll.setText("");
        txtError.setText("");
        txtTitle.setVisible(false);
        txtTitle2.setVisible(false);
        txtSelectedDeEnrollemnt.setText("");
        courseToEnroll = null;
        courseToDeEnroll = null;

        for (int i = 0; i < coursesAddedData.size(); i++) {
            coursesAddedData.remove(i);
        }
        if (!coursesAddedData.isEmpty()) {
            coursesAddedData.remove(0);
        }
        for (int i = 0; i < deEnrollCoursesData.size(); i++) {
            deEnrollCoursesData.remove(i);
        }
        if (!deEnrollCoursesData.isEmpty()) {
            deEnrollCoursesData.remove(0);
        }

    }

    @FXML
    private void btnInitEnrollment(ActionEvent event) {

        if (studentEnrollment != null) {

            cleanAll();
            txtTitle.setVisible(true);
            paneSelectStudent.setVisible(true);

            Career c = null;
            boolean canEroll = false;

            try {

                if (enrollmentList.isEmpty() || !enrollmentList.contains(new Enrollment(new Date(), studentEnrollment.get(1), "", "", 0))) {

                    paneEnrollCourses.setVisible(true);

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
                    if (canEroll) {

                        for (int i = 0; i < coursesAddedData.size(); i++) {
                            coursesAddedData.remove(i);
                        }
                        if (!coursesAddedData.isEmpty()) {
                            coursesAddedData.remove(0);
                        }
                        if (!coursesAddedData.isEmpty()) {
                            coursesAddedData.remove(0);
                        }
                        txtIDcourseEnroll.setText("");
                        initComboBox(cbScheduleCourse);
                        initCoursesEnrollmentTable();
                        paneEnrollCourses.setVisible(true);
                        careerStudentEnroll.setText(c.getDescription());
                        txtNameStudentEnroll.setText(studentEnrollment.get(2) + " " + studentEnrollment.get(3));
                        idStudetEnroll.setText(studentEnrollment.get(0) + "");
                        carneStudentEnroll.setText(studentEnrollment.get(1) + "");

                    } else {

                        paneEnrollCourses.setVisible(false);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Matrícula");
                        alert.setHeaderText("El estudiante no puede realizar la matrícula \n"
                                + "debido a que la carrera " + c.getDescription() + " no tiene \n"
                                + "cursos con horarios asignados.");
                        alert.showAndWait();
                    }

                } else {

                    paneEnrolledCourses.setVisible(true);
                    txtNameStudentEnrolled.setText(studentEnrollment.get(2) + " " + studentEnrollment.get(3));
                    txtidStudetEnrolled.setText(studentEnrollment.get(0));
                    txtCarneStudentEnrolled.setText(studentEnrollment.get(1));
                    txtCareerStudentEnrolled.setText(studentEnrollment.get(4));
                    initTableEnrolledCourses();
                }

            } catch (ListException ex) {

            }

        }
    }

    @FXML
    private void btnSaveEnrollemnt(ActionEvent event) {

        if (!coursesAddedData.isEmpty()) {

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Retiro de cursos");
            alert2.setHeaderText("\nSe realizará la matrícula con los cursos seleccionados.\n\n"
                    + "Precione aceptar para continuar.");
            Optional<ButtonType> action = alert2.showAndWait();

            if (action.get() == ButtonType.OK) {
                enrollmentList = util.Utility.getEnrollmentList();

                for (int i = 0; i < coursesAddedData.size(); i++) {

                    int id = 1;
                    if (!enrollmentList.isEmpty()) {

                        try {
                            Enrollment aux = (Enrollment) enrollmentList.getLast();
                            id = aux.getId() + 1;
                        } catch (ListException ex) {
                        }
                    }
                    Enrollment e = new Enrollment(new Date(), studentEnrollment.get(1), coursesAddedData.get(i).get(0), coursesAddedData.get(i).get(4), id);
                    enrollmentList.add(e);
                    data.FileManagementEnrollement.add(e);
                }

                paneEnrollCourses.setVisible(false);
                paneEnrolledCourses.setVisible(true);
                txtNameStudentEnrolled.setText(studentEnrollment.get(2) + " " + studentEnrollment.get(3));
                txtidStudetEnrolled.setText(studentEnrollment.get(0));
                txtCarneStudentEnrolled.setText(studentEnrollment.get(1));
                txtCareerStudentEnrolled.setText(studentEnrollment.get(4));
                try {
                    util.Mail.sendEnrollmentMessage((Student) studentList.getNode(studentList.indexOf(new Student(Integer.parseInt(studentEnrollment.get(0)), studentEnrollment.get(1), "", "", new Date(), "", "", "", 0))).data);
                } catch (Exception ex) {
                    Logger.getLogger(EnrollmentFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
                initTableEnrolledCourses();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Matrícula");
                alert.setHeaderText("La matrícula se ha realizado satisfatoriamente  ✓");
                alert.showAndWait();

            }

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
        courseToDeEnroll = null;
        for (int i = 0; i < coursesAddedData.size(); i++) {
            coursesAddedData.remove(i);
        }
        if (!coursesAddedData.isEmpty()) {
            coursesAddedData.remove(0);
        }
        txtSelectedDeEnrollemnt.setText("");
        System.out.println();
    }

    @FXML
    private void btnBackEnrolledCourses(ActionEvent event) {

        paneEnrolledCourses.setVisible(false);
    }

    @FXML
    private void btnDerollSelected(ActionEvent event) {

        if (courseToDeEnroll != null) {

            if (!(taObservation.getText().isBlank())) {
                int id = 1;
                if (!deEnrollmentList.isEmpty()) {

                    try {
                        DeEnrollment aux = (DeEnrollment) deEnrollmentList.getLast();
                        id = aux.getId() + 1;
                    } catch (ListException ex) {
                    }
                }
                if (!deEnrollmentListAux.isEmpty() && deEnrollmentList.isEmpty()) {

                    try {
                        DeEnrollment aux = (DeEnrollment) deEnrollmentListAux.getLast();
                        id = aux.getId() + 1;
                    } catch (ListException ex) {
                    }
                }
                if (courseDeEnrollmentPosition >= 0) {
                    deEnrollmentListAux.add(new DeEnrollment(new Date(), txtCarneStudentDeroll.getText(), courseToDeEnroll.get(0), courseToDeEnroll.get(3), id, taObservation.getText().replace('\n', ' ')));
                    deEnrollCoursesData.remove(courseDeEnrollmentPosition);
                    taObservation.setText("");
                } else {
                    taObservation.setText("");
                    taObservation.setDisable(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Retiro de cursos");
                    alert.setHeaderText("No hay cursos para retirar.");
                    alert.showAndWait();
                }

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Retiro de cursos");
                alert.setHeaderText("Debe agregar una observación al retiro.");
                alert.showAndWait();
            }

        } else {

            if (deEnrollCoursesData.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Retiro de cursos");
                alert.setHeaderText("Debe selecionar un curso para remover.");
                alert.showAndWait();
            }

        }
    }

    @FXML
    private void btnCancelDeroll(ActionEvent event
    ) {

        cleanAll();
        panelSearchStudentDeroll.setVisible(true);
        txtTitle2.setVisible(true);
        courseToDeEnroll = null;

        for (int i = 0; i < deEnrollCoursesData.size(); i++) {
            deEnrollCoursesData.remove(i);
        }
        if (!deEnrollCoursesData.isEmpty()) {
            deEnrollCoursesData.remove(0);
        }
        txtSelectedDeEnrollemnt.setText("");

    }

    @FXML
    private void btnSaveDeroll(ActionEvent event
    ) {

        if (!deEnrollmentListAux.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Retiro de cursos");
            alert.setHeaderText("\nLa siguiente acción retirará los cursos seleccionados.\n\n"
                    + "Precione aceptar para continuar.");
            Optional<ButtonType> action = alert.showAndWait();

            if (action.get() == ButtonType.OK) {

                try {

                    enrollmentList = FileManagementEnrollement.getEnrollmentList();
                    deEnrollmentList = FileManagementEnrollement.getDeEnrollmentList();

                    for (int i = 1; i <= deEnrollmentListAux.size(); i++) {

                        deEnrollmentList.add((DeEnrollment) deEnrollmentListAux.getNode(i).data);
                        FileManagementEnrollement.add((DeEnrollment) deEnrollmentListAux.getNode(i).data);
                    }

                    for (int i = 1; i <= deEnrollmentListAux.size(); i++) {

                        DeEnrollment d = (DeEnrollment) deEnrollmentListAux.getNode(i).data;

                        for (int j = 1; j <= enrollmentList.size(); j++) {

                            Enrollment e = (Enrollment) enrollmentList.getNode(j).data;

                            if (e.getCourseID().equalsIgnoreCase(d.getCourseID()) && e.getStudentID().equalsIgnoreCase(d.getStudentID())) {
                                System.out.print("");
                                enrollmentList.remove(j);
                                if (enrollmentList.isEmpty()) {
                                    break;
                                }
                            }
                        }

                    }

                    util.Mail.sendDeEnrollmentMessage((Student) studentList.getNode(studentList.indexOf(new Student(0, txtCarneStudentDeroll.getText(), "", "", new Date(), "", "", "", 0))).data, deEnrollmentListAux);
                    deEnrollmentListAux = new CircularDoublyLinkedList();
                    FileManagementEnrollement.overwriteEnrollmentFile(enrollmentList);
                    paneDeEnnrollCourses.setVisible(false);
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Retiro de cursos");
                    alert2.setHeaderText("El retiro de cursos se ha realizado satisfatoriamente  ✓");
                    alert2.showAndWait();

                } catch (MessagingException ex) {

                } catch (ListException ex) {
                }

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Retiro de cursos");
            alert.setHeaderText("\nDebe retirar al menos un curso para continuar.");
            alert.showAndWait();
        }

    }

    @FXML
    private void tfSearchCarrerUpdate(KeyEvent event
    ) {
    }

    @FXML
    private void btnSearchStudentDeroll(ActionEvent event
    ) {

        if (!tfSearchStudentDeroll.getText().equals("")) {

            enrollmentList = util.Utility.getEnrollmentList();
            Enrollment e = new Enrollment(new Date(), tfSearchStudentDeroll.getText(), "", "", 0);
            for (int i = 0; i < deEnrollCoursesData.size(); i++) {
                deEnrollCoursesData.remove(i);
            }
            if (!deEnrollCoursesData.isEmpty()) {
                deEnrollCoursesData.remove(0);
            }
            if (!deEnrollCoursesData.isEmpty()) {
                deEnrollCoursesData.remove(0);
            }
            taObservation.setText("");
            try {
                if (enrollmentList.contains(e)) {

                    e = (Enrollment) enrollmentList.getNode(enrollmentList.indexOf(e)).data;
                    Student s = (Student) studentList.getNode(studentList.indexOf(new Student(0, e.getStudentID(), "", "", new Date(), "", "", "", 0))).data;
                    Career c = (Career) careersList.getNode(careersList.indexOf(new Career(s.getCareerID(), ""))).data;
                    txtNameStudentDeroll.setText(s.getFirstname() + " " + s.getLastname());
                    txtIdStudetDeroll.setText(s.getId() + "");
                    txtCarneStudentDeroll.setText(s.getStudentID());
                    txtCareerStudentDeroll.setText(c.getDescription());
                    txtError.setText("");
                    paneDeEnnrollCourses.setVisible(true);
                    initTableDeEnrollCourses();
                    taObservation.setDisable(true);
                } else if (studentList.contains(new Student(0, e.getStudentID(), "", "", new Date(), "", "", "", 0))) {

                    txtError.setText("El estudiante no tiene cursos matriculados.");
                } else {
                    txtError.setText("El estudiante no está registrado en el sistema.");
                }
            } catch (ListException ex) {
                txtError.setText("No hay estudiantes con matrícula.");
            }
        } else {
            txtError.setText("Debe rellenar lo se le solicita.");
        }
    }

    @FXML
    private void btnAddCourse(ActionEvent event
    ) {

        if (courseToEnroll != null) {

            //Esto es para agregar un elemento a la tabla
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
            boolean validSchedule = true;

            if (!coursesAddedData.isEmpty()) {
                //Validar que no hayan choques de horarios
                for (int i = 0; i < coursesAddedData.size(); i++) {

                    //Hora y día de los cursos agregados
                    String[] array = coursesAddedData.get(i).get(4).split("-");
                    //Horas día 1
                    String[] initHour1 = array[1].split(":");
                    String[] finalHour1 = array[2].split(":");
                    //Horas día2
                    String[] initHour2 = array[4].split(":");
                    String[] finalHour2 = array[5].split(":");
                    //Hora y día del curso a agregar
                    String[] array4 = aux.get(4).split("-");
                    //Horas día 1
                    String[] initHour3 = array4[1].split(":");
                    String[] finalHour3 = array4[2].split(":");
                    //Horas día 3
                    String[] initHour4 = array4[4].split(":");
                    String[] finalHour4 = array4[5].split(":");

                    //Valida si el día 1 es igual
                    if (array[0].equals(array4[0])) {

                        //Horas del día 1
                        int init1 = Integer.parseInt(initHour1[0]);
                        int final1 = Integer.parseInt(finalHour1[0]);
                        int init2 = Integer.parseInt(initHour3[0]);
                        int final2 = Integer.parseInt(finalHour3[0]);

                        //Si las horas chocan el horario no es válido
                        if (init1 >= init2 && init1 <= final2) {
                            validSchedule = false;
                        } else if (init2 >= init1 && init2 <= final1) {
                            validSchedule = false;
                        } else if (final1 >= init2 && final1 <= final2) {
                            validSchedule = false;
                        } else if (final2 >= init1 && final2 <= final1) {
                            validSchedule = false;
                        }

                    }
                    //Valida si el día 2 es igual
                    if (array[3].equals(array4[3])) {

                        //Horas del día 2
                        int init1 = Integer.parseInt(initHour2[0]);
                        int final1 = Integer.parseInt(finalHour2[0]);
                        int init2 = Integer.parseInt(initHour4[0]);
                        int final2 = Integer.parseInt(finalHour4[0]);

                        //Si las horas chocan el horario no es válido
                        if (init1 >= init2 && init1 <= final2) {
                            validSchedule = false;
                        } else if (init2 >= init1 && init2 <= final1) {
                            validSchedule = false;
                        } else if (final1 >= init2 && final1 <= final2) {
                            validSchedule = false;
                        } else if (final2 >= init1 && final2 <= final1) {
                            validSchedule = false;
                        }
                    }

                    //Valida si el día 1 es igual al día 2 del otro horario
                    if (array[0].equals(array4[3])) {

                        //Horas del día 2
                        int init1 = Integer.parseInt(initHour1[0]);
                        int final1 = Integer.parseInt(finalHour1[0]);
                        int init2 = Integer.parseInt(initHour4[0]);
                        int final2 = Integer.parseInt(finalHour4[0]);

                        //Si las horas chocan el horario no es válido
                        if (init1 >= init2 && init1 <= final2) {
                            validSchedule = false;
                        } else if (init2 >= init1 && init2 <= final1) {
                            validSchedule = false;
                        } else if (final1 >= init2 && final1 <= final2) {
                            validSchedule = false;
                        } else if (final2 >= init1 && final2 <= final1) {
                            validSchedule = false;
                        }
                    }
                    //Valida si el día 2 es igual al día 1 del otro horario
                    if (array[3].equals(array4[0])) {

                        //Horas del día 2
                        int init1 = Integer.parseInt(initHour2[0]);
                        int final1 = Integer.parseInt(finalHour2[0]);
                        int init2 = Integer.parseInt(initHour3[0]);
                        int final2 = Integer.parseInt(finalHour3[0]);

                        //Si las horas chocan el horario no es válido
                        if (init1 >= init2 && init1 <= final2) {
                            validSchedule = false;
                        } else if (init2 >= init1 && init2 <= final1) {
                            validSchedule = false;
                        } else if (final1 >= init2 && final1 <= final2) {
                            validSchedule = false;
                        } else if (final2 >= init1 && final2 <= final1) {
                            validSchedule = false;
                        }
                    }
                    //Se sale del for una vez encuentra un choque de horarios
                    if (!validSchedule) {

                        i = coursesAddedData.size();
                    }

                }
            }
            if (validSchedule) {
                coursesAddedData.add(aux);
                initTableAddedCourses();

                if (courseEnrollmentPosition >= 0) {
                    coursesEnrollmentData.remove(courseEnrollmentPosition);

                    if (courseEnrollmentPosition < 0) {
                        txtIDcourseEnroll.setText("");
                        courseToEnroll = null;
                    }
                }
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Matrícula");
                alert.setHeaderText("Existe un choque de horarios, imposible agregar.");
                alert.showAndWait();
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

    private void initTableEnrolledCourses() {

        courseIDEnrolledCoursesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        nameEnrolledCoursesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        creditsEnrolledCoursesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        scheduleEnrolledCoursesCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        periodEnrolledCoursesCol1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(4)); //To change body of generated methods, choose Tools | Templates.
            }
        });

        ObservableList<List<String>> aux = FXCollections.observableArrayList();

        int totalCourses = 0, credits = 0;
        if (!enrollmentList.isEmpty()) {

            try {
                for (int i = 1; i <= enrollmentList.size(); i++) {

                    //Matrícula actual
                    Enrollment e1 = (Enrollment) enrollmentList.getNode(i).data;
                    //Matrícula a buscar
                    Enrollment e2 = new Enrollment(new Date(), studentEnrollment.get(1), "", "", 0);

                    if (util.Utility.equals(e1, e2)) {

                        //Obtengo todos los datos del curso matriculado
                        Course c = (Course) courseList.getNode(courseList.indexOf(new Course(e1.getCourseID(), "", 0, 0))).data;
                        //Obtengo los datos del horario asignado
                        TimeTable t = (TimeTable) timeTableList.getNode(timeTableList.indexOf(new TimeTable(c.getId(), "", "", ""))).data;
                        List<String> list = new ArrayList<>();

                        //Agrego a la tabla
                        list.add(c.getId());
                        list.add(c.getName());
                        list.add(c.getCredits() + "");
                        list.add(e1.getSchedule());
                        list.add(t.getPeriod());
                        aux.add(list);

                        credits += c.getCredits();
                        totalCourses++;
                        txtDateEnrollment.setText(util.Utility.dateFormat(e1.getDate()));

                    }

                }
                txtTotalCoursesEnrollment.setText(totalCourses + "");
                txtCreditsCoursesEnrollment.setText(credits + "");

            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tableEnrolledCourses.setItems(aux);
    }

    private void initTableDeEnrollCourses() {

        nameCourseDeEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        courseIdDenrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        creditsDeEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        scheduleDeEnrollmentCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        final ObservableList<List<String>> coursesDeEnrollmentCell = tableCoursesToDeroll.getSelectionModel().getSelectedItems();
        coursesDeEnrollmentCell.addListener(selectTableCoursesToDeEnroll);

        if (!enrollmentList.isEmpty()) {

            try {
                Enrollment e2 = new Enrollment(new Date(), tfSearchStudentDeroll.getText(), "", "", 0);
                e2 = (Enrollment) enrollmentList.getNode(enrollmentList.indexOf(e2)).data;
                tfSearchStudentDeroll.setText("");
                for (int i = 1; i <= enrollmentList.size(); i++) {

                    //Matrícula actual
                    Enrollment e1 = (Enrollment) enrollmentList.getNode(i).data;

                    if (util.Utility.equals(e1, e2)) {

                        //Obtengo todos los datos del curso matriculado
                        Course c = (Course) courseList.getNode(courseList.indexOf(new Course(e1.getCourseID(), "", 0, 0))).data;

                        List<String> list = new ArrayList<>();

                        //Agrego a la tabla
                        list.add(c.getId());
                        list.add(c.getName());
                        list.add(c.getCredits() + "");
                        list.add(e1.getSchedule());
                        deEnrollCoursesData.add(list);
                    }

                }

            } catch (ListException ex) {
            }
        }
        tableCoursesToDeroll.setItems(deEnrollCoursesData);
    }

}
