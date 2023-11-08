package com.eprern.reclamosweb.services;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
public class EmailService {
    public void sendEmailWithAttachment(String recipient, String subject, String message, List<MultipartFile> files) throws EmailException {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("mail.eprern.gov.ar"); // Reemplaza "your_host_name" con tu servidor SMTP
        email.setSmtpPort(25); // Reemplaza con el puerto SMTP correspondiente

        email.setAuthentication("lavila@eprern.gov.ar", "At8q_27z6f1"); // Reemplaza con tus credenciales de correo electrónico
        email.setSSLOnConnect(false); // O false si no usas SSL

        email.setFrom("lavila@eprern.gov.ar");
        email.addTo(recipient);
        email.setSubject(subject);
        email.setMsg(message);

        // Agregar los adjuntos
        for (MultipartFile file : files) {
            try {
                EmailAttachment emailAttachment = new EmailAttachment();
                emailAttachment.setPath(file.getOriginalFilename());
                emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
                emailAttachment.setDescription("Archivo adjunto");
                email.attach(emailAttachment);
            } catch (EmailException e) {
                // Manejar la excepción apropiadamente
            }
        }

        email.send();
    }
}
