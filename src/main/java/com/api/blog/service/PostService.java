package com.api.blog.service;

import com.api.blog.payload.PostDto;
import com.api.blog.payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo , int pageSize);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto , long id);

    void deletePostById(long id);
}

