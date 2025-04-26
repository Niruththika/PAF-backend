package com.example.eduposts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;


    
    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) throws Exception {
        
        
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
           
            
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new Exception("Could not create upload directory", ex);
        }
    }

    
    
    public String storeFile(MultipartFile file) throws IOException {
        
        
        if (file.isEmpty()) {
            throw new IOException("Failed to store empty file.");
        }

        
        
        @SuppressWarnings("null")
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName == null || fileName.isEmpty()) {
            throw new IOException("Failed to store file with empty or invalid filename.");
        }

        
        
        String newFileName = UUID.randomUUID() + "_" + fileName;


        Path targetLocation = this.fileStorageLocation.resolve(newFileName);


        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return newFileName;
    }


    public void deleteFile(String fileName) throws IOException {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        Files.deleteIfExists(filePath);
    }
}
