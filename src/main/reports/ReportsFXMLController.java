/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.reports;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import data.FileManagementReports;
import domain.list.CircularDoublyLinkedList;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class ReportsFXMLController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private ImageView pages;
    @FXML
    private javafx.scene.control.Button btnBack;

    private File[] images;
    private File[] images2;
    @FXML
    private javafx.scene.control.Button btnNextPage;
    @FXML
    private javafx.scene.control.Button btnBackPage;
    @FXML
    private Text txtNavegation;
    @FXML
    private javafx.scene.control.Button btnEnrollmentReports;
    @FXML
    private javafx.scene.control.Button btnDeEnrollmentReports;
    @FXML
    private Text txtTitle;
    @FXML
    private Pane visor1;
    @FXML
    private Pane paneButtonsEnrollment;
    @FXML
    private Pane paneButtonsDeEnrollment;
    @FXML
    private javafx.scene.control.Button btnNextPage2;
    @FXML
    private javafx.scene.control.Button btnBackPage2;
    @FXML
    private Text txtNavegation2;
    @FXML
    private Pane visor2;
    @FXML
    private ImageView pages2;

    private CircularDoublyLinkedList deEnrollmentList = util.Utility.getDeEnrollmentList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            FileManagementReports.addReportEnrollement();
            FileManagementReports.convertTextToPDF("ReportsEnrollment.txt");
        } catch (Exception ex) {
        }

        readPDF("ReportsEnrollment.pdf");
        txtNavegation.setText(numPage + "/" + totalPages);
        txtNavegation2.setText(numPage2 + "/" + totalPages2);
        File f1 = new File("ReportsEnrollment.pdf");
        f1.deleteOnExit();
        File f2 = new File("ReportsEnrollment.txt");
        f2.deleteOnExit();
        File f3 = new File("ReportsDeEnrollment.pdf");
        f3.deleteOnExit();
        File f4 = new File("ReportsDeEnrollment.txt");
        f4.deleteOnExit();
        boolean success = false;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sección de reportes");
        alert.setHeaderText("Generando los reportes...\n\n"
                + "Esto puede tardar un momento.");
        alert.showAndWait();

