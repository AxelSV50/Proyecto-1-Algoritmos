/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.student;

import data.FileManagementUsers;
import domain.Career;
import domain.list.DoublyLinkedList;
import domain.list.ListException;
import domain.list.SinglyLinkedList;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import main.FXMain;
import main.career.CareerFXMLController;

/**
 * FXML Controller class
 *
 * @author carlo
 */
public class StudentFXMLController implements Initializable {

    @FXML
    private AnchorPane displayPanel;
    @FXML
    private Pane navPanel;
    @FXML
    private Button btnShowStudent;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnAddStudent;
    @FXML
    private Button btnModifyStudent;
    @FXML
    private Button btnBack;
    @FXML
    private Pane addStudentPanel;
    @FXML
    private Button btnDeleteStudent;
    @FXML
    private Pane modifyPanel;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;
    @FXML
    private Pane panelDeleteCareer;
    @FXML
    private TextField tfDeleteStudent;
    @FXML
    private Text txtTitle;
    @FXML
    private TextField tfSearchStudentUpdate;
    @FXML
    private Text txtCareerSearchUpdate;
    @FXML
    private Button btnSearchStudentUpdate;
    @FXML
    private TextField tfIdModify;
    @FXML
    private Button btnCancelModify;
    @FXML
    private TextField tfStudentIdModify;
    @FXML
    private TextField tfNameModify;
    @FXML
    private TextField tfLastNameModify;
    @FXML
    private TextField tfPhoneModify;
    @FXML
    private TextField tfEmailModify;
    @FXML
    private TextField tfAdrressModify;
    @FXML
    private DatePicker datePickerBirthdayModify;
    @FXML
    private ComboBox<String> comboBoxStudentModify;
    @FXML
    private BorderPane bp;
    @FXML
    private ComboBox<String> cbCareers;
    @FXML
    private Pane panelDelete;
    @FXML
    private TextField tfAddCarnet;
    @FXML
    private TextField tfAddNameStudent;
    @FXML
    private TextField tfAddLastName;
    @FXML
    private TextField tfAddPhoneNumber;
    @FXML
    private TextField tfAddEmail;
    @FXML
    private DatePicker dpAddBirthday;
    @FXML
    private TextField tfAddId;
    @FXML
    private TextField tfAddAddress;
    @FXML
    private Text txtError;
    @FXML
    private Text txtSuccess;
    @FXML
    private TextField tfAddPassword;

    //Copiar esto
    private DoublyLinkedList careersList = util.Utility.getCareersList();
    private SinglyLinkedList studentList = util.Utility.getStudentsList();

    @FXML
    private Button btnModify;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtTitle.setText("Agregar estudiante");

