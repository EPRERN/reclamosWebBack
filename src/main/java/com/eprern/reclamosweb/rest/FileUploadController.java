package com.eprern.reclamosweb.rest;

import com.eprern.reclamosweb.model.Archivo;
import com.eprern.reclamosweb.repositories.ArchivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private ArchivoRepository archivoRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            Archivo archivo = new Archivo();
            archivo.setNombre(file.getOriginalFilename());
            archivo.setDatos(file.getBytes());
            archivoRepository.save(archivo);

            return ResponseEntity.ok("Archivo subido exitosamente: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fallo al subir el archivo: " + file.getOriginalFilename());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Archivo>> getAllFiles() {
        List<Archivo> archivos = archivoRepository.findAll();
        if (!archivos.isEmpty()) {
            return ResponseEntity.ok(archivos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
/*
//TRAER UN ARCHIVO ESPECIFICO CON PETICION GET EN POSTMAN
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<Archivo> archivoOptional = archivoRepository.findById(id);
        if (archivoOptional.isPresent()) {
            Archivo archivo = archivoOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo.getNombre() + "\"")
                    .body(archivo.getDatos());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}*/


