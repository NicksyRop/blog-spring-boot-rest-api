package com.api.blog.controller;


import com.api.blog.payload.PostDto;
import com.api.blog.service.PostService;
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
    public List<PostDto>  getAllPosts(){
        return postService.getAllPosts();
    }

    //get post by id

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostByid(@PathVariable(name = "id") long id){

        return new ResponseEntity(postService.getPostById(id),HttpStatus.OK);

    }
 }
