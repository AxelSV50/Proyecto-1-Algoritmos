/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Career;
import domain.list.CircularDoublyLinkedList;
import domain.Course;
import domain.DeEnrollment;
import domain.Enrollment;
import domain.list.DoublyLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Usuario
 */
public class FileManagementEnrollement {

    private static String nameFileEnrollment = "Enrollment.txt";
    private static String nameFileDeEnrollment = "DeEnrollment.txt";

    public static boolean add(Enrollment enrollment) {
        try {

            File f1 = new File(nameFileEnrollment);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(enrollment.toString());
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

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

                    String[] array2 = array[3].split("/");
                    Calendar c = new GregorianCalendar(Integer.parseInt(array2[2]), Integer.parseInt(array2[1]) - 1, Integer.parseInt(array2[0]));
                    list.add(new Enrollment(c.getTime(), array[0], array[1], array[2], Integer.parseInt(array[4])));
                }
                br.close();

            }
        } catch (IOException | NumberFormatException ex) {
        }
        return list;
    }

    public static boolean overwriteEnrollmentFile(CircularDoublyLinkedList list) {
        try {

            File f1 = new File(nameFileEnrollment);
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

                        Enrollment c = (Enrollment) list.getNode(i).data;
                        bw.write(c.toString());
                    }
                }
                bw.close();

            }

        } catch (Exception e) {

        }
        return true;
    }
    //MÉTODOS DE RETIRO DE CURSOS:

    public static CircularDoublyLinkedList getDeEnrollmentList() {

        CircularDoublyLinkedList list = new CircularDoublyLinkedList();

        try {

            File f1 = new File(nameFileDeEnrollment);
            String array[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {

                    array = line.split("~");//la '~' se designó para separar los elementos del fichero

                    String[] array2 = array[3].split("/");
                    Calendar c = new GregorianCalendar(Integer.parseInt(array2[2]), Integer.parseInt(array2[1]) - 1, Integer.parseInt(array2[0]));
                    list.add(new DeEnrollment(c.getTime(), array[0], array[1], array[2], Integer.parseInt(array[4]), array[5]));
                }
                br.close();

            }
        } catch (IOException | NumberFormatException ex) {
        }
        return list;
    }

    public static boolean add(DeEnrollment deEnrollment) {
        try {

            File f1 = new File(nameFileDeEnrollment);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(deEnrollment.toString());
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static boolean overwriteDeEnrollmentFile(CircularDoublyLinkedList list) {
        try {

            File f1 = new File(nameFileDeEnrollment);
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

                        DeEnrollment c = (DeEnrollment) list.getNode(i).data;
                        bw.write(c.toString());
                    }
                }
                bw.close();

            }

        } catch (Exception e) {

        }
        return true;
    }

}
