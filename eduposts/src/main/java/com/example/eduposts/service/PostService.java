package com.example.eduposts.service;

import com.example.eduposts.dto.PostCreateRequest;
import com.example.eduposts.dto.PostResponse;
import com.example.eduposts.dto.PostUpdateRequest;
import com.example.eduposts.exception.ResourceNotFoundException;
import com.example.eduposts.model.Post;
import com.example.eduposts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final FileStorageService fileStorageService;

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return mapToResponse(post);
    }

    @Transactional
    public PostResponse createPost(PostCreateRequest request) throws IOException {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setCategory(request.getCategory());
        post.setType(request.getType());
        post.setDuration(request.getDuration());
        post.setDescription(request.getDescription());

        if (request.getFile() != null && !request.getFile().isEmpty()) {
            String fileUrl = fileStorageService.storeFile(request.getFile());
            post.setImageUrl(fileUrl);
        }

        Post savedPost = postRepository.save(post);
        return mapToResponse(savedPost);
    }

    @Transactional
    public PostResponse updatePost(Long id, PostUpdateRequest request) throws IOException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        post.setTitle(request.getTitle());
        post.setCategory(request.getCategory());
        post.setType(request.getType());
        post.setDuration(request.getDuration());
        post.setDescription(request.getDescription());

        if (request.getFile() != null && !request.getFile().isEmpty()) {
            // Delete old file if exists
            if (post.getImageUrl() != null) {
                fileStorageService.deleteFile(post.getImageUrl());
            }
            String fileUrl = fileStorageService.storeFile(request.getFile());
            post.setImageUrl(fileUrl);
        }

        Post updatedPost = postRepository.save(post);
        return mapToResponse(updatedPost);
    }

    @Transactional
    public void deletePost(Long id) throws IOException {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));

        if (post.getImageUrl() != null) {
            fileStorageService.deleteFile(post.getImageUrl());
        }

        postRepository.delete(post);
    }

    public List<PostResponse> filterPosts(List<String> categories, List<String> types, List<String> durations) {
        if (categories != null && !categories.isEmpty() &&
                types != null && !types.isEmpty() &&
                durations != null && !durations.isEmpty()) {
            return postRepository.findByCategoryInAndTypeInAndDurationIn(categories, types, durations)
                    .stream().map(this::mapToResponse).collect(Collectors.toList());
        } else if (categories != null && !categories.isEmpty() && types != null && !types.isEmpty()) {
            return postRepository.findByCategoryInAndTypeIn(categories, types)
                    .stream().map(this::mapToResponse).collect(Collectors.toList());
        } else if (categories != null && !categories.isEmpty() && durations != null && !durations.isEmpty()) {
            return postRepository.findByCategoryInAndDurationIn(categories, durations)
                    .stream().map(this::mapToResponse).collect(Collectors.toList());
        } else if (types != null && !types.isEmpty() && durations != null && !durations.isEmpty()) {
            return postRepository.findByTypeInAndDurationIn(types, durations)
                    .stream().map(this::mapToResponse).collect(Collectors.toList());
        } else if (categories != null && !categories.isEmpty()) {
            return postRepository.findByCategoryIn(categories)
                    .stream().map(this::mapToResponse).collect(Collectors.toList());
        } else if (types != null && !types.isEmpty()) {
            return postRepository.findByTypeIn(types)
                    .stream().map(this::mapToResponse).collect(Collectors.toList());
        } else if (durations != null && !durations.isEmpty()) {
            return postRepository.findByDurationIn(durations)
                    .stream().map(this::mapToResponse).collect(Collectors.toList());
        } else {
            return getAllPosts();
        }
    }

    private PostResponse mapToResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setCategory(post.getCategory());
        response.setType(post.getType());
        response.setDuration(post.getDuration());
        response.setImageUrl(post.getImageUrl());
        response.setDescription(post.getDescription());
        response.setCreatedAt(post.getCreatedAt());
        response.setUpdatedAt(post.getUpdatedAt());
        return response;
    }
}