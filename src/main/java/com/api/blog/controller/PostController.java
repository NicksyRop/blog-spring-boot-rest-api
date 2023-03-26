package com.api.blog.controller;


import com.api.blog.payload.PostDto;
import com.api.blog.payload.PostResponse;
import com.api.blog.service.PostService;
import com.api.blog.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create blog post endpoint
    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }

    //get all posts
    @GetMapping()
    public PostResponse getAllPosts(@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo ,
                                    @RequestParam(name = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE ,required = false) int pageSize,
                                    @RequestParam( name = "sortBy" , defaultValue = AppConstants.DEFAULT_SORT_BY , required = false) String sortBy,
                                    @RequestParam( name = "sortDir" , defaultValue = AppConstants.DEFAULT_SORT_DIRECTION ,required = false) String sortDir
                                    ){
        return postService.getAllPosts(pageNo,pageSize,sortBy ,sortDir);
    }

    //get post by id

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostByid(@PathVariable(name = "id") long id){

        return new ResponseEntity(postService.getPostById(id),HttpStatus.OK);

    }
    //update post by id

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){

    return  new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }

    //delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id){

        return  new ResponseEntity<>("Post deleted successfully" , HttpStatus.OK);
    }
 }
