/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Career;
import domain.CircularLinkedList;
import domain.DoublyLinkedList;
import domain.Security;
import domain.SinglyLinkedList;
import domain.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//

/**
 *
 * @author Usuario
 */
public class FileManagementUsers {

    private static String nameFileAdmin = "Administration.txt";
    private static String nameFileStudents = "Students.txt";

    public static String getNameFileAdmin() {
        return nameFileAdmin;
    }

    public static String getNameFileStudents() {
        return nameFileStudents;
    }

    public static boolean add(String userData, String password, String fileName) {
        try {

            File f1 = new File(fileName);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(userData + "~" + Security.encrypt(password) + "\n");
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static SinglyLinkedList getStudentsList() {

        SinglyLinkedList list = new SinglyLinkedList();

        try {

            File f1 = new File(nameFileStudents);
            String array[];
            String array2[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {

                    array = line.split("~");//la '~' se designó para separar los elementos del fichero
                    array2 = array[4].split("/");
                    Calendar c = new GregorianCalendar(Integer.parseInt(array2[2]), Integer.parseInt(array2[1])-1, Integer.parseInt(array2[0]));
                    list.add(new Student(Integer.parseInt(array[1]), array[0], array[2], array[3], c.getTime(), array[5], array[6], array[7], Integer.parseInt(array[8])));
                }
                br.close();

            }
        } catch (IOException | NumberFormatException ex) {
        }
        return list;

    }

    public static CircularLinkedList getDataLogin(String fileName) {

        CircularLinkedList list = new CircularLinkedList();

        try {

            File f1 = new File(fileName);
            String array[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {
                    array = line.split("~");//la '/' se designó para separar los elementos del fichero

                    if (fileName.equals(nameFileAdmin)) {

                        list.add(array[0] + "~" + array[1]);

                    } else {

                        list.add(array[0] + "~" + array[9]);
                    }
                }
                br.close();
            } else {
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
   public static boolean overwriteStudentsFile(SinglyLinkedList list) {
        try {

            File f1 = new File(nameFileStudents);
            CircularLinkedList list2 = getDataLogin(nameFileStudents);
            
            if (f1.exists()) {
                f1.delete();
            }
            f1.createNewFile();

            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (!list.isEmpty()) {
                for (int i = 1; i <= list.size(); i++) {

                    if (list.getNode(i) != null && list2.getNode(i) != null) {
                        
                        Student c = (Student) list.getNode(i).data;
                        String array[] = list2.getNode(i).data.toString().split("~");
                        bw.write(c.toString()+"~"+array[1]+"\n");
                    }
                }
                bw.close();

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }
}
