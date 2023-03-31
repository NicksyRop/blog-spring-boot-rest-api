package com.api.blog.service.impl;


import com.api.blog.entity.Comment;
import com.api.blog.entity.Post;
import com.api.blog.exception.ResourceNotFoundException;
import com.api.blog.payload.CommentDto;
import com.api.blog.respository.CommentRepository;
import com.api.blog.respository.PostRepository;
import com.api.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository , PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        //Retrieve post by id

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" , "id", postId));
        //set post to comment
        comment.setPost(post);

        // save comment to database
        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);


    }


    private CommentDto mapToDto(Comment comment){

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setEmail(comment.getEmail());
        commentDto.setName(comment.getName());
        commentDto.setBody(comment.getBody());

        return  commentDto;
    }


    private  Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();

        comment.setBody(commentDto.getEmail());
        comment.setId(commentDto.getId());
        comment.setEmail(commentDto.getEmail());
        comment.setName(comment.getName());

        return comment;
    }
}
