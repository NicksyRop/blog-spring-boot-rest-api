package com.api.blog.service;

import com.api.blog.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId , CommentDto commentDto);
}
