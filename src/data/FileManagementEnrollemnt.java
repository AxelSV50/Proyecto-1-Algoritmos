/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.CircularDoublyLinkedList;
import domain.Course;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Usuario
 */
public class FileManagementEnrollemnt {

    private static String nameFileEnrollment = "Enrollment.txt";

    public static CircularDoublyLinkedList getEnrollmentList() {
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();

        try {

            File f1 = new File(nameFileEnrollment);
            String array[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {

                    array = line.split("~");//la '~' se designó para separar los elementos del fichero

                    list.add(new Course(array[0], array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3])));
                }
                br.close();

            }
        } catch (IOException | NumberFormatException ex) {
        }
        return list;
    }

}
