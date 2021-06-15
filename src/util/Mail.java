/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domain.Career;
import domain.Course;
import domain.DeEnrollment;
import domain.Enrollment;
import domain.list.DoublyLinkedList;
import domain.list.ListException;
import domain.Student;
import domain.list.CircularDoublyLinkedList;
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

    private static DoublyLinkedList careersList = util.Utility.getCareersList();
    private static CircularDoublyLinkedList enrollmentList = util.Utility.getEnrollmentList();
    private static CircularDoublyLinkedList courseList = util.Utility.getCoursesList();

  public static void sendWelcomeMessage(Student student) throws MessagingException, ListException {

        // se obtiene el objeto Session
        // y se rellena la variable Properties.
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", SYSTEM_MAIL);
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(props, null);

        // Se accede a careersList
        int index = careersList.indexOf(new Career(student.getCareerID(), ""));
        Career c = (Career) careersList.getNode(index).data;
       //Para construir el cuerpo del mensaje se debe tener dos partes el texto y la imagen a agregar.

        //Se compone el texto del correo 
        BodyPart texto = new MimeBodyPart();
        texto.setText("La Universidad de Costa Rica le desea la más cordial bienvenida. \n\n"
                + "Sus datos personales son: \n" + "\nNombre completo: " + student.getFirstname() + " " + student.getLastname()
                + "\nCédula: " + student.getId() + "\nCarné universitario: " + student.getStudentID()
                + "\nTeléfono: " + student.getPhoneNumber() + "\nDirección :" + student.getAddress()
                + "\nCorreo electrónico: " + student.getEmail() + "\nCarrera: " + c.getDescription()
                + "\n\n© 2021 Sistema de Matrícula");

        // Se compone  la imagen
        BodyPart adjunto = new MimeBodyPart();
        adjunto.setDataHandler(
                new DataHandler(new FileDataSource("firma-ucr-ico.png")));
        adjunto.setFileName("firma-ucr-ico.png");

        // Una MultiParte para agrupar el texto e imagen.
        MimeMultipart multiParte = new MimeMultipart();
        multiParte.addBodyPart(adjunto);
        multiParte.addBodyPart(texto);
        // se debe pasarle el objeto Session 
        // se debe rellenar los campos de destinatario 
        //el contenido de MineMultipart, todo esto se pasa a MimeMessage.
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("yo@yo.com"));
        message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(student.getEmail()));
        message.setSubject("Bienvenido(a) a la Universidad de Costa Rica");
        message.setContent(multiParte);

        //  Transport se encarga del envío del correo 
        //para esto requiere  los datos y contraseña del correo del remitente
        Transport t = session.getTransport("smtp");
        t.connect(SYSTEM_MAIL, PASSWORD);
        t.sendMessage(message, message.getAllRecipients());
        t.close();

    }

  // para los demas metodos no cambia la estrutura.

    public static void sendModifyMessage(Student student) throws MessagingException, ListException {

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

        // Se compone la parte del texto
        int index = careersList.indexOf(new Career(student.getCareerID(), ""));
        Career c = (Career) careersList.getNode(index).data;

        BodyPart texto = new MimeBodyPart();
        texto.setText("Sus datos se han actualizado satisfactoriamente."
                + "\n\nNombre completo: " + student.getFirstname() + " " + student.getLastname()
                + "\nCédula: " + student.getId() + "\nCarné universitario: " + student.getStudentID()
                + "\nTeléfono: " + student.getPhoneNumber() + "\nDirección :" + student.getAddress()
                + "\nCorreo electrónico: " + student.getEmail() + "\nCarrera: " + c.getDescription()
                + "\n\n© 2021 Sistema de Matrícula");

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
        message.setSubject("Actualización de datos personales");
        message.setContent(multiParte);

        // Se envia el correo.
        Transport t = session.getTransport("smtp");
        t.connect(SYSTEM_MAIL, PASSWORD);
        t.sendMessage(message, message.getAllRecipients());
        t.close();

    }

    public static void sendEnrollmentMessage(Student student) throws MessagingException, ListException {

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

        // Se compone la parte del texto
        int index = careersList.indexOf(new Career(student.getCareerID(), ""));
        Career c = (Career) careersList.getNode(index).data;

        String courses = "";
        int totalCredits = 0;
        
        for (int i = 1; i <= enrollmentList.size(); i++) {

            Enrollment e = (Enrollment) enrollmentList.getNode(i).data;

            if (e.getStudentID().equalsIgnoreCase(student.getStudentID())) {

                for (int j = 1; j <= courseList.size(); j++) {

                    Course c1 = (Course) courseList.getNode(j).data;

                    if (c1.getId().equalsIgnoreCase(e.getCourseID())) {

                        courses += "Nombre: " + c1.getName() + "\n"
                                + "Sigla: " + c1.getId() + "\n"
                                + "Créditos:" + c1.getCredits() + "\n"
                                + "Horario:" + e.getSchedule() + "\n"
                                + "------------------\n";
                        totalCredits += c1.getCredits();
                    }
                }
            }
        }
        courses += "Carga total: " + totalCredits;

        BodyPart texto = new MimeBodyPart();
        texto.setText("La matrícula se ha realizado satisfactoriamente."
                + "\n\nNombre completo: " + student.getFirstname() + " " + student.getLastname()
                + "\nCarné universitario: " + student.getStudentID()
                + "\nCursos matriculados:\n\n" + courses
                + "\n\n© 2021 Sistema de Matrícula");

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
        message.setSubject("Matrícula efectuada");
        message.setContent(multiParte);

        // Se envia el correo.
        Transport t = session.getTransport("smtp");
        t.connect(SYSTEM_MAIL, PASSWORD);
        t.sendMessage(message, message.getAllRecipients());
        t.close();

    }

    public static void sendDeEnrollmentMessage(Student student, CircularDoublyLinkedList deEnrollmentList) throws MessagingException, ListException {

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

        // Se compone la parte del texto
        int index = careersList.indexOf(new Career(student.getCareerID(), ""));
        Career c = (Career) careersList.getNode(index).data;

        System.out.print("");
        String courses = "";
        int totalCredits = 0;
        for (int i = 1; i <= deEnrollmentList.size(); i++) {

            DeEnrollment e = (DeEnrollment) deEnrollmentList.getNode(i).data;

            for (int j = 1; j <= courseList.size(); j++) {

                Course c1 = (Course) courseList.getNode(j).data;

                if (c1.getId().equalsIgnoreCase(e.getCourseID())) {

                    courses += "Nombre: " + c1.getName() + "\n"
                            + "Sigla: " + c1.getId() + "\n"
                            + "Créditos:" + c1.getCredits() + "\n"
                            + "Horario:" + e.getSchedule() + "\n"
                            + "Motivo de retiro:" + e.getRemark() + "\n"
                            + "------------------\n";
                    totalCredits += c1.getCredits();
                }
            }
        }
        courses += "Créditos totales retirados: " + totalCredits;

        BodyPart texto = new MimeBodyPart();
        texto.setText("Retiro de cursos."
                + "\n\nNombre completo: " + student.getFirstname() + " " + student.getLastname()
                + "\nCarné universitario: " + student.getStudentID()
                + "\nCursos retirados:\n\n" + courses
                + "\n\n© 2021 Sistema de Matrícula");

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
        message.setSubject("Retiro de cursos efectuado.");
        message.setContent(multiParte);

        // Se envia el correo.
        Transport t = session.getTransport("smtp");
        t.connect(SYSTEM_MAIL, PASSWORD);
        t.sendMessage(message, message.getAllRecipients());
        t.close();

    }
}
