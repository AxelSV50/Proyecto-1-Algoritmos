/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Student {

    private int id;
    private String studentID;
    private String lastname;
    private String firstname;
    private Date birthday;
    private String formatedDate;
    private String phoneNumber;
    private String email;
    private String address;
    private int careerID;
    //Copiar
    private String careerDescription;

    public Student(int id, String studentID, String lastname, String firstname, Date birthday, String phoneNumber, String email, String address, int careerID) {

        this.id = id;
        this.studentID = studentID;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthday = birthday;

        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.careerID = careerID;
        this.formatedDate = util.Utility.dateFormat(birthday);
        this.careerDescription = getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCareerID() {
        return careerID;
    }

    public void setCareerID(int careerID) {
        this.careerID = careerID;
    }

    public String getFormatedDate() {
        return formatedDate;
    }

    //Copiar
    private String getDescription() {

        DoublyLinkedList list = util.Utility.getCareersList();
        int index;
        String description = null;
        try {
            if (list.contains(new Career(careerID, ""))) {
                
                index = list.indexOf(new Career(careerID, ""));
                Career c = (Career) list.getNode(index).data;
                description = c.getDescription();
            }

        } catch (ListException ex) {
        }

        return description;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    @Override
    public String toString() {
        return studentID + "~" + id + "~" + lastname + "~" + firstname + "~" + formatedDate + "~" + phoneNumber + "~" + email + "~" + address + "~" + careerID;
    }

}
