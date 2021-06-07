/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Course;
import domain.Security;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author yeison
 */
public class FileManagementCourses {

    private static String nameFileCourses = "Courses.txt";

    public static String getNameFileCourses() {
        return nameFileCourses;
    }

    public static boolean add(String id, String name, int credits, int careerID, String fileName) {
        try {

            File f1 = new File(fileName);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(id + "~" + name + "~" + credits + "~" + careerID + "\n");
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static boolean overwriteCourseFile(CircularDoublyLinkedList list) {
        try {

            File f1 = new File(nameFileCourses);
            if (f1.exists()) {
                f1.delete();
            }
            f1.createNewFile();

            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (!list.isEmpty()) {
                for (int i = 1; i <= list.size(); i++) {

                    if (list.getNode(i) != null) {

                        Course c = (Course) list.getNode(i).data;
                        bw.write(c.getId() + "~" + c.getName() + "~" + c.getCredits() + "~" + c.getCareerID() + "\n");
                    }
                }
                bw.close();

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static CircularDoublyLinkedList getCoursesList() {

        CircularDoublyLinkedList list = new CircularDoublyLinkedList();

        try {

            File f1 = new File(nameFileCourses);
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
