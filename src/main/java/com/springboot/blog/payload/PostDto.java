package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min=10, message = "Post title should have at least 10 characters")
    private String description;

    @NotEmpty
    private String content;
    // not empty and atleast 2 chars
    @NotEmpty
    @Size(min=2, message = "Post title should have at least 2 characters")
    private String title;
    private Set<CommentDto> comments;

    private Long categoryId;

}