//        while (!success) {
//
//            try {
//                if (deEnrollmentList.isEmpty()) {
//                    
//                    pages.setImage(new Image("/data/reports/TEMP_REPORT_1.png"));
//                    success = true;
//                } else {
//                    pages.setImage(new Image("/data/reports/TEMP_REPORT_1.png"));
//                    pages2.setImage(new Image("/data/reports/TEMP_REPORT_DEENROLLMENT_1.png"));
//                    success = true;
//                }
//
//            } catch (Exception e) {
//
//                success = false;
//            }
//
//        }
    }

    @FXML
    private void btnBack(ActionEvent event) {
        bp.setVisible(false);
        File f1 = new File("ReportsEnrollment.pdf");
        f1.delete();
        File f2 = new File("ReportsEnrollment.txt");
        f2.delete();
        File f3 = new File("ReportsDeEnrollment.pdf");
        f3.delete();
        File f4 = new File("ReportsDeEnrollment.txt");
        f4.delete();
        for (int i = 0; i < images.length; i++) {

            if (images[i].exists()) {
                images[i].delete();
            }
        }

    }

    public void readPDF(String path) {
        try {
            File pdfFile = new File(path);
            while (!pdfFile.exists()) {
            }
            pdfFile.deleteOnExit();

            RandomAccessFile raf = new RandomAccessFile(pdfFile, "r");
            FileChannel channel = raf.getChannel();
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            PDFFile pdf = new PDFFile(buf);

            images = new File[pdf.getNumPages()];
            numPage = 1;

            totalPages = pdf.getNumPages();
            for (int i = 0; i < pdf.getNumPages(); i++) {
                createImage(pdf.getPage(i + 1), "src/data/reports/TEMP_REPORT_" + (i + 1) + ".png");
                images[i] = new File("src/data/reports/TEMP_REPORT_" + (i + 1) + ".png");
                images[i].deleteOnExit();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readPDF2(String path) {
        try {
            File pdfFile = new File(path);
            while (!pdfFile.exists()) {
            }
            pdfFile.deleteOnExit();
            pdfFile.deleteOnExit();
            RandomAccessFile raf = new RandomAccessFile(pdfFile, "r");
            FileChannel channel = raf.getChannel();
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            PDFFile pdf = new PDFFile(buf);
            images2 = new File[pdf.getNumPages()];
            numPage2 = 1;

            totalPages2 = pdf.getNumPages();
            for (int i = 0; i < pdf.getNumPages(); i++) {
                createImage(pdf.getPage(i + 1), "src/data/reports/TEMP_REPORT_DEENROLLMENT_" + (i + 1) + ".png");
                images2[i] = new File("src/data/reports/TEMP_REPORT_DEENROLLMENT_" + (i + 1) + ".png");
                images2[i].deleteOnExit();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createImage(PDFPage page, String destination) throws IOException {
        Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(),
                (int) page.getBBox().getHeight());
        BufferedImage bufferedImage = new BufferedImage(rect.width, rect.height,
                BufferedImage.SCALE_REPLICATE);

        java.awt.Image image = page.getImage(rect.width, rect.height, // width & height
                rect, // clip rect
                null, // null for the ImageObserver
                true, // fill background with white
                true // block until drawing is done
        );
        Graphics2D bufImageGraphics = bufferedImage.createGraphics();
        bufImageGraphics.drawImage(image, 0, 0, null);
        ImageIO.write(bufferedImage, "PNG", new File(destination));
        System.out.println();
    }

    private int numPage, totalPages;
    private int numPage2, totalPages2;

    @FXML
    private void btnNextPage(ActionEvent event) {

        if (numPage < totalPages) {

            numPage++;
            pages.setImage(new Image("/data/reports/TEMP_REPORT_" + numPage + ".png"));
            txtNavegation.setText(numPage + "/" + totalPages);
        }

    }

    @FXML
    private void btnBackPage(ActionEvent event) {

        if (numPage <= totalPages && numPage > 1) {

            numPage--;
            pages.setImage(new Image("/data/reports/TEMP_REPORT_" + numPage + ".png"));
            txtNavegation.setText(numPage + "/" + totalPages);
        }
    }

    @FXML
    private void btnNextPage2(ActionEvent event) {

        if (numPage2 < totalPages2) {

            numPage2++;
            pages2.setImage(new Image("/data/reports/TEMP_REPORT_DEENROLLMENT_" + numPage2 + ".png"));
            txtNavegation2.setText(numPage2 + "/" + totalPages2);
        }

    }

    @FXML
    private void btnBackPage2(ActionEvent event) {

        if (numPage2 <= totalPages2 && numPage2 > 1) {

            numPage2--;
            pages2.setImage(new Image("/data/reports/TEMP_REPORT_DEENROLLMENT_" + numPage2 + ".png"));
            txtNavegation2.setText(numPage2 + "/" + totalPages2);
        }
    }

    @FXML
    private void btnEnrollmentReports(ActionEvent event) {
        txtTitle.setText("Reportes de matrícula");
        paneButtonsEnrollment.setVisible(true);
        paneButtonsDeEnrollment.setVisible(false);
        visor1.setVisible(true);
        visor2.setVisible(false);
    }

    @FXML
    private void btnDeEnrollmentReports(ActionEvent event) {

        if (!deEnrollmentList.isEmpty()) {

            txtTitle.setText("Reportes de retiro");
            paneButtonsEnrollment.setVisible(false);
            paneButtonsDeEnrollment.setVisible(true);
            visor1.setVisible(false);
            visor2.setVisible(true);

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Matrícula");
            alert.setHeaderText("No hay reporte de retiros.");
            alert.showAndWait();
        }

    }
}
