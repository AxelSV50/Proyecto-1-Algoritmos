/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.reports;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
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
    @FXML
    private javafx.scene.control.Button btnNextPage;
    @FXML
    private javafx.scene.control.Button btnBackPage;
    @FXML
    private Text txtNavegation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        readPDF("C:/Users/Usuario/Desktop/P2 - Trabajo en clase 8 junio - copia.pdf");
        boolean success = false;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sección de reportes");
        alert.setHeaderText("Generando los reportes...\n\n"
                + "Esto puede tardar un momento.");
        alert.showAndWait();

        while (!success) {

            try {
                pages.setImage(new Image("/data/reports/TEMP_REPORT_1.png"));
                success = true;
            } catch (Exception e) {

                success = false;

            }
        }
        txtNavegation.setText(numPage+"/"+totalPages);

    }

    @FXML
    private void btnBack(ActionEvent event) {
        bp.setVisible(false);
        for (int i = 0; i < images.length; i++) {

            if (images[i].exists()) {
                images[i].delete();
            }
        }
        
    }

    public void readPDF(String path) {
        try {
            File pdfFile = new File(path);
            while(!pdfFile.exists()){
            }
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
    }

    private int numPage, totalPages;

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

        if (numPage <= totalPages && numPage>1) {
            
            numPage--;
            pages.setImage(new Image("/data/reports/TEMP_REPORT_" + numPage + ".png"));
            txtNavegation.setText(numPage + "/" + totalPages);
        }
    }
}
