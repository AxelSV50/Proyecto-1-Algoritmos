/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.Career;
import domain.DoublyLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author yeison
 */
public class FileManagementCareers {

    private static String nameFileCareer = "Careers.txt";
    
    
    public static boolean add(int id, String description) {
        try {

            File f1 = new File(nameFileCareer);

            if (!f1.exists()) {
                f1.createNewFile();
            }
            //Abre un flujo de escritua a el fichero
            FileWriter fw = new FileWriter(f1, true);
            BufferedWriter bw = new BufferedWriter(fw);
            //Escribe los parámetros
            bw.write(id + "," + description + "\n");
            bw.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return true;
    }

    public static DoublyLinkedList getCareers() {

        DoublyLinkedList list = new DoublyLinkedList();

        try {

            File f1 = new File(nameFileCareer);
            String array[];

            if (f1.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(f1));
                String line;
                while ((line = br.readLine()) != null) {
                    array = line.split(",");//la ',' se designó para separar los elementos del fichero

                    list.add(new Career(Integer.parseInt(array[0]), array[1]));
                }
                br.close();
            } else {
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;

    }

    public static boolean overwriteCareersFile(DoublyLinkedList list) {
        try {

            File f1 = new File(nameFileCareer);
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
                        
                        Career c = (Career) list.getNode(i).data;
                        bw.write(c.getId() + "," + c.getDescription() + "\n");
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
