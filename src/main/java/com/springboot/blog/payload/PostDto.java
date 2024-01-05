package com.springboot.blog.payload;

import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;
    private String description;
    private String content;
    private String title;
    private Set<CommentDto> comments;

}
