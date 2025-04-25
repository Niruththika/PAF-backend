package com.example.eduposts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;

@Data
public class CommentDto {
    private Long id;

    @NotBlank(message = "Content is required")
    @Size(min = 10, message = "Comment must be at least 10 characters")
    private String content;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;

    @NotNull(message = "Post ID is required")
    private Long postId;

    private Date createdAt; // Add this field
}