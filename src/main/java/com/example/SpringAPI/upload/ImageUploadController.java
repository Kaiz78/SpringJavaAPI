package com.example.SpringAPI.upload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("api/v1/upload")
@CrossOrigin
public class ImageUploadController {
/*
    private static String imageDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/";
    @RequestMapping(value = "/image", produces =  {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file, @RequestParam("name") String name){
        makeDirectoryIfNotExist(imageDirectory);
        Path fileNamePath = Paths.get(imageDirectory,
                name.concat(".").concat(FilenameUtils.getExtension(file.getOriginalFilename())));
        try {
            Files.write(fileNamePath, file.getBytes());
            return new ResponseEntity<>(name, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Image is not uploaded",HttpStatus.BAD_REQUEST);
        }
    }


    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()){
            directory.mkdir();
        }
    }

}

*/

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Welcome to Spring Boot API");
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {

        try {
            // Spécifiez le chemin où vous souhaitez enregistrer l'image.
            String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/";
            // Récupérer l'extension de l'image
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            // Créer un nouveau nom de fichier avec la date actuelle pour éviter les doublons
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            String formattedDate = sdf.format(date);
            fileName = formattedDate +  "." + extension;

            File destFile = new File(uploadDirectory + File.separator + fileName);
            file.transferTo(destFile);

            return ResponseEntity.ok("Image enregistrée avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'image.");
        }
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

}