        //Pasarle el nombre del comboBox
        initComboBox(cbCareers);
        initComboBox(comboBoxStudentModify);
    }

    //Copiar este método
    private void initComboBox(ComboBox<String> cbCareers) {
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

        cbCareers.setItems(tableContent);
        cbCareers.setVisibleRowCount(3);

        if (cbCareers == comboBoxStudentModify) {

            cbCareers.setValue("");

        } else {

            cbCareers.setPromptText("Seleccione");
        }
    }

    @FXML
    private void btnShowStudent(ActionEvent event) {

        Stage stage = new Stage();
        loadTable(stage);
    }

    @FXML
    private void btnDelete(ActionEvent event) {

        cleanAll();
        panelDelete.setVisible(true);
        txtTitle.setText("Suprimir estudiante");
    }

    @FXML
    private void btnAddStudent(ActionEvent event) {

        if (!(tfAddCarnet.getText().equals("") || tfAddId.getText().equals("")
                || tfAddNameStudent.getText().equals("") || tfAddLastName.getText().equals("")
                || tfAddEmail.getText().equals("") || (dpAddBirthday.getValue() == null)
                || tfAddPhoneNumber.getText().equals("") || tfAddAddress.getText().equals("")
                || cbCareers.getValue() == null || tfAddPassword.getText().equals(""))) {

            int minChars = 0;

            //Cuenta los caracteres del tf
            for (int i = 0; i < tfAddPassword.getText().length(); i++) {

                minChars++;
            }

            if (minChars >= 8) {

                try {

                    //Se agrega el estudiante al archivo y a la lista:
                    String idCareer = cbCareers.getValue();
                    //Si el usuario no selecciona nada, el combo devuelve null

                    //Separa el id carrera, de su descripción
                    String[] array = idCareer.split("-");

                    Calendar c = new GregorianCalendar(dpAddBirthday.getValue().getYear(), dpAddBirthday.getValue().getMonthValue() - 1, dpAddBirthday.getValue().getDayOfMonth());

                    //Creo el estudiante a agregar
                    Student a = new Student(Integer.parseInt(tfAddId.getText()), tfAddCarnet.getText(), tfAddLastName.getText(), tfAddNameStudent.getText(),
                            c.getTime(), Integer.parseInt(tfAddPhoneNumber.getText()) + "", tfAddEmail.getText(), tfAddAddress.getText(), Integer.parseInt(array[0]));

                    //Si no lo contiene en la lista, lo agrega
                    if (studentList.isEmpty() || !studentList.contains(a)) {

                        FileManagementUsers.add(a.toString(), tfAddPassword.getText(), FileManagementUsers.getNameFileStudents());
                        studentList.add(a);

                        //Se envía el correo de bienvenida:
                        try {
                            util.Mail.sendWelcomeMessage(a);
                            cleanAll();
                            addStudentPanel.setVisible(true);
                            txtTitle.setText("Agregar estudiante");
                            txtError.setText("");
                            txtSuccess.setText("Agregado con éxito");

                        } catch (MessagingException ex) {

                            txtError.setText("El correo introducido no es válido");
                            txtSuccess.setText("");
                        }
                    } else {

                        txtError.setText("Los datos como correo, cédula y carné no deben estar en uso");
                        txtSuccess.setText("");
                    }

                } catch (NumberFormatException e) {

                    txtError.setText("Los campos cédula y/o teléfono deben ser numéricos");
                    txtSuccess.setText("");

                } catch (ListException e) {

                }

            } else {

                txtSuccess.setText("");
                txtError.setText("La contraseña debe tener al menos 8 carácteres");
            }

        } else {
            txtSuccess.setText("");
            txtError.setText("Debe ingresar todos los datos");
        }
    }

    @FXML
    private void btnModifyStudent(ActionEvent event) {

        cleanAll();
        modifyPanel.setVisible(true);
        txtTitle.setText("Modificar estudiante");
    }

    @FXML
    private void btnBack(ActionEvent event) {

        bp.setVisible(false);
    }

    @FXML
    private void btnDeleteStudent(ActionEvent event) {

        studentList = util.Utility.getStudentsList();

        if (!tfDeleteStudent.getText().equals("")) {

            try {
                Student a = new Student(0, tfDeleteStudent.getText(), "", "", new Date(), "", "", "", 0);

                if (studentList.contains(a)) {

                    studentList.remove(a);
                    FileManagementUsers.overwriteStudentsFile(studentList);
                    studentList = util.Utility.getStudentsList();
                    txtSuccess.setText("Eliminado correctamente");
                    txtError.setText("");
                    tfDeleteStudent.setText("");

                } else {
                    txtSuccess.setText("");
                    txtError.setText("Estudiante no agregado");
                }
            } catch (ListException ex) {

                txtError.setText("No hay estudiantes agregados");
                txtSuccess.setText("");

            }
        } else {

            txtError.setText("Debe rellenar el recuadro");
            txtSuccess.setText("");
        }
    }

    @FXML
    private void btnAdd(ActionEvent event) {

        cleanAll();
        addStudentPanel.setVisible(true);
        txtTitle.setText("Agregar estudiante");
    }

    @FXML
    private void tfSearchCarrerUpdate(KeyEvent event) {
    }

    @FXML
    private void btnSearchStudentUpdate(ActionEvent event) {

        if (!(tfSearchStudentUpdate.getText().equals(""))) {

            txtSuccess.setText("");
            txtError.setText("");
            studentList = util.Utility.getStudentsList();
            try {

                Student element = new Student(0, tfSearchStudentUpdate.getText(), "", "", new Date(), "", "", "", 0);

                if (studentList.contains(element)) {

                    Student a = (Student) studentList.getNode(studentList.indexOf(element)).data;

                    tfStudentIdModify.setText(a.getId() + "");
                    tfIdModify.setText(a.getStudentID() + "");
                    tfLastNameModify.setText(a.getLastname());
                    tfEmailModify.setText(a.getEmail());
                    tfNameModify.setText(a.getFirstname());
                    tfPhoneModify.setText(a.getPhoneNumber());
                    tfAdrressModify.setText(a.getAddress());

                    int index = careersList.indexOf(new Career(a.getCareerID(), ""));
                    Career c = (Career) careersList.getNode(index).data;

                    comboBoxStudentModify.setValue(a.getCareerID() + "-" + c.getDescription());
                    datePickerBirthdayModify.getEditor().setText(util.Utility.dateFormat(a.getBirthday()));

                    tfSearchStudentUpdate.setDisable(false);
                    tfSearchStudentUpdate.setText("");

                    tfIdModify.setDisable(true);
                    tfStudentIdModify.setDisable(true);
                    tfLastNameModify.setDisable(false);
                    tfEmailModify.setDisable(false);
                    tfNameModify.setDisable(false);
                    tfPhoneModify.setDisable(false);
                    tfAdrressModify.setDisable(false);
                    btnCancelModify.setDisable(false);
                    comboBoxStudentModify.setDisable(false);
                    btnModify.setDisable(false);
                    datePickerBirthdayModify.setDisable(false);

                } else {

                    txtError.setText("Estudiante no encontrado");
                    txtSuccess.setText("");
                }

            } catch (ListException ex) {

                txtError.setText("No hay estudiantes agregados");
                txtSuccess.setText("");

            }
        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtSuccess.setText("");

        }

    }

    @FXML
    private void btnCancelModify(ActionEvent event) {
        txtSuccess.setText("");
        txtError.setText("");
        tfSearchStudentUpdate.setText("");
        tfIdModify.setText("");
        tfStudentIdModify.setText("");
        tfLastNameModify.setText("");
        tfEmailModify.setText("");
        tfNameModify.setText("");
        tfPhoneModify.setText("");
        tfAdrressModify.setText("");
        comboBoxStudentModify.setValue("");
        datePickerBirthdayModify.getEditor().setText("");

        tfSearchStudentUpdate.setDisable(false);
        tfIdModify.setDisable(true);
        tfStudentIdModify.setDisable(true);
        tfLastNameModify.setDisable(true);
        tfEmailModify.setDisable(true);
        tfNameModify.setDisable(true);
        tfPhoneModify.setDisable(true);
        tfAdrressModify.setDisable(true);
        btnSearchStudentUpdate.setDisable(false);
        btnCancelModify.setDisable(true);
        comboBoxStudentModify.setDisable(true);
        btnModify.setDisable(true);
        datePickerBirthdayModify.setDisable(true);

    }

    private void cleanAll() {

        addStudentPanel.setVisible(false);
        modifyPanel.setVisible(false);
        panelDelete.setVisible(false);
        txtTitle.setText("");
        tfAddAddress.setText("");
        tfAddCarnet.setText("");
        tfAddLastName.setText("");
        tfAddNameStudent.setText("");
        tfAddPassword.setText("");
        tfAddId.setText("");
        tfAddPhoneNumber.setText("");
        tfAddEmail.setText("");
        txtSuccess.setText("");
        txtError.setText("");
        dpAddBirthday.setValue(null);

    }

    private void loadTable(Stage primaryStage) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("StudentsTable.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Estudiantes registrados");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tfAddId(KeyEvent event) {

        txtSuccess.setText("");

        try {

            char c = event.getText().charAt(0);

            if (!Character.isDigit(c)) {

                tfAddId.setEditable(false);

            } else {

                tfAddId.setEditable(true);
            }

        } catch (Exception e) {

            tfAddId.setEditable(true);
        }

    }

    @FXML
    private void tfAddPhoneNumber(KeyEvent event) {

        txtSuccess.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (!Character.isDigit(c)) {

                tfAddPhoneNumber.setEditable(false);

            } else {

                tfAddPhoneNumber.setEditable(true);
            }

        } catch (Exception e) {

            tfAddPhoneNumber.setEditable(true);

        }
    }

    @FXML
    private void tfAddCarnet(KeyEvent event) {

        txtSuccess.setText("");
    }

    @FXML
    private void tfDeleteStudent(KeyEvent event) {

        txtSuccess.setText("");
    }

    @FXML
    private void btnCancel(ActionEvent event) {

        tfAddAddress.setText("");
        tfAddCarnet.setText("");
        tfAddLastName.setText("");
        tfAddNameStudent.setText("");
        tfAddPassword.setText("");
        tfAddId.setText("");
        tfAddPhoneNumber.setText("");
        tfAddEmail.setText("");
        txtSuccess.setText("");
        txtError.setText("");
        dpAddBirthday.setValue(null);

    }

    @FXML
    private void btnModify(ActionEvent event) {

        if (!(tfIdModify.getText().equals("") || tfStudentIdModify.getText().equals("")
                || tfLastNameModify.getText().equals("")
                || tfNameModify.getText().equals("") || tfPhoneModify.getText().equals("")
                || tfEmailModify.getText().equals("") || tfAdrressModify.getText().equals("")
                || comboBoxStudentModify.getValue() == null)) {

            try {

                Student oldElement = new Student(Integer.parseInt(tfStudentIdModify.getText()), tfSearchStudentUpdate.getText(), "", "", new Date(), "", "", "", 0);
                Calendar c = null;

                if (datePickerBirthdayModify.getValue() != null) {

                    c = new GregorianCalendar(datePickerBirthdayModify.getValue().getYear(), datePickerBirthdayModify.getValue().getMonthValue() - 1, datePickerBirthdayModify.getValue().getDayOfMonth());
                } else {

                    String array[] = datePickerBirthdayModify.getEditor().getText().split("/");
                    c = new GregorianCalendar(Integer.parseInt(array[2]), Integer.parseInt(array[1]) - 1, Integer.parseInt(array[0]));
                }

                String array[] = comboBoxStudentModify.getValue().split("-");
                Student newElement = new Student(Integer.parseInt(tfStudentIdModify.getText()), tfIdModify.getText(), tfLastNameModify.getText(),
                        tfNameModify.getText(), c.getTime(), tfPhoneModify.getText(), tfEmailModify.getText(), tfAdrressModify.getText(), Integer.parseInt(array[0]));

                Student a = (Student) studentList.getNode(studentList.indexOf(oldElement)).data;

                boolean validEmail = true;
                int count = 0;

                for (int i = 1; i <= studentList.size(); i++) {

                    Student aux = (Student) studentList.getNode(i).data;

                    if (aux.getEmail().equals(tfEmailModify.getText())) {

                        count++;
                    }
                }

                if (tfEmailModify.getText().equals(a.getEmail())) {
                    count--;
                }
                
                if (count == 0) {

                    util.Mail.sendModifyMessage(newElement);

                    studentList.modify(studentList.indexOf(oldElement), newElement);
                    FileManagementUsers.overwriteStudentsFile(studentList);
                    txtError.setText("");
                    tfSearchStudentUpdate.setText("");
                    tfIdModify.setText("");
                    tfStudentIdModify.setText("");
                    tfLastNameModify.setText("");
                    tfEmailModify.setText("");
                    tfNameModify.setText("");
                    tfPhoneModify.setText("");
                    tfAdrressModify.setText("");
                    comboBoxStudentModify.setValue("");
                    datePickerBirthdayModify.getEditor().setText("");

                    tfSearchStudentUpdate.setDisable(false);
                    tfIdModify.setDisable(true);
                    tfStudentIdModify.setDisable(true);
                    tfLastNameModify.setDisable(true);
                    tfEmailModify.setDisable(true);
                    tfNameModify.setDisable(true);
                    tfPhoneModify.setDisable(true);
                    tfAdrressModify.setDisable(true);
                    btnSearchStudentUpdate.setDisable(false);
                    btnCancelModify.setDisable(true);
                    comboBoxStudentModify.setDisable(true);
                    btnModify.setDisable(true);
                    datePickerBirthdayModify.setDisable(true);

                    txtSuccess.setText("Estudiante modificado correctamente");

                }else{
                    
                    txtError.setText("El nuevo correo ya está en uso");
                txtSuccess.setText("");
                }

            } catch (ListException ex) {

                txtError.setText("No hay estudiantes agregados");
                txtSuccess.setText("");
            } catch (MessagingException ex) {

                txtError.setText("El correo no es válido");
                txtSuccess.setText("");
            }
        } else {

            txtError.setText("Debe rellenar todos los recuadros");
            txtSuccess.setText("");
        }

    }

    @FXML
    private void tfSearchStudentUpdate(ActionEvent event) {

        txtSuccess.setText("");

    }

    @FXML
    private void tfAddEmail(KeyEvent event) {
        
         txtSuccess.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (Character.isSpaceChar(c)) {

                tfAddEmail.setEditable(false);

            } else {

                tfAddEmail.setEditable(true);
            }

        } catch (Exception e) {

            tfAddEmail.setEditable(true);

        }
    }

    @FXML
    private void tfEmailModify(KeyEvent event) {
        
        txtSuccess.setText("");

        try {

            char c = event.getText().charAt(0);

            //isDigit permite sólo números, isAphabetic permite sólo letras
            if (Character.isSpaceChar(c)) {

                tfEmailModify.setEditable(false);

            } else {

                tfEmailModify.setEditable(true);
            }

        } catch (Exception e) {

            tfEmailModify.setEditable(true);

        }
    }

}
