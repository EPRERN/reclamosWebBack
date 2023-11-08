package com.eprern.reclamosweb.rest;

import com.eprern.reclamosweb.model.EmailRequest;
import com.eprern.reclamosweb.services.EmailService;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestPart("file") MultipartFile[] files, @RequestPart EmailRequest emailRequest) {
        try {
            // Obtén los datos del formulario
            String recipient = "lavila@eprern.gov.ar"; ///CORREO AL QUE SE LE ENVIARÁ EL MAIL
            String subject = "Asunto del correo electrónico";
            String message = generateEmailMessage(emailRequest);

            // Convierte los archivos MultipartFile a archivos File si es necesario
            List<File> attachments = Arrays.stream(files)
                    .map(this::convertMultiPartToFile)
                    .collect(Collectors.toList());

            // Envía el correo electrónico
            emailService.sendEmailWithAttachment(recipient, subject, message, attachments);
            return ResponseEntity.ok("Correo electrónico enviado exitosamente.");
        } catch (EmailException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fallo al enviar el correo electrónico.");
        }
    }
    private File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            file.transferTo(convFile);
        } catch (IOException e) {
            // Maneja la excepción apropiadamente
        }
        return convFile;
    }

    private String generateEmailMessage(EmailRequest emailRequest) {

        return "Nombre: " + emailRequest.getNombre() + "\n" +
                "Apellido: " + emailRequest.getApellido() + "\n" +
                "DNI: " + emailRequest.getDni() + "\n" +
                "Teléfono: " + emailRequest.getTelefono() + "\n" +
                "Check Nombre Propio: " + emailRequest.isCheckNombrePropio() + "\n" +
                "Nombre Representante: " + emailRequest.getNombreRepresentante() + "\n" +
                "Apellido Representante: " + emailRequest.getApellidoRepresentante() + "\n" +
                "DNI Representante: " + emailRequest.getDniRepresentante() + "\n" +
                "Dirección: " + emailRequest.getDireccion() + "\n" +
                "Localidad: " + emailRequest.getLocalidad() + "\n" +
                "Código Postal: " + emailRequest.getCodigoPostal() + "\n" +
                "Dirección Alternativa: " + emailRequest.getDireccionAlternativa() + "\n" +
                "Localidad Alternativa: " + emailRequest.getLocalidadAlternativa() + "\n" +
                "Código Postal Alternativo: " + emailRequest.getCodigoPostalAlternativo() + "\n" +
                "NIS: " + emailRequest.getNis() + "\n" +
                "Número de Cliente: " + emailRequest.getNumeroDeCliente() + "\n" +
                "Email: " + emailRequest.getEmail() + "\n" +
                "Error de Facturación: " + emailRequest.isErrorFacturacion() + "\n" +
                "Resarcimiento: " + emailRequest.isResarcimiento() + "\n" +
                "Suspensión de Suministro: " + emailRequest.isSuspencionSuministro() + "\n" +
                "Mala Atención Comercial: " + emailRequest.isMalaAtencionComercial() + "\n" +
                "Negativa de Conexión: " + emailRequest.isNegativaConexion() + "\n" +
                "Inconveniente de Tensión: " + emailRequest.isInconvenienteTension() + "\n" +
                "Descripción: " + emailRequest.getDescripcion() + "\n" +
                "Notificaciones por correo electrónico: " + emailRequest.isEmailNotifications() + "\n";




    }
}
