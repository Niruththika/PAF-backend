package com.example.eduposts.controller;

import com.example.eduposts.dto.PostCreateRequest;
import com.example.eduposts.dto.PostResponse;
import com.example.eduposts.dto.PostUpdateRequest;
import com.example.eduposts.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @Valid @RequestParam("title") String title,
            @RequestParam("category") String category,
            @RequestParam("type") String type,
            @RequestParam("duration") String duration,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file) throws IOException {

        PostCreateRequest request = new PostCreateRequest();
        request.setTitle(title);
        request.setCategory(category);
        request.setType(type);
        request.setDuration(duration);
        request.setDescription(description);
        request.setFile(file);

        return ResponseEntity.ok(postService.createPost(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long id,
            @Valid @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "duration", required = false) String duration,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        PostUpdateRequest request = new PostUpdateRequest();
        request.setTitle(title);
        request.setCategory(category);
        request.setType(type);
        request.setDuration(duration);
        request.setDescription(description);
        request.setFile(file);

        return ResponseEntity.ok(postService.updatePost(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) throws IOException {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PostResponse>> filterPosts(
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) List<String> types,
            @RequestParam(required = false) List<String> durations) {
        return ResponseEntity.ok(postService.filterPosts(categories, types, durations));
    }
}
