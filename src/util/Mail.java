/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Career;
import domain.Student;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Usuario
 */
public class Mail {

    private static final String SYSTEM_MAIL = "sistemadematriculainfo@gmail.com";
    private static final String PASSWORD = "sistemadematricula9834";

    public static void sendWelcomeMessage(Student student, Career career) throws MessagingException {

        // se obtiene el objeto Session. La configuración es para
        // una cuenta de gmail.
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", SYSTEM_MAIL);
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(props, null);
//            session.setDebug(true);

        // Se compone la parte del texto
        BodyPart texto = new MimeBodyPart();
        texto.setText("La Universidad de Costa Rica le desea la más cordial bienvenida. \n\n"
                + "Sus datos personales son: \n"+"\nNombre completo: "+student.getFirstname()+" "+student.getLastname()
        +"\nCédula: "+student.getId()+"\nCarné universitario: "+student.getStudentID()+
        "\nTeléfono: "+student.getPhoneNumber()+"\nDirección :"+student.getAddress()+
                "\nCorreo electrónico: "+student.getEmail()+"\nCarrera: "+career.getDescription()+
                "\n\n© 2021 Sistema de Matrícula");

        // Se compone el adjunto con la imagen
        BodyPart adjunto = new MimeBodyPart();
        adjunto.setDataHandler(
                new DataHandler(new FileDataSource("firma-ucr-ico.png")));
        adjunto.setFileName("firma-ucr-ico.png");

        // Una MultiParte para agrupar texto e imagen.
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(adjunto);
        multiParte.addBodyPart(texto);
        // Se compone el correo, dando to, from, subject y el
        // contenido.
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("yo@yo.com"));
        message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(student.getEmail()));
        message.setSubject("Bienvenido(a) a la Universidad de Costa Rica");
        message.setContent(multiParte);

        // Se envia el correo.
        Transport t = session.getTransport("smtp");
        t.connect(SYSTEM_MAIL, PASSWORD);
        t.sendMessage(message, message.getAllRecipients());
        t.close();

    }

}
