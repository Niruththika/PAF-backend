package com.example.eduposts.controller;

import com.example.eduposts.exception.ResourceNotFoundException;
import com.example.eduposts.model.Post;
import com.example.eduposts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    private final Path fileStorageLocation;
    private final PostRepository postRepository;

    public FileController(@Value("${file.upload-dir}") String uploadDir, PostRepository postRepository) throws Exception {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        this.postRepository = postRepository;
        Files.createDirectories(this.fileStorageLocation);
    }

    @GetMapping("/api/posts/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws Exception {
        Path filePath = this.fileStorageLocation.resolve(filename).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            String contentType = determineContentType(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            throw new Exception("Could not read file: " + filename);
        }
    }

    private String determineContentType(String filename) {
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "mp4":
                return "video/mp4";
            case "webm":
                return "video/webm";
            case "avi":
                return "video/x-msvideo";
            default:
                return "application/octet-stream";
        }
    }
}