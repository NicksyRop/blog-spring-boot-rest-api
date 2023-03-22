package com.api.blog.service;

import com.api.blog.payload.PostDto;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);
}

