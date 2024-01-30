package com.springboot.blog.controller;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostDtoV2;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
@Tag(
    name="CRUD REST API for Post resource"
)
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "create post REST Api",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )
@SecurityRequirement(
        name = "Bear Authentication"
)
@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        System.out.println("In post mapping of controller");
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

// get all post api
    // added pagination support
@Operation(
        summary = "GET post REST Api",
        description = "get Post REST API is used to fetch posts from database"
)
@ApiResponse(
        responseCode = "200",
        description = "Http status 200 SUCCESS"
)
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir


                                     ){
        return postService.getAllPosts(pageNo, pageSize,sortBy, sortDir);
    }

// get post by id
@Operation(
        summary = "GET post by id REST Api",
        description = "get Post REST API is used to fetch a post by id from database"
)
@ApiResponse(
        responseCode = "200",
        description = "Http status 200 SUCCESS"
)
    @GetMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    // supports tags
    @GetMapping("/api/v2/posts/{id}")
    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name = "id") long id){
        PostDto postDto = postService.getPostById(id);
        PostDtoV2 postDtoV2 = new PostDtoV2();
        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setContent(postDto.getContent());
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("JS");

postDtoV2.setTags(tags);


        return ResponseEntity.ok(postDtoV2);
    }
    // update post by id
    @Operation(
            summary = "Update post by id REST Api",
            description = "Update Post REST API is used to update a post by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto , @PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.updatePost(postDto,id));
    }

    // update post by id
    @Operation(
            summary = "Delete post by id REST Api",
            description = "Delete Post REST API is used to delete a post by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")

    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost( @PathVariable(name = "id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    // Get post by category
    @Operation(
            summary = "Get all post by category id REST Api",
            description = "get  Post by cat id REST API is used to get all post by category id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"
    )
    @GetMapping("/api/v1/posts/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId){
        List<PostDto> postDtos = postService.getPostByCategory(categoryId);
        return ResponseEntity.ok(postDtos);

    }
}
