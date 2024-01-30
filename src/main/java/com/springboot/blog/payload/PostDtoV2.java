package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
@Schema(
        description = "PostDto Model info"
)
public class PostDtoV2 {

    private long id;
    @NotEmpty
    @Size(min=10, message = "Post title should have at least 10 characters")
    private String description;

    @Schema(
            description = "Post content"
    )
    @NotEmpty
    private String content;
    // not empty and atleast 2 chars
    @Schema(
            description = "Post title"
    )
    @NotEmpty
    @Size(min=2, message = "Post title should have at least 2 characters")
    private String title;
    private Set<CommentDto> comments;

    private Long categoryId;
    private List<String> tags;
}