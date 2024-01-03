package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        System.out.println("In post mapping of controller");
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

// get all post api
    // added pagination support
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir


                                     ){
        return postService.getAllPosts(pageNo, pageSize,sortBy, sortDir);
    }
// get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update post by id
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.updatePost(postDto,id));
    }

    // update post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost( @PathVariable(name = "id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
