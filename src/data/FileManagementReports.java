package data;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import domain.Career;
import domain.Course;
import domain.DeEnrollment;
import domain.Enrollment;
import domain.Student;
import domain.TimeTable;
import domain.list.CircularDoublyLinkedList;
import domain.list.DoublyLinkedList;
import domain.list.ListException;
import domain.list.SinglyLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.util.Date;
import main.career.CareerFXMLController;

public class FileManagementReports {

    private static String fileReport = "Report.txt";

    public static String getNameFileAdmin() {
        return fileReport;
    }

    public static boolean addReportEnrollement() {

        try {
            CircularDoublyLinkedList enrollmentList = util.Utility.getEnrollmentList();
            SinglyLinkedList tableList = util.Utility.getTimeTableList();
            CircularDoublyLinkedList courseList = util.Utility.getCoursesList();
            SinglyLinkedList studentList = util.Utility.getStudentsList();
            DoublyLinkedList careersList = util.Utility.getCareersList();

            File f = new File("ReportsEnrollment.txt");

            if (!f.exists()) {
                f.createNewFile();
            }
            f.deleteOnExit();
            try {
                FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("                                LISTA DE ESTUDIANTES\n\n");

                if (!enrollmentList.isEmpty()) {
                    for (int i = 1; i <= studentList.size(); i++) {
                        Student s = (Student) studentList.getNode(i).data;
                        Career c = (Career) careersList.getNode(careersList.indexOf(new Career(s.getCareerID(), ""))).data;
                        bw.write("Estudiante #" + i + "\n\nC??dula : " + String.valueOf(s.getId()) + "\n"
                                + "Carn?? : " + s.getStudentID() + "\n"
                                + "Nombre : " + s.getFirstname() + "\n"
                                + "Apellido : " + s.getLastname() + "\n"
                                + "Fecha de nacimiento : " + util.Utility.dateFormat(s.getBirthday()) + "\n"
                                + "N??mero de telefono : " + s.getPhoneNumber() + "\n"
                                + "Email : " + s.getEmail() + "\n"
                                + "Direcci??n : " + s.getAddress() + "\n"
                                + "Carrera : " + c.getDescription() + "\n");
                        bw.write("______________________________________________________"
                                + "\n                               CURSOS MATRICULADOS:\n\n");
                        if (enrollmentList.contains(new Enrollment(null, s.getStudentID(), "", "", 0))) {

                            for (int j = 1; j <= enrollmentList.size(); j++) {

                                Enrollment e = (Enrollment) enrollmentList.getNode(j).data;
                                if (e.getStudentID().equalsIgnoreCase(s.getStudentID())) {

                                    Course c2 = (Course) courseList.getNode(courseList.indexOf(new Course(e.getCourseID(), "", 0, 0))).data;
                                    TimeTable t = (TimeTable) tableList.getNode(tableList.indexOf(new TimeTable(c2.getId(), "", "", ""))).data;
                                    bw.write("Curso : " + c2.getName() + "\n"
                                            + "Sigla : " + c2.getId() + "\n"
                                            + "Cr??ditos : " + c2.getCredits() + "\n"
                                            + "Periodo : " + t.getPeriod() + "\n"
                                            + "Horario: " + e.getSchedule() + "\n"
                                            + "______________________________________________________\n");

                                }

                            }

                        } else {
                            bw.write("                                     Sin cursos matriculados"
                                    + "\n______________________________________________________\n");
                        }

                    }
                    bw.close();

                }
            } catch (ListException ex) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean convertTextToPDF(String filename) throws Exception {//metodo encargado de convertir txt a pedf
        File file = new File(filename);
        FileInputStream fis = null;
        DataInputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            //se instacea un objeto Document
            // y se crean 2 variables string necesarias para implementar PDfWriter
            Document pdfDoc = new Document();
            String newFileName = file.getName().replace(".txt", "");
            String output_file = newFileName + ".pdf";
            
            //se crea el PdfWriter y se definen sus parametros
            PdfWriter writer = PdfWriter.getInstance(pdfDoc,
                    new FileOutputStream(output_file));
            pdfDoc.open();
            pdfDoc.setMarginMirroring(true);
            pdfDoc.setMargins(36, 72, 108, 180);
            pdfDoc.topMargin();

            //BaseFont y Font se encargan de darle formato al contenido del pdf
            BaseFont courier = BaseFont.createFont(BaseFont.HELVETICA,
                    BaseFont.CP1257, BaseFont.EMBEDDED);
            Font myfont = new Font(courier);
            Font bold_font = new Font();
            bold_font.setStyle(Font.BOLD);
            bold_font.setSize(13);
            myfont.setStyle(Font.NORMAL);
            myfont.setSize(13);

            pdfDoc.add(new Paragraph("\n"));

            if (file.exists()) {
                fis = new FileInputStream(file);
                in = new DataInputStream(fis);
                isr = new InputStreamReader(in);
                br = new BufferedReader(isr);

                String strLine;
                //con esto se agregage el cotenido linea a linea
                while ((strLine = br.readLine()) != null) {
                    Paragraph para = new Paragraph(strLine + "\n", myfont);
                    para.setAlignment(Element.ALIGN_JUSTIFIED);

                    pdfDoc.add(para);
                }
            } else {

                System.out.println("no such file exists!");
                return false;
            }
            pdfDoc.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {//verifica  fueron correctamente llenados, ==true lo cierra
            if (br != null) {
                br.close();
            }
            if (fis != null) {
                fis.close();
            }
            if (in != null) {
                in.close();
            }
            if (isr != null) {
                isr.close();
            }
        }
        return true;
    } // ConverterTxTToPDF

    public static boolean addReportDeEnrollement() {

        try {
            CircularDoublyLinkedList deenrollmentList = util.Utility.getDeEnrollmentList();
            SinglyLinkedList tableList = util.Utility.getTimeTableList();
            CircularDoublyLinkedList courseList = util.Utility.getCoursesList();
            SinglyLinkedList studentList = util.Utility.getStudentsList();
            DoublyLinkedList careersList = util.Utility.getCareersList();

            File f = new File("ReportsDeEnrollment.txt");

            if (!f.exists()) {
                f.createNewFile();
            }
            f.deleteOnExit();
            try {
                FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("                                LISTA DE ESTUDIANTES\n\n");

                if (!deenrollmentList.isEmpty()) {
                    for (int i = 1; i <= studentList.size(); i++) {
                        Student s = (Student) studentList.getNode(i).data;
                        Career c = (Career) careersList.getNode(careersList.indexOf(new Career(s.getCareerID(), ""))).data;
                        bw.write("Estudiante #" + i + "\n\nC??dula : " + String.valueOf(s.getId()) + "\n"
                                + "Carn?? : " + s.getStudentID() + "\n"
                                + "Nombre : " + s.getFirstname() + "\n"
                                + "Apellido : " + s.getLastname() + "\n"
                                + "Fecha de nacimiento : " + util.Utility.dateFormat(s.getBirthday()) + "\n"
                                + "N??mero de telefono : " + s.getPhoneNumber() + "\n"
                                + "Email : " + s.getEmail() + "\n"
                                + "Direcci??n : " + s.getAddress() + "\n"
                                + "Carrera : " + c.getDescription() + "\n");
                        bw.write("______________________________________________________"
                                + "\n                               CURSOS RETIRADOS:\n\n");
                        
                        
                        if (deenrollmentList.contains(new DeEnrollment(null, s.getStudentID(), "", "", 0, ""))) {

                            
                            for (int j = 1; j <= deenrollmentList.size(); j++) {

                                DeEnrollment e = (DeEnrollment) deenrollmentList.getNode(j).data;
                                
                                if (e.getStudentID().equalsIgnoreCase(s.getStudentID())) {

                                    Course c2 = (Course) courseList.getNode(courseList.indexOf(new Course(e.getCourseID(), "", 0, 0))).data;
                                    TimeTable t = (TimeTable) tableList.getNode(tableList.indexOf(new TimeTable(c2.getId(), "", "", ""))).data;
                                    bw.write("Curso : " + c2.getName() + "\n"
                                            + "Sigla : " + c2.getId() + "\n"
                                            + "Cr??ditos : " + c2.getCredits() + "\n"
                                            + "Periodo : " + t.getPeriod() + "\n"
                                            + "Horario: " + e.getSchedule() + "\n"
                                            + "Motivo del retiro: " + e.getRemark() + "\n"
                                            + "______________________________________________________\n");

                                }
                            }

                        } else {
                            bw.write("                                     Sin cursos retirados"
                                    + "\n______________________________________________________\n");
                        }

                    }
                    bw.close();

                }
            } catch (ListException ex) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
