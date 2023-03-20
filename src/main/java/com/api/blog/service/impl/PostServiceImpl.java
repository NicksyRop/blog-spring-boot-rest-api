package com.api.blog.service.impl;

import com.api.blog.entity.Post;
import com.api.blog.payload.PostDto;
import com.api.blog.respository.PostRepository;
import com.api.blog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //convert DTO to entity

        Post post = new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost =   postRepository.save(post);

        //convert entity to DTO

        PostDto postResponse = new PostDto();
        postResponse.setContent(newPost.getContent());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());


        return postResponse;
    }
}
