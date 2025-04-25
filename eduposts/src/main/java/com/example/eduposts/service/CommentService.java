//package com.example.eduposts.service;
//
//import com.example.eduposts.dto.CommentRequest;
//import com.example.eduposts.dto.CommentResponse;
//import com.example.eduposts.exception.ResourceNotFoundException;
//import com.example.eduposts.model.Comment;
//import com.example.eduposts.model.Post;
//import com.example.eduposts.repository.CommentRepository;
//import com.example.eduposts.repository.PostRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class CommentService {
//    private final CommentRepository commentRepository;
//    private final PostRepository postRepository;
//
//    @Transactional
//    public CommentResponse createComment(CommentRequest request) {
//        Post post = postRepository.findById(request.getPostId())
//                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + request.getPostId()));
//
//        Comment comment = new Comment();
//        comment.setName(request.getName());
//        comment.setEmail(request.getEmail());
//        comment.setTutorial(request.getTutorial());
//        comment.setContent(request.getContent());
//        comment.setTags(request.getTags());
//        comment.setPost(post);
//
//        Comment savedComment = commentRepository.save(comment);
//        return mapToResponse(savedComment);
//    }
//
//    public List<CommentResponse> getCommentsByPostId(Long postId) {
//        return commentRepository.findByPostId(postId).stream()
//                .map(this::mapToResponse)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public CommentResponse updateComment(Long id, CommentRequest request) {
//        Comment comment = commentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
//
//        comment.setName(request.getName());
//        comment.setEmail(request.getEmail());
//        comment.setTutorial(request.getTutorial());
//        comment.setContent(request.getContent());
//        comment.setTags(request.getTags());
//
//        Comment updatedComment = commentRepository.save(comment);
//        return mapToResponse(updatedComment);
//    }
//
//    @Transactional
//    public void deleteComment(Long id) {
//        Comment comment = commentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
//        commentRepository.delete(comment);
//    }
//
//    private CommentResponse mapToResponse(Comment comment) {
//        CommentResponse response = new CommentResponse();
//        response.setId(comment.getId());
//        response.setName(comment.getName());
//        response.setEmail(comment.getEmail());
//        response.setTutorial(comment.getTutorial());
//        response.setContent(comment.getContent());
//        response.setTags(comment.getTags());
//        response.setCreatedAt(comment.getCreatedAt());
//        response.setUpdatedAt(comment.getUpdatedAt());
//        return response;
//    }
//}
package com.example.eduposts.service;

import com.example.eduposts.dto.CommentDto;
import com.example.eduposts.exception.ResourceNotFoundException;
import com.example.eduposts.model.Comment;
import com.example.eduposts.model.Post;
import com.example.eduposts.repository.CommentRepository;
import com.example.eduposts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public CommentDto createComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setName(commentDto.getName());
        comment.setContent(commentDto.getContent());
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);
        return convertToDto(savedComment);
    }

    public List<CommentDto> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        // Only update name and content (removed email, tutorial, tags)
        existingComment.setName(commentDto.getName());
        existingComment.setContent(commentDto.getContent());

        Comment updatedComment = commentRepository.save(existingComment);
        return convertToDto(updatedComment);
    }

    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    private CommentDto convertToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setPostId(comment.getPost().getId());
        return dto;
    }
}