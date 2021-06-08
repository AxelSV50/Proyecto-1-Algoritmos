/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.student;

import domain.Career;
import domain.list.DoublyLinkedList;
import domain.list.ListException;
import domain.list.SinglyLinkedList;
import domain.Student;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import main.career.CareerFXMLController;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class StudentsTableController implements Initializable {

    @FXML
    private TableView<List<String>> studentsTable;
    @FXML
    private TableColumn<List<String>, String> colId;
    @FXML
    private TableColumn<List<String>, String> colStudentId;
    @FXML
    private TableColumn<List<String>, String> colName;
    @FXML
    private TableColumn<List<String>, String> colLastName;
    @FXML
    private TableColumn<List<String>, String> colBirthday;
    @FXML
    private TableColumn<List<String>, String> colPhoneNumber;
    @FXML
    private TableColumn<List<String>, String> colEmail;
    @FXML
    private TableColumn<List<String>, String> colAddress;
    @FXML
    private TableColumn<List<String>, String> colCareer;

    private SinglyLinkedList studentList = util.Utility.getStudentsList();
    private DoublyLinkedList careersList = util.Utility.getCareersList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        colAddress.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colBirthday.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colCareer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colEmail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(4)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colLastName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(5)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(6)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colPhoneNumber.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(7)); //To change body of generated methods, choose Tools | Templates.
            }
        });
        colStudentId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(8)); //To change body of generated methods, choose Tools | Templates.
            }
        });

        //Lista que guarda toda la infomación del archivo
        ObservableList<List<String>> tableContent = FXCollections.observableArrayList();

        if (!studentList.isEmpty()) {

            try {
                for (int i = 1; i <= studentList.size(); i++) {

                    //La información de cada fila se guardará en listas
                    List<String> arrayList = new ArrayList<>();
                    //Se crea el estudiante que irá en la fila i apartir de los datos de studentsList
                    Student a = (Student) studentList.getNode(i).data;
                    //Se busca el índice de la carrera en la lista CareersList a la cual pertenece ese estudiante
                    int index = careersList.indexOf(new Career(a.getCareerID(), ""));
                    Career c = (Career) careersList.getNode(index).data;
                    //Agregmos todos los datos al arrayList
                    arrayList.add(a.getAddress());
                    arrayList.add(util.Utility.dateFormat(a.getBirthday()));
                    arrayList.add(c.getDescription());
                    arrayList.add(a.getEmail());
                    arrayList.add(a.getId() + "");
                    arrayList.add(a.getLastname());
                    arrayList.add(a.getFirstname());
                    arrayList.add(a.getPhoneNumber());
                    arrayList.add(a.getStudentID());

                    tableContent.add(arrayList);
                }
            } catch (ListException ex) {
                Logger.getLogger(CareerFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Establecemos los datos 
        studentsTable.setItems(tableContent);
    }

}
