/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import domain.DoublyLinkedList;
import domain.Security;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author yeison
 */
public class FileCareer {
    private static String nameFileCareer = "Career.txt";

    public static String getNameFileCareer() {
        return nameFileCareer;
    }

    public static boolean add(int id, String  description, String fileName) {
        try {

            File f1 = new File(fileName);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(id + "," + description + "\n");
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }
    public static DoublyLinkedList getCareer(String fileName) { 
          DoublyLinkedList list = new DoublyLinkedList();

        try {

            File f1 = new File(fileName);
            String array[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {
                    array = line.split(",");//la ',' se designó para separar los elementos del fichero

                    if (fileName.equals(nameFileCareer)) {
                        
                        list.add(array[0] + "," + array[1]);
                        
                    } else {
                        
                        list.add(array[0] + "," + array[9]);
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
}