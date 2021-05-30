/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.SinglyLinkedList;
import domain.TimeTable;
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
public class FileManagementTimeTable {
    private static String nameFileTime = "TimeTable.txt";

    public static String getNameFileTime() {
        return nameFileTime;
    }
    public static boolean add( String courseID, String period,String schedule1,String schedule2, String fileName) {
        try {

            File f1 = new File(fileName);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(courseID + "~" + period + "~" + schedule1 + "~" + schedule2 + "\n");
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

public static boolean overwriteTimetableFile(SinglyLinkedList list) {
        try {

            File f1 = new File(nameFileTime);
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
                        
                        TimeTable t = (TimeTable) list.getNode(i).data;
                        bw.write(t.getCourseID() + "~" + t.getPeriod() + "~" + t.getSchedule1()+ "~" +t.getSchedule2()+ "\n");
                    }
                }
                bw.close();

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }
 public static SinglyLinkedList getTimeTableList() {

      SinglyLinkedList list = new SinglyLinkedList();

        try {

            File f1 = new File(nameFileTime);
            String array[];
  

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {

                    array = line.split("~");//la '~' se designó para separar los elementos del fichero
                    
                    list.add(new TimeTable(array[0], array[1], array[2], array[3]));
                }
                br.close();

            }
        } catch (IOException | NumberFormatException ex) {
        }
        return list;

    }
    
   }

