/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.list.CircularLinkedList;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Axel Solís
 */
public class Security {

    private String user;
    private String password;
    private static final String ENCRYPTION_KEY = "bvc6ecs8923bfcefewd";

    public Security(String user, String password) {

        this.user = user;
        this.password = password;
    }

    public String getAccess(CircularLinkedList studentList, CircularLinkedList adminList){
        
        String access = "DENIED";
        
        if(adminList != null && !adminList.isEmpty()){

            try {
      
                if(adminList.contains(user+"~"+encrypt(password))){
                    
                    access = "ADMIN";
                }
            } catch (Exception ex) {
                Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (studentList != null && !studentList.isEmpty()){
            
            try {
                
                if(studentList.contains(user+"~"+encrypt(password))){
                    
                    access = "STUDENT";
                }
            } catch (Exception ex) {
                Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return access;
    }
    
//Fuente: https://hashblogeando.wordpress.com/2019/09/13/encriptacion-aes-en-java/
    
    public static String encrypt(String datos) throws Exception {
        
        SecretKeySpec secretKey = getEncryptionKey();

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] datosEncriptar = datos.getBytes("UTF-8");
        byte[] bytesEncriptados = cipher.doFinal(datosEncriptar);
        String encriptado = Base64.getEncoder().encodeToString(bytesEncriptados);

        return encriptado;
    }

    private String decrypt(String encrypted) throws Exception {

        SecretKeySpec secretKey = getEncryptionKey();
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] bytesEncriptados = Base64.getDecoder().decode(encrypted);
        byte[] datosDesencriptados = cipher.doFinal(bytesEncriptados);
        String datos = new String(datosDesencriptados);

        return datos;
    }

    private static SecretKeySpec getEncryptionKey() throws Exception {
        
        byte[] claveEncriptacion = ENCRYPTION_KEY.getBytes("UTF-8");

        MessageDigest sha = MessageDigest.getInstance("SHA-1");

        claveEncriptacion = sha.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);

        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");

        return secretKey;
    }
}
