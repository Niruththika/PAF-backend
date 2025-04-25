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

    // Constructor with @Value for injecting the file upload directory from
    // application properties
    public FileStorageService(@Value("${file.upload-dir}") String uploadDir) throws Exception {
        // Convert the relative path to an absolute path
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            // Create the directories if they don't exist
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new Exception("Could not create upload directory", ex);
        }
    }

    // Method to store a file
    public String storeFile(MultipartFile file) throws IOException {
        // Check if the file is null or empty
        if (file.isEmpty()) {
            throw new IOException("Failed to store empty file.");
        }

        // Get the original filename, ensure it's not null
        @SuppressWarnings("null")
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName == null || fileName.isEmpty()) {
            throw new IOException("Failed to store file with empty or invalid filename.");
        }

        // Ensure the filename is clean
        String newFileName = UUID.randomUUID() + "_" + fileName;

        // Define the target location to store the file
        Path targetLocation = this.fileStorageLocation.resolve(newFileName);

        // Copy the file to the target location
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return newFileName;
    }

    // Method to delete a file
    public void deleteFile(String fileName) throws IOException {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        Files.deleteIfExists(filePath);
    }
}
