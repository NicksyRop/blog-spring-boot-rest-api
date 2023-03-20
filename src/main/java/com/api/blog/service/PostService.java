package com.api.blog.service;

import com.api.blog.payload.PostDto;

public interface PostService {

    PostDto createPost(PostDto postDto);
}
