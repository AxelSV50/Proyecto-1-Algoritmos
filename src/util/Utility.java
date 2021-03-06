/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Career;
import domain.list.CircularDoublyLinkedList;
import domain.Course;
import domain.DeEnrollment;
import domain.Enrollment;
import domain.list.DoublyLinkedList;
import domain.list.SinglyLinkedList;
import domain.Student;
import domain.TimeTable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Profesor Lic. Gilberth Chaves Avila
 */
public class Utility {

    private static DoublyLinkedList careersList;
    private static SinglyLinkedList studentsList;
    private static CircularDoublyLinkedList coursesList;
    private static CircularDoublyLinkedList enrollmentList;
    private static CircularDoublyLinkedList deEnrollmentList;
    private static SinglyLinkedList timeTableList;

    //Tiene las carreras agregadas
    public static DoublyLinkedList getCareersList() {
        careersList = data.FileManagementCareers.getCareers();
        return careersList;
    }

    //Tiene los estudiantes agregados
    public static SinglyLinkedList getStudentsList() {
        studentsList = data.FileManagementUsers.getStudentsList();
        return studentsList;
    }

    public static CircularDoublyLinkedList getCoursesList() {
        coursesList = data.FileManagementCourses.getCoursesList();
        return coursesList;
    }

    public static SinglyLinkedList getTimeTableList() {
        timeTableList = data.FileManagementTimeTable.getTimeTableList();
        return timeTableList;
    }

    public static CircularDoublyLinkedList getEnrollmentList() {
        enrollmentList = data.FileManagementEnrollement.getEnrollmentList();
        return enrollmentList;
    }

    public static CircularDoublyLinkedList getDeEnrollmentList() {
        deEnrollmentList = data.FileManagementEnrollement.getDeEnrollmentList();
        return deEnrollmentList;
    }

    public static int random() {
        return 1 + (int) Math.floor(Math.random() * 99);
    }

    public static int random(int bound) {
        //return 1+random.nextInt(bound);
        return 1 + (int) Math.floor(Math.random() * bound);
    }

    public static String format(double value) {
        return new DecimalFormat("###,###,###,###.##")
                .format(value);
    }

    public static String $format(double value) {
        return new DecimalFormat("$###,###,###,###.##")
                .format(value);
    }

    public static String perFormat(double value) {
        //#,##0.00 '%'
        return new DecimalFormat("#,##0.00'%'")
                .format(value);
    }

    public static String dateFormat(Date value) {
        return new SimpleDateFormat("dd/MM/yyyy")
                .format(value);
    }

    public static boolean equals(Object a, Object b) {

        switch (instanceOf(a, b)) {

            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x.equals(y);
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                //return s1.compareTo(s2)==0; //OPCION 1
                return s1.equalsIgnoreCase(s2); //OPCION 2
            case "career":
                Career c1 = (Career) a;
                Career c2 = (Career) b;
                //return s1.compareTo(s2)==0; //OPCION 1
                return c1.getId() == c2.getId(); //OPCION 2
            case "student":
                Student st1 = (Student) a;
                Student st2 = (Student) b;
                //return s1.compareTo(s2)==0; //OPCION 1
                return st2.getId() == st1.getId() || st2.getEmail().equalsIgnoreCase(st1.getEmail()) || st2.getStudentID().equalsIgnoreCase(st1.getStudentID()); //OPCION 2
            case "course":
                Course co1 = (Course) a;
                Course co2 = (Course) b;
                //return s1.compareTo(s2)==0; //OPCION 1
                return co1.getId().equalsIgnoreCase(co2.getId()); //OPCION 2
            case "timeTable":
                TimeTable t1 = (TimeTable) a;
                TimeTable t2 = (TimeTable) b;
                return t1.getCourseID().equalsIgnoreCase(t2.getCourseID());
            case "enrollment":
                Enrollment en1 = (Enrollment) a;
                Enrollment en2 = (Enrollment) b;
                return en1.getStudentID().equalsIgnoreCase(en2.getStudentID());

            case "deEnrollment":
                DeEnrollment den1 = (DeEnrollment) a;
                DeEnrollment den2 = (DeEnrollment) b;
                return den1.getStudentID().equalsIgnoreCase(den2.getStudentID());
        }//revisar esta condicion
        return false; //en cualquier otro caso
    }

    private static String instanceOf(Object a, Object b) {
        if (a instanceof Integer && b instanceof Integer) {
            return "integer";
        }
        if (a instanceof String && b instanceof String) {
            return "string";
        }
        if (a instanceof Career && b instanceof Career) {
            return "career";
        }
        if (a instanceof Student && b instanceof Student) {
            return "student";
        }
        if (a instanceof Course && b instanceof Course) {
            return "course";
        }
        if (a instanceof TimeTable && b instanceof TimeTable) {
            return "timeTable";
        }
        if (a instanceof Enrollment && b instanceof Enrollment) {
            return "enrollment";
        }
        if (a instanceof DeEnrollment && b instanceof DeEnrollment) {
            return "deEnrollment";
        }
        return "unknown"; //desconocido
    }

    public static boolean lessT(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x < y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) < 0;

        }
        return false; //en cualquier otro caso
    }

    public static boolean greaterT(Object a, Object b) {
        switch (instanceOf(a, b)) {
            case "integer":
                Integer x = (Integer) a;
                Integer y = (Integer) b;
                return x > y;
            case "string":
                String s1 = (String) a;
                String s2 = (String) b;
                return s1.compareTo(s2) > 0;
            case "career":
                Career c1 = (Career) a;
                Career c2 = (Career) b;
                return c1.getId() > c2.getId();

        }
        return false; //en cualquier otro caso
    }

    public static boolean equals2(Object a, Object b) {

        switch (instanceOf(a, b)) {

            case "enrollment":
                Enrollment en1 = (Enrollment) a;
                Enrollment en2 = (Enrollment) b;
                return en1.getStudentID().equalsIgnoreCase(en2.getStudentID()) && en2.getCourseID().equalsIgnoreCase(en2.getCourseID());
            case "course":
                Course co1 = (Course) a;
                Course co2 = (Course) b;
                //return s1.compareTo(s2)==0; //OPCION 1
                return co1.getId().equalsIgnoreCase(co2.getId()); //OPCION 2
        }//revisar esta condicion

        return false; //en cualquier otro caso
    }

}
