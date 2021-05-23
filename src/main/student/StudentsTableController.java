/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.student;

import domain.Career;
import domain.ListException;
import domain.SinglyLinkedList;
import domain.Student;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.career.CareerFXMLController;


/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class StudentsTableController implements Initializable {

    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private TableColumn<Student, Integer> colId;
    @FXML
    private TableColumn<Student, String> colStudentId;
    @FXML
    private TableColumn<Student, String> colName;
    @FXML
    private TableColumn<Student, String> colLastName;
    @FXML
    private TableColumn<Student, String> colBirthday;
    @FXML
    private TableColumn<Student, String> colPhoneNumber;
    @FXML
    private TableColumn<Student, String> colEmail;
    @FXML
    private TableColumn<Student, String> colAddress;
    @FXML
    private TableColumn<Student, String> colCareer;
    @FXML
    private TableColumn<Student, Integer> colIdCarrer;

    private SinglyLinkedList studentList = util.Utility.getStudentsList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         colAddress.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
         colBirthday.setCellValueFactory(new PropertyValueFactory<Student, String>("formatedDate"));
         colCareer.setCellValueFactory(new PropertyValueFactory<Student, String>("careerDescription"));
         colEmail.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
         colId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
         colLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
         colName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
         colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));
         colStudentId.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
         colIdCarrer.setCellValueFactory(new PropertyValueFactory<Student, Integer>("careerID"));
         
        ObservableList<Student> tableContent = FXCollections.observableArrayList();

        if (!studentList.isEmpty()) {

            try {
                for (int i = 1; i <= studentList.size(); i++) {

                    Student a = (Student) studentList.getNode(i).data;
                    tableContent.add(a);
                }
            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        studentsTable.setItems(tableContent);
    } 
    
}
